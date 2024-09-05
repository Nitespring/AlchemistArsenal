package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
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
        if(this.level().isThundering()) {
            if(new Random().nextFloat()<=0.3f) {
                if (!this.level().isClientSide) {

                    LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                    lightning.setPos(this.position());
                    if (this.getOwner() instanceof ServerPlayer player) {
                        lightning.setCause(player);
                    }


                    this.level().addFreshEntity(lightning);
                    //this.discard();
                }
            }
        }
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
