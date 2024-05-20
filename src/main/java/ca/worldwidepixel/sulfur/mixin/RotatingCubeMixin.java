package ca.worldwidepixel.sulfur.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RotatingCubeMapRenderer.class)
public class RotatingCubeMixin {

    @Final
    @Shadow
    private MinecraftClient client;

    @Shadow
    private float pitch;
    @Shadow
    private float yaw;
    @Shadow
    private static float wrapOnce(float a, float b) {
        return a > b ? a - b : a;
    }
    @Final
    @Shadow
    private CubeMapRenderer cubeMap;


    /**
     * @author WWP
     * @reason LOLS
     */
    @Overwrite
    public void render(float delta, float alpha) {
        float f = (float)((double)delta * 10);
        this.pitch = wrapOnce(this.pitch + f * 0.1F, 360.0F);
        this.yaw = wrapOnce(this.yaw + f * 0.1F, (float) (8));
        this.cubeMap.draw(this.client, 10.0F, this.pitch, alpha);
    }

}
