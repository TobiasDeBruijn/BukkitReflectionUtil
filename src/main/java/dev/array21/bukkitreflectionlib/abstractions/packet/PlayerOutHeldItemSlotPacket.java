package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.entity.Player;

/**
 * Packet to set the item held by the player
 * @param inner The inner object
 */
public record PlayerOutHeldItemSlotPacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static PlayerOutHeldItemSlotPacket getInstance(Player player) throws ReflectException {
        try {
            Class<?> clazz = getPacketPlayOutHeldItemSlotClass();
            Object inner = ReflectionUtil.invokeConstructor(clazz,
                    new Class<?>[] { int.class },
                    new Object[] { player.getInventory().getHeldItemSlot() });

            return new PlayerOutHeldItemSlotPacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> getPacketPlayOutHeldItemSlotClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.protocol.game.PacketPlayOutHeldItemSlot");
            } else {
                return ReflectionUtil.getNmsClass("PacketPlayOutHeldItemSlot");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
