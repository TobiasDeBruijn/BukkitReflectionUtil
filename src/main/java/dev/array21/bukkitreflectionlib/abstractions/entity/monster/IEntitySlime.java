package dev.array21.bukkitreflectionlib.abstractions.entity.monster;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityLiving;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public interface IEntitySlime extends EntityLiving {

    default void setSize(int size) throws ReflectException {
        try {
            // TODO unsure what the boolean flag does
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                ReflectionUtil.invokeMethod(EntitySlime.getInnerClass(), this.getInner(), "a", new Class<?>[] { int.class, boolean.class }, new Object[] { size, true });
            } else {
                ReflectionUtil.invokeMethod(EntitySlime.getInnerClass(), this.getInner(), "setSize", new Class<?>[] { int.class, boolean.class }, new Object[] { size, true });
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
