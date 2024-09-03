package github.nitespring.alchemistarsenal.common.item.weapons;

import com.google.common.collect.Lists;
import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
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
        super(pProperties.component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                .component(DataComponentInit.CHARGED_PROJECTILES2, ChargedProjectiles.EMPTY)
                .component(DataComponentInit.CHARGED_PROJECTILES3, ChargedProjectiles.EMPTY));
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
                ItemStack ammo1 = player.getProjectile(stack);
                if(player.isCreative()){
                    stack.set(DataComponentInit.CHARGED_PROJECTILES2, ChargedProjectiles.of(ammo1));
                    stack.set(DataComponentInit.CHARGED_PROJECTILES3, ChargedProjectiles.of(ammo1));
                }
                if(ammo1!=ItemStack.EMPTY){
                    stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(ammo1));
                    if(ammo1.is(Items.FIREWORK_ROCKET)){
                        if(!player.isCreative()){useAmmo(stack,ammo1,livingEntity,false);}
                        ItemStack ammo2 = player.getItemBySlot(EquipmentSlot.OFFHAND);
                        if(ammo2!=ItemStack.EMPTY) {
                            if (ammo2.is(Items.FIREWORK_ROCKET)) {
                                stack.set(DataComponentInit.CHARGED_PROJECTILES2, ChargedProjectiles.of(ammo2));
                                if (!player.isCreative()) {
                                    useAmmo(stack, ammo2, livingEntity, false);
                                }
                            }
                            ItemStack ammo3 = player.getItemBySlot(EquipmentSlot.OFFHAND);
                            if(ammo3!=ItemStack.EMPTY) {
                                if (ammo3.is(Items.FIREWORK_ROCKET)) {
                                    stack.set(DataComponentInit.CHARGED_PROJECTILES3, ChargedProjectiles.of(ammo3));
                                    if (!player.isCreative()) {
                                        useAmmo(stack, ammo3, livingEntity, false);
                                    }
                                }
                            }
                        }

                    }else{
                        if(!player.isCreative()){useAmmo(stack,ammo1,livingEntity,false);}
                            int j = 0;
                            ItemStack ammo2 =ItemStack.EMPTY;
                            while (ammo2==ItemStack.EMPTY && j < player.getInventory().getContainerSize()) {
                                ItemStack itemstack1 = player.getInventory().getItem(j);
                                if (itemstack1.is(ItemTags.ARROWS)) {
                                    ammo2=itemstack1;
                                    stack.set(DataComponentInit.CHARGED_PROJECTILES2, ChargedProjectiles.of(ammo2));
                                    if(!player.isCreative()){useAmmo(stack,itemstack1,livingEntity,false);}
                                }
                                j++;
                            }

                            int k = 0;
                            ItemStack ammo3 =ItemStack.EMPTY;
                            while (ammo3==ItemStack.EMPTY && k < player.getInventory().getContainerSize()) {
                                ItemStack itemstack1 = player.getInventory().getItem(k);
                                if (itemstack1.is(ItemTags.ARROWS)) {
                                    ammo3=itemstack1;
                                    stack.set(DataComponentInit.CHARGED_PROJECTILES3, ChargedProjectiles.of(ammo3));
                                    if(!player.isCreative()){useAmmo(stack,itemstack1,livingEntity,false);}
                                }
                                k++;
                            }

                    }
                }else if(player.getAbilities().instabuild){
                    stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(getDefaultCreativeAmmo(player, stack)));
                    stack.set(DataComponentInit.CHARGED_PROJECTILES2, ChargedProjectiles.of(getDefaultCreativeAmmo(player, stack)));
                    stack.set(DataComponentInit.CHARGED_PROJECTILES3, ChargedProjectiles.of(getDefaultCreativeAmmo(player, stack)));
                }

            }
        }
    }

    public static boolean isCharged(ItemStack pCrossbowStack) {
        ChargedProjectiles chargedprojectiles1 = pCrossbowStack.get(DataComponents.CHARGED_PROJECTILES);
        ChargedProjectiles chargedprojectiles2 = pCrossbowStack.get(DataComponents.CHARGED_PROJECTILES);
        ChargedProjectiles chargedprojectiles3 = pCrossbowStack.get(DataComponents.CHARGED_PROJECTILES);
        return !chargedprojectiles1.isEmpty();
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
            ChargedProjectiles chargedprojectiles1 = pWeapon.get(DataComponents.CHARGED_PROJECTILES);
            ChargedProjectiles chargedprojectiles2 = pWeapon.get(DataComponentInit.CHARGED_PROJECTILES2);
            ChargedProjectiles chargedprojectiles3 = pWeapon.get(DataComponentInit.CHARGED_PROJECTILES3);
            if (chargedprojectiles3 != null && !chargedprojectiles3.isEmpty()) {
                this.shoot(serverlevel, pShooter, pHand, pWeapon, chargedprojectiles3.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget);
                pWeapon.set(DataComponentInit.CHARGED_PROJECTILES3,ChargedProjectiles.EMPTY);

                if (pShooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }else if (chargedprojectiles2 != null && !chargedprojectiles2.isEmpty()) {
                this.shoot(serverlevel, pShooter, pHand, pWeapon, chargedprojectiles2.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget);
                pWeapon.set(DataComponentInit.CHARGED_PROJECTILES2,ChargedProjectiles.EMPTY);

                if (pShooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }else if (chargedprojectiles1 != null && !chargedprojectiles1.isEmpty()) {
                this.shoot(serverlevel, pShooter, pHand, pWeapon, chargedprojectiles1.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget);
                pWeapon.set(DataComponents.CHARGED_PROJECTILES,ChargedProjectiles.EMPTY);

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
        ChargedProjectiles chargedprojectiles1 = pStack.get(DataComponents.CHARGED_PROJECTILES);
        ChargedProjectiles chargedprojectiles2 = pStack.get(DataComponentInit.CHARGED_PROJECTILES2);
        ChargedProjectiles chargedprojectiles3 = pStack.get(DataComponentInit.CHARGED_PROJECTILES3);

        if (chargedprojectiles1 != null && !chargedprojectiles1.isEmpty()) {
                ItemStack itemstack = chargedprojectiles1.getItems().get(0);
                pTooltipComponents.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
                if (pTooltipFlag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                    List<Component> list = Lists.newArrayList();
                    Items.FIREWORK_ROCKET.appendHoverText(itemstack, pContext, list, pTooltipFlag);
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            list.set(i, Component.literal("  ").append(list.get(i)).withStyle(ChatFormatting.GRAY));
                        }

                        pTooltipComponents.addAll(list);
                    }
                }
        }
        if(chargedprojectiles2!= null && !chargedprojectiles2.isEmpty()){
            ItemStack itemstack = chargedprojectiles2.getItems().get(0);
            pTooltipComponents.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
            if (pTooltipFlag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                List<Component> list = Lists.newArrayList();
                Items.FIREWORK_ROCKET.appendHoverText(itemstack, pContext, list, pTooltipFlag);
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.set(i, Component.literal("  ").append(list.get(i)).withStyle(ChatFormatting.GRAY));
                    }

                    pTooltipComponents.addAll(list);
                }
            }
        }
        if(chargedprojectiles3!= null && !chargedprojectiles3.isEmpty()){
            ItemStack itemstack = chargedprojectiles3.getItems().get(0);
            pTooltipComponents.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
            if (pTooltipFlag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                List<Component> list = Lists.newArrayList();
                Items.FIREWORK_ROCKET.appendHoverText(itemstack, pContext, list, pTooltipFlag);
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.set(i, Component.literal("  ").append(list.get(i)).withStyle(ChatFormatting.GRAY));
                    }

                    pTooltipComponents.addAll(list);
                }
            }
        }
    }
}
