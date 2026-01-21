package com.hellobrick;

import com.hellobrick.client.ClusterBombRenderer;
import com.hellobrick.client.ChickenChaserRenderer;
import com.hellobrick.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class HelloBrickClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register the cluster bomb entity renderer
		EntityRendererRegistry.register(ModEntities.CLUSTER_BOMB, ClusterBombRenderer::new);
		EntityRendererRegistry.register(ModEntities.CHICKEN_CHASER, ChickenChaserRenderer::new);
		EntityRendererRegistry.register(ModEntities.CHICKEN_CHASER_EGG_PROJECTILE, ThrownItemRenderer::new);
	}
}