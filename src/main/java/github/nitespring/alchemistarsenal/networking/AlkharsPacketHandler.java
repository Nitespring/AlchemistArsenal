package github.nitespring.alchemistarsenal.networking;





import github.nitespring.alchemistarsenal.AlchemistArsenal;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

//@EventBusSubscriber(modid = DarkestSouls.MODID)
public class AlkharsPacketHandler {

	//@SubscribeEvent
	public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(AlchemistArsenal.MODID)
				.versioned("1.0")
				.optional();
		registrar.playToServer(
				BoostWingsAction.TYPE,
				BoostWingsAction.STREAM_CODEC,
				BoostWingsAction.ServerPayloadHandler::handleData);
		registrar.playToServer(
				ShootRocketAction.TYPE,
				ShootRocketAction.STREAM_CODEC,
				ShootRocketAction.ServerPayloadHandler::handleData);
	}

	public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
		PacketDistributor.sendToServer(message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
		PacketDistributor.sendToPlayer(player, message);
	}


}

