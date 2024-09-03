package github.nitespring.alchemistarsenal.common.item;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class RepeatingCrossbow extends CrossbowItem {
    public static final Predicate<ItemStack> FIREWORK_ONLY = p_43017_ -> p_43017_.is(Items.FIREWORK_ROCKET);
    public RepeatingCrossbow(Properties pProperties) {
        super(pProperties.component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY));
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        ChargedProjectiles chargedprojectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
            ItemStack projectile = chargedprojectiles.getItems().getLast();
            this.performShooting(pLevel, pPlayer, pHand, itemstack, getShootingPower(projectile), 1.0F, null);
            pPlayer.getCooldowns().addCooldown(itemstack.getItem(), 4);
            return InteractionResultHolder.consume(itemstack);
        } else if (!pPlayer.getProjectile(itemstack).isEmpty()) {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }
    private static float getShootingPower(ItemStack projectile) {
        return projectile.is(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        int i = this.getUseDuration(stack, livingEntity) - timeLeft;
        float f = getPowerForTime(i, stack, livingEntity);
        if (f >= 1.0F && !isCharged(stack)) {
            if(livingEntity instanceof Player player) {
                ArrayList<ItemStack> projectiles = new ArrayList<ItemStack>();
                ItemStack ammo1 = player.getProjectile(stack);
                if(ammo1!=ItemStack.EMPTY){
                    projectiles.add(ammo1);
                    if(ammo1.is(Items.FIREWORK_ROCKET)){
                        if(!player.isCreative()){useAmmo(stack,ammo1,livingEntity,false);}
                        for (int k = 0; i < 2; i++) {
                            ItemStack ammo = player.getItemBySlot(EquipmentSlot.OFFHAND);
                            if(ammo.is(Items.FIREWORK_ROCKET)){
                                projectiles.set(k+1,ammo);
                                if(!player.isCreative()){ammo.shrink(1);}

                            }
                        }
                        stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(projectiles));
                    }else{
                        if(!player.isCreative()){useAmmo(stack,ammo1,livingEntity,false);}
                        for (int k = 0; i < 2; i++) {
                            int j = 0;
                            ItemStack ammo =ItemStack.EMPTY;
                            while (ammo==ItemStack.EMPTY && j < player.getInventory().getContainerSize()) {
                                ItemStack itemstack1 = player.getInventory().getItem(i);
                                if (itemstack1.is(ItemTags.ARROWS)) {
                                    ammo=itemstack1;
                                    projectiles.set(k+1,ammo);
                                    if(!player.isCreative()){useAmmo(stack,itemstack1,livingEntity,false);}
                                }
                            }
                        }
                        stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(projectiles));
                    }
                }else if(player.isCreative()){
                    for (int k = 0; i < 2; i++) {
                        ItemStack ammo = getDefaultCreativeAmmo(player, stack);
                            projectiles.set(k,ammo);
                    }
                    stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(projectiles));
                }

            }
        }
    }

    public static boolean isCharged(ItemStack pCrossbowStack) {
        ChargedProjectiles chargedprojectiles = pCrossbowStack.get(DataComponents.CHARGED_PROJECTILES);
        return !chargedprojectiles.isEmpty();
    }

    private static float getPowerForTime(int pTimeLeft, ItemStack pStack, LivingEntity pShooter) {
        float f = (float)pTimeLeft / (float)getChargeDuration(pStack, pShooter);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public void performShooting(Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pWeapon, float pVelocity, float pInaccuracy, @Nullable LivingEntity pTarget) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if (pShooter instanceof Player player && net.neoforged.neoforge.event.EventHooks.onArrowLoose(pWeapon, pShooter.level(), player, 1, true) < 0) return;
            ChargedProjectiles chargedprojectiles = pWeapon.get(DataComponents.CHARGED_PROJECTILES);
            if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
                int size = chargedprojectiles.getItems().size();
                this.shoot(serverlevel, pShooter, pHand, pWeapon, Collections.singletonList(chargedprojectiles.getItems().getLast()), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget);
                if(size<=1){
                    pWeapon.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.EMPTY);
                }else{
                    pWeapon.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.of(chargedprojectiles.getItems().removeLast()));
                }
                if (pShooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }
        }
    }
    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {return ARROW_OR_FIREWORK;}
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        ChargedProjectiles chargedprojectiles = pStack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
            for(int i = 0; i<chargedprojectiles.getItems().size();i++) {
                ItemStack itemstack = chargedprojectiles.getItems().get(i);
                pTooltipComponents.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
                if (pTooltipFlag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                    List<Component> list = Lists.newArrayList();
                    Items.FIREWORK_ROCKET.appendHoverText(itemstack, pContext, list, pTooltipFlag);
                    if (!list.isEmpty()) {
                        for (int j = 0; j < list.size(); j++) {
                            list.set(j, Component.literal("  ").append(list.get(i)).withStyle(ChatFormatting.GRAY));
                        }

                        pTooltipComponents.addAll(list);
                    }
                }
            }
        }
    }
}
