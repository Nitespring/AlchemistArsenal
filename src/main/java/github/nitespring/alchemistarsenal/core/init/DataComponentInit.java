package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentInit {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(AlchemistArsenal.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGE = DATA_COMPONENTS.registerComponentType(
            "charge",
            builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ChargedProjectiles>> CHARGED_PROJECTILES2 = DATA_COMPONENTS.registerComponentType(
            "charged_projectiles2",
            builder -> builder.persistent(ChargedProjectiles.CODEC).networkSynchronized(ChargedProjectiles.STREAM_CODEC).cacheEncoding()
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ChargedProjectiles>> CHARGED_PROJECTILES3 = DATA_COMPONENTS.registerComponentType(
            "charged_projectiles3",
            builder -> builder.persistent(ChargedProjectiles.CODEC).networkSynchronized(ChargedProjectiles.STREAM_CODEC).cacheEncoding()
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceLocation>> CUSTOM_WINGS_TEXTURE = DATA_COMPONENTS.registerComponentType(
            "custom_wings_texture",
            builder -> builder.persistent(ResourceLocation.CODEC).networkSynchronized(ResourceLocation.STREAM_CODEC)
    );


}
