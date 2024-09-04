package github.nitespring.alchemistarsenal.client.render;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpectralArrowRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class InfernalArrowRenderer extends ArrowRenderer {
    public InfernalArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"textures/entity/projectiles/flaming_arrow.png");
    }

    @Override
    protected int getBlockLightLevel(Entity pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    protected int getSkyLightLevel(Entity pEntity, BlockPos pPos) {
        return 15;
    }
}
