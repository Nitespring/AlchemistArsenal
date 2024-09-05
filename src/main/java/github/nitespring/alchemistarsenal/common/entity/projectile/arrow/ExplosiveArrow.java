package github.nitespring.alchemistarsenal.common.entity.projectile.arrow;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class ExplosiveArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public ExplosiveArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ExplosiveArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.EXPLOSIVE_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public ExplosiveArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(EntityInit.EXPLOSIVE_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
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
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().explode(this,this.position().x(),this.position().y(),this.position().z(),1.5f,this.isOnFire(), Level.ExplosionInteraction.TNT);
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
