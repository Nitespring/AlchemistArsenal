package github.nitespring.alchemistarsenal.common.entity.projectile.arrow;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class LightningArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public LightningArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public LightningArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.LIGHTNING_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public LightningArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.LIGHTNING_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    @Override
    protected ItemStack getPickupItem() {

        return ItemInit.LIGHTNING_ARROW.get().getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.LIGHTNING_ARROW.get().getDefaultInstance();
    }


    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        Random rand = new Random();
        if(pResult.getDirection() == Direction.UP) {
            BlockPos bPos = this.blockPosition();
            if (this.level().isThundering() && this.level().canSeeSky(bPos)) {
                if (rand.nextFloat() <= 0.45f) {
                    if (!this.level().isClientSide()) {
                        Vec3 pos = new Vec3(this.position().x, bPos.getY(), this.position().z);
                        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                        lightning.setPos(pos);
                        if (this.getOwner() instanceof ServerPlayer player) {
                            lightning.setCause(player);
                        }


                        this.level().addFreshEntity(lightning);
                        //this.discard();
                    }
                }
            }
        }else if(pResult.getDirection() == Direction.DOWN){
            BlockPos bPos = pResult.getBlockPos();
            if (this.level().isThundering()) {
                if (rand.nextFloat() <= 0.45f) {
                    if (!this.level().isClientSide()) {
                        for (int i = 0; i <= 2; i++) {
                            if (!level().getBlockState(bPos.above()).isAir()) {
                                bPos = bPos.above();
                            }
                        }
                        if(this.level().canSeeSky(bPos)){
                            Vec3 pos = new Vec3(bPos.getX(), bPos.getY(), bPos.getZ());
                            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                            lightning.setPos(pos);
                            if (this.getOwner() instanceof ServerPlayer player) {
                                lightning.setCause(player);
                            }
                            this.level().addFreshEntity(lightning);
                        }
                        //this.discard();
                    }
                }
            }
        }else{
            //BlockPos bPos = pResult.getBlockPos().relative(pResult.getDirection().getOpposite());
            BlockPos bPos = pResult.getBlockPos();
            if (this.level().isThundering()) {
                if (rand.nextFloat() <= 0.45f) {
                    if (!this.level().isClientSide()) {
                        for (int i = 0; i <= 2; i++) {
                            if (!level().getBlockState(bPos.above()).isAir()) {
                                bPos = bPos.above();
                            }
                        }
                        if(this.level().canSeeSky(bPos)){
                            Vec3 pos = new Vec3(bPos.getX(), bPos.getY(), bPos.getZ());
                            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                            lightning.setPos(pos);
                            if (this.getOwner() instanceof ServerPlayer player) {
                                lightning.setCause(player);
                            }
                            this.level().addFreshEntity(lightning);
                        }
                        //this.discard();
                    }
                }
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        BlockPos bPos = this.blockPosition();
        if (this.level().isThundering() && this.level().canSeeSky(bPos)) {
            if (new Random().nextFloat() <= 0.65f) {
                if (!this.level().isClientSide()) {
                    Vec3 pos = new Vec3(entity.position().x, entity.position().y, entity.position().z);
                    LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                    lightning.setPos(pos);
                    if (this.getOwner() instanceof ServerPlayer player) {
                        lightning.setCause(player);
                    }
                    this.level().addFreshEntity(lightning);
                    //this.discard();
                }
            }
        }
    }

}
