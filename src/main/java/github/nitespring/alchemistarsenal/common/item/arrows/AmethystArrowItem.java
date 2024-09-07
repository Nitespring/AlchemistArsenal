package github.nitespring.alchemistarsenal.common.item.arrows;

import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.FragmentingArrow;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AmethystArrowItem extends ArrowItem implements ProjectileItem {

    float basedamage = 3.0f;

    public AmethystArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity e, @Nullable ItemStack stack1) {
        AbstractArrow arrow = new FragmentingArrow(EntityInit.AMETHYST_ARROW.get(),e,level, stack.copyWithCount(1),stack1);
        arrow.setBaseDamage(basedamage);
        return arrow;
    }

    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        AbstractArrow arrow = new FragmentingArrow(EntityInit.AMETHYST_ARROW.get(),pPos.x(), pPos.y(), pPos.z(),pLevel, pStack.copyWithCount(1), null);
        arrow.setBaseDamage(basedamage);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("comment.alkhars.amethyst_arrow").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }



}
