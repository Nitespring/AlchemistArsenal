package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class InfernalArrow extends AbstractArrow {


    public InfernalArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public InfernalArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.INFERNAL_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }

    public InfernalArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.INFERNAL_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }

    @Override
    protected ItemStack getPickupItem() {

        return ItemInit.INFERNAL_ARROW.get().getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.INFERNAL_ARROW.get().getDefaultInstance();
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        BlockPos blockPos = result.getBlockPos().relative(result.getDirection());
        if(level().getBlockState(blockPos).is(BlockTags.LEAVES)){
            level().destroyBlock(blockPos, true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
        }
        if(BaseFireBlock.canBePlacedAt(level(),blockPos, result.getDirection())) {
            BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
            level().setBlock(blockPos, blockstate, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }
    }
}
