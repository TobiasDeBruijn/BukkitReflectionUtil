package dev.array21.bukkitreflectionlib.abstractions.chat;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record RemoteChatSessionData(Object inner) {
    public static Class<?> getDataClass() throws ReflectException {
        try {
            return ReflectionUtil.getMinecraftClass("network.chat.RemoteChatSession$a");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static RemoteChatSessionData getInstance(RemoteChatSession session) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeMethod(
                    session.inner(),
                    "b"
            );
            return new RemoteChatSessionData(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
