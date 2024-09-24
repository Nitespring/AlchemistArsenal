package github.nitespring.alchemistarsenal.networking;


import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.item.equipment.ICustomElytra;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record BoostWingsAction() implements CustomPacketPayload{

	public static final Type<BoostWingsAction> TYPE = new Type<>(
			ResourceLocation.fromNamespaceAndPath(AlchemistArsenal.MODID, "boost"));
	public static final BoostWingsAction INSTANCE = new BoostWingsAction();

	public static final StreamCodec<ByteBuf, BoostWingsAction> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public class ServerPayloadHandler {

		public static void handleData(final BoostWingsAction data, final IPayloadContext ctx) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				if (player==null)
					return;
				ItemStack itemstack = player.getItemBySlot(EquipmentSlot.CHEST);

				if(itemstack.getItem() instanceof ICustomElytra wings && wings.shouldAutoboost()) {
					wings.autoBoost(player, itemstack);
				}
			})
			.exceptionally(e -> {
				ctx.disconnect(Component.translatable(
						AlchemistArsenal.MODID + ".networking.failed", e.getMessage()));
				return null;
			});
		}
	}
}

