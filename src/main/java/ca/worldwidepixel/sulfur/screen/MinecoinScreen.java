package ca.worldwidepixel.sulfur.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class MinecoinScreen extends Screen {
	private static final Identifier DEMO_BG = new Identifier("sulfur", "textures/gui/minecoin_bg.png");
	private MultilineText movementText = MultilineText.EMPTY;
	private MultilineText fullWrappedText = MultilineText.EMPTY;

	public MinecoinScreen() {
		super(Text.translatable("menu.minecoins.title"));
	}

	@Override
	protected void init() {
		Random random = new Random();
		int amount = random.nextInt(0, 6500);
		Text coinStatus = Text.translatable("menu.minecoins.status").append(" ").append(Text.of(String.valueOf(amount))).append(" ")
				.append(Text.literal("Minecoins."));
		int i = -16;
		this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.minecoins.buy"), button -> {
			button.active = false;
			this.client.setScreen(new BuyMinecoinScreen(Text.translatable("menu.minecoins.buy")));
		}).dimensions(this.width / 2 - 116, this.height / 2 + 62 + -16, 114, 20).build());
		this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.minecoins.ignore"), button -> {
			this.client.setScreen(null);
			this.client.mouse.lockCursor();
		}).dimensions(this.width / 2 + 2, this.height / 2 + 62 + -16, 114, 20).build());
		GameOptions gameOptions = this.client.options;
		this.movementText = MultilineText.create(
			this.textRenderer,
			coinStatus,
			Text.literal(" "),
			Text.translatable("menu.minecoins.price500"),
			Text.translatable("menu.minecoins.price1000")
		);
		this.fullWrappedText = MultilineText.create(this.textRenderer, Text.translatable("menu.minecoins.desc"), 218);
	}

	@Override
	public void renderBackground(DrawContext context) {
		super.renderBackground(context);
		int i = (this.width - 248) / 2;
		int j = (this.height - 166) / 2;
		context.drawTexture(DEMO_BG, i, j, 0, 0, 248, 166);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context);
		int i = (this.width - 248) / 2 + 10;
		int j = (this.height - 166) / 2 + 8;
		context.drawText(this.textRenderer, this.title, i, j, 2039583, false);
		j = this.movementText.draw(context, i, j + 12, 12, 5197647);
		this.fullWrappedText.draw(context, i, j + 20, 9, 2039583);
		super.render(context, mouseX, mouseY, delta);
	}
}
