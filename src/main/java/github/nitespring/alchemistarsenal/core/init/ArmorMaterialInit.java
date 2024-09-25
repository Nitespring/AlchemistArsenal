package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ArmorMaterialInit {
    public static final DeferredRegister<ArmorMaterial> MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL,
            AlchemistArsenal.MODID);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TURTLE_MASTER = register(
            "turtle_master",
                    Util.make(new EnumMap<>(ArmorItem.Type.class), p_323381_ -> {
                                p_323381_.put(ArmorItem.Type.BOOTS, 5);
                                p_323381_.put(ArmorItem.Type.LEGGINGS, 8);
                                p_323381_.put(ArmorItem.Type.CHESTPLATE, 10);
                                p_323381_.put(ArmorItem.Type.HELMET, 5);
                                p_323381_.put(ArmorItem.Type.BODY, 13);
                            }),
                    12,
                    SoundEvents.ARMOR_EQUIP_NETHERITE,
                    9.0F,
                    0.4F,
                    () -> Ingredient.of(Items.NETHERITE_INGOT)

    );
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STEAMPUNK = register(
            "steampunk",
            Util.make(new EnumMap<>(ArmorItem.Type.class), p_323381_ -> {
                p_323381_.put(ArmorItem.Type.BOOTS, 3);
                p_323381_.put(ArmorItem.Type.LEGGINGS, 6);
                p_323381_.put(ArmorItem.Type.CHESTPLATE, 8);
                p_323381_.put(ArmorItem.Type.HELMET, 3);
                p_323381_.put(ArmorItem.Type.BODY, 11);
            }),
            14,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            5.0F,
            0.1F,
            () -> Ingredient.of(Items.NETHERITE_INGOT)

    );
    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String pName,
            EnumMap<ArmorItem.Type, Integer> pDefense,
            int pEnchantmentValue,
            Holder<SoundEvent> pEquipSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngredient
    ) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(pName)));
        return register(pName, pDefense, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngredient, list);
    }

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String pName,
            EnumMap<ArmorItem.Type, Integer> pDefense,
            int pEnchantmentValue,
            Holder<SoundEvent> pEquipSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngridient,
            List<ArmorMaterial.Layer> pLayers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, pDefense.get(armoritem$type));
        }

        return MATERIALS.register(pName,
                () -> new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
        );
    }



}
