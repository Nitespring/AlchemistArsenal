package github.nitespring.alchemistarsenal.client.render.equipment;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
    private final ModelPart skirt;
    private final ModelPart cannons;
    public final ModelPart cannon_right;
    public final ModelPart cannon_left;

    public SteampunkSuitModel(ModelPart root) {
        super(root);
        this.skirt = body.getChild("skirt");
        this.cannons = body.getChild("cannons");
        this.cannon_right = cannons.getChild("cannon_right");
        this.cannon_left = cannons.getChild("cannon_left");

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

        PartDefinition cannons = body.addOrReplaceChild("cannons", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cannon_right = cannons.addOrReplaceChild("cannon_right", CubeListBuilder.create().texOffs(88, 0).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.25F))
                .texOffs(64, 0).addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(-0.75F))
                .texOffs(64, 12).addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(112, 2).addBox(0.0F, -6.5F, 4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = cannon_right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(79, 31).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.75F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r2 = cannon_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(82, 36).addBox(-3.0F, -6.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cannon_left = cannons.addOrReplaceChild("cannon_left", CubeListBuilder.create().texOffs(88, 0).mirror().addBox(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.25F)).mirror(false)
                .texOffs(64, 0).mirror().addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(-0.75F)).mirror(false)
                .texOffs(64, 12).mirror().addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(-0.5F)).mirror(false)
                .texOffs(112, 2).mirror().addBox(0.0F, -6.5F, 4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = cannon_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(79, 31).mirror().addBox(-3.0F, -5.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.75F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r4 = cannon_left.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(82, 36).mirror().addBox(-3.0F, -6.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, -0.6109F, 0.0F, 0.0F));

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

    @Override
    public void setupAnim(T entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(entity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        //body.getChild("cannons").copyFrom(this.body);
        //cannon_left.xRot= entity.yHeadRot;
        /*cannon_left.yRot= head.yRot;
        //cannon_right.xRot= head.xRot;
        cannon_right.yRot= cannon_left.yRot;*/
    }

    @Override
    public void copyPropertiesTo(HumanoidModel<T> pModel) {
        super.copyPropertiesTo(pModel);
    }

    @Override
    public void prepareMobModel(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
        super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        float cannonYRot = Math.signum(head.yRot)*Math.min(0.63f,Math.abs(head.yRot));
        float cannonXRot = Math.min(0.24f,Math.signum(head.xRot)*Math.min(0.98f,Math.abs(head.xRot)));
        cannon_left.yRot=cannonYRot;
        cannon_right.yRot=cannonYRot;

        float f11 = 14.0f;
        float f12 = 0.0f;
        if(crouching){
            f11=13f;
            f12=-2.75f;
            cannon_left.xRot=cannonXRot-body.xRot;
            cannon_right.xRot=cannonXRot-body.xRot;
        }else{
            cannon_left.xRot=cannonXRot;
            cannon_right.xRot=cannonXRot;
        }
        this.skirt.y=f11;
        this.skirt.z=f12;
        this.skirt.xRot = -body.xRot;
        //System.out.println(cannonXRot);
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
    }
}
