package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.CraftPlayer;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public record ClientboundPlayerInfoRemovePacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static ClientboundPlayerInfoRemovePacket getInstance(CraftPlayer player) throws ReflectException {
        try {
            if (ReflectionUtil.getMajorVersion() >= 19 && (ReflectionUtil.getMajorVersion() == 19 && ReflectionUtil.getMinorVersion() < 3)) {
                throw new RuntimeException("This packet is not supported on this Minecraft version");
            }

            UUID playerUuid = player.bukkitPlayer().getUniqueId();

            Class<?> clazz = ReflectionUtil.getMinecraftClass("network.protocol.game.ClientboundPlayerInfoRemovePacket");
            Object inner = ReflectionUtil.invokeConstructor(
                    clazz,
                    new Class<?>[] { List.class },
                    new Object[] { List.of(playerUuid) }
            );

            return new ClientboundPlayerInfoRemovePacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
