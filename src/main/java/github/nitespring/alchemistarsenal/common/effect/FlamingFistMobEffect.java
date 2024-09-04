package github.nitespring.alchemistarsenal.common.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlamingFistMobEffect extends MobEffect {


    public FlamingFistMobEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entityIn, int id) {
        ParticleOptions flame = ParticleTypes.FLAME;

        float width = entityIn.getBbWidth();
        float height = entityIn.getBbHeight();
        Vec3 pos = new Vec3(entityIn.position().x(), entityIn.position().y(), entityIn.position().z());
        Level world = entityIn.level();

        RandomSource rng = entityIn.getRandom();

        for (int i = 0; i < 1; ++i) {

            Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                    rng.nextDouble() * width - width / 2);
            if(world instanceof ServerLevel) {
                ((ServerLevel) world).sendParticles( flame, pos.x+0.5*off.x, pos.y+0.8+0.5*off.y, pos.z+0.5*off.z, 1,  0.05*off.x, 0.05*off.y + 0.2D, 0.05*off.z, 0.05);

            }
        }


        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int a, int b) {
        return true;
        /*int k = 4 >> b;
        if (k > 0) {
            return a % k == 0;
        } else {
            return true;
        }*/
    }
}
