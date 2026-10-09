package com.yourname.configablesigns;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class ConfigableSigns implements ModInitializer {
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient()) return ActionResult.PASS;

            if (player.isSneaking()) {
                BlockPos pos = hitResult.getBlockPos();
                BlockEntity blockEntity = world.getBlockEntity(pos);

                if (blockEntity instanceof SignBlockEntity sign) {
                    player.sendMessage(Text.literal("§b[ConfigableSigns] Selected Sign at " + pos.toShortString()), true);
                    player.sendMessage(Text.literal("§eSign interval configuration activated!"), false);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}