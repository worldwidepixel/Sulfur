package ca.worldwidepixel.sulfur.mixin;

import ca.worldwidepixel.sulfur.shader.SuperSecret;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuMixin extends Screen {

    @Shadow protected abstract ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier);

    protected GameMenuMixin(Text title) {
        super(title);
    }
    @Inject(
            method = "initWidgets",
            at = @At("HEAD")
    )
    private void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.secret"), (button) -> {
            SuperSecret.changeShader();
        }).dimensions(this.width / 2 - (98 / 2), this.height - 24, 98, 20).build());
    }
}
