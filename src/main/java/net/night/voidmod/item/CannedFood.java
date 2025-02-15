package net.night.voidmod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CannedFood extends Item {
    public CannedFood(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            ItemStack emptyCan = new ItemStack(ModItems.Empty_MRE_Can.get());

            if (!player.getAbilities().instabuild) {
                if (!player.getInventory().add(emptyCan)) {
                    player.drop(emptyCan, false);
                }
            }
        }

        return resultStack;
    }
}