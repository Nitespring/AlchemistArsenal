package github.nitespring.alchemistarsenal.common.entity.projectile.arrow;

import github.nitespring.alchemistarsenal.common.entity.projectile.throwable.FireBottle;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WindChargeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class WindArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public WindArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public WindArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.WIND_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public WindArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.WIND_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    @Override
    protected ItemStack getPickupItem() {

        return Items.ARROW.getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return Items.ARROW.getDefaultInstance();
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();
        Random rng = new Random();
        double ox = mov.x;
        double oy = mov.y;
        double oz = mov.z;
        if(this.isCritArrow()) {
            for (int i = 0; i < 2; i++) {
                this.level().addParticle(ParticleTypes.GUST,
                        this.getX() + ox * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getY() + oy * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getZ() + oz * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        -0.05*ox + 0.05 * (rng.nextFloat() - 0.5), -0.05*oy - 0.1 + 0.05 * (rng.nextFloat() - 0.5), -0.05*oz + 0.05 * (rng.nextFloat() - 0.5));
            }
        }else{
            this.level().addParticle(ParticleTypes.SMALL_GUST,
                    this.getX() + ox + 0.5 * (rng.nextFloat() - 0.5),
                    this.getY() + oy + 0.5 * (rng.nextFloat() - 0.5),
                    this.getZ() + oz + 0.5 * (rng.nextFloat() - 0.5),
                    -0.01*ox + 0.05 * (rng.nextFloat() - 0.5), -0.01*oy - 0.1 + 0.05 * (rng.nextFloat() - 0.5), -0.01*oz + 0.05 * (rng.nextFloat() - 0.5));
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        Vec3 pos = this.position();
        if (!this.level().isClientSide) {
            /*WindCharge wind = new WindCharge(EntityType.WIND_CHARGE, this.level());
            wind.setOwner(this.getOwner());
            wind.setPos(pos);
            this.level().addFreshEntity(wind);*/
            this.level().explode(
                            this,
                            null,
                            new SimpleExplosionDamageCalculator(
                                    true, true, Optional.of(1.75F), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
                            ),
                            pos.x(),
                            pos.y(),
                            pos.z(),
                            1.5F,
                            this.isOnFire(),
                            Level.ExplosionInteraction.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.WIND_CHARGE_BURST
                    );
        }
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
        return super.getBaseDamage();
    }
}
