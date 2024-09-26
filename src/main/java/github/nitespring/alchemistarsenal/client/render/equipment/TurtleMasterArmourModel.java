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

public class TurtleMasterArmourModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "turtle_master_armour"),
            "outer"
    );
    public TurtleMasterArmourModel(ModelPart root) {
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


        PartDefinition body_overlay = body.addOrReplaceChild("body_overlay",
                CubeListBuilder.create().texOffs(16, 32)
                        .addBox(-4.0F, 0.0F, -2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.9F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition shell = body.addOrReplaceChild("shell",
                CubeListBuilder.create().texOffs(0, 48)
                        .addBox(-4.0F, 0.0F, 2.0F, 8.0F,
                                12.0F, 4.0F,
                                new CubeDeformation(1.0F))
                .texOffs(24, 48)
                        .addBox(-4.0F, 0.0F, 2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(1.25F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition skirt = body.addOrReplaceChild("skirt",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 14.0F, 0.0F));
        PartDefinition skirt_right = skirt.addOrReplaceChild("skirt_right",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.25F, 0.0F));
        PartDefinition skirt_right_r1 = skirt_right.addOrReplaceChild("skirt_right_r1",
                CubeListBuilder.create().texOffs(64, 54)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                3.0F, 6.0F, 4.0F,
                                new CubeDeformation(1.25F))
                .texOffs(50, 54)
                        .addBox(-2.0F, -2.0F, -2.0F,
                                3.0F, 6.0F, 4.0F,
                                new CubeDeformation(1.0F)),
                PartPose.offsetAndRotation(-2.6F, -2.0F, 0.0F,
                        0.0F, 0.0F, 0.1309F));
        PartDefinition skirt_left = skirt.addOrReplaceChild("skirt_left",
                CubeListBuilder.create(), PartPose.offset(0.0F, 0.25F, 0.0F));
        PartDefinition skirt_left_r1 = skirt_left.addOrReplaceChild("skirt_left_r1",
                CubeListBuilder.create().texOffs(64, 54).mirror()
                        .addBox(-1.0F, -2.0F, -2.0F,
                                3.0F, 6.0F, 4.0F,
                                new CubeDeformation(1.25F)).mirror(false)
                .texOffs(50, 54).mirror()
                        .addBox(-1.0F, -2.0F, -2.0F,
                                3.0F, 6.0F, 4.0F,
                                new CubeDeformation(1.0F)).mirror(false),
                PartPose.offsetAndRotation(2.6F, -2.0F, 0.0F,
                        0.0F, 0.0F, -0.1309F));
        PartDefinition right_arm_overlay = right_arm.addOrReplaceChild("right_arm_overlay",
                CubeListBuilder.create().texOffs(40, 32)
                        .addBox(-3.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(1.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition left_arm_overlay = left_arm.addOrReplaceChild("left_arm_overlay",
                CubeListBuilder.create().texOffs(40, 32).mirror()
                        .addBox(-1.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(1.0F)).mirror(false),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition right_leg_overlay = right_leg.addOrReplaceChild("right_leg_overlay",
                CubeListBuilder.create().texOffs(0, 32)
                        .addBox(-2.0F, 0.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.9F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition left_leg_overlay = left_leg.addOrReplaceChild("left_leg_overlay",
                CubeListBuilder.create().texOffs(0, 32).mirror()
                        .addBox(-2.0F, 0.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.9F)).mirror(false),
                PartPose.offset(0.0F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 128, 64);

    }



}
