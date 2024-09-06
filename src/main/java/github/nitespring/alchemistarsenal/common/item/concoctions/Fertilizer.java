package github.nitespring.alchemistarsenal.common.item.concoctions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class Fertilizer extends Item {
    private final int intensity;
    private final int range;
    public Fertilizer(int range, int intensity, Properties properties) {
        super(properties);
        this.range=range;
        this.intensity=intensity;
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


}
