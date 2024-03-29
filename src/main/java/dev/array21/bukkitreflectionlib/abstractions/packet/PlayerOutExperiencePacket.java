package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.entity.Player;

/**
 * Packet to set the experience of a player.
 * Only usable in 1.18+
 * @param inner The inner object
 */
public record PlayerOutExperiencePacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static PlayerOutExperiencePacket getInstance(Player player) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeConstructor(getPacketPlayOutExperienceClass(),
                    new Class<?>[] { float.class, int.class, int.class },
                    new Object[] { player.getExp(), player.getTotalExperience(), player.getLevel() });

            return new PlayerOutExperiencePacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> getPacketPlayOutExperienceClass() throws ReflectException {
        try {
            if(ReflectionUtil.getMajorVersion() >= 18) {
                return ReflectionUtil.getMinecraftClass("network.protocol.game.PacketPlayOutExperience");
            } else {
                throw new IllegalStateException("This Minecraft version does not require the PacketPlayOutExperience packet to be send");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
