//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ca.worldwidepixel.sulfur.screen;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class PremiumScreen extends Screen {
    private final Screen parent;
    private final Text subtitle;
    private MultilineText wrappedText;
    protected int field_32236;

    public PremiumScreen(Screen parent, Text title, Text subtitle) {
        super(title);
        this.wrappedText = MultilineText.EMPTY;
        this.parent = parent;
        this.subtitle = subtitle;
    }

    public static void openScreen(String menuTitle) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.setScreen(new PremiumScreen((Screen)null, Text.literal(menuTitle), Text.translatable("premium.subtitle")));
    }

    protected void init() {
        super.init();
        this.wrappedText = MultilineText.create(this.textRenderer, this.subtitle, this.width - 50);
        int var10000 = this.wrappedText.count() + 1;
        Objects.requireNonNull(this.textRenderer);
        int i = var10000 * 9;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("premium.buy"), (button) -> {
            this.client.setScreen(new RickRollScreen(Text.translatable("rick.title")));
        }).dimensions(this.width / 2 - 155, 100 + i, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("premium.plus"), (button) -> {
            Util.getOperatingSystem().open("https://github.com/CaffeineMC/sodium-fabric/issues/2400");
        }).dimensions(this.width / 2 - 155 + 160, 100 + i, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> {
            this.client.setScreen(this.parent);
        }).dimensions(this.width / 2 - 155 + 80, 124 + i, 150, 20).build());

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 50, 16777215);
        this.wrappedText.drawCenterWithShadow(context, this.width / 2, 70);
        super.render(context, mouseX, mouseY, delta);
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.client.setScreen(this.parent);
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Environment(EnvType.CLIENT)
    public interface Callback {
        void proceed(boolean backup, boolean eraseCache);
    }
}
