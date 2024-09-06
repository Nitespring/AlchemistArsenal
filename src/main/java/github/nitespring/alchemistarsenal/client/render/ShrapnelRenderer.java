package github.nitespring.alchemistarsenal.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.events.ClientListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

public class ShrapnelRenderer<T extends Entity, M extends SquareTextureEntityModel<T>> extends EntityRenderer<T> {

    private final SquareTextureEntityModel<T> model;

    public ShrapnelRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"textures/entity/projectiles/crystal_shard.png");
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack stack, MultiBufferSource bufferSource, int packedLight) {

        VertexConsumer vertexconsumer;
        stack.pushPose();

        stack.mulPose(Axis.YP.rotationDegrees(-135+entity.getYRot()));
        stack.mulPose(Axis.ZP.rotationDegrees(18+entity.getXRot()));


        stack.translate(0.1, -0.5, 0);
        stack.scale(0.4f, 0.4f, 0.4f);
        vertexconsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

        this.model.renderToBuffer(stack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, packedLight);
        stack.popPose();
        super.render(entity, entityYaw, partialTick, stack, bufferSource, packedLight);

    }
}
