package dev.array21.bukkitreflectionlib.abstractions.entity;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

import java.sql.Ref;

public interface Entity {

    Object getInner();

    static Class<?> getInnerClass() throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                return ReflectionUtil.getMinecraftClass("world.entity.Entity");
            } else {
                return ReflectionUtil.getNmsClass("Entity");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    default void setFlag(int idx, boolean value) throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                ReflectionUtil.invokeMethod(Entity.getInnerClass(), this.getInner(), "b", new Class<?>[] { int.class, boolean.class }, new Object[] { idx, value });
            } else {
                ReflectionUtil.invokeMethod(Entity.getInnerClass(), this.getInner(), "setFlag", new Class<?>[] { int.class, boolean.class }, new Object[] { idx, value });
            }
        } catch (Exception e) {
            throw new ReflectException((e));
        }
    }

    default void setInvisible(boolean invisible) throws ReflectException {
        this.setFlag(5, invisible);
    }

    default void setGlowing(boolean glowing) throws ReflectException {
        this.setFlag(6, glowing);
    }

    default void setLocation(double x, double y, double z, float xrot, float yrot) throws ReflectException {
        try {
            if(ReflectionUtil.isUseNewSpigotPackaging()) {
                Class<?> entityClass = Entity.getInnerClass();
                // setPosRaw
                ReflectionUtil.invokeMethod(entityClass, this.getInner(), "o",
                        new Class<?>[] { double.class, double.class, double.class },
                        new Object[] { x, y, z });
                // setYRot
                ReflectionUtil.invokeMethod(entityClass, this.getInner(), "o",
                        new Class<?>[] { float.class },
                        new Object[] { xrot });
                // setXRot
                ReflectionUtil.invokeMethod(entityClass, this.getInner(), "p",
                        new Class<?>[] { float.class },
                        new Object[] { yrot });
            } else {
                ReflectionUtil.invokeMethod(Entity.getInnerClass(), this.getInner(), "setLocation",
                        new Class<?>[] { double.class, double.class, double.class, float.class, float.class},
                        new Object[] { x, y, xrot, yrot});
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    default int getId() throws ReflectException {
        try {
            if(ReflectionUtil.getMajorVersion() >= 18) {
                return (Integer) ReflectionUtil.invokeMethod(Entity.getInnerClass(), this.getInner(), "ae");
            } else {
                return (Integer) ReflectionUtil.invokeMethod(Entity.getInnerClass(), this.getInner(), "getId");
            }
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    default DataWatcher getDataWatcher() throws ReflectException {
        return DataWatcher.getInstance(this);
    }
}
