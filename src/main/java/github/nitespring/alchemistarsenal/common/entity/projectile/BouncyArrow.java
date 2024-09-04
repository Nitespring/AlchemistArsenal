package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BouncyArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public BouncyArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BouncyArrow( double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.BOUNCY_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public BouncyArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.BOUNCY_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
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

        if (!this.inGround) {
            if(this.isCritArrow()) {
                for (int i = 0; i < 4; i++) {
                this.level().addParticle(ParticleTypes.ITEM_SLIME,
                        this.getX() + ox * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getY() + oy * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        this.getZ() + oz * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                        -0.05*ox + 0.05 * (rng.nextFloat() - 0.5), -0.05*oy - 0.1 + 0.05 * (rng.nextFloat() - 0.5), -0.05*oz + 0.05 * (rng.nextFloat() - 0.5));
                }
            }else{
                this.level().addParticle(ParticleTypes.ITEM_SLIME,
                        this.getX() + ox + 0.5 * (rng.nextFloat() - 0.5),
                        this.getY() + oy + 0.5 * (rng.nextFloat() - 0.5),
                        this.getZ() + oz + 0.5 * (rng.nextFloat() - 0.5),
                        -0.01*ox + 0.05 * (rng.nextFloat() - 0.5), -0.01*oy - 0.1 + 0.05 * (rng.nextFloat() - 0.5), -0.01*oz + 0.05 * (rng.nextFloat() - 0.5));
            }
        }
    }



    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(hitBlocks<=MAX_HIT_BLOCKS) {
            hitBlocks++;
            Vec3 mov = this.getDeltaMovement();
            if (pResult.getDirection() == Direction.DOWN || pResult.getDirection() == Direction.UP){
            this.setDeltaMovement(mov.x, -mov.y, mov.z);
            }else if (pResult.getDirection() == Direction.EAST){
                this.setDeltaMovement(Math.abs(mov.x), -mov.y, mov.z);
            }else if (pResult.getDirection() == Direction.WEST){
                this.setDeltaMovement(-Math.abs(mov.x), -mov.y, mov.z);
            }else if (pResult.getDirection() == Direction.NORTH){
                this.setDeltaMovement(mov.x, -mov.y, -Math.abs(mov.z));
            }else if (pResult.getDirection() == Direction.SOUTH){
                this.setDeltaMovement(mov.x, -mov.y, Math.abs(mov.z));
            }
            Random rng = new Random();
            for (int i = 0; i < 8; i++) {
                    this.level().addParticle(ParticleTypes.ITEM_SLIME,
                            this.getX() + 1.5f*(rng.nextFloat() - 0.5),
                            this.getY(),
                            this.getZ() + 1.5f*(rng.nextFloat() - 0.5),
                             0.15 * (rng.nextFloat() - 0.5), 0.2 + 0.1 * (rng.nextFloat() - 0.5), 0.15 * (rng.nextFloat() - 0.5));
            }
            this.playSound(SoundEvents.SLIME_JUMP);
        }else {
            super.onHitBlock(pResult);
        }

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
