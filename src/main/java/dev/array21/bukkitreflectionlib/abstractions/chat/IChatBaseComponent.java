package dev.array21.bukkitreflectionlib.abstractions.chat;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.CraftPlayer;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record IChatBaseComponent(Object inner) {

    public static Class<?> getInterfaceClass() throws ReflectException {
        try {
            return ReflectionUtil.getMinecraftClass("network.chat.IChatBaseComponent");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static IChatBaseComponent getInstance(CraftPlayer craftPlayer) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeMethod(
                    craftPlayer.inner(),
                    "K"
            );

            return new IChatBaseComponent(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

}
