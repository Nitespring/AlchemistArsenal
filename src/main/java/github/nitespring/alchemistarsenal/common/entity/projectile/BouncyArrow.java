package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
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

        return ItemInit.BOUNCY_ARROW.get().getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.BOUNCY_ARROW.get().getDefaultInstance();
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

            this.playSound(SoundEvents.SLIME_JUMP);
        }else {
            super.onHitBlock(pResult);
        }

    }
}
