package github.nitespring.alchemistarsenal.common.entity.projectile.throwable;

import github.nitespring.alchemistarsenal.core.init.ItemInit;
import github.nitespring.alchemistarsenal.core.tags.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;

public class ExplosiveBottle extends ThrowableItemProjectile implements ItemSupplier{
    public ExplosiveBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ExplosiveBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public ExplosiveBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.UNSTABLE_CONCOCTION.get();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.05;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().explode(this,this.position().x(),this.position().y(),this.position().z(),3.0f,this.isOnFire(), Level.ExplosionInteraction.TNT);
        }
        this.discard();
    }


}
