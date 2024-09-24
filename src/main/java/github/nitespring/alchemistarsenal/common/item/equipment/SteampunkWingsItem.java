package github.nitespring.alchemistarsenal.common.item.equipment;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Collections;
import java.util.Random;

public class SteampunkWingsItem extends ElytraItem implements ICustomElytra {
    public SteampunkWingsItem(Properties properties) {
        super(properties);
    }


    public static boolean isFlyEnabled(ItemStack pElytraStack) {
        return pElytraStack.getDamageValue() < pElytraStack.getMaxDamage() - 1;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(Items.PHANTOM_MEMBRANE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pHand);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return SteampunkWingsItem.isFlyEnabled(stack);
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
    public Holder<SoundEvent> getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_ELYTRA;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }



    @Override
    public ResourceLocation getDefaultElytraTexture() {
        return ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,
                "textures/equipment/steampunk_wings.png");
    }

    @Override
    public boolean shouldAutoboost() {
        return true;
    }

}
