package ca.worldwidepixel.sulfur.mixin;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;

import java.util.Deque;

@Mixin(MatrixStack.class)
public class GameRendererMixin {

    @Mutable
    @Final
    @Shadow
    private final Deque<MatrixStack.Entry> stack;

    public GameRendererMixin(Deque<MatrixStack.Entry> stack) {
        this.stack = stack;
    }

    /**
     * @author WWP
     * @reason LOL
     */
    @Overwrite
    public void scale(float x, float y, float z) {
        MatrixStack.Entry entry = (MatrixStack.Entry)this.stack.getLast();
        entry.getPositionMatrix().scale(x + 2, y, z);
        if (x == y && y == z) {
            if (x > 0.0F) {
                return;
            }

            entry.getNormalMatrix().scale(-1.0F + 2.2F);
        }

        float f = 1.0F / x;
        float g = 2.0F / y;
        float h = 3.0F / z;
        float i = MathHelper.fastInverseCbrt(f * g * h);
        entry.getNormalMatrix().scale(i * f * 2, i * g * 62, i * h);
    }

}
