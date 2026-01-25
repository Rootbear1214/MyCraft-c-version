package com.hellobrick.item;

import com.hellobrick.entity.ModEntities;
import com.hellobrick.entity.ProtectorWolfEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class ProtectorWolfSpawnEggItem extends SpawnEggItem {

    public ProtectorWolfSpawnEggItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<?> getType(ItemStack stack) {
        return ModEntities.PROTECTOR_WOLF;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!(context.getLevel() instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();
        BlockPos spawnPos = pos.relative(face);

        ProtectorWolfEntity wolf = new ProtectorWolfEntity(ModEntities.PROTECTOR_WOLF, serverLevel);
        wolf.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);

        if (player != null) {
            wolf.setFollowPlayerUuid(player.getUUID());
            wolf.tame(player);
        }

        wolf.setPersistenceRequired();
        serverLevel.addFreshEntity(wolf);

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        stack.consume(1, player);
        return InteractionResult.SUCCESS;
    }
}
