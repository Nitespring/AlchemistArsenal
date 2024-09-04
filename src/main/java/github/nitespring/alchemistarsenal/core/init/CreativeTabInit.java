package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            AlchemistArsenal.MODID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> ALCHEMIST_ARSENAL =   TABS.register("alkhars",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.alkhars.alkhars"))
                    .icon(ItemInit.AUTOMATIC_CROSSBOW.get()::getDefaultInstance)
                    .displayItems((displayParams,output)->{
                        output.accept(ItemInit.BOUNCY_ARROW.get());
                        output.accept(ItemInit.LIGHTNING_ARROW.get());
                        output.accept(ItemInit.INFERNAL_ARROW.get());
                        output.accept(ItemInit.EXPLOSIVE_ARROW.get());
                        output.accept(ItemInit.DRAGON_ARROW.get());
                        output.accept(ItemInit.FLAME_IN_A_BOTTLE.get());
                        output.accept(ItemInit.UNSTABLE_CONCOCTION.get());
                        output.accept(ItemInit.POISON_FLASK.get());
                        output.accept(ItemInit.INFERNAL_FLASK.get());
                        output.accept(ItemInit.CROSSBOW_SCYTHE.get());
                        output.accept(ItemInit.REPEATING_CROSSBOW.get());
                        output.accept(ItemInit.AUTOMATIC_CROSSBOW.get());
                    })
                    .build());




}
