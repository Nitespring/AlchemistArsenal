package github.nitespring.alchemistarsenal.common.item.equipment;

import com.google.common.base.Suppliers;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SteampunkSuitItem extends ArmorItem{
    public static final ResourceLocation TEXTURE_LOCATION_OUTER_LAYER = ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "textures/equipment/steampunk_suit.png");
    public static final ResourceLocation TEXTURE_LOCATION_INNER_LAYER = ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "textures/equipment/steampunk_leggings.png");

    public SteampunkSuitItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return slot == EquipmentSlot.LEGS ? TEXTURE_LOCATION_INNER_LAYER : TEXTURE_LOCATION_OUTER_LAYER;
    }

    public static final IClientItemExtensions STEAMPUNK_ARMOUR_EXTENSION = new IClientItemExtensions(){
        //private static final ArmorRender INSTANCE = new ArmorRender();

        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : SteampunkSuitModel.LAYER_LOCATION);

            SteampunkSuitModel aModel = new SteampunkSuitModel(root);
            return aModel;
        }


    };

}
