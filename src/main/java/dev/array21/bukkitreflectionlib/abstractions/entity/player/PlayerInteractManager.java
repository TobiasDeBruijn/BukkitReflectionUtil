package dev.array21.bukkitreflectionlib.abstractions.entity.player;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record PlayerInteractManager(Object inner) {
    static PlayerInteractManager getInstance(CraftPlayer craftPlayer) throws ReflectException {
        Object o;

        try {
            if (ReflectionUtil.isUseNewSpigotPackaging()) {
                o = switch(ReflectionUtil.getMajorVersion()) {
                    case 20 -> ReflectionUtil.getObject(craftPlayer.inner(), "e");
                    default -> ReflectionUtil.getObject(craftPlayer.inner(), "d");
                };
            } else {
                o = ReflectionUtil.getObject(craftPlayer.inner(), "playerInteractManager");
            }
        } catch(Exception e) {
            throw new ReflectException(e);
        }

        return new PlayerInteractManager(o);
    }

    public Gamemode getGamemode() throws ReflectException {
        return Gamemode.getInstance(this);
    }

    public EnumGamemode getEnumGamemode() throws ReflectException {
        return EnumGamemode.getInstance(this);
    }
}
