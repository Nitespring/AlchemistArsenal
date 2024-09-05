package github.nitespring.alchemistarsenal.common.item.arrows;

import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.InfernalArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class InfernalArrowItem extends ArrowItem {
    public InfernalArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity e, @Nullable ItemStack stack1) {
        return new InfernalArrow(e,level, stack.copyWithCount(1),stack1);
    }



}
