package com.hellobrick.client;

import com.hellobrick.HelloBrick;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;

public class ProtectorWolfRenderer extends WolfRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            HelloBrick.MOD_ID,
            "textures/entity/chicken/protector_wolf.png"
    );

    public ProtectorWolfRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(WolfRenderState renderState) {
        return TEXTURE;
    }
}
