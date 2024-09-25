package github.nitespring.alchemistarsenal.client.render.equipment;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class SteampunkSuitModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "steampunk_suit"),
            "outer"
    );
    public SteampunkSuitModel(ModelPart root) {
        super(root);
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.75F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");
        PartDefinition hat = partdefinition.getChild("hat");
        PartDefinition body = partdefinition.getChild("body");
        PartDefinition right_arm = partdefinition.getChild("right_arm");
        PartDefinition left_arm = partdefinition.getChild("left_arm");
        PartDefinition right_leg = partdefinition.getChild("right_leg");
        PartDefinition left_leg = partdefinition.getChild("left_leg");


        PartDefinition top_hat = head.addOrReplaceChild("top_hat", CubeListBuilder.create().texOffs(64, 46).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(96, 30).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                .texOffs(88, 18).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.25F, 0.0F));

        PartDefinition body_overlay = body.addOrReplaceChild("body_overlay", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.9F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition skirt = body.addOrReplaceChild("skirt", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition skirt_right = skirt.addOrReplaceChild("skirt_right", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.0F));

        PartDefinition skirt_right_r1 = skirt_right.addOrReplaceChild("skirt_right_r1", CubeListBuilder.create().texOffs(114, 0).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-2.6F, -2.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition skirt_left = skirt.addOrReplaceChild("skirt_left", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.0F));

        PartDefinition skirt_left_r1 = skirt_left.addOrReplaceChild("skirt_left_r1", CubeListBuilder.create().texOffs(114, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(2.6F, -2.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition right_arm_overlay = right_arm.addOrReplaceChild("right_arm_overlay", CubeListBuilder.create().texOffs(40, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm_overlay = left_arm.addOrReplaceChild("left_arm_overlay", CubeListBuilder.create().texOffs(40, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg_overlay = right_leg.addOrReplaceChild("right_leg_overlay", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg_overlay = left_leg.addOrReplaceChild("left_leg_overlay", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);

    }



}
