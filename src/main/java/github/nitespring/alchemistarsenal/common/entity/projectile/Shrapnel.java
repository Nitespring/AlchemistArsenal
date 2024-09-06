package github.nitespring.alchemistarsenal.common.entity.projectile;

import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Shrapnel extends AbstractHurtingProjectile {
    protected int lifeTicks = 0;
    protected int maxLifeTicks = 7;
    protected float damage = 6.0f;
    protected boolean fire = false;

    public Shrapnel(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Shrapnel(double pX, double pY, double pZ, Level pLevel) {
        super(EntityInit.SHRAPNEL.get(), pX, pY, pZ, pLevel);
    }

    public Shrapnel(double pX, double pY, double pZ, Vec3 pMovement, Level pLevel) {
        super(EntityInit.SHRAPNEL.get(), pX, pY, pZ, pMovement, pLevel);
    }

    public Shrapnel(LivingEntity pOwner, Vec3 pMovement, Level pLevel) {
        super(EntityInit.SHRAPNEL.get(), pOwner, pMovement, pLevel);
    }
    public Shrapnel(LivingEntity pOwner, Level pLevel) {
        super(EntityInit.SHRAPNEL.get(), pLevel);
        this.setOwner(pOwner);
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();

        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
        }
        if(++lifeTicks>=maxLifeTicks){
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.level().addParticle(
                new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AMETHYST_CLUSTER.defaultBlockState()),
                this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D),
                0.0, 0.0, 0.0);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if(this.getOwner() instanceof LivingEntity livingEntity) {
            result.getEntity().hurt(this.level().damageSources().mobProjectile(this, livingEntity), this.damage);
        }else{
            result.getEntity().hurt(this.level().damageSources().mobProjectile(this, null), this.damage);
        }
        if(fire){
            result.getEntity().igniteForSeconds(4);
        }
        playSound(SoundEvents.GLASS_BREAK);
        this.discard();
    }

    @Override
    public boolean isOnFire() {
        return super.isOnFire();
    }
    @Override
    public boolean fireImmune() {
        return true;
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        //playSound(SoundEvents.GLASS_BREAK);
        playSound(SoundEvents.GLASS_BREAK);
        this.discard();
    }

    @Override
    public boolean displayFireAnimation() {
        return this.isOnFire();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {

        return new ClientboundAddEntityPacket(this,e);
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }

}
