package dev.array21.bukkitreflectionlib.abstractions;

import dev.array21.bukkitreflectionlib.ReflectionUtil;

public class PlayerConnection {

    private final Object connection;

    protected PlayerConnection(Object connection) {
        this.connection = connection;
    }

    private static Class<?> packetClass;

    static {
        try {
            packetClass = switch(ReflectionUtil.getMajorVersion()) {
                case 16 -> ReflectionUtil.getNmsClass("Packet");
                default -> ReflectionUtil.getMinecraftClass("network.protocol.Packet");
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a packet
     *
     * The caller must guarantee the passed in object is a Packet
     * @param packet The packet to send
     */
    public void sendPacket(Object packet) {
        try {
            switch(ReflectionUtil.getMajorVersion()) {
                case 16, 17 -> ReflectionUtil.invokeMethod(this.connection, "sendPacket", new Class<?>[] { packetClass }, new Object[] { packet });
                default -> ReflectionUtil.invokeMethod(this.connection, "a", new Class<?>[] { packetClass }, new Object[] { packet });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
