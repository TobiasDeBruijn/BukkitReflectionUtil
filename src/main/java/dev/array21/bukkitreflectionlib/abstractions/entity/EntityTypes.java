package dev.array21.bukkitreflectionlib.abstractions.entity;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntityTypes() {

    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.entity.EntityTypes");
            } else {
                return ReflectionUtil.getNmsClass("EntityTypes");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Object getMagmaCubeType() throws ReflectException {
        Class<?> inner = getInnerClass();
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return switch(ReflectionUtil.getMajorVersion()) {
                    case 19 -> ReflectionUtil.getObject(inner, (Object) null, "aa");
                    default -> ReflectionUtil.getObject(inner, (Object) null, "X");
                };
            } else {
                return ReflectionUtil.getObject(inner, (Object) null, "MAGMA_CUBE");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Object getSlimeType() throws ReflectException {
        Class<?> inner = getInnerClass();
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return switch(ReflectionUtil.getMajorVersion()) {
                    case 19 -> ReflectionUtil.getObject(inner, (Object) null, "aG");
                    default -> ReflectionUtil.getObject(inner, (Object) null, "X");
                };
            } else {
                return ReflectionUtil.getObject(inner, (Object) null, "SLIME");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
