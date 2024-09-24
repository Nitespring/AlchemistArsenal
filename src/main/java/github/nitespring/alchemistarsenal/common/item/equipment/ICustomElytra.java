package github.nitespring.alchemistarsenal.common.item.equipment;

import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import github.nitespring.alchemistarsenal.core.tags.CustomItemTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;

import java.util.Collections;
import java.util.List;

public interface ICustomElytra {


    public default ResourceLocation getElytraTexture(ItemStack stack) {
        if(stack.has(DataComponentInit.CUSTOM_WINGS_TEXTURE)){
            return DataComponentInit.CUSTOM_WINGS_TEXTURE.getId();
        }else {
            return getDefaultElytraTexture();
        }
    }
    public ResourceLocation getDefaultElytraTexture();
    public default void autoBoost(Player player, ItemStack stack){

        if(player.isCreative()||player.getInventory().contains(CustomItemTags.STEAMPUNK_WINGS_FUEL)) {
            if (!player.level().isClientSide()) {
                ItemStack item = new ItemStack(Items.FIREWORK_ROCKET);
                item.set(DataComponents.FIREWORKS, new Fireworks(5, List.of()));
                FireworkRocketEntity rocket = new FireworkRocketEntity(player.level(), item, player);
                rocket.setPos(player.position());
                //rocket.setSilent(true);
                player.level().addFreshEntity(rocket);
            }

            player.level().playSound(
                    null, player.blockPosition(),
                    SoundEvents.FIRECHARGE_USE,
                    SoundSource.PLAYERS,
                    1.0f, 0.6f);
            int n = player.getInventory().getContainerSize();
            int i = 0;
            boolean hasFuel = false;
            while(!hasFuel&&i<=n){
                if(player.getInventory().getItem(i).is(CustomItemTags.STEAMPUNK_WINGS_FUEL)){
                    player.getInventory().getItem(i).shrink(1);
                }
                i++;
            }
            stack.hurtAndBreak(5, player, EquipmentSlot.CHEST);
        }

    }
    public boolean shouldAutoboost();

}
