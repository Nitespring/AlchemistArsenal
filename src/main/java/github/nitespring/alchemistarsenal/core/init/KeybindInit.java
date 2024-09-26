package github.nitespring.alchemistarsenal.core.init;


import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = AlchemistArsenal.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class KeybindInit {

		public static final Lazy<KeyMapping> BOOST_KEYBIND = Lazy.of(()->new KeyMapping("key.alkhars.boost", GLFW.GLFW_KEY_B, "key.categories.gameplay"));
		public static final Lazy<KeyMapping> SHOOT_KEYBIND = Lazy.of(()->new KeyMapping("key.alkhars.shoot", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.gameplay"));
	    
	    @SubscribeEvent
	    public static void registerKeyBinds(RegisterKeyMappingsEvent event) {
	    	
	    	event.register(BOOST_KEYBIND.get());
			event.register(SHOOT_KEYBIND.get());
	    	
	    }
	
}
