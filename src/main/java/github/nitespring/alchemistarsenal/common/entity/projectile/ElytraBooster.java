package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.OptionalInt;

public class ElytraBooster extends FireworkRocketEntity {

    public ElytraBooster(EntityType<? extends FireworkRocketEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ElytraBooster(Level pLevel, double pX, double pY, double pZ, ItemStack pStack) {
        this(EntityInit.ELYTRA_BOOSTER.get(), pLevel);
        this.life = 0;
        this.setPos(pX, pY, pZ);
        this.entityData.set(DATA_ID_FIREWORKS_ITEM, pStack.copy());
        int i = 1;
        Fireworks fireworks = pStack.get(DataComponents.FIREWORKS);
        if (fireworks != null) {
            i += fireworks.flightDuration();
        }

        this.setDeltaMovement(this.random.triangle(0.0, 0.002297), 0.05, this.random.triangle(0.0, 0.002297));
        this.lifetime = 10 * i + this.random.nextInt(6) + this.random.nextInt(7);
    }
    public ElytraBooster(Level pLevel, @Nullable Entity pShooter, double pX, double pY, double pZ, ItemStack pStack) {
        this(pLevel, pX, pY, pZ, pStack);
        this.setOwner(pShooter);
    }
    public ElytraBooster(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        this(pLevel, pShooter, pShooter.getX(), pShooter.getY(), pShooter.getZ(), pStack);
        this.entityData.set(DATA_ATTACHED_TO_TARGET, OptionalInt.of(pShooter.getId()));
        this.attachedToEntity = pShooter;
    }


    @Override
    public void tick() {
        super.tick();
        if (this.attachedToEntity != null) {
            Vec3 vec3;
            if (this.attachedToEntity.isFallFlying()) {
                Vec3 vec31 = this.attachedToEntity.getLookAngle();
                double d0 = 1.5;
                double d1 = 0.1;
                Vec3 vec32 = this.attachedToEntity.getDeltaMovement();
                this.attachedToEntity
                        .setDeltaMovement(
                                vec32.add(
                                        vec31.x * 0.1 + (vec31.x * 1.5 - vec32.x) * 0.5,
                                        vec31.y * 0.1 + (vec31.y * 1.5 - vec32.y) * 0.5,
                                        vec31.z * 0.1 + (vec31.z * 1.5 - vec32.z) * 0.5
                                )
                        );
                vec3 = this.attachedToEntity.getHandHoldingItemAngle(Items.FIREWORK_ROCKET);
            } else {
                vec3 = Vec3.ZERO;
            }
            if (this.level().isClientSide && this.tickCount % 2 < 2) {
                this.level()
                        .addParticle(
                                ParticleTypes.FIREWORK,
                                this.attachedToEntity.getX() - vec3.x,
                                this.attachedToEntity.getY() - vec3.y,
                                this.attachedToEntity.getZ() - vec3.z,
                                this.random.nextGaussian() * 0.05,
                                -this.getDeltaMovement().y * 0.5,
                                this.random.nextGaussian() * 0.05
                        );
            }
        }
    }

}
