package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.effect.FlamingFistMobEffect;
import github.nitespring.alchemistarsenal.common.effect.SimpleMobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class MobEffectInit {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT,
            AlchemistArsenal.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION,
            AlchemistArsenal.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> FLAMING_FIST = MOB_EFFECTS.register("flaming_fist",
            () -> new FlamingFistMobEffect(MobEffectCategory.BENEFICIAL, 16756480));
    public static final DeferredHolder<MobEffect, MobEffect> POISONOUS_CLAW = MOB_EFFECTS.register("poisonous_claw",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 5149489));

    public static final DeferredHolder<MobEffect, MobEffect> LONG_LEGS = MOB_EFFECTS.register("long_legs",
        () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 15003647)
        .addAttributeModifier(Attributes.STEP_HEIGHT,
                ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.step_height"),
                1.0, AttributeModifier.Operation.ADD_VALUE));
    public static final DeferredHolder<MobEffect, MobEffect> LONG_ARMS = MOB_EFFECTS.register("long_arms",
        () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 10647376)
        .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.entity_interaction_range"),
                1.5, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.block_interaction_range"),
                1.5, AttributeModifier.Operation.ADD_VALUE));
    public static final DeferredHolder<MobEffect, MobEffect> ENGORGEMENT = MOB_EFFECTS.register("engorgement",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 9266590)
                    .addAttributeModifier(Attributes.SCALE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up"),
                            1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_speed"),
                            0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_jump"),
                            0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.GRAVITY,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_gravity"),
                            0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_step"),
                            0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_safe_fall"),
                            1.0f, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_up_knockback"),
                            0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );
    public static final DeferredHolder<MobEffect, MobEffect> SHRINKAGE = MOB_EFFECTS.register("shrinkage",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 16751439)
                    .addAttributeModifier(Attributes.SCALE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down"),
                            -0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                          ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_speed"),
                            -0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.GRAVITY,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_gravity"),
                            -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_step"),
                            -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_safe_fall"),
                            -0.5f, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_jump"),
                            -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"effect.scale_down_knockback"),
                            -0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );


    public static final DeferredHolder<Potion, Potion> TASTY_POTION = POTIONS.register("tasty_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,2000,2)));
    public static final DeferredHolder<Potion, Potion> UNTASTY_POTION = POTIONS.register("untasty_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,2000,0)));
    public static final DeferredHolder<Potion, Potion> LONG_LEGS_POTION = POTIONS.register("long_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS,20000)));
    public static final DeferredHolder<Potion, Potion> LONGER_LONG_LEGS_POTION = POTIONS.register("long_long_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS,50000)));
    public static final DeferredHolder<Potion, Potion> LONGER_LEGS_POTION = POTIONS.register("longer_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS,12000,1)));
    public static final DeferredHolder<Potion, Potion> LONGEST_LEGS_POTION = POTIONS.register("longest_legs",
            () -> new Potion(new MobEffectInstance(LONG_LEGS,4000,2)));
    public static final DeferredHolder<Potion, Potion> LONG_ARMS_POTION = POTIONS.register("long_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS,20000)));
    public static final DeferredHolder<Potion, Potion> LONGER_LONG_ARMS_POTION = POTIONS.register("long_long_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS,50000)));
    public static final DeferredHolder<Potion, Potion> LONGER_ARMS_POTION = POTIONS.register("longer_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS,12000,1)));
    public static final DeferredHolder<Potion, Potion> LONGEST_ARMS_POTION = POTIONS.register("longest_arms",
            () -> new Potion(new MobEffectInstance(LONG_ARMS,4000,2)));
    public static final DeferredHolder<Potion, Potion> ENGORGEMENT_POTION = POTIONS.register("engorgement_minor",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,8000,0)));
    public static final DeferredHolder<Potion, Potion> ENGORGEMENT_II_POTION = POTIONS.register("engorgement",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,6000,1)));
    public static final DeferredHolder<Potion, Potion> ENGORGEMENT_III_POTION = POTIONS.register("engorgement_maior",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,4000,2)));
    public static final DeferredHolder<Potion, Potion> LONG_ENGORGEMENT_POTION = POTIONS.register("long_engorgement_minor",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,20000,0)));
    public static final DeferredHolder<Potion, Potion> LONG_ENGORGEMENT_II_POTION = POTIONS.register("long_engorgement",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,15000,1)));
    public static final DeferredHolder<Potion, Potion> LONG_ENGORGEMENT_III_POTION = POTIONS.register("long_engorgement_maior",
            () -> new Potion(new MobEffectInstance(ENGORGEMENT,10000,2)));
    public static final DeferredHolder<Potion, Potion> SHRINKAGE_POTION = POTIONS.register("shrinkage_minor",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,8000,0)));
    public static final DeferredHolder<Potion, Potion> SHRINKAGE_II_POTION = POTIONS.register("shrinkage",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,6000,1)));
    public static final DeferredHolder<Potion, Potion> SHRINKAGE_III_POTION = POTIONS.register("shrinkage_maior",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,4000,2)));
    public static final DeferredHolder<Potion, Potion> LONG_SHRINKAGE_POTION = POTIONS.register("long_shrinkage_minor",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,20000,0)));
    public static final DeferredHolder<Potion, Potion> LONG_SHRINKAGE_II_POTION = POTIONS.register("long_shrinkage",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,15000,1)));
    public static final DeferredHolder<Potion, Potion> LONG_SHRINKAGE_III_POTION = POTIONS.register("long_shrinkage_maior",
            () -> new Potion(new MobEffectInstance(SHRINKAGE,10000,2)));

}
