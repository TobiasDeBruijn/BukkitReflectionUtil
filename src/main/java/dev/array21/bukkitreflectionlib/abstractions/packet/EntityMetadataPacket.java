package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.DataWatcher;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityLiving;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntityMetadataPacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static EntityMetadataPacket getInstance(EntityLiving entityLiving) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeConstructor(
                    getPacketPlayOutEntityMetadataClass(),
                    new Class[] { int.class, DataWatcher.getInnerClass(), boolean.class },
                    new Object[] { entityLiving.getId(), entityLiving.getDataWatcher().inner(), false }
            );
            return new EntityMetadataPacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> getPacketPlayOutEntityMetadataClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.protocol.game.PacketPlayOutEntityMetadata");
            } else {
                return ReflectionUtil.getNmsClass("PacketPlayOutEntityMetadata");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
