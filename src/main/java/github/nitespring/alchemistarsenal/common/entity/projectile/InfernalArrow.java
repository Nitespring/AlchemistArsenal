package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import github.nitespring.alchemistarsenal.core.tags.CustomBlockTags;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

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
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();
        Random rng = new Random();
        double ox = mov.x;
        double oy = mov.y;
        double oz = mov.z;
        if(!this.isInWater()) {
            if (!this.inGround) {
                for (int i = 0; i < 4; i++) {
                    this.level().addParticle(ParticleTypes.SMOKE,
                            this.getX() + ox * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                            this.getY() + oy * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                            this.getZ() + oz * (double) i / 4.0 + 0.5 * (rng.nextFloat() - 0.5),
                            -0.05*ox + 0.1 * (rng.nextFloat() - 0.5), -0.05*oy + 0.1 * (rng.nextFloat() - 0.5), -0.05*oz + 0.1 * (rng.nextFloat() - 0.5));
                }
            } else {
                if (tickCount % 4 == 0) {
                    this.level().addParticle(ParticleTypes.SMOKE,
                            this.getX() + (rng.nextFloat() - 0.5),
                            this.getY() + (rng.nextFloat() - 0.5),
                            this.getZ() + (rng.nextFloat() - 0.5),
                            0.1 * (rng.nextFloat() - 0.5), 0.15, 0.1 * (rng.nextFloat() - 0.5));
                }
                if (tickCount % 24 == 0) {
                    this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                            this.getX() + (rng.nextFloat() - 0.5),
                            this.getY() + (rng.nextFloat() - 0.5),
                            this.getZ() + (rng.nextFloat() - 0.5),
                            0.1 * (rng.nextFloat() - 0.5), 0.1, 0.1 * (rng.nextFloat() - 0.5));
                }
            }

            if (!this.inGround) {
                for (int i = 0; i < 8; i++) {
                    this.level().addAlwaysVisibleParticle(ParticleTypes.FLAME,
                            this.getX() + ox * (double) i / 6.0 + 0.5 * (rng.nextFloat() - 0.5),
                            this.getY() + oy * (double) i / 6.0 + 0.5 * (rng.nextFloat() - 0.5),
                            this.getZ() + oz * (double) i / 6.0 + 0.5 * (rng.nextFloat() - 0.5),
                            -0.1*ox + 0.1 * (rng.nextFloat() - 0.5), -0.1*oy + 0.1 * (rng.nextFloat() - 0.5), -0.1*oz + 0.1 * (rng.nextFloat() - 0.5));
                }
            } else {
                if (tickCount % 6 == 0) {
                    this.level().addParticle(ParticleTypes.FLAME,
                            this.getX() + (rng.nextFloat() - 0.5),
                            this.getY() + (rng.nextFloat() - 0.5),
                            this.getZ() + (rng.nextFloat() - 0.5),
                            0.15 * (rng.nextFloat() - 0.5), 0.2, 0.15 * (rng.nextFloat() - 0.5));
                }
            }
        }

    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.INFERNAL_ARROW.get().getDefaultInstance();
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        Vec3 mov = this.getDeltaMovement();
        BlockPos blockPos = result.getBlockPos().relative(result.getDirection());
        if(level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)){
            level().destroyBlock(blockPos, true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
        }
        if(BaseFireBlock.canBePlacedAt(level(),blockPos, result.getDirection())) {
            BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
            level().setBlock(blockPos, blockstate, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }
        if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.UP)) {
            BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
            level().setBlock(blockPos, blockstate, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().igniteForTicks(40);
        BlockState block = this.level().getBlockState(this.blockPosition());
        BlockPos blockPos = this.blockPosition();
        if(level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)){
            level().destroyBlock(blockPos, true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
        }
        if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(this.position()))) {
            BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
            level().setBlock(blockPos, blockstate, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }
    }
    @Override
    public double getBaseDamage() {
        return super.getBaseDamage()+3;
    }
}
