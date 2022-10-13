package dev.array21.bukkitreflectionlib.abstractions.entity.monster;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityTypes;
import dev.array21.bukkitreflectionlib.abstractions.world.CraftWorld;
import dev.array21.bukkitreflectionlib.abstractions.world.World;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntitySlime(Object inner) implements IEntitySlime {

    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.entity.monster.EntitySlime");
            } else {
                return ReflectionUtil.getNmsClass("EntitySlime");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static EntitySlime getInstance(CraftWorld craftWorld) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeConstructor(getInnerClass(),
                    new Class<?>[] { EntityTypes.getInnerClass(), World.getInnerClass() },
                    new Object[] { EntityTypes.getSlimeType(), craftWorld.inner() });
            return new EntitySlime(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
