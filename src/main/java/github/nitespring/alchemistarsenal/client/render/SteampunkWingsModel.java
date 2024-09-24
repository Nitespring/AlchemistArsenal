package github.nitespring.alchemistarsenal.client.render;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SteampunkWingsModel<T extends LivingEntity> extends ElytraModel<T>{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "steampunk_wings"),
            "main"
    );
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart boiler;
    public SteampunkWingsModel(ModelPart root) {
        super(root);
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
        this.boiler = root.getChild("boiler");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition left_wing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create()
                .texOffs(22, 0)
                        .addBox(-4.5F, 0.0F, -1.0F,
                                10.0F, 24.0F, 2.0F,
                                new CubeDeformation(0.0F))
                .texOffs(48, 42)
                        .addBox(3.5F, 0.0F, -0.5F,
                        2.0F, 16.0F, 2.0F,
                        new CubeDeformation(0.15F)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition left_wing_r1 = left_wing.addOrReplaceChild("left_wing_r1", CubeListBuilder.create()
                .texOffs(56, 42)
                .addBox(2.5F, 15.5F, -0.5F,
                        2.0F, 8.0F, 2.0F,
                        new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(8.65F, 0.25F, 0.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition right_wing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(22, 0).mirror()
                        .addBox(-5.5F, 0.0F, -1.0F,
                        10.0F, 24.0F, 2.0F,
                        new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 42).mirror()
                        .addBox(-5.5F, 0.0F, -0.5F,
                        2.0F, 16.0F, 2.0F,
                        new CubeDeformation(0.15F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -3.1416F, 0.0F, -3.1416F));

        PartDefinition right_wing_r1 = right_wing.addOrReplaceChild("right_wing_r1",
                CubeListBuilder.create()
                        .texOffs(56, 42).mirror()
                        .addBox(-4.5F, 15.5F, -0.5F,
                                2.0F, 8.0F, 2.0F,
                                new CubeDeformation(0.1F)).mirror(false),
                PartPose.offsetAndRotation(-8.65F, 0.25F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition boiler = partdefinition.addOrReplaceChild("boiler",
                CubeListBuilder.create().texOffs(0, 56)
                        .addBox(-4.0F, -2.0F, -1.0F,
                                8.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.5F))
                .texOffs(0, 48)
                        .addBox(-4.0F, -2.0F, -1.0F,
                                8.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = (float) (Math.PI / 12);
        float f1 = (float) (-Math.PI / 12);
        float f2 = 0.0F;
        float f3 = 0.0F;
        if (pEntity.isFallFlying()) {
            float f4 = 1.0F;
            Vec3 vec3 = pEntity.getDeltaMovement();
            if (vec3.y < 0.0) {
                Vec3 vec31 = vec3.normalize();
                f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5);
            }

            f = f4 * (float) (Math.PI / 9) + (1.0F - f4) * f;
            f1 = f4 * (float) (-Math.PI / 2) + (1.0F - f4) * f1;
            f2=5;
        } else if (pEntity.isCrouching()) {
            f = (float) (Math.PI * 2.0 / 9.0);
            f1 = (float) (-Math.PI / 4);
            f2 = 3.0F;
            f3 = 0.08726646F;
        }

        this.leftWing.y = f2;
        if (pEntity instanceof AbstractClientPlayer abstractclientplayer) {
            abstractclientplayer.elytraRotX = abstractclientplayer.elytraRotX + (f - abstractclientplayer.elytraRotX) * 0.1F;
            abstractclientplayer.elytraRotY = abstractclientplayer.elytraRotY + (f3 - abstractclientplayer.elytraRotY) * 0.1F;
            abstractclientplayer.elytraRotZ = abstractclientplayer.elytraRotZ + (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
            this.leftWing.xRot = abstractclientplayer.elytraRotX;
            this.leftWing.yRot = abstractclientplayer.elytraRotY;
            this.leftWing.zRot = abstractclientplayer.elytraRotZ;
        } else {
            this.leftWing.xRot = f;
            this.leftWing.zRot = f1;
            this.leftWing.yRot = f3;
        }

        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.y = this.leftWing.y;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.zRot = -this.leftWing.zRot;
    }

}
