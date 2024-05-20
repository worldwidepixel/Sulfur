package ca.worldwidepixel.sulfur.mixin;

import ca.worldwidepixel.sulfur.screen.PremiumScreen;
import ca.worldwidepixel.sulfur.screen.RickRollScreen;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DemoScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnderChestBlock.class)
public class EnderChestMixin {
    /**
     * @author WWP
     * @reason Because Enderchests are for paying subscribers :(
     */
    @Overwrite
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        MinecraftClient client = MinecraftClient.getInstance();
        player.sendMessage(Text.literal("Hey, " + player.getGameProfile().getName() + "! That feature is for Premium subscribers."));
        client.setScreen(new PremiumScreen((Screen)null, Text.translatable("premium.title"), Text.translatable("premium.subtitle")));
        return ActionResult.success(world.isClient);
    }
}
