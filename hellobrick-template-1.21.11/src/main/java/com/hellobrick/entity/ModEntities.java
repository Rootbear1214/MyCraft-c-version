package com.hellobrick.entity;

import com.hellobrick.HelloBrick;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final EntityType<ClusterBombEntity> CLUSTER_BOMB = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "cluster_bomb")),
            EntityType.Builder.<ClusterBombEntity>of(ClusterBombEntity::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "cluster_bomb")))
    );

    public static final EntityType<ChickenChaserEntity> CHICKEN_CHASER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "chicken_chaser")),
            EntityType.Builder.<ChickenChaserEntity>of(ChickenChaserEntity::new, MobCategory.MONSTER)
                    .sized(0.4F, 0.7F)
                    .clientTrackingRange(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "chicken_chaser")))
    );

    public static final EntityType<ChickenChaserEggProjectile> CHICKEN_CHASER_EGG_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "chicken_chaser_egg_projectile")),
            EntityType.Builder.<ChickenChaserEggProjectile>of(ChickenChaserEggProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HelloBrick.MOD_ID, "chicken_chaser_egg_projectile")))
    );

    public static void registerModEntities() {
        HelloBrick.LOGGER.info("Registering Mod Entities for " + HelloBrick.MOD_ID);

        FabricDefaultAttributeRegistry.register(CHICKEN_CHASER, ChickenChaserEntity.createAttributes());
    }
}
