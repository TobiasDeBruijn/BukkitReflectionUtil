package dev.array21.bukkitreflectionlib.abstractions.entity;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public interface EntityLiving extends Entity {

    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.entity.EntityLiving");
            } else {
                return ReflectionUtil.getNmsClass("EntityLiving");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
