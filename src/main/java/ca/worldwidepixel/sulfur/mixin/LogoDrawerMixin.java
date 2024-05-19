package ca.worldwidepixel.sulfur.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {

    @Mutable
    @Shadow @Final private boolean minceraft;

    @Inject(
            method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void draw(DrawContext context, int screenWidth, float alpha, int y, CallbackInfo ci) {
        this.minceraft = true;
    }
}
