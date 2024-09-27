package github.nitespring.alchemistarsenal.common.item.equipment;

import com.google.common.base.Suppliers;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import github.nitespring.alchemistarsenal.core.init.KeybindInit;
import github.nitespring.alchemistarsenal.core.tags.CustomItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SteampunkChestplateItem extends SteampunkSuitItem implements ICustomElytra{

    public static final Predicate<ItemStack> ROCKETS = predicate -> predicate.is(Items.FIREWORK_ROCKET);

    public SteampunkChestplateItem(float speedModifier, float jumpModifier, float stepHeightModifier, float miningEfficiencyModifier, float blockBreakSpeed, float fallDamageModifier, float gravityModifier, float safeFallHeightModifier, Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(speedModifier, jumpModifier, stepHeightModifier, miningEfficiencyModifier, blockBreakSpeed, fallDamageModifier, gravityModifier, safeFallHeightModifier, material, type, properties);
    }


    public static boolean isFlyEnabled(ItemStack pElytraStack) {
        return pElytraStack.getDamageValue() < pElytraStack.getMaxDamage() - 1;
    }

    @Override
    public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return SteampunkChestplateItem.isFlyEnabled(stack);
    }


    @Override
    public boolean elytraFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {
        Vec3 pos = entity.position();
        Vec3 mov = entity.getDeltaMovement();
        Level level = entity.level();
        int nextFlightTick = flightTicks + 1;
        Random rand = new Random();

        if (!entity.level().isClientSide) {
            if (nextFlightTick % 10 == 0) {
                if (nextFlightTick % 20 == 0) {
                    stack.hurtAndBreak(1, entity, net.minecraft.world.entity.EquipmentSlot.CHEST);
                }
                entity.gameEvent(net.minecraft.world.level.gameevent.GameEvent.ELYTRA_GLIDE);
            }
            if (nextFlightTick % 12 == 0) {
                for(int i = 0; i<=5;i++) {
                    ((ServerLevel) level).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            pos.x - 0.1 * mov.x + 0.5*rand.nextFloat(-0.5f,0.5f),
                            pos.y + 0.5 - 0.1 * mov.y,
                            pos.z - 0.1 * mov.z + 0.5*rand.nextFloat(-0.5f,0.5f),
                            0,
                            mov.x+ 0.25*rand.nextFloat(-0.5f,0.5f),
                            mov.y+ 0.1*rand.nextFloat(-0.5f,0.5f),
                            mov.z+ 0.25*rand.nextFloat(-0.5f,0.5f), 0.1F);
                }
            }
            if(mov.length()>=0.25) {
                if (nextFlightTick % 8 == 0) {
                    for (int i = 0; i < 3; i++) {
                        ((ServerLevel) level).sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                                pos.x - 0.1 * mov.x + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                pos.y + 0.5 - 0.1 * mov.y,
                                pos.z - 0.1 * mov.z + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                0,
                                mov.x + 0.25 * rand.nextFloat(-0.5f, 0.5f),
                                mov.y + 0.1 * rand.nextFloat(-0.5f, 0.5f),
                                mov.z + 0.25 * rand.nextFloat(-0.5f, 0.5f), 0.1F);
                    }
                }
                if (nextFlightTick % 2 == 0) {
                    for (int i = 0; i < 5; i++) {
                        ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE,
                                pos.x - 0.1 * mov.x + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                pos.y + 0.5 - 0.1 * mov.y,
                                pos.z - 0.1 * mov.z + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                0,
                                mov.x + 0.25 * rand.nextFloat(-0.5f, 0.5f),
                                mov.y + 0.1 * rand.nextFloat(-0.5f, 0.5f),
                                mov.z + 0.25 * rand.nextFloat(-0.5f, 0.5f), 0.1F);
                    }
                }
                if (nextFlightTick % 5 == 0) {
                    for (int i = 0; i < 2; i++) {
                        ((ServerLevel) level).sendParticles(ParticleTypes.LARGE_SMOKE,
                                pos.x - 0.1 * mov.x + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                pos.y + 0.5 - 0.1 * mov.y,
                                pos.z - 0.1 * mov.z + 0.5 * rand.nextFloat(-0.5f, 0.5f),
                                0,
                                mov.x + 0.25 * rand.nextFloat(-0.5f, 0.5f),
                                mov.y + 0.1 * rand.nextFloat(-0.5f, 0.5f),
                                mov.z + 0.25 * rand.nextFloat(-0.5f, 0.5f), 0.1F);
                    }
                }
            }
        }
        if(entity.getDeltaMovement().length()>=0.1) {
            if (nextFlightTick % 16 == 0) {
                entity.playSound(SoundEvents.PHANTOM_FLAP, 0.6f + 0.1f * rand.nextFloat(), 1.5f + 0.75f * rand.nextFloat());
            }
        }
        if(nextFlightTick % 8 == 0) {
            entity.playSound(SoundEvents.FIRE_AMBIENT, 0.6f + 0.1f * rand.nextFloat(), 1.5f + 0.75f * rand.nextFloat());
        }
        return true;
    }
    @Override
    public ResourceLocation getDefaultElytraTexture() {
        return SteampunkWingsItem.TEXTURE_LOCATION;
    }

    @Override
    public boolean shouldAutoboost() {
        return true;
    }


    public void shoot(Player player, ItemStack stack){
        if (player.isCreative() || player.getInventory().contains(ROCKETS)) {

            player.level().playSound(
                    null, player.blockPosition(),
                    SoundEvents.FIRECHARGE_USE,
                    SoundSource.PLAYERS,
                    1.0f, 0.6f);

            player.level().playSound(
                    null, player.blockPosition(),
                    SoundEvents.GENERIC_EXPLODE.value(),
                    SoundSource.PLAYERS,
                    0.2f, 0.6f);

            ItemStack rocketItem = new ItemStack(Items.FIREWORK_ROCKET);
            rocketItem.set(DataComponents.FIREWORKS, new Fireworks(1, Collections.singletonList(FireworkExplosion.DEFAULT)));

            int n = player.getInventory().getContainerSize();
            int i = 0;
            boolean hasAmmo = false;
            while (!hasAmmo && i <= n) {
                if (ROCKETS.test(player.getInventory().getItem(i))) {
                    rocketItem = player.getInventory().getItem(i);
                    if(!player.isCreative()) {
                        player.getInventory().getItem(i).shrink(1);
                    }
                    hasAmmo=true;
                }
                i++;
            }


            Random rand = new Random();
            Level level = player.level();
            Vec3 pos = player.position();
            Vec3 poseye = new Vec3(pos.x,player.getEyeY(), pos.z);
            Vec3 aim = player.getLookAngle();
            Vec3 pos1 = poseye.add(
                    1.5 * aim.x + rand.nextFloat(-0.5f,0.5f),
                    1.5 * aim.y + rand.nextFloat(-0.15f,0.15f) + 0.25,
                    1.5 * aim.z + rand.nextFloat(-0.5f,0.5f));

            if (!level.isClientSide()) {
                FireworkRocketEntity rocket = new FireworkRocketEntity(level, rocketItem, player, pos1.x, pos1.y, pos1.z, true);
                rocket.shoot(aim.x, aim.y, aim.z, 2.5f, 1.5f);
                level.addFreshEntity(rocket);


                for(int k = 0; k<=2;k++) {
                    ((ServerLevel) level).sendParticles(
                            ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            pos1.x() + rand.nextFloat(-0.5f, 0.5f),
                            pos1.y() + 0.25f + rand.nextFloat(-0.5f, 0.5f),
                            pos1.z() + rand.nextFloat(-0.5f, 0.5f),
                            0,
                            player.getRandom().nextGaussian() * 0.05,
                            -player.getDeltaMovement().y * 0.5,
                            player.getRandom().nextGaussian() * 0.05,
                            0.1f
                    );
                }
                for(int k = 0; k<=4;k++) {
                    ((ServerLevel) level).sendParticles(
                            ParticleTypes.LARGE_SMOKE,
                            pos1.x() + rand.nextFloat(-0.5f, 0.5f),
                            pos1.y() + 0.25f + rand.nextFloat(-0.5f, 0.5f),
                            pos1.z() + rand.nextFloat(-0.5f, 0.5f),
                            0,
                            player.getRandom().nextGaussian() * 0.05,
                            -player.getDeltaMovement().y * 0.5,
                            player.getRandom().nextGaussian() * 0.05,
                            0.1f
                    );
                }
                for(int k = 0; k<=7;k++) {
                    ((ServerLevel) level).sendParticles(
                            ParticleTypes.SMOKE,
                            pos1.x() + rand.nextFloat(-0.5f, 0.5f),
                            pos1.y() + 0.25f + rand.nextFloat(-0.5f, 0.5f),
                            pos1.z() + rand.nextFloat(-0.5f, 0.5f),
                            0,
                            player.getRandom().nextGaussian() * 0.05,
                            -player.getDeltaMovement().y * 0.5,
                            player.getRandom().nextGaussian() * 0.05,
                            0.1f
                    );
                }

            }

            stack.hurtAndBreak(3, player, EquipmentSlot.CHEST);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable("comment.alkhars.steampunk_chestplate")
                        .withStyle(ChatFormatting.GRAY)
        );
        tooltipComponents.add(
                Component.translatable("comment.alkhars.steampunk_wings1")
                        .append(KeybindInit.BOOST_KEYBIND.get().getKey().getDisplayName())
                        .append(Component.translatable("comment.alkhars.steampunk_wings2"))
                        .withStyle(ChatFormatting.AQUA)
        );
        tooltipComponents.add(
                Component.translatable("comment.alkhars.steampunk_chestplate1")
                        .append(KeybindInit.SHOOT_KEYBIND.get().getKey().getDisplayName())
                        .append(Component.translatable("comment.alkhars.steampunk_chestplate2"))
                        .withStyle(ChatFormatting.YELLOW)
        );
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }


}
