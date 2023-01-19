package dev.array21.bukkitreflectionlib.abstractions.chat;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.CraftPlayer;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record RemoteChatSession(Object inner) {

    public static RemoteChatSession getInstance(CraftPlayer craftPlayer) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeMethod(
                    craftPlayer.inner(),
                    "Y"
            );

            return new RemoteChatSession(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public RemoteChatSessionData getData() throws ReflectException {
        return RemoteChatSessionData.getInstance(this);
    }
}
