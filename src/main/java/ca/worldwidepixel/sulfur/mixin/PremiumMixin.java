package ca.worldwidepixel.sulfur.mixin;

import ca.worldwidepixel.sulfur.screen.MarketplaceScreen;
import ca.worldwidepixel.sulfur.screen.MinecoinScreen;
import ca.worldwidepixel.sulfur.screen.PremiumScreen;
import ca.worldwidepixel.sulfur.screen.RickRollScreen;
import ca.worldwidepixel.sulfur.shader.SuperSecret;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.WorldGenerationProgressTracker;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(TitleScreen.class)
public class PremiumMixin extends Screen {
    protected PremiumMixin(Text title) {
        super(title);
    }

    @Inject(
            method = "init",
            at = @At("HEAD")
    )
    protected void init(CallbackInfo ci) {
        int l = this.height / 4 + 48;
        int y = l;
        int spacingY = 24;
        Identifier MINECOIN_TEXTURE = new Identifier("sulfur", "textures/gui/minecoin.png");
        Identifier DIHYDRO_TEXTURE = new Identifier("sulfur", "textures/gui/dihydro.png");

        this.addDrawableChild(
                new TexturedButtonWidget(
                        this.width / 2 - 124,
                        y + spacingY * 2,
                        20,
                        20,
                        0,
                        0,
                        20,
                        MINECOIN_TEXTURE,
                        20,
                        40,
                        button -> this.client.setScreen(new MinecoinScreen()),
                        Text.translatable("narrator.button.minecoins")
                )
        );
        this.addDrawableChild(
                new TexturedButtonWidget(
                        this.width / 2 + 104,
                        y + spacingY * 2,
                        20,
                        20,
                        0,
                        0,
                        20,
                        DIHYDRO_TEXTURE,
                        20,
                        40,
                        (button) -> {
                            Util.getOperatingSystem().open("https://youtube.com/playlist?list=PLloy2Jjr9RsQfzJHYKolokxQc8ioqoCwb");
                        },
                        Text.translatable("narrator.button.dihydro")
                )
        );
    }

    @Inject(
            method = "initWidgetsNormal",
            at = @At("HEAD")
    )
    private void initWidgetsNormal(int y, int spacingY, CallbackInfo ci) {
        int l = this.height / 4 + 48;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.premium"), (button) -> {
            //Sulfur.LOGGER.info("WOW");
            this.client.setScreen(new PremiumScreen(client.currentScreen, Text.translatable("premium.title"), Text.translatable("premium.subtitle")));
        }).dimensions(this.width / 2 + 2, y, 98, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.secret"), (button) -> {
            SuperSecret.changeShader();
        }).dimensions(this.width / 2 + 2, y + spacingY, 98, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.rick"), (button) -> {
            //Util.getOperatingSystem().open("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            this.client.setScreen(new RickRollScreen(Text.translatable("rick.title")));
        }).dimensions(this.width / 2 + 2, y + spacingY * 2, 98, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.market"), (button) -> {
            this.client.setScreen(new MarketplaceScreen(Text.translatable("menu.market")));
        }).dimensions(this.width / 2 - (200 / 2), l + 72 + 12 + spacingY, 200, 20).build());
        //this.addDrawableChild(ButtonWidget.builder(Text.literal("DEBUG MENU"), (button) -> {
        //    this.client.setScreen(new LevelLoadingScreen(new WorldGenerationProgressTracker(0)));
        //}).dimensions(this.width / 2 + 2, y + spacingY * 3, 98, 20).build());
    }

    @ModifyArg(
            method = "initWidgetsNormal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"
            ),
            index = 2
    )
    public int width(int x) {
        return 98;
    }

    @Override
    public Optional<Element> hoveredElement(double mouseX, double mouseY) {
        return super.hoveredElement(mouseX, mouseY);
    }
}
