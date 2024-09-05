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
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;

import java.util.Random;

public class FireBottle extends ThrowableItemProjectile implements ItemSupplier{

    int explosionTick = 3;
    boolean exploding = false;

    public FireBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public FireBottle(EntityType<? extends ThrowableItemProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.FLAME_IN_A_BOTTLE.get();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.05;
    }

    @Override
    public void tick() {
        super.tick();
        if(exploding){
            explosion();
            this.setDeltaMovement(0,0,0);
            explosionTick--;
        }
        if(explosionTick<=0){
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        //this.explosion();
        this.exploding = true;
        this.setDeltaMovement(0,0,0);
        //this.discard();
    }

    public void explosion(){

        this.playSound(SoundEvents.SPLASH_POTION_BREAK);
        for(LivingEntity livingentity : level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0,4.0,4.0))) {
            if(((livingentity instanceof Player ||livingentity!=this.getOwner())&&!(this.getOwner().isAlliedTo(livingentity)))) {
                livingentity.igniteForTicks(60);
                if(livingentity.hurtTime<=0&&!livingentity.fireImmune()) {
                    livingentity.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 2.5f);
                }
            }

        }

        int zSpread = 2;
        int ySpread = 2;

        double x0 = this.position().x();
        double y0 = this.position().y();
        double z0 = this.position().z();

        for(int i = 0; i<=24; i++) {
            for(int j = 0;  j<=zSpread; j++) {
                for(int k = -ySpread; k<=ySpread; k++) {
                    double a=  Math.PI/12;
                    double d = j;
                    float xVar = (float) (d*Math.sin(i*a));
                    float yVar = k;
                    float zVar = (float) (d*Math.cos(i*a));;
                    int x= Math.round((float)x0+xVar);
                    int z= Math.round((float)z0+zVar);
                    int y = Math.round((float)y0+yVar);

                    BlockPos blockPos = new BlockPos(x,y,z);
                    if(!this.level().isClientSide()) {
                        if(!this.isUnderWater()) {
                            if (new Random().nextFloat() <= 0.15f) {
                                if (level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)) {
                                    level().destroyBlock(blockPos, true, this.getOwner());
                                    level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
                                }

                                if (BaseFireBlock.canBePlacedAt(level(), blockPos, Direction.getNearest(x0, y0, z0))) {
                                    BlockState blockstate1 = BaseFireBlock.getState(level(), blockPos);
                                    level().setBlock(blockPos, blockstate1, 11);
                                    level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
                                }
                            }
                        }
                    }
                    if(level().isClientSide()) {
                        for (int n = 0; n <= 1; n++) {
                            double xVar1 = 0.5*Math.sin(i * a);
                            float yVar1 = k / 2;
                            double zVar1 = 0.5*Math.cos(i * a);
                            level().addParticle(getExplosionParticleI(),
                                    x0 + 0.6 * (1 + 2.5 * n) * xVar1 + 0.75 * (random.nextFloat() - 0.5),
                                    y0 + 0.05 * (1 + 2.5 * n) * yVar1 + 0.5 * (random.nextFloat() - 0.5),
                                    z0 + 0.6 * (1 + 2.5 * n) * zVar1 + 0.75 * (random.nextFloat() - 0.5),
                                    0.25 * xVar1 + 0.25 * (random.nextFloat() - 0.5),
                                    0.1*random.nextFloat()+0.025f * yVar1 + 0.05 * (random.nextFloat() - 0.5),
                                    0.25 * zVar1 + 0.25 * (random.nextFloat() - 0.5));
                            level().addParticle(getExplosionParticleII(),
                                    x0 + 0.5 * (1 + 2.5 * n) * xVar1 + 0.75 * (random.nextFloat() - 0.5),
                                    y0 + 0.15 * (1 + 2.5 * n) * yVar1 + 0.25 * (random.nextFloat() - 0.5),
                                    z0 + 0.5 * (1 + 2.5 * n) * zVar1 + 0.75 * (random.nextFloat() - 0.5),
                                    0.25 * xVar1 + 0.25 * (random.nextFloat() - 0.5),
                                    0.15f * yVar1 + 0.05 * (random.nextFloat() - 0.5),
                                    0.25 * zVar1 + 0.25 * (random.nextFloat() - 0.5));

                        }
                    }
                }
            }
        }
    }
    public ParticleOptions getExplosionParticleI(){
        return ParticleTypes.FLAME;
    }
    public ParticleOptions getExplosionParticleII(){
        return ParticleTypes.EFFECT;
    }
}
