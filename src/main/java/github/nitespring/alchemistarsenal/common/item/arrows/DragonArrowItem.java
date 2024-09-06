package github.nitespring.alchemistarsenal.common.item.arrows;

import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.BouncyArrow;
import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.DragonArrow;
import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.FragmentingArrow;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DragonArrowItem extends ArrowItem implements ProjectileItem {
    float basedamage = 4.0f;
    public DragonArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity e, @Nullable ItemStack stack1) {
        AbstractArrow arrow = new DragonArrow(e,level, stack.copyWithCount(1),stack1);
        arrow.setBaseDamage(basedamage);
        return arrow;
    }
    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        AbstractArrow arrow = new DragonArrow(pPos.x(), pPos.y(), pPos.z(),pLevel, pStack.copyWithCount(1), null);
        arrow.setBaseDamage(basedamage);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }


}
