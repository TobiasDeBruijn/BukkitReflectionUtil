package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * Packet to set the position of the Player
 * @param inner The inner object
 */
public record PlayerOutPositionPacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static PlayerOutPositionPacket getInstance(Location location) throws ReflectException {
        try {
            Class<?> clazz = getPacketPlayOutPositionClass();

            Object inner;
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                inner = switch(ReflectionUtil.getMajorVersion()) {
                    case 19 -> switch(ReflectionUtil.getMinorVersion()) {
                        case 4 -> getOldPackagingAndPostEqual1_19_4(clazz, location);
                        default -> getInnerNewPackagingBefore1_19_4(clazz, location);
                    };
                    case 20 -> getOldPackagingAndPostEqual1_19_4(clazz, location);
                    default -> getInnerNewPackagingBefore1_19_4(clazz, location);
                };
            } else {
                inner = getOldPackagingAndPostEqual1_19_4(clazz, location);
            }

            return new PlayerOutPositionPacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Object getOldPackagingAndPostEqual1_19_4(Class<?> clazz, Location location) throws Exception {
        return ReflectionUtil.invokeConstructor(clazz,
                new Class<?>[] { double.class, double.class, double.class, float.class, float.class, Set.class, int.class },
                new Object[] { location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), new HashSet<Enum<?>>(), 0 });
    }

    private static Object getInnerNewPackagingBefore1_19_4(Class<?> clazz, Location location) throws Exception {
        return ReflectionUtil.invokeConstructor(clazz,
                new Class<?>[] { double.class, double.class, double.class, float.class, float.class, Set.class, int.class, boolean.class },
                new Object[] { location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), new HashSet<Enum<?>>(), 0, false });
    }

    private static Class<?> getPacketPlayOutPositionClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.protocol.game.PacketPlayOutPosition");
            } else {
                return ReflectionUtil.getNmsClass("PacketPlayOutPosition");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

}
