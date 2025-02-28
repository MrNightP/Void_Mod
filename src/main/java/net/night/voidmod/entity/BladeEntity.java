package net.night.voidmod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class BladeEntity extends Entity {
    private final Player owner;
    private int mode = 1;
    private boolean returning = false;

    public BladeEntity(EntityType<? extends BladeEntity> entityType, Level world) {
        super(entityType, world);
        this.owner = null;
    }

    public BladeEntity(Level world, Player owner, int mode) {
        this(VoidModEntities.BLADE_ENTITY.get(), world);
        this.mode = mode;
        this.setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public void tick() {
        super.tick();
        if (owner == null || !owner.isAlive()) {
            this.discard();
            return;
        }

        if (returning) {
            this.setDeltaMovement(owner.position().subtract(this.position()).normalize().scale(0.7));
            if (this.position().distanceTo(owner.position()) < 1.5) {
                this.discard();
            }
        }
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
