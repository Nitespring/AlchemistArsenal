package github.nitespring.alchemistarsenal.core.init;

import com.mojang.serialization.Codec;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class DataComponentInit {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE.key(),AlchemistArsenal.MODID);
    public static final RegistryObject<DataComponentType<Integer>> CHARGE = DATA_COMPONENTS.register(
            "charge",
            () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build()
    );
    public static final RegistryObject<DataComponentType<ChargedProjectiles>> CHARGED_PROJECTILES2 = DATA_COMPONENTS.register(
            "charged_projectiles2",
            () -> DataComponentType.<ChargedProjectiles>builder().persistent(ChargedProjectiles.CODEC).networkSynchronized(ChargedProjectiles.STREAM_CODEC).cacheEncoding().build()
    );
    public static final RegistryObject<DataComponentType<ChargedProjectiles>> CHARGED_PROJECTILES3 = DATA_COMPONENTS.register(
            "charged_projectiles3",
            () -> DataComponentType.<ChargedProjectiles>builder().persistent(ChargedProjectiles.CODEC).networkSynchronized(ChargedProjectiles.STREAM_CODEC).cacheEncoding().build()
    );

}
