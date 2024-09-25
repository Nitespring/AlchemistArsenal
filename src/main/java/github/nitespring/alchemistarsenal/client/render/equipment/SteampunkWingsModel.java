package github.nitespring.alchemistarsenal.client.render.equipment;

import com.google.common.collect.ImmutableList;
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
                        .addBox(-4.0F, -16.0F, -2.0F,
                                8.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.6F))
                .texOffs(0, 48)
                        .addBox(-4.0F, -16.0F, -2.0F,
                                8.0F, 4.0F, 4.0F,
                                new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.leftWing, this.rightWing, this.boiler);
    }
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = (float) (Math.PI / 12);
        float f1 = (float) (-Math.PI / 12);
        float f2 = 0.0F;
        float f3 = 0.0F;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f11 = 24.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
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
            //f5 = (float) (Math.min(1.2*Math.abs(vec3.length()),0.5f)*Math.cos(0.4*pAgeInTicks*Math.min(Math.abs(vec3.length()),1.5f)));
            //f5 = (float) (Math.min(1.2*Math.abs(vec3.length()),0.5f)*Math.cos(0.4*pAgeInTicks));
            if(f4>0) {
                f5 = (float) (0.5f * Math.cos(0.35 * pAgeInTicks));
            }
            f6=1.0f;
            f7=0.5f;
            f11=22;
            f12=1.5f;
        } else if (pEntity.isCrouching()) {
            f = (float) (Math.PI * 2.0 / 9.0);
            f1 = (float) (-Math.PI / 4);
            f2 = 6.0F;
            f3 = 0.08726646F;
            f6=-1.0f;
            f7=1.5f;
            f11=26.5f;
            f12=4;
            //f13=f;
        }
        this.boiler.y=f11;
        this.boiler.z=f12;
        //this.boiler.xRot=f13;
        this.leftWing.y = f2;
        this.leftWing.x = f6;
        this.leftWing.z = f7;
        if (pEntity instanceof AbstractClientPlayer abstractclientplayer) {
            abstractclientplayer.elytraRotX = abstractclientplayer.elytraRotX + (f - abstractclientplayer.elytraRotX) * 0.1F + (f5 - abstractclientplayer.elytraRotY) * 0.1F;
            abstractclientplayer.elytraRotY = abstractclientplayer.elytraRotY + (f3 - abstractclientplayer.elytraRotY) * 0.1F;
            abstractclientplayer.elytraRotZ = abstractclientplayer.elytraRotZ + (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
            this.leftWing.xRot = abstractclientplayer.elytraRotX;
            this.leftWing.yRot = abstractclientplayer.elytraRotY;
            this.leftWing.zRot = abstractclientplayer.elytraRotZ;
        } else {
            this.leftWing.xRot = f+f5;
            this.leftWing.zRot = f1;
            this.leftWing.yRot = f3;
        }

        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.y = this.leftWing.y;
        this.rightWing.x = -this.leftWing.x;
        this.rightWing.z = this.leftWing.z;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.zRot = -this.leftWing.zRot;
    }

}
