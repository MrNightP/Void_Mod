package net.night.voidmod.item.client;

import net.night.voidmod.item.custom.DragonArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;


public class DragonArmorRenderer extends GeoArmorRenderer<DragonArmorItem> {
    public DragonArmorRenderer() {
        super(new DragonArmorModel());
    }
}
