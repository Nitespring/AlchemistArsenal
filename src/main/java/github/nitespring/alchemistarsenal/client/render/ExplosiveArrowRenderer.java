package github.nitespring.alchemistarsenal.client.render;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ExplosiveArrowRenderer extends ArrowRenderer {
    public ExplosiveArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"textures/entity/projectiles/explosive_arrow.png");
    }


}
