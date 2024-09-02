package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.init.ItemInit;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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
                            return CrossbowItem.isCharged(stack) ? 0.0F
                                    : (float) (stack.getUseDuration(player) - player.getUseItemRemainingTicks())
                                    / (float) CrossbowItem.getChargeDuration(stack, player);
                        }
                    }
            );
            ItemProperties.register(
                    ItemInit.AUTOMATIC_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("pulling"),
                    (stack, level, player, seed) -> player != null
                            && player.isUsingItem() && player.getUseItem() == stack
                            && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
            );
            ItemProperties.register(
                    ItemInit.AUTOMATIC_CROSSBOW.get(),
                    ResourceLocation.withDefaultNamespace("charged"),
                    (stack, level, player, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
            );
        });
    }
}
