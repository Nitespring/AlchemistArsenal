package github.nitespring.alchemistarsenal;

import github.nitespring.alchemistarsenal.core.events.GameEvents;
import github.nitespring.alchemistarsenal.core.init.*;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/forge.mods.toml file
@Mod(AlchemistArsenal.MODID)
public class AlchemistArsenal
{

    public static final String MODID = "alkhars";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AlchemistArsenal(FMLJavaModLoadingContext context)
    {

        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(GameEvents::commonSetup);

        DataComponentInit.DATA_COMPONENTS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);
        MobEffectInit.MOB_EFFECTS.register(modEventBus);
        MobEffectInit.POTIONS.register(modEventBus);
        CreativeTabInit.TABS.register(modEventBus);


    }



}
