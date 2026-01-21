package com.hellobrick.client;

import com.hellobrick.HelloBrick;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

public class ChickenChaserRenderer extends ChickenRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            HelloBrick.MOD_ID,
            "textures/entity/chicken/chicken_chaser.png"
    );

    public ChickenChaserRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(ChickenRenderState renderState) {
        return TEXTURE;
    }
}
