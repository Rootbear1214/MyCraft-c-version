package com.hellobrick.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.phys.HitResult;

public class ChickenChaserEggProjectile extends ThrowableItemProjectile {

    public ChickenChaserEggProjectile(EntityType<? extends ChickenChaserEggProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ChickenChaserEggProjectile(Level level, LivingEntity owner, ItemStack itemStack) {
        super(ModEntities.CHICKEN_CHASER_EGG_PROJECTILE, owner, level, itemStack);
        this.setItem(itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return com.hellobrick.item.ModItems.CHICKEN_CHASER_EGG;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        Entity owner = this.getOwner();
        if (owner instanceof Player player && serverLevel.random.nextBoolean()) {
            ChickenChaserEntity chaser = new ChickenChaserEntity(ModEntities.CHICKEN_CHASER, serverLevel);
            chaser.setPos(this.getX(), this.getY(), this.getZ());
            chaser.setYRot(this.getYRot());
            chaser.setTargetPlayerUuid(player.getUUID());
            chaser.setPersistenceRequired();
            serverLevel.addFreshEntity(chaser);
        }

        serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(), this.getZ(), 8, 0.1, 0.1, 0.1, 0.0);
        this.discard();
    }
}
