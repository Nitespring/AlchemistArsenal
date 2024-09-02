package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
            AlchemistArsenal.MODID);



    //Special Weapons
    public static final DeferredHolder<Item, CrossbowItem> AUTOMATIC_CROSSBOW = ITEMS.register("automatic_crossbow",
            ()->new CrossbowItem(new Item.Properties().stacksTo(1).durability(512)));


}
