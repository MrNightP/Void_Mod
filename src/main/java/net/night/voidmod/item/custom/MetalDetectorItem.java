package net.night.voidmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.night.voidmod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            // Список для хранения найденных блоков и их координат
            StringBuilder foundBlocks = new StringBuilder();

            // Радиус поиска по горизонтальной оси (X и Z)
            int searchRadiusXZ = 8;

            // Глубина поиска вниз и высота поиска вверх по вертикальной оси (Y)
            int searchDepth = 16;   // Глубина поиска вниз
            int searchHeight = 16;  // Высота поиска вверх

            // Переменная для подсчёта найденных блоков
            int foundCount = 0;

            // Переводы для локализации
            String foundText = I18n.get("message.found");
            String atText = I18n.get("message.at");
            String valuableBlocksText = I18n.get("message.valuable_blocks");
            String noValuableOresText = I18n.get("message.no_valuable_ores");

            // Перебираем блоки в указанном радиусе
            for (int dx = -searchRadiusXZ; dx <= searchRadiusXZ; dx++) {
                for (int dz = -searchRadiusXZ; dz <= searchRadiusXZ; dz++) {
                    for (int dy = -searchDepth; dy <= searchHeight; dy++) { // Здесь добавлены диапазоны высоты и глубины
                        BlockPos checkingPos = positionClicked.offset(dx, dy, dz);
                        BlockState state = pContext.getLevel().getBlockState(checkingPos);

                        if (isValuableBlock(state)) {
                            // Добавляем найденный блок в список
                            foundBlocks.append("- ")
                                    .append(I18n.get(state.getBlock().getDescriptionId()))
                                    .append(" ")
                                    .append(atText)
                                    .append(" (x:")
                                    .append(checkingPos.getX())
                                    .append(", y:")
                                    .append(checkingPos.getY())
                                    .append(", z:")
                                    .append(checkingPos.getZ())
                                    .append(")\n");

                            foundCount++;
                        }
                    }
                }
            }

            // Отправляем одно сообщение со всеми найденными блоками
            if (foundCount == 0) {
                player.sendSystemMessage(Component.literal(noValuableOresText));
            } else {
                player.sendSystemMessage(Component.literal(
                        foundText + " " + foundCount + " " + valuableBlocksText + ":\n" + foundBlocks));
            }
        }

        // Уменьшаем долговечность инструмента
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.voidmod.metal_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(x:" + blockPos.getX() + " ,y:" + blockPos.getY() + " ,z:" + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE)
                || state.is(Blocks.DIAMOND_ORE)
                || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)
                || state.is(Blocks.DEEPSLATE_IRON_ORE)
                || state.is(ModBlocks.VoidShardOreStone.get())
                || state.is(ModBlocks.EndGemOre.get());
    }

}