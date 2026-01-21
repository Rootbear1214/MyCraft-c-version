package com.hellobrick.block;

import com.hellobrick.entity.ClusterBombEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import org.jetbrains.annotations.Nullable;
import java.util.function.BiConsumer;

public class ClusterBombBlock extends Block {

    public ClusterBombBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            if (level.hasNeighborSignal(pos)) {
                primeBomb(level, pos, null);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide() && level.hasNeighborSignal(pos)) {
            primeBomb(level, pos, null);
            level.removeBlock(pos, false);
            return;
        }

        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    @Override
    protected void onExplosionHit(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        Entity source = explosion.getDirectSourceEntity();
        LivingEntity igniter = source instanceof LivingEntity living ? living : null;
        primeBomb(level, pos, igniter);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, net.minecraft.world.InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            primeBomb(level, pos, player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            if (stack.is(Items.FLINT_AND_STEEL)) {
                stack.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
            } else {
                stack.consume(1, player);
            }
            return InteractionResult.SUCCESS_SERVER;
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            BlockPos pos = hit.getBlockPos();
            Entity owner = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(serverLevel, pos)) {
                primeBomb(level, pos, owner instanceof LivingEntity living ? living : null);
                level.removeBlock(pos, false);
            }
        }
    }

    public static void primeBomb(Level level, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!level.isClientSide()) {
            ClusterBombEntity entity = new ClusterBombEntity(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, igniter);
            level.addFreshEntity(entity);
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }
}
