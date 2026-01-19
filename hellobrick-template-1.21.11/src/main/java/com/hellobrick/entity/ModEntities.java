package com.hellobrick.entity;

import com.hellobrick.HelloBrick;
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

    public static void registerModEntities() {
        HelloBrick.LOGGER.info("Registering Mod Entities for " + HelloBrick.MOD_ID);
    }
}
