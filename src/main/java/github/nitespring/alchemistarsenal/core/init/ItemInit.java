package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.entity.projectile.BouncyArrow;
import github.nitespring.alchemistarsenal.common.item.arrows.*;
import github.nitespring.alchemistarsenal.common.item.weapons.AutomaticCrossbow;
import github.nitespring.alchemistarsenal.common.item.weapons.CrossbowScythe;
import github.nitespring.alchemistarsenal.common.item.weapons.RepeatingCrossbow;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
            AlchemistArsenal.MODID);


    public static final DeferredHolder<Item, AutomaticCrossbow> AUTOMATIC_CROSSBOW = ITEMS.register("automatic_crossbow",
            ()->new AutomaticCrossbow(new Item.Properties().stacksTo(1).durability(512)));
    public static final DeferredHolder<Item, RepeatingCrossbow> REPEATING_CROSSBOW = ITEMS.register("repeating_crossbow",
            ()->new RepeatingCrossbow(new Item.Properties().stacksTo(1).durability(512)));

    public static final DeferredHolder<Item, CrossbowItem> CROSSBOW_SCYTHE = ITEMS.register("crossbow_scythe",
            ()->new CrossbowScythe(Tiers.IRON, 4,-2.8f,new Item.Properties().stacksTo(1).durability(512).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));
    public static final DeferredHolder<Item, ArrowItem> DRAGON_ARROW = ITEMS.register("dragon_arrow",
            ()->new DragonArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, BouncyArrowItem> BOUNCY_ARROW = ITEMS.register("bouncy_arrow",
            ()->new BouncyArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ArrowItem> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
            ()->new LightningArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, InfernalArrowItem> INFERNAL_ARROW = ITEMS.register("infernal_arrow",
            ()->new InfernalArrowItem(new Item.Properties()));
    public static final DeferredHolder<Item, ExplosiveArrowItem> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            ()->new ExplosiveArrowItem(new Item.Properties()));


}
