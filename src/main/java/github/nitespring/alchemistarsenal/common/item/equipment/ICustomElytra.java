package github.nitespring.alchemistarsenal.common.item.equipment;

import github.nitespring.alchemistarsenal.common.entity.projectile.ElytraBooster;
import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import github.nitespring.alchemistarsenal.core.tags.CustomItemTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.phys.Vec3;

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
        if(player.isFallFlying()) {
            if (player.isCreative() || player.getInventory().contains(CustomItemTags.STEAMPUNK_WINGS_FUEL)) {
                if (!player.level().isClientSide()) {
                    ItemStack item = new ItemStack(Items.FIREWORK_ROCKET);
                    item.set(DataComponents.FIREWORKS, new Fireworks(5, List.of()));
                    ElytraBooster rocket = new ElytraBooster(player.level(), item, player);
                    rocket.setPos(player.position());
                    //rocket.setSilent(true);
                    player.level().addFreshEntity(rocket);


                    Vec3 pos = player.position().add(0, player.getBbHeight() * 0.5f, 0);
                    Vec3 aim = player.getLookAngle().normalize();
                    Vec3 aim1 = new Vec3(aim.z, -aim.y, aim.x);
                    Vec3 pos1 = pos.add(aim.scale(-0.25f));
                    float a = (float) (-Math.PI * 1 / 6);
                    float d = 0.75f;
                    for (int i = 0; i < 12; i++) {
                        Vec3 aim2 = new Vec3(
                                d * (aim.z * Math.cos(i * a) - aim.y * Math.sin(i * a)),
                                d * (aim.y * Math.cos(i * a) + Math.sqrt(aim.x * aim.x + aim.z * aim.z) * Math.sin(i * a)),
                                d * (-aim.x * Math.cos(i * a) - aim.y * Math.sin(i * a)));
                        Vec3 posf = pos1.add(aim2);
                        ((ServerLevel) player.level()).sendParticles(
                                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                posf.x(),
                                posf.y(),
                                posf.z(),
                                0,
                                player.getRandom().nextGaussian() * 0.05,
                                -player.getDeltaMovement().y * 0.5,
                                player.getRandom().nextGaussian() * 0.05,
                                0.1f
                        );
                    }
                }
                //if (player.level().isClientSide) {

                //}
                player.level().playSound(
                        null, player.blockPosition(),
                        SoundEvents.FIRECHARGE_USE,
                        SoundSource.PLAYERS,
                        1.0f, 0.6f);
                int n = player.getInventory().getContainerSize();
                int i = 0;
                boolean hasFuel = false;
                while (!hasFuel && i <= n) {
                    if (player.getInventory().getItem(i).is(CustomItemTags.STEAMPUNK_WINGS_FUEL)) {
                        player.getInventory().getItem(i).shrink(1);
                    }
                    i++;
                }
                stack.hurtAndBreak(5, player, EquipmentSlot.CHEST);
            }
        }

    }
    public boolean shouldAutoboost();

}
