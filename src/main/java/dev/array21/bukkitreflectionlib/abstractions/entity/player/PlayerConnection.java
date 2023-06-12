package dev.array21.bukkitreflectionlib.abstractions.entity.player;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record PlayerConnection(Object inner) {

    static PlayerConnection getInstance(CraftPlayer craftPlayer) throws ReflectException {
        try {
            Object inner;

            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                inner = switch(ReflectionUtil.getMajorVersion()) {
                    case 17, 18, 19 -> ReflectionUtil.getObject(craftPlayer.inner(), "b");
                    case 20 -> ReflectionUtil.getObject(craftPlayer.inner(), "c");
                    default -> throw new RuntimeException("Unsupported major version");
                };
            } else {
                inner = ReflectionUtil.getObject(craftPlayer.inner(), "playerConnection");
            }

            return new PlayerConnection(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
