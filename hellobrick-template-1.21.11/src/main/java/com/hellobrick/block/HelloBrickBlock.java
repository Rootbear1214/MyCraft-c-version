package com.hellobrick.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HelloBrickBlock extends Block {

    public HelloBrickBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        // Only send message on server side to avoid duplicate messages
        if (!level.isClientSide()) {
            player.displayClientMessage(Component.literal("Hello, Brick!"), false);
        }
        return InteractionResult.SUCCESS;
    }
}
