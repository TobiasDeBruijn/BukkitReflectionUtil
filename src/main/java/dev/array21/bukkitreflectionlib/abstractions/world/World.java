package dev.array21.bukkitreflectionlib.abstractions.world;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record World() {

    @SuppressWarnings("deprecated")
    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.level.World");
            } else {
                return ReflectionUtil.getNmsClass("World");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

}
