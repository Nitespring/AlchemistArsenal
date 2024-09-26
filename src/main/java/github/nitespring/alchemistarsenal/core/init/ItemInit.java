package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.item.arrows.*;
import github.nitespring.alchemistarsenal.common.item.concoctions.*;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkChestplateItem;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkSuitItem;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkWingsItem;
import github.nitespring.alchemistarsenal.common.item.equipment.TurtleMasterArmourItem;
import github.nitespring.alchemistarsenal.common.item.weapons.AutomaticCrossbow;
import github.nitespring.alchemistarsenal.common.item.weapons.CrossbowScythe;
import github.nitespring.alchemistarsenal.common.item.weapons.RepeatingCrossbow;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
            AlchemistArsenal.MODID);


    public static final DeferredHolder<Item, SteampunkWingsItem> STEAMPUNK_WINGS = ITEMS.register("steampunk_wings",
            ()->new SteampunkWingsItem(new Item.Properties().stacksTo(1).durability(720).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_HELMET = ITEMS.register("steampunk_helmet",
            ()->new SteampunkSuitItem(
                    0,0,0,
                    2.5f,0,
                    0,0, 0,
                    ArmorMaterialInit.STEAMPUNK, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(1080).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, SteampunkChestplateItem> STEAMPUNK_CHESTPLATE = ITEMS.register("steampunk_chestplate",
            ()->new SteampunkChestplateItem(
                    0,0,0,
                    0,1.5f,
                    0,-0.02f, 0,
                    ArmorMaterialInit.STEAMPUNK, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).durability(1080).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_LEGGINGS = ITEMS.register("steampunk_leggings",
            ()->new SteampunkSuitItem(
                    0.02f,0,0.5f,
                    0,0,
                    0,0, 0,
                    ArmorMaterialInit.STEAMPUNK, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).durability(1080).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_BOOTS = ITEMS.register("steampunk_boots",
            ()->new SteampunkSuitItem(
                    0,0.1f,0,
                    0,0,
                    -0.25f,0, +2.5f,
                    ArmorMaterialInit.STEAMPUNK, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).durability(1080).rarity(Rarity.EPIC)));

    public static final DeferredHolder<Item, TurtleMasterArmourItem> TURTLE_MASTER_HELMET = ITEMS.register("turtle_master_helmet",
            ()->new TurtleMasterArmourItem(
                    -0.01f,0,6.0f, 10.0f,
                    0, 0,
                    ArmorMaterialInit.TURTLE_MASTER, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(1720).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, TurtleMasterArmourItem> TURTLE_MASTER_CHESTPLATE = ITEMS.register("turtle_master_chestplate",
            ()->new TurtleMasterArmourItem(
                    -0.01f,0.01f,6.0f, 0.0f,
                    2.5f, 0,
                    ArmorMaterialInit.TURTLE_MASTER, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).durability(1720).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, TurtleMasterArmourItem> TURTLE_MASTER_LEGGINGS = ITEMS.register("turtle_master_leggings",
            ()->new TurtleMasterArmourItem(
                    0,0,4.0f, 0.0f,
                    0f, 0.1f,
                    ArmorMaterialInit.TURTLE_MASTER, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).durability(1720).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, TurtleMasterArmourItem> TURTLE_MASTER_BOOTS = ITEMS.register("turtle_master_boots",
            ()->new TurtleMasterArmourItem(
                    0,0,4.0f, 0.0f,
                    0f, 0.1f,
                    ArmorMaterialInit.TURTLE_MASTER, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).durability(1720).rarity(Rarity.EPIC)));


    public static final DeferredHolder<Item, AutomaticCrossbow> AUTOMATIC_CROSSBOW = ITEMS.register("automatic_crossbow",
            ()->new AutomaticCrossbow(new Item.Properties().stacksTo(1).durability(512).rarity(Rarity.EPIC)));
    public static final DeferredHolder<Item, RepeatingCrossbow> REPEATING_CROSSBOW = ITEMS.register("repeating_crossbow",
            ()->new RepeatingCrossbow(new Item.Properties().stacksTo(1).durability(360).rarity(Rarity.RARE)));

    public static final DeferredHolder<Item, CrossbowItem> CROSSBOW_SCYTHE = ITEMS.register("crossbow_scythe",
            ()->new CrossbowScythe(Tiers.IRON, 4,-2.8f,new Item.Properties().stacksTo(1).durability(360).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));
    public static final DeferredHolder<Item, ArrowItem> DRAGON_ARROW = ITEMS.register("dragon_arrow",
            ()->new DragonArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> BOUNCY_ARROW = ITEMS.register("bouncy_arrow",
            ()->new BouncyArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
            ()->new LightningArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> INFERNAL_ARROW = ITEMS.register("infernal_arrow",
            ()->new InfernalArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            ()->new ExplosiveArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> AMETHYST_ARROW = ITEMS.register("amethyst_arrow",
            ()->new AmethystArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> WIND_ARROW = ITEMS.register("wind_arrow",
            ()->new WindArrowItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FLAME_IN_A_BOTTLE = ITEMS.register("flame_in_a_bottle",
            ()->new FireBottleItem(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> UNSTABLE_CONCOCTION = ITEMS.register("unstable_concoction",
            ()->new ExplosiveBottleItem(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> INFERNAL_FLASK = ITEMS.register("infernal_flask",
            ()->new FlamingFlask(new Item.Properties().stacksTo(8)));
    public static final DeferredHolder<Item, Item> POISON_FLASK = ITEMS.register("poison_flask",
            ()->new PoisonFlask(new Item.Properties().stacksTo(8)));
    public static final DeferredHolder<Item, Item> FERTILIZER = ITEMS.register("fertilizer",
            ()->new Fertilizer(1,1,0,new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> ENHANCED_FERTILIZER = ITEMS.register("enhanced_fertilizer",
            ()->new Fertilizer(2,3,1,new Item.Properties().stacksTo(16)));


}
