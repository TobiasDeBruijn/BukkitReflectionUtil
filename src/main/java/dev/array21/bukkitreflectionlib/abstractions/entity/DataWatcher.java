package dev.array21.bukkitreflectionlib.abstractions.entity;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record DataWatcher(Object inner) {

    public static DataWatcher getInstance(Entity entity) throws ReflectException {
        try {
            Object inner;
            if(ReflectionUtil.getMajorVersion() >= 18) {
                inner = ReflectionUtil.invokeMethod(Entity.getInnerClass(), entity.getInner(), "ai");
            } else {
                inner = ReflectionUtil.invokeMethod(Entity.getInnerClass(), entity.getInner(), "getDataWatcher");
            }
            return new DataWatcher(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("network.syncher.DataWatcher");
            } else {
                return ReflectionUtil.getNmsClass("DataWatcher");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
