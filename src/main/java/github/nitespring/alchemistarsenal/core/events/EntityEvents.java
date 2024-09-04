package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.init.MobEffectInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = AlchemistArsenal.MODID, bus = EventBusSubscriber.Bus.GAME)
public class EntityEvents {


    @SubscribeEvent
    public static void applyDamageEvent(final LivingIncomingDamageEvent event){
        DamageSource source = event.getSource();
        //System.out.println(event.getAmount());
        //System.out.println(event.getEntity().getType());
        //System.out.println(event.getEntity().getType().getTags().count());
        LivingEntity entity = event.getEntity();
        if(event.getSource().getEntity() instanceof LivingEntity attacker && event.getSource().isDirect()){
            if(attacker.hasEffect(MobEffectInit.FLAMING_FIST)){
                if(!entity.fireImmune()){
                    entity.igniteForTicks(120);
                }
            }
            if(attacker.hasEffect(MobEffectInit.POISONOUS_CLAW)){
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON,100));
            }
        }


    }


}
