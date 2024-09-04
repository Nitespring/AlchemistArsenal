package github.nitespring.alchemistarsenal;

import github.nitespring.alchemistarsenal.core.init.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AlchemistArsenal.MODID)
public class AlchemistArsenal
{

    public static final String MODID = "alkhars";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AlchemistArsenal(IEventBus modEventBus, ModContainer modContainer)
    {
        //NeoForge.EVENT_BUS.register(this);
        DataComponentInit.DATA_COMPONENTS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);
        MobEffectInit.MOB_EFFECTS.register(modEventBus);
        MobEffectInit.POTIONS.register(modEventBus);
        CreativeTabInit.TABS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
