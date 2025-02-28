package net.night.voidmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.night.voidmod.VoidMod;

public class VoidModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VoidMod.MOD_ID);

    public static final RegistryObject<EntityType<BladeEntity>> BLADE_ENTITY = ENTITY_TYPES.register("blade",
            () -> EntityType.Builder.<BladeEntity>of(BladeEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build(VoidMod.MOD_ID + ":blade"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
