package com.hellobrick.client;

import com.hellobrick.entity.ClusterBombEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;

public class ClusterBombRenderer extends EntityRenderer<ClusterBombEntity, TntRenderState> {

    private final BlockRenderDispatcher blockRenderer;

    public ClusterBombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    @Override
    public void extractRenderState(ClusterBombEntity entity, TntRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.fuseRemainingInTicks = (float) entity.getFuse();
        state.blockState = Blocks.TNT.defaultBlockState();
    }

    public void submit(TntRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, CameraRenderState cameraState) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5F, 0.0F);
        float fuse = state.fuseRemainingInTicks;
        if (fuse - 1.0F < 10.0F) {
            float scale = 1.0F - (fuse - 1.0F) / 10.0F;
            scale = Mth.clamp(scale, 0.0F, 1.0F);
            scale *= scale;
            scale *= scale;
            float size = 1.0F + scale * 0.3F;
            poseStack.scale(size, size, size);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        
        int packedLight = state.lightCoords;
        this.blockRenderer.renderSingleBlock(state.blockState, poseStack, bufferSource, packedLight, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY);
        
        poseStack.popPose();
    }
}
