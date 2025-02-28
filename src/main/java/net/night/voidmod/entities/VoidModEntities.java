package net.night.voidmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.VoidMod;
import net.night.voidmod.entity.BladeEntity;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Bus.MOD)
public class VoidModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VoidMod.MOD_ID);

    public static final RegistryObject<EntityType<BladeEntity>> BLADE_ENTITY = ENTITY_TYPES.register("blade",
            () -> EntityType.Builder.<BladeEntity>of(BladeEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("blade"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

