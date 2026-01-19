package com.hellobrick.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;

public class ClusterBombEntity extends Entity {

    private static final int DEFAULT_FUSE_TIME = 80;
    private static final double LAUNCH_VELOCITY = 1.5;
    private static final int CLUSTER_COUNT = 8;

    private int fuse;
    @Nullable
    private LivingEntity owner;
    private boolean launched = false;

    public ClusterBombEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.fuse = DEFAULT_FUSE_TIME;
        this.blocksBuilding = true;
    }

    public ClusterBombEntity(Level level, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntities.CLUSTER_BOMB, level);
        this.setPos(x, y, z);
        double randomAngle = level.random.nextDouble() * Math.PI * 2;
        this.setDeltaMovement(-Math.sin(randomAngle) * 0.02, 0.2, -Math.cos(randomAngle) * 0.02);
        this.fuse = DEFAULT_FUSE_TIME;
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = igniter;
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        this.fuse = input.getShortOr("Fuse", (short) DEFAULT_FUSE_TIME);
        this.launched = input.getBooleanOr("Launched", false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putShort("Fuse", (short) this.fuse);
        output.putBoolean("Launched", this.launched);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tick() {
        // Apply gravity if not launched yet or after launch
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));

        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        // Launch into the air when first ignited
        if (!this.launched && this.fuse == DEFAULT_FUSE_TIME - 1) {
            this.launched = true;
            this.setDeltaMovement(new Vec3(0, LAUNCH_VELOCITY, 0));
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    net.minecraft.sounds.SoundEvents.FIREWORK_ROCKET_LAUNCH,
                    net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        --this.fuse;

        if (this.fuse <= 0) {
            this.discard();
            if (!this.level().isClientSide()) {
                this.explodeAndSpawnCluster();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide()) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explodeAndSpawnCluster() {
        // Main explosion
        this.level().explode(this, this.getX(), this.getY(0.0625), this.getZ(), 4.0F,
                Level.ExplosionInteraction.TNT);

        // Spawn 8 TNT entities in different directions
        for (int i = 0; i < CLUSTER_COUNT; i++) {
            double angle = (2 * Math.PI * i) / CLUSTER_COUNT;
            double horizontalSpeed = 0.5 + this.level().random.nextDouble() * 0.3;
            double verticalSpeed = 0.3 + this.level().random.nextDouble() * 0.2;

            double vx = Math.cos(angle) * horizontalSpeed;
            double vz = Math.sin(angle) * horizontalSpeed;
            double vy = verticalSpeed;

            PrimedTnt tnt = new PrimedTnt(this.level(), this.getX(), this.getY(), this.getZ(), this.owner);
            tnt.setDeltaMovement(vx, vy, vz);
            tnt.setFuse(40 + this.level().random.nextInt(20)); // Random fuse between 40-60 ticks
            this.level().addFreshEntity(tnt);
        }
    }

    public int getFuse() {
        return this.fuse;
    }

    public void setFuse(int fuse) {
        this.fuse = fuse;
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }
}
