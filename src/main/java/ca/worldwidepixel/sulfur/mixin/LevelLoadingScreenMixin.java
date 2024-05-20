package ca.worldwidepixel.sulfur.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public class LevelLoadingScreenMixin {

    private static final Identifier ADRINTH = new Identifier("sulfur", "textures/gui/adrinth.png");

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {

        context.drawTexture(ADRINTH, context.getScaledWindowWidth() / 2 - 256 / 2,  80, 0, 0, 256, 30);

    }

    }
