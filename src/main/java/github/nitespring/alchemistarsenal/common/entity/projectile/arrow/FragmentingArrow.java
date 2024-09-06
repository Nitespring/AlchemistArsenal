package github.nitespring.alchemistarsenal.common.entity.projectile.arrow;

import github.nitespring.alchemistarsenal.common.entity.projectile.Shrapnel;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FragmentingArrow extends AbstractArrow {



    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    @Override
    protected ItemStack getPickupItem() {

        return ItemInit.AMETHYST_ARROW.get().getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.AMETHYST_ARROW.get().getDefaultInstance();
    }


    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        for(int i = 0; i<=2; i++) {
            this.level().addParticle(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                    this.getRandomX(0.75D), this.getRandomY(), this.getRandomZ(0.75D),
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        Vec3 pos0 = this.position();
        playSound(SoundEvents.GLASS_BREAK);
        this.spawnShrapnel(pos0.x(),pos0.y()+this.getBbHeight()/2,pos0.z(), 0.5f);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Vec3 pos0 = pResult.getEntity().position();
        playSound(SoundEvents.GLASS_BREAK);
        this.spawnShrapnel(pos0.x(),pos0.y()+0.1f+this.getBbHeight()/2,pos0.z(), pResult.getEntity().getBbWidth()+0.25f);

    }

    public void spawnShrapnel(double x0,double y0,double z0, double d){
        float movementSpeed = (float)this.getDeltaMovement().length();
        double baseArrowDamage = this.getBaseDamage();
        if (this.getWeaponItem() != null ) {
            baseArrowDamage = baseArrowDamage + this.getWeaponItem().getEnchantmentLevel(
                    this.level().registryAccess().registry(Registries.ENCHANTMENT).get()
                            .getHolder(Enchantments.POWER).get());
        }
        int approximateArrowDamage = Mth.ceil(Mth.clamp((double)movementSpeed * baseArrowDamage, 0.0, 2.147483647E9));
        if (this.isCritArrow()) {
            long k = (long)this.random.nextInt(approximateArrowDamage / 2 + 2);
            approximateArrowDamage = (int)Math.min(k + (long)approximateArrowDamage, 2147483647L);
        }

        Random rand = new Random();
        double oVarI = rand.nextFloat() * Math.PI/2;
        for(int i = -1; i<=1; i++){
            double yVarI = i * Math.PI/3;
            for(int j = 0; j<= 5; j++){
                double yVarII = rand.nextFloat() * Math.PI/6;
                double oVarII = j * Math.PI/4;
                for(int k = 0; k<=1;k++){
                    int c = 1 - 2*k;
                    double oVarIII = rand.nextFloat() * Math.PI/12;
                    double yVarIII = rand.nextFloat() * Math.PI/16;

                    double oRad = c*(oVarI + oVarII + oVarIII);
                    double yRad = c*(yVarI + yVarII + yVarIII);

                    double x = x0 + d*Math.cos(oRad)+0.5*rand.nextFloat(-1,1);
                    double y = y0 + d*Math.sin(yRad)+0.25*rand.nextFloat(-1,1);
                    double z = z0 + d*Math.sin(oRad)+0.5*rand.nextFloat(-1,1);

                    Vec3 aim = new Vec3(x-x0,y-y0,z-z0).normalize();

                    if(random.nextBoolean()) {
                        if (!this.level().isClientSide) {
                            Shrapnel shrapnel = new Shrapnel(EntityInit.SHRAPNEL.get(), this.level());
                            Entity entity = this.getOwner();
                            if (entity instanceof LivingEntity) {
                                shrapnel.setOwner((LivingEntity) entity);
                            }
                            shrapnel.setPos(x, y, z);
                            float a = 0.2f;
                            shrapnel.accelerationPower = a;
                            shrapnel.setDeltaMovement(aim.scale(a));
                            shrapnel.setDamage(approximateArrowDamage*0.75f);
                            if(this.isOnFire()){
                                shrapnel.setFire(true);
                            }
                            this.level().addFreshEntity(shrapnel);
                            /*if (this.getWeaponItem().getEnchantmentLevel(
                                    this.level().registryAccess().registry(Registries.ENCHANTMENT).get()
                                            .getHolder(Enchantments.POWER).get())>0) {*/


                        }
                    }
                }
            }
        }


    }
}
