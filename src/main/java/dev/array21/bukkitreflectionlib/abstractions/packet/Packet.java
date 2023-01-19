package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.PlayerConnection;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

import java.util.Objects;

public interface Packet {
    Object getInner();

    default void send(PlayerConnection playerConnection) throws ReflectException {
        Object packetInner = this.getInner();
        if(packetInner == null) {
            // Made this mistake before,
            // added this warning to immediately inform me of the issue
            System.err.println("[BukkitReflectionUtil] Received `null` value for packet data. This is a programmer error!");
        }

        try {
            switch (ReflectionUtil.getMajorVersion()) {
                case 16, 17 -> ReflectionUtil.invokeMethod(
                        playerConnection.inner(),
                        "sendPacket",
                        new Class<?>[] { getPacketClass() },
                        new Object[] { packetInner });
                case 18, 19 -> ReflectionUtil.invokeMethod(
                        playerConnection.inner(),
                        "a",
                        new Class<?>[] { getPacketClass() },
                        new Object[] { packetInner });
                default -> throw new RuntimeException("Unsupported version");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
    private static Class<?> getPacketClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.protocol.Packet");
            } else {
                return ReflectionUtil.getNmsClass("Packet");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
