package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class DragonArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public DragonArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DragonArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.DRAGON_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public DragonArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.DRAGON_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    @Override
    protected ItemStack getPickupItem() {

        return ItemInit.DRAGON_ARROW.get().getDefaultInstance();
    }
    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.DRAGON_ARROW.get().getDefaultInstance();
    }
    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();
        Random rng = new Random();
        double ox = mov.x;
        double oy = mov.y;
        double oz = mov.z;

        if (!this.inGround) {
            for (int i = 0; i < 4; i++) {
                this.level().addParticle(ParticleTypes.DRAGON_BREATH,
                        this.getX() + ox * (double) i / 4.0 + (rng.nextFloat() - 0.5),
                        this.getY() + oy * (double) i / 4.0 + (rng.nextFloat() - 0.5),
                        this.getZ() + oz * (double) i / 4.0 + (rng.nextFloat() - 0.5),
                        -0.1*ox, -0.1*oy, -0.1*oz);
            }
        }else{
            if (tickCount % 3 == 0) {
                this.level().addParticle(ParticleTypes.DRAGON_BREATH,
                        this.getX() + (rng.nextFloat() - 0.5),
                        this.getY() + (rng.nextFloat() - 0.5),
                        this.getZ() + (rng.nextFloat() - 0.5),
                        0.2 * (rng.nextFloat() - 0.5), 0.25, 0.2 * (rng.nextFloat() - 0.5));
            }
        }

    }



    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0, 2.0, 4.0));
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            Entity entity = this.getOwner();
            if (entity instanceof LivingEntity) {
                areaeffectcloud.setOwner((LivingEntity)entity);
            }

            areaeffectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
            areaeffectcloud.setRadius(1.5F);
            areaeffectcloud.setDuration(100);
            areaeffectcloud.setRadiusPerTick((1.5F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
            areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
            if (!list.isEmpty()) {
                for (LivingEntity livingentity : list) {
                    double d0 = this.distanceToSqr(livingentity);
                    if (d0 < 16.0) {
                        areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        break;
                    }
                }
            }

            //this.level().levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);

            this.level().addFreshEntity(areaeffectcloud);

        }
        /*if(!this.isSilent()) {
            this.playSound(SoundEvents.DRAGON_FIREBALL_EXPLODE, 0.8f,1.0f);
            if (this.getOwner() != null && this.getOwner().distanceToSqr(this.blockPosition().getX(), this.blockPosition().getY(), this.blockPosition().getZ()) >= 24 * 24) {
                Vec3 pos = this.getOwner().position();
                this.getOwner().level().playSound(null, pos.x(),pos.y(),pos.z(),SoundEvents.DRAGON_FIREBALL_EXPLODE,SoundSource.PLAYERS, 0.6f, 1.0f);
            }
        }*/
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
    }
    @Override
    public double getBaseDamage() {
        return super.getBaseDamage()+2;
    }
}
