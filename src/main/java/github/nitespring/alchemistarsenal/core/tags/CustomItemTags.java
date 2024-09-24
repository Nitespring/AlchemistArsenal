package github.nitespring.alchemistarsenal.core.tags;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class CustomItemTags {

    public static final TagKey<Item> STEAMPUNK_WINGS_FUEL = create("steampunk_wings_fuel");

    private CustomItemTags() {
    }


    private static TagKey<Item> create(String p_203847_) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID , p_203847_));
    }

}
