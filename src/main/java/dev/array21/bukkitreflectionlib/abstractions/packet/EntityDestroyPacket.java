package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.Entity;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntityDestroyPacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    /**
     * Get an instance of the entity destroy packet.
     * On 1.17.0, only the first entity is destroyed
     * @param entites The entities to destroy
     * @return The packet
     * @throws ReflectException
     */
    public static EntityDestroyPacket getInstance(Entity[] entites) throws ReflectException {
        try {
            Class<?>[] classes = switch(ReflectionUtil.getMajorVersion()) {
                case 17 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 0 -> new Class<?>[] { int.class};
                    default -> new Class<?>[] { int[].class };
                };
                default -> new Class<?>[] { int[].class };
            };

            Object[] objects = switch(ReflectionUtil.getMajorVersion()) {
                case 17 -> switch (ReflectionUtil.getMinorVersion()) {
                    case 0 -> new Object[] { entites[0].getId() };
                    default -> new Object[] { getIdsFromEntities(entites) };
                };
                default -> new Object[] { getIdsFromEntities(entites) };
            };

            Object inner = ReflectionUtil.invokeConstructor(getInnerClass(), classes, objects);
            return new EntityDestroyPacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static int[] getIdsFromEntities(Entity[] entities) throws ReflectException {
        int[] result = new int[entities.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = entities[i].getId();
        }

        return result;
    }

    private static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.protocol.game.PacketPlayOutEntityDestroy");
            } else {
                return ReflectionUtil.getNmsClass("PacketPlayOutEntityDestroy");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
