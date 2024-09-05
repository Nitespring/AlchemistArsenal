package github.nitespring.alchemistarsenal.common.item.concoctions;

import github.nitespring.alchemistarsenal.common.entity.projectile.throwable.ExplosiveBottle;
import github.nitespring.alchemistarsenal.common.entity.projectile.throwable.FireBottle;
import github.nitespring.alchemistarsenal.core.init.EntityInit;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosiveBottleItem extends Item implements ProjectileItem {
    public ExplosiveBottleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        ExplosiveBottle bottle = new ExplosiveBottle(EntityInit.EXPLOSIVE_BOTTLE.get(),pLevel);
        bottle.setPos(pPos.x(),pPos.y(),pPos.z());
        return bottle;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();
        player.playSound(SoundEvents.SPLASH_POTION_THROW);
        if (!level.isClientSide) {
            ExplosiveBottle bottle = new ExplosiveBottle(EntityInit.EXPLOSIVE_BOTTLE.get(), level);
            bottle.setOwner(player);
            bottle.setPos(pos.add(aim.x()*0.5f,1.75f+aim.y()*0.5f,aim.z()*0.5f));
            bottle.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.8F, 1.0F);
            level.addFreshEntity(bottle);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        player.getCooldowns().addCooldown(this,2);
        return InteractionResultHolder.consume(itemstack);
    }
}
