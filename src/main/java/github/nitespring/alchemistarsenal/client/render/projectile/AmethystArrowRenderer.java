package github.nitespring.alchemistarsenal.client.render.projectile;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AmethystArrowRenderer extends ArrowRenderer {
    public AmethystArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"textures/entity/projectiles/amethyst_arrow.png");
    }

}
