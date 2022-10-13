package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityLiving;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntitySpawnPacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static EntitySpawnPacket getInstance(EntityLiving entityLiving) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeConstructor(getInnerClass(), new Class<?>[] { EntityLiving.getInnerClass() }, new Object[] { entityLiving.getInner() });
            return new EntitySpawnPacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                String name = switch(ReflectionUtil.getMajorVersion()) {
                    case 19 -> "network.protocol.game.PacketPlayOutSpawnEntity";
                    default -> "network.protocol.game.PacketPlayOutSpawnEntityLiving";
                };

                return ReflectionUtil.getMinecraftClass(name);
            } else {
                return ReflectionUtil.getNmsClass("PacketPlayOutSpawnEntityLiving");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
