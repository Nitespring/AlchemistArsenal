package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.init.MobEffectInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = AlchemistArsenal.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {


    @SubscribeEvent
    public static void applyDamageEvent(final LivingDamageEvent event){
        DamageSource source = event.getSource();
        //System.out.println(event.getAmount());
        //System.out.println(event.getEntity().getType());
        //System.out.println(event.getEntity().getType().getTags().count());
        LivingEntity entity = event.getEntity();
        if(event.getSource().getEntity() instanceof LivingEntity attacker && event.getSource().isDirect()){
            if(attacker.hasEffect(MobEffectInit.FLAMING_FIST.getHolder().get())){
                if(!entity.fireImmune()){
                    entity.igniteForTicks(120);
                }
            }
            if(attacker.hasEffect(MobEffectInit.POISONOUS_CLAW.getHolder().get())){
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON,100));
            }
        }


    }


}
