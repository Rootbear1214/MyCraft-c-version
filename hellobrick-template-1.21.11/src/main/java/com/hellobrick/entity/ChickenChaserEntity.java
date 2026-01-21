package com.hellobrick.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ChickenChaserEntity extends Chicken {

    @Nullable
    private UUID targetPlayerUuid;

    public ChickenChaserEntity(EntityType<? extends ChickenChaserEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Chicken.createAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35);
    }

    public void setTargetPlayerUuid(UUID uuid) {
        this.targetPlayerUuid = uuid;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4D, true));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.targetPlayerUuid == null) {
            return;
        }

        Player target = null;
        for (var player : this.level().players()) {
            if (player.getUUID().equals(this.targetPlayerUuid)) {
                target = (Player) player;
                break;
            }
        }

        if (target != null && target.isAlive()) {
            this.setTarget(target);
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        String uuidString = input.getStringOr("TargetPlayerUuid", "");
        if (!uuidString.isEmpty()) {
            try {
                this.targetPlayerUuid = UUID.fromString(uuidString);
            } catch (IllegalArgumentException ignored) {
                this.targetPlayerUuid = null;
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);

        if (this.targetPlayerUuid != null) {
            output.putString("TargetPlayerUuid", this.targetPlayerUuid.toString());
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
