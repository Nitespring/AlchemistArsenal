package github.nitespring.alchemistarsenal.common.item.concoctions;

import github.nitespring.alchemistarsenal.core.init.MobEffectInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class FlamingFlask extends Item {
    public FlamingFlask(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 70;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffectInit.FLAMING_FIST.getHolder().get(),22000));
        itemStack.consume(1,entity);
        return itemStack;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("comment.alkhars.flaming_flask").withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }

}
