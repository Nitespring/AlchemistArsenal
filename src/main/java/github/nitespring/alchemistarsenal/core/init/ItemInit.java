package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.item.arrows.*;
import github.nitespring.alchemistarsenal.common.item.concoctions.*;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkChestplateItem;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkSuitItem;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkWingsItem;
import github.nitespring.alchemistarsenal.common.item.weapons.AutomaticCrossbow;
import github.nitespring.alchemistarsenal.common.item.weapons.CrossbowScythe;
import github.nitespring.alchemistarsenal.common.item.weapons.RepeatingCrossbow;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
            AlchemistArsenal.MODID);


    public static final DeferredHolder<Item, SteampunkWingsItem> STEAMPUNK_WINGS = ITEMS.register("steampunk_wings",
            ()->new SteampunkWingsItem(new Item.Properties().stacksTo(1).durability(1080)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_HELMET = ITEMS.register("steampunk_helmet",
            ()->new SteampunkChestplateItem(ArmorMaterials.NETHERITE, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1).durability(1080)));
    public static final DeferredHolder<Item, SteampunkChestplateItem> STEAMPUNK_CHESTPLATE = ITEMS.register("steampunk_chestplate",
            ()->new SteampunkChestplateItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1).durability(1080)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_LEGGINGS = ITEMS.register("steampunk_leggings",
            ()->new SteampunkChestplateItem(ArmorMaterials.NETHERITE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1).durability(1080)));
    public static final DeferredHolder<Item, SteampunkSuitItem> STEAMPUNK_BOOTS = ITEMS.register("steampunk_boots",
            ()->new SteampunkChestplateItem(ArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1).durability(1080)));

    public static final DeferredHolder<Item, AutomaticCrossbow> AUTOMATIC_CROSSBOW = ITEMS.register("automatic_crossbow",
            ()->new AutomaticCrossbow(new Item.Properties().stacksTo(1).durability(512)));
    public static final DeferredHolder<Item, RepeatingCrossbow> REPEATING_CROSSBOW = ITEMS.register("repeating_crossbow",
            ()->new RepeatingCrossbow(new Item.Properties().stacksTo(1).durability(360)));

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
