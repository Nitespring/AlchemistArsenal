package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.effect.FlamingFistMobEffect;
import github.nitespring.alchemistarsenal.common.effect.SimpleMobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class MobEffectInit {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,
            AlchemistArsenal.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION,
            AlchemistArsenal.MODID);

    public static final RegistryObject<MobEffect> FLAMING_FIST = MOB_EFFECTS.register("flaming_fist",
            () -> new FlamingFistMobEffect(MobEffectCategory.BENEFICIAL, 16756480));
    public static final RegistryObject< MobEffect> POISONOUS_CLAW = MOB_EFFECTS.register("poisonous_claw",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 5149489));

    public static final RegistryObject< MobEffect> LONG_LEGS = MOB_EFFECTS.register("long_legs",
        () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 15003647)
        .addAttributeModifier(Attributes.STEP_HEIGHT,
                ResourceLocation.withDefaultNamespace("effect.step_height"),
                1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final RegistryObject< MobEffect> LONG_ARMS = MOB_EFFECTS.register("long_arms",
        () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 10647376)
        .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                ResourceLocation.withDefaultNamespace("effect.entity_interaction_range"),
                1.5, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                ResourceLocation.withDefaultNamespace("effect.block_interaction_range"),
                1.5, AttributeModifier.Operation.ADD_VALUE));


    public static final RegistryObject< Potion> TASTY_POTION = POTIONS.register("tasty_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,2000,2)));
    public static final RegistryObject< Potion> LONG_LEGS_POTION = POTIONS.register("long_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS.getHolder().get(),20000)));
    public static final RegistryObject< Potion> LONGER_LONG_LEGS_POTION = POTIONS.register("long_long_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS.getHolder().get(),50000)));
    public static final RegistryObject< Potion> LONGER_LEGS_POTION = POTIONS.register("longer_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS.getHolder().get(),12000,1)));
    public static final RegistryObject< Potion> LONGEST_LEGS_POTION = POTIONS.register("longest_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS.getHolder().get(),4000,2)));
    public static final RegistryObject< Potion> LONG_ARMS_POTION = POTIONS.register("long_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS.getHolder().get(),20000)));
    public static final RegistryObject< Potion> LONGER_LONG_ARMS_POTION = POTIONS.register("long_long_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS.getHolder().get(),50000)));
    public static final RegistryObject< Potion> LONGER_ARMS_POTION = POTIONS.register("longer_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS.getHolder().get(),12000,1)));
    public static final RegistryObject< Potion> LONGEST_ARMS_POTION = POTIONS.register("longest_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS.getHolder().get(),4000,2)));


}
