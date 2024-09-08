package github.nitespring.alchemistarsenal.common.item.weapons;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.ForgeEventFactory;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AutomaticCrossbow extends ProjectileWeaponItem {


    public AutomaticCrossbow(Properties pProperties) {
        super(pProperties.component(DataComponentInit.CHARGE.get(),0));
    }

    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ARROW_OR_FIREWORK;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
       ItemStack stack = player.getItemInHand(hand);
        if(isCharged(stack)){
            if(player.isCreative()||!player.getProjectile(stack).isEmpty()) {
                performShooting(level,player,hand,stack,getShootingPower(player.getProjectile(stack)),1.0f,null);
                //shootProjectile(player, createProjectile(level, player, stack, player.getProjectile(stack), false), 0, 0, 0, 0, null);
                player.getCooldowns().addCooldown(stack.getItem(), 4);
                decreaseCharge(stack);
                useAmmo(stack,player.getProjectile(stack),player,false);
                if(isCharged(stack)){
                    player.level().playSound((Player) null, player, SoundEvents.CROSSBOW_LOADING_END.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
                }
                player.level().playSound((Player) null, player, SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 0.8f, 1.2f);
                return InteractionResultHolder.consume(stack);
            }else{
                return InteractionResultHolder.fail(stack);
            }
        }else{
            player.startUsingItem(hand);
            player.level().playSound((Player) null, player, SoundEvents.CROSSBOW_LOADING_START.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
            return InteractionResultHolder.pass(stack);
        }
    }
    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return Tiers.NETHERITE.getRepairIngredient().test(pRepair);
    }
    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 12;
    }
    public void performShooting(Level pLevel, LivingEntity shooter, InteractionHand pHand, ItemStack weapon, float pVelocity, float pInaccuracy, @Nullable LivingEntity pTarget) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if (shooter instanceof Player player && ForgeEventFactory.onArrowLoose(weapon, shooter.level(), player, 1, true) < 0) return;
            ArrayList<ItemStack> projectiles = new ArrayList<ItemStack>();
            projectiles.add(shooter.getProjectile(weapon));
                this.shoot(serverlevel, shooter, pHand, weapon, projectiles, pVelocity, pInaccuracy, shooter instanceof Player, pTarget);
                if (shooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, weapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(weapon.getItem()));
                }

        }
    }
    public ItemStack getDefaultCreativeAmmo(Player player, ItemStack stack){
        return Items.ARROW.getDefaultInstance();
    }
    private static float getShootingPower(ItemStack pProjectile) {
        return pProjectile.is(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        int i = this.getUseDuration(stack, entityLiving) - timeLeft;
        float f = getPowerForTime(i, stack, entityLiving);
        if (f >= 1.0F && !isCharged(stack)) {
            setFullCharge(stack);
            entityLiving.level().playSound((Player) null, entityLiving, SoundEvents.CROSSBOW_LOADING_END.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
            if(entityLiving instanceof Player player) {
                player.getCooldowns().addCooldown(stack.getItem(), 8);
            }
        }
    }
    private static float getPowerForTime(int pTimeLeft, ItemStack pStack, LivingEntity pShooter) {
        float f = (float)pTimeLeft / (float)getChargeDuration(pStack, pShooter);
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if(remainingUseDuration<=1){releaseUsing(stack,level,livingEntity,remainingUseDuration);}
        if(remainingUseDuration % 5==0){
            livingEntity.level().playSound((Player) null, livingEntity, SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 0.4f, 1.2f);
        }
        if(remainingUseDuration % 16==0){
            livingEntity.level().playSound((Player) null, livingEntity, SoundEvents.CROSSBOW_LOADING_MIDDLE.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
        }
        if(remainingUseDuration % 2==0){
            livingEntity.level().playSound((Player) null, livingEntity, SoundEvents.CHAIN_STEP, SoundSource.PLAYERS, 0.6f, 0.4f+ 0.3f*new Random().nextFloat());
        }
    }

    @Override
    protected void shoot(ServerLevel pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pWeapon, List<ItemStack> pProjectileItems, float pVelocity, float pInaccuracy, boolean pIsCrit, @org.jetbrains.annotations.Nullable LivingEntity pTarget) {
        float f = EnchantmentHelper.processProjectileSpread(pLevel, pWeapon, pShooter, 0.0F);
        int multiShot = EnchantmentHelper.getItemEnchantmentLevel(pShooter.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.MULTISHOT).get(),pWeapon);
        int amount = 1 + 2*multiShot;
        if(multiShot>=1) {
            for (int i = -multiShot; i <= multiShot; i++) {
                ItemStack itemstack = pProjectileItems.get(0);
                if (!itemstack.isEmpty()) {
                    float f4 = (float) (Math.PI * 1 / 3) / multiShot * i * 12;
                    Projectile projectile = this.createProjectile(pLevel, pShooter, pWeapon, itemstack, pIsCrit);
                    this.shootProjectile(pShooter, projectile, i, pVelocity, pInaccuracy, f4, pTarget);
                    pLevel.addFreshEntity(projectile);
                    pWeapon.hurtAndBreak(this.getDurabilityUse(itemstack), pShooter, LivingEntity.getSlotForHand(pHand));
                    if (pWeapon.isEmpty()) {
                        break;
                    }
                }
            }
        }else{
            float f1 = pProjectileItems.size() == 1 ? 0.0F : 2.0F * f / (float)(pProjectileItems.size() - 1);
            float f2 = (float)((pProjectileItems.size() - 1) % 2) * f1 / 2.0F;
            float f3 = 1.0F;

            for (int i = 0; i < pProjectileItems.size(); i++) {
                ItemStack itemstack = pProjectileItems.get(i);
                if (!itemstack.isEmpty()) {
                    float f4 = f2 + f3 * (float)((i + 1) / 2) * f1;
                    f3 = -f3;
                    Projectile projectile = this.createProjectile(pLevel, pShooter, pWeapon, itemstack, pIsCrit);
                    this.shootProjectile(pShooter, projectile, i, pVelocity, pInaccuracy, f4, pTarget);
                    pLevel.addFreshEntity(projectile);
                    pWeapon.hurtAndBreak(this.getDurabilityUse(itemstack), pShooter, LivingEntity.getSlotForHand(pHand));
                    if (pWeapon.isEmpty()) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return getChargeDuration(pStack, pEntity) + 12;
    }

    public static int getChargeDuration(ItemStack pStack, LivingEntity pShooter) {
        float f = EnchantmentHelper.modifyCrossbowChargingTime(pStack, pShooter, 1.25F);
        return Mth.floor(f * 20.0F);
    }

    public void decreaseCharge(ItemStack stack){setCharge(stack, Math.max(0,getCharge(stack)-1));}
    public void setCharge(ItemStack stack,int i){
        stack.set(DataComponentInit.CHARGE.get(), i);
    }
    public void setFullCharge(ItemStack stack){setCharge(stack,24);}
    public static int getCharge(ItemStack stack){
        return stack.get(DataComponentInit.CHARGE.get())!=null ? stack.getOrDefault(DataComponentInit.CHARGE.get(),0) : 0;

    }
    public static boolean isCharged(ItemStack stack) {
        return getCharge(stack)>0;
    }

    //Crossbow
    @Override
    protected int getDurabilityUse(ItemStack pStack) {
        return pStack.is(Items.FIREWORK_ROCKET) ? 3 : 1;
    }
    @Override
    protected void shootProjectile(LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget) {
        Vector3f vector3f;
        if (pTarget != null) {
            double d0 = pTarget.getX() - pShooter.getX();
            double d1 = pTarget.getZ() - pShooter.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            double d3 = pTarget.getY(0.3333333333333333) - pProjectile.getY() + d2 * 0.2F;
            vector3f = getProjectileShotVector(pShooter, new Vec3(d0, d3, d1), pAngle);
        } else {
            Vec3 vec3 = pShooter.getUpVector(1.0F);
            Quaternionf quaternionf = new Quaternionf().setAngleAxis((double)(pAngle * (float) (Math.PI / 180.0)), vec3.x, vec3.y, vec3.z);
            Vec3 vec31 = pShooter.getViewVector(1.0F);
            vector3f = vec31.toVector3f().rotate(quaternionf);
        }

        pProjectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), pVelocity, pInaccuracy);
        float f = getShotPitch(pShooter.getRandom(), pIndex);
        pShooter.level().playSound(null, pShooter.getX(), pShooter.getY(), pShooter.getZ(), SoundEvents.CROSSBOW_SHOOT, pShooter.getSoundSource(), 0.4F, f);
    }

    private static Vector3f getProjectileShotVector(LivingEntity pShooter, Vec3 pDistance, float pAngle) {
        Vector3f vector3f = pDistance.toVector3f().normalize();
        Vector3f vector3f1 = new Vector3f(vector3f).cross(new Vector3f(0.0F, 1.0F, 0.0F));
        if ((double)vector3f1.lengthSquared() <= 1.0E-7) {
            Vec3 vec3 = pShooter.getUpVector(1.0F);
            vector3f1 = new Vector3f(vector3f).cross(vec3.toVector3f());
        }

        Vector3f vector3f2 = new Vector3f(vector3f).rotateAxis((float) (Math.PI / 2), vector3f1.x, vector3f1.y, vector3f1.z);
        return new Vector3f(vector3f).rotateAxis(pAngle * (float) (Math.PI / 180.0), vector3f2.x, vector3f2.y, vector3f2.z);
    }
    private static float getShotPitch(RandomSource pRandom, int pIndex) {
        return pIndex == 0 ? 1.0F : getRandomShotPitch((pIndex & 1) == 1, pRandom);
    }

    private static float getRandomShotPitch(boolean pIsHighPitched, RandomSource pRandom) {
        float f = pIsHighPitched ? 0.63F : 0.43F;
        return 1.0F / (pRandom.nextFloat() * 0.5F + 1.8F) + f;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public boolean useOnRelease(ItemStack pStack) {
        return true;
    }



    @Override
    protected Projectile createProjectile(Level pLevel, LivingEntity pShooter, ItemStack pWeapon, ItemStack pAmmo, boolean pIsCrit) {
        if (pAmmo.is(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(pLevel, pAmmo, pShooter, pShooter.getX(), pShooter.getEyeY() - 0.15F, pShooter.getZ(), true);
        } else {
            Projectile projectile = super.createProjectile(pLevel, pShooter, pWeapon, pAmmo, pIsCrit);
            if (projectile instanceof AbstractArrow abstractarrow) {
                abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }
            return projectile;
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                return isCharged(itemStack)&&!entityLiving.swinging ? HumanoidModel.ArmPose.CROSSBOW_HOLD : HumanoidModel.ArmPose.ITEM;
            }

        });
    }
}
