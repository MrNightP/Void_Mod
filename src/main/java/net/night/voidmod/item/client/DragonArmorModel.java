package net.night.voidmod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.night.voidmod.VoidMod;
import net.night.voidmod.item.custom.DragonArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class DragonArmorModel extends GeoModel<DragonArmorItem> {
    @Override
    public ResourceLocation getModelResource(DragonArmorItem animatable) {
        return new ResourceLocation(VoidMod.MOD_ID, "geo/dragon_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DragonArmorItem animatable) {
        return new ResourceLocation(VoidMod.MOD_ID, "textures/armor/dragon_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DragonArmorItem animatable) {
        return new ResourceLocation(VoidMod.MOD_ID, "animations/dragon_armor.animation.json");
    }
}
