package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.item.AutomaticCrossbow;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = AlchemistArsenal.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

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
                                    / (float) CrossbowItem.getChargeDuration(stack, player);
                        }
                    }
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pulling"),
                    (stack, level, player, seed) -> player != null
                            && player.isUsingItem() && player.getUseItem() == stack
                            && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.REPEATING_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("charged"),
                    (stack, level, player, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
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
        });
    }
    @SubscribeEvent
    public static void onClientSetup(RegisterClientExtensionsEvent event) {

    }


}
