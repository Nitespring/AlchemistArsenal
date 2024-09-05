package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FragmentingArrow extends AbstractArrow {

    protected int hitBlocks;
    public static final int MAX_HIT_BLOCKS = 3;

    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    public FragmentingArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }


    @Override
    protected ItemStack getPickupItem() {

        return ItemInit.AMETHYST_ARROW.get().getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemInit.AMETHYST_ARROW.get().getDefaultInstance();
    }


    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        this.discard();
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
