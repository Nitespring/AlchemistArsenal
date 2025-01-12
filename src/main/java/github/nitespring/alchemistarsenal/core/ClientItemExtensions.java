package github.nitespring.alchemistarsenal.core;

import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import github.nitespring.alchemistarsenal.client.render.equipment.TurtleMasterArmourModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class ClientItemExtensions {
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
    public static final IClientItemExtensions TURTLE_ARMOUR_CLIENT_EXTENSIONS = new IClientItemExtensions(){
        //private static final ArmorRender INSTANCE = new ArmorRender();

        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : TurtleMasterArmourModel.LAYER_LOCATION);

            TurtleMasterArmourModel aModel = new TurtleMasterArmourModel(root);
            return aModel;
        }


    };
}
