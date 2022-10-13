package dev.array21.bukkitreflectionlib.abstractions.entity.monster;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityLiving;
import dev.array21.bukkitreflectionlib.abstractions.entity.EntityTypes;
import dev.array21.bukkitreflectionlib.abstractions.world.CraftWorld;
import dev.array21.bukkitreflectionlib.abstractions.world.World;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record EntityMagmaCube(Object inner) implements EntityLiving, IEntitySlime {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.entity.monster.EntityMagmaCube");
            } else {
                return ReflectionUtil.getNmsClass("EntityMagmaCube");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static EntityMagmaCube getInstance(CraftWorld craftWorld) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeConstructor(getInnerClass(), new Class<?>[] {EntityTypes.getInnerClass(), World.getInnerClass() }, new Object[] { EntityTypes.getMagmaCubeType(), craftWorld.inner() });
            return new EntityMagmaCube(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
