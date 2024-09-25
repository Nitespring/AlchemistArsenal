package github.nitespring.alchemistarsenal.common.item.equipment;

import com.google.common.base.Suppliers;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class SteampunkChestplateItem extends SteampunkSuitItem implements ICustomElytra{

    public SteampunkChestplateItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
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
}
