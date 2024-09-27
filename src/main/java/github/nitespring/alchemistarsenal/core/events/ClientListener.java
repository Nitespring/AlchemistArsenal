package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkSuitModel;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkWingsLayer;
import github.nitespring.alchemistarsenal.client.render.equipment.SteampunkWingsModel;
import github.nitespring.alchemistarsenal.client.render.equipment.TurtleMasterArmourModel;
import github.nitespring.alchemistarsenal.client.render.projectile.*;
import github.nitespring.alchemistarsenal.common.item.equipment.SteampunkChestplateItem;
import github.nitespring.alchemistarsenal.common.item.equipment.TurtleMasterArmourItem;
import github.nitespring.alchemistarsenal.common.item.weapons.AutomaticCrossbow;
import github.nitespring.alchemistarsenal.common.item.weapons.RepeatingCrossbow;
import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.FireworkEntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.HashMap;

@EventBusSubscriber(modid = AlchemistArsenal.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "square_texture"), "main");

    private static final HashMap<RenderLayer<?, ?>, LivingEntityRenderer> mappedfashion = new HashMap<>();




    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {

        mappedfashion.clear();

        /*event.getEntityTypes().forEach(entityTypeName -> {
            if(event.getRenderer(entityTypeName) instanceof LivingEntityRenderer<?,?> renderer){
                renderer.addLayer(new SteampunkWingsLayer<>(renderer,event.getEntityModels()));
                mappedfashion.put(new SteampunkWingsLayer<>(renderer,event.getEntityModels()), renderer);
            }
        });*/
        event.getEntityTypes().forEach(entityTypeName -> {
            if(event.getRenderer(entityTypeName) instanceof LivingEntityRenderer<?,?> renderer){
                renderer.addLayer(new SteampunkWingsLayer<>(renderer,event.getEntityModels()));
                mappedfashion.put(new SteampunkWingsLayer<>(renderer,event.getEntityModels()), renderer);
            }
        });

        event.getSkins().forEach(skinTypeName -> {
            if (event.getSkin(skinTypeName) instanceof PlayerRenderer) {

                PlayerRenderer renderer = (PlayerRenderer)event.getSkin(skinTypeName);

                renderer.addLayer(new SteampunkWingsLayer<>(renderer,event.getEntityModels()));
                mappedfashion.put(new SteampunkWingsLayer<>(renderer,event.getEntityModels()), renderer);
            }
        });
    }

    public static HashMap<RenderLayer<?, ?>, LivingEntityRenderer> getMappedfashion() {

        return mappedfashion;
    }




    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);
        event.registerLayerDefinition(SteampunkWingsModel.LAYER_LOCATION,SteampunkWingsModel::createBodyLayer);
        event.registerLayerDefinition(SteampunkSuitModel.LAYER_LOCATION,SteampunkSuitModel::createBodyLayer);
        event.registerLayerDefinition(TurtleMasterArmourModel.LAYER_LOCATION,TurtleMasterArmourModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.INFERNAL_ARROW.get(), InfernalArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.BOUNCY_ARROW.get(), BouncyArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.DRAGON_ARROW.get(), DragonArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.LIGHTNING_ARROW.get(), LightningArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.AMETHYST_ARROW.get(), AmethystArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.WIND_ARROW.get(), WindArrowRenderer::new);
        event.registerEntityRenderer(EntityInit.FIRE_BOTTLE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityInit.EXPLOSIVE_BOTTLE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityInit.SHRAPNEL.get(), ShrapnelRenderer::new);
        event.registerEntityRenderer(EntityInit.ELYTRA_BOOSTER.get(), FireworkEntityRenderer::new);
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    ItemInit.AUTOMATIC_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pull"),
                    (stack, level, player, seed) -> {
                        if (player == null) {
                            return 0.0F;
                        } else {
                            return AutomaticCrossbow.isCharged(stack) ? 0.0F
                                    : (float) (stack.getUseDuration(player) - player.getUseItemRemainingTicks())
                                    / (float) AutomaticCrossbow.getChargeDuration(stack, player);
                        }
                    }
            );
            ItemProperties.register(
                    ItemInit.AUTOMATIC_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pulling"),
                    (stack, level, player, seed) -> player != null
                            && player.isUsingItem() && player.getUseItem() == stack
                            && !AutomaticCrossbow.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.AUTOMATIC_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("charged"),
                    (stack, level, player, seed) -> AutomaticCrossbow.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.CROSSBOW_SCYTHE.get(),
                    ResourceLocation.withDefaultNamespace("pull"),
                    (stack, level, player, seed) -> {
                        if (player == null) {
                            return 0.0F;
                        } else {
                            return CrossbowItem.isCharged(stack) ? 0.0F
                                    : (float) (stack.getUseDuration(player) - player.getUseItemRemainingTicks())
                                    / (float) CrossbowItem.getChargeDuration(stack, player);
                        }
                    }
            );
            ItemProperties.register(
                    ItemInit.CROSSBOW_SCYTHE.get(),
                    ResourceLocation.withDefaultNamespace("pulling"),
                    (stack, level, player, seed) -> player != null
                            && player.isUsingItem() && player.getUseItem() == stack
                            && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.CROSSBOW_SCYTHE.get(),
                    ResourceLocation.withDefaultNamespace("charged"),
                    (stack, level, player, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.CROSSBOW_SCYTHE.get(),
                    ResourceLocation.withDefaultNamespace("firework"),
                    (stack, level, player, seed) ->
                    {
                        ChargedProjectiles chargedprojectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
                        return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                    }
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pull"),
                    (stack, level, player, seed) -> {
                        if (player == null) {
                            return 0.0F;
                        } else {
                            return CrossbowItem.isCharged(stack) ? 0.0F
                                    : (float) (stack.getUseDuration(player) - player.getUseItemRemainingTicks())
                                    / (float) RepeatingCrossbow.getChargeDuration(stack, player);
                        }
                    }
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pulling"),
                    (stack, level, player, seed) -> player != null
                            && player.isUsingItem() && player.getUseItem() == stack
                            && !RepeatingCrossbow.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("charged"),
                    (stack, level, player, seed) -> RepeatingCrossbow.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("firework"),
                    (stack, level, player, seed) ->
                    {
                        ChargedProjectiles chargedprojectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
                        return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                    }
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID,"amount"),
                    (stack, level, player, seed) ->
                    {
                        ChargedProjectiles chargedprojectiles1 = stack.get(DataComponents.CHARGED_PROJECTILES);
                        ChargedProjectiles chargedprojectiles2 = stack.get(DataComponentInit.CHARGED_PROJECTILES2);
                        ChargedProjectiles chargedprojectiles3 = stack.get(DataComponentInit.CHARGED_PROJECTILES3);

                        return (chargedprojectiles3!=null && !chargedprojectiles3.isEmpty()) ? 3 :
                                (chargedprojectiles2!=null &&!chargedprojectiles2.isEmpty()) ? 2 :
                                        (chargedprojectiles1!=null &&!chargedprojectiles1.isEmpty()) ? 1 : 0;
                    }
            );

        });
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event){
        event.registerItem(SteampunkChestplateItem.STEAMPUNK_ARMOUR_CLIENT_EXTENSIONS, ItemInit.STEAMPUNK_HELMET);
        event.registerItem(SteampunkChestplateItem.STEAMPUNK_ARMOUR_CLIENT_EXTENSIONS, ItemInit.STEAMPUNK_CHESTPLATE);
        event.registerItem(SteampunkChestplateItem.STEAMPUNK_ARMOUR_CLIENT_EXTENSIONS, ItemInit.STEAMPUNK_LEGGINGS);
        event.registerItem(SteampunkChestplateItem.STEAMPUNK_ARMOUR_CLIENT_EXTENSIONS, ItemInit.STEAMPUNK_BOOTS);
        event.registerItem(TurtleMasterArmourItem.TURTLE_ARMOUR_CLIENT_EXTENSIONS, ItemInit.TURTLE_MASTER_HELMET);
        event.registerItem(TurtleMasterArmourItem.TURTLE_ARMOUR_CLIENT_EXTENSIONS, ItemInit.TURTLE_MASTER_CHESTPLATE);
        event.registerItem(TurtleMasterArmourItem.TURTLE_ARMOUR_CLIENT_EXTENSIONS, ItemInit.TURTLE_MASTER_LEGGINGS);
        event.registerItem(TurtleMasterArmourItem.TURTLE_ARMOUR_CLIENT_EXTENSIONS, ItemInit.TURTLE_MASTER_BOOTS);
    }




}
