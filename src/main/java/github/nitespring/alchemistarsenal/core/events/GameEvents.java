package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.item.concoctions.Fertilizer;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import github.nitespring.alchemistarsenal.core.init.MobEffectInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.brewing.BrewingRecipe;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = AlchemistArsenal.MODID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {


    public static void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(
                () -> {
                    DispenserBlock.registerProjectileBehavior(ItemInit.DRAGON_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.BOUNCY_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.EXPLOSIVE_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.AMETHYST_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.LIGHTNING_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.WIND_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.INFERNAL_ARROW.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.UNSTABLE_CONCOCTION.get());
                    DispenserBlock.registerProjectileBehavior(ItemInit.FLAME_IN_A_BOTTLE.get());
                    DispenserBlock.registerBehavior(ItemInit.FERTILIZER.get(), Fertilizer.FERTILIZER_DISPENSER_BEHAVIOUR);
                    DispenserBlock.registerBehavior(ItemInit.ENHANCED_FERTILIZER.get(), Fertilizer.ENCHANCED_FERTILIZER_DISPENSER_BEHAVIOUR);

                }
        );
    }


    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        DispenserBlock.registerProjectileBehavior(ItemInit.AMETHYST_ARROW.get().asItem());
        // Gets the builder to add recipes to
        PotionBrewing.Builder builder = event.getBuilder();

        // Will add brewing recipes for all container potions (e.g. potion, splash potion, lingering potion)
        builder.addMix(
                Potions.AWKWARD,
                ItemInit.ENHANCED_FERTILIZER.get(),
                MobEffectInit.TASTY_POTION
        );
        builder.addMix(
                MobEffectInit.TASTY_POTION,
                Items.COCOA_BEANS,
                MobEffectInit.LONG_ARMS_POTION
        );
        builder.addMix(
                MobEffectInit.LONG_ARMS_POTION,
                Items.REDSTONE,
                MobEffectInit.LONGER_LONG_ARMS_POTION
        );
        builder.addMix(
                MobEffectInit.LONG_ARMS_POTION,
                Items.GLOWSTONE_DUST,
                MobEffectInit.LONGER_ARMS_POTION
        );
        builder.addMix(
                MobEffectInit.LONGER_ARMS_POTION,
                Items.GLOWSTONE,
                MobEffectInit.LONGEST_ARMS_POTION
        );
        builder.addMix(
                MobEffectInit.TASTY_POTION,
                Items.SUGAR,
                MobEffectInit.LONG_LEGS_POTION
        );
        builder.addMix(
                MobEffectInit.LONG_LEGS_POTION,
                Items.REDSTONE,
                MobEffectInit.LONGER_LONG_LEGS_POTION
        );
        builder.addMix(
                MobEffectInit.LONG_LEGS_POTION,
                Items.GLOWSTONE_DUST,
                MobEffectInit.LONGER_LEGS_POTION
        );
        builder.addMix(
                MobEffectInit.LONGER_LEGS_POTION,
                Items.GLOWSTONE,
                MobEffectInit.LONGEST_LEGS_POTION
        );
    }





}
