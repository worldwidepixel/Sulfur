package ca.worldwidepixel.sulfur.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EnderChestBlock;
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
     * @WorldWidePixel
     * @Because Enderchests are for paying subscribers :(
     */
    @Overwrite
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.sendMessage(Text.literal("Hey, " + player.getGameProfile().getName() + "! That feature is for Premium subscribers."));
        return ActionResult.success(world.isClient);
    }
}
