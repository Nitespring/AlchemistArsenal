package github.nitespring.alchemistarsenal.common.item.concoctions;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.List;

public class Fertilizer extends Item {
    private final int intensity;
    private final int range;
    private final int type;

    public Fertilizer(int range, int intensity, int type, Properties properties) {
        super(properties);
        this.range=range;
        this.intensity=intensity;
        this.type=type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(!context.getLevel().isClientSide()) {
        BlockPos pos = context.getClickedPos();
        BlockState block = context.getLevel().getBlockState(pos);
        if(block.getBlock() instanceof BonemealableBlock bonemealableblock
                && bonemealableblock.isValidBonemealTarget(context.getLevel(),pos,block)) {
            for (int i = -range; i <= range; i++) {
                for (int k = -range; k <= range; k++) {
                        for (int j = -range; j <= range; j++) {
                            BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY()+j, pos.getZ() + k);
                            BlockState block1 = context.getLevel().getBlockState(pos1);
                            if (block1.getBlock() instanceof BonemealableBlock bonemealableblock1
                                    && bonemealableblock1.isValidBonemealTarget(context.getLevel(), pos1, block1)) {
                                for (int n = 0; n < intensity; n++) {
                                    bonemealableblock1.performBonemeal(
                                            (ServerLevel) context.getLevel(), context.getLevel().getRandom(), pos1, block1);
                                }
                                context.getPlayer().gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                                context.getLevel().levelEvent(1505, pos1, 15);
                            }
                        }
                    }
                }
            context.getItemInHand().consume(1, context.getPlayer());
                /*if(!context.getPlayer().hasInfiniteMaterials()){
                    if(context.getPlayer().)
                    context.getPlayer().setItemInHand();
                }*/
            return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
            }
        }

        return InteractionResult.PASS;

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        switch(type){
            case 1:
                tooltipComponents.add(Component.translatable("comment.alkhars.enhanced_fertilizer").withStyle(ChatFormatting.GOLD));
                break;
            default:
                tooltipComponents.add(Component.translatable("comment.alkhars.fertilizer").withStyle(ChatFormatting.AQUA));
                break;
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
    public static DispenseItemBehavior FERTILIZER_DISPENSER_BEHAVIOUR = new OptionalDispenseItemBehavior() {
        @Override
        protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
            this.setSuccess(true);
            Level level = blockSource.level();
            if (level.isClientSide()) {
                this.setSuccess(false);
            } else {
                BlockPos pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                BlockState block = level.getBlockState(pos);
                if (block.getBlock() instanceof BonemealableBlock bonemealableblock
                        && bonemealableblock.isValidBonemealTarget(level, pos, block)) {
                    if(blockSource.state().getValue(DispenserBlock.FACING)!=Direction.DOWN&&blockSource.state().getValue(DispenserBlock.FACING)!=Direction.UP) {
                        pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING), 2);
                    }
                    for (int i = -1; i <= 1; i++) {
                        for (int k = -1; k <= 1; k++) {
                            for (int j = -1; j <= 1; j++) {
                                BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                                BlockState block1 = level.getBlockState(pos1);
                                if (block1.getBlock() instanceof BonemealableBlock bonemealableblock1
                                        && bonemealableblock1.isValidBonemealTarget(level, pos1, block1)) {
                                    for (int n = 0; n < 1; n++) {
                                        bonemealableblock1.performBonemeal(
                                                (ServerLevel) level, level.getRandom(), pos1, block1);
                                    }
                                    level.levelEvent(1505, pos1, 15);
                                }
                            }
                        }
                    }
                    stack.consume(1, null);
                }else{
                    this.setSuccess(false);
                }
            }
            return stack;
        }
    };
    public static DispenseItemBehavior ENCHANCED_FERTILIZER_DISPENSER_BEHAVIOUR = new OptionalDispenseItemBehavior() {
        @Override
        protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
            this.setSuccess(true);
            Level level = blockSource.level();
            if (level.isClientSide()) {
                this.setSuccess(false);
            } else {
                BlockPos pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                BlockState block = level.getBlockState(pos);
                if (block.getBlock() instanceof BonemealableBlock bonemealableblock
                        && bonemealableblock.isValidBonemealTarget(level, pos, block)) {
                    if(blockSource.state().getValue(DispenserBlock.FACING)!=Direction.DOWN&&blockSource.state().getValue(DispenserBlock.FACING)!=Direction.UP) {
                        pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING), 3);
                    }
                    for (int i = -2; i <= 2; i++) {
                        for (int k = -2; k <= 2; k++) {
                            for (int j = -2; j <= 2; j++) {
                                BlockPos pos1 = new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                                BlockState block1 = level.getBlockState(pos1);
                                if (block1.getBlock() instanceof BonemealableBlock bonemealableblock1
                                        && bonemealableblock1.isValidBonemealTarget(level, pos1, block1)) {
                                    for (int n = 0; n < 3; n++) {
                                        bonemealableblock1.performBonemeal(
                                                (ServerLevel) level, level.getRandom(), pos1, block1);
                                    }
                                    level.levelEvent(1505, pos1, 15);
                                }
                            }
                        }
                    }
                    stack.consume(1, null);
                }else{
                    this.setSuccess(false);
                }
            }
            return stack;
        }
    };
}
