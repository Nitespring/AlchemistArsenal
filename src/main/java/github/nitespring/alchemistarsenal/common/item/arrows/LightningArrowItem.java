package github.nitespring.alchemistarsenal.common.item.arrows;

import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.BouncyArrow;
import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.FragmentingArrow;
import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.LightningArrow;
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
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LightningArrowItem extends ArrowItem implements ProjectileItem {
    float basedamage = 2.5f;
    public LightningArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity e, @Nullable ItemStack stack1) {
        AbstractArrow arrow = new LightningArrow(e,level, stack.copyWithCount(1),stack1);
        arrow.setBaseDamage(3.0f);
        return arrow;
    }
    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        AbstractArrow arrow = new LightningArrow(pPos.x(), pPos.y(), pPos.z(),pLevel, pStack.copyWithCount(1), null);
        arrow.setBaseDamage(basedamage);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }



}
