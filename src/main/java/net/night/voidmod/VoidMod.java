package net.night.voidmod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.night.voidmod.block.ModBlocks;
import net.night.voidmod.entity.VoidModEntities;
import net.night.voidmod.item.CreativeModTabs;
import net.night.voidmod.item.ModItems;
import net.night.voidmod.loot.ModLootModifiers;
import net.night.voidmod.network.DashPacket;
import net.night.voidmod.network.EffectPacket;
import net.night.voidmod.network.VoidModNetwork;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VoidMod.MOD_ID)
public class VoidMod {
    public static final String MOD_ID = "voidmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VoidMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VoidModEntities.ENTITY_TYPES.register(modEventBus);
        VoidModEntities.register(modEventBus);
        CreativeModTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::doClientStuff);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        VoidModEntities.register(modEventBus);
        VoidModNetwork.register();

        EffectPacket.register();
        DashPacket.register();

        ModLootModifiers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Настройка клиентской части
        }
    }
}
