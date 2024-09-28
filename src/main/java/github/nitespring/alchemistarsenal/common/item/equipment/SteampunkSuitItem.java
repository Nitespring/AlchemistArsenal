package github.nitespring.alchemistarsenal.common.item.equipment;

import com.google.common.base.Suppliers;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SteampunkSuitItem extends ArmorItem{
    public static final ResourceLocation TEXTURE_LOCATION_OUTER_LAYER = ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "textures/equipment/steampunk_suit.png");
    public static final ResourceLocation TEXTURE_LOCATION_INNER_LAYER = ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "textures/equipment/steampunk_leggings.png");
    private final Supplier<ItemAttributeModifiers> customModifiers;
    public SteampunkSuitItem(float speedModifier, float jumpModifier, float stepHeightModifier,
                             float miningEfficiencyModifier, float blockBreakSpeed,
                             float fallDamageModifier, float gravityModifier, float safeFallHeightModifier,
                             Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
        this.customModifiers = Suppliers.memoize(
                () -> {
                    int defense = material.value().getDefense(type);
                    float toughness = material.value().toughness();
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                    ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR, new AttributeModifier(resourcelocation, (double)defense, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, (double)toughness, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                    float knockbackResistance = material.value().knockbackResistance();
                    if (knockbackResistance > 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.KNOCKBACK_RESISTANCE,
                                new AttributeModifier(resourcelocation, (double)knockbackResistance, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (speedModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.MOVEMENT_SPEED,
                                new AttributeModifier(resourcelocation, speedModifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                equipmentslotgroup
                        );
                    }
                    if (jumpModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.JUMP_STRENGTH,
                                new AttributeModifier(resourcelocation, (double)jumpModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (stepHeightModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.STEP_HEIGHT,
                                new AttributeModifier(resourcelocation, (double)stepHeightModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (miningEfficiencyModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.MINING_EFFICIENCY,
                                new AttributeModifier(resourcelocation, (double)miningEfficiencyModifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                equipmentslotgroup
                        );
                    }
                    if (blockBreakSpeed != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.BLOCK_BREAK_SPEED,
                                new AttributeModifier(resourcelocation, (double)blockBreakSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                equipmentslotgroup
                        );
                    }
                    if (gravityModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.GRAVITY,
                                new AttributeModifier(resourcelocation, (double)gravityModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (fallDamageModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.FALL_DAMAGE_MULTIPLIER,
                                new AttributeModifier(resourcelocation, (double)fallDamageModifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                equipmentslotgroup
                        );
                    }
                    if (safeFallHeightModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.SAFE_FALL_DISTANCE,
                                new AttributeModifier(resourcelocation, (double)safeFallHeightModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }



                    return itemattributemodifiers$builder.build();
                }
        );
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.customModifiers.get();
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return slot == EquipmentSlot.LEGS ? TEXTURE_LOCATION_INNER_LAYER : TEXTURE_LOCATION_OUTER_LAYER;
    }

    public static final IClientItemExtensions STEAMPUNK_ARMOUR_CLIENT_EXTENSIONS = new IClientItemExtensions(){
        //private static final ArmorRender INSTANCE = new ArmorRender();

        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : SteampunkSuitModel.LAYER_LOCATION);

            if(slot!=EquipmentSlot.LEGS){
                SteampunkSuitModel<LivingEntity> aModel = new SteampunkSuitModel<LivingEntity>(root);
                //aModel.setupAnim(living, 0, 0, 0, 0, 0);
                return aModel;
            }else{
                HumanoidArmorModel<LivingEntity> aModel = new HumanoidArmorModel<LivingEntity>(root);
                return aModel;
            }

        }


    };

}
