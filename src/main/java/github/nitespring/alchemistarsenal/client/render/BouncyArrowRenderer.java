package github.nitespring.alchemistarsenal.client.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BouncyArrowRenderer extends ArrowRenderer {
    public BouncyArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow");
    }

}
