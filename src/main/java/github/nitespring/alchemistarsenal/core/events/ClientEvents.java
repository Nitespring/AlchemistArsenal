package github.nitespring.alchemistarsenal.core.events;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.core.init.KeybindInit;
import github.nitespring.alchemistarsenal.networking.AlkharsPacketHandler;
import github.nitespring.alchemistarsenal.networking.BoostWingsAction;
import github.nitespring.alchemistarsenal.networking.ShootRocketAction;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;


@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AlchemistArsenal.MODID, value = Dist.CLIENT)
public class ClientEvents {
	


	private static boolean isBoostKeyDown = false;
	


	@SubscribeEvent
	public static void boostKeybind(ClientTickEvent.Pre event) {
		if(KeybindInit.BOOST_KEYBIND.get().isDown()) {
			if(!isBoostKeyDown) {
				AlkharsPacketHandler.sendToServer(new BoostWingsAction());
				isBoostKeyDown=true;
			}
		}else {
			isBoostKeyDown=false;
		}

	}

	private static boolean isShootKeyDown = false;



	@SubscribeEvent
	public static void shootKeybind(ClientTickEvent.Pre event) {
		if(KeybindInit.SHOOT_KEYBIND.get().isDown()) {
			if(!isShootKeyDown) {
				AlkharsPacketHandler.sendToServer(new ShootRocketAction());
				isShootKeyDown=true;
			}
		}else {
			isShootKeyDown=false;
		}

	}

	    
	    
}
