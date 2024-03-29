package dev.array21.bukkitreflectionlib.abstractions.world;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.World;

public record CraftWorld(Object inner) {
    public static CraftWorld getInstance(World world) throws ReflectException {
        try {
            final Class<?> clazz = ReflectionUtil.getBukkitClass("CraftWorld");
            final Object entityPlayer = ReflectionUtil.invokeMethod(clazz, world, "getHandle");

            return new CraftWorld(entityPlayer);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public DimensionKey getDimensionKey() throws ReflectException {
        return DimensionKey.getInstance(this);
    }

    public DimensionManager getDimensionManager() throws ReflectException {
        return DimensionManager.getInstance(this);
    }

    public boolean isDebugWorld() throws ReflectException {
        try {
            return (boolean) switch(ReflectionUtil.getMajorVersion()) {
                case 16, 17 -> ReflectionUtil.invokeMethod(this.inner.getClass().getSuperclass(), this.inner, "isDebugWorld");
                case 18 -> ReflectionUtil.invokeMethod(this.inner.getClass().getSuperclass(), this.inner, "ad");
                case 19 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 3 -> ReflectionUtil.invokeMethod(this.inner.getClass().getSuperclass(), this.inner, "af");
                    default -> ReflectionUtil.invokeMethod(this.inner.getClass().getSuperclass(), this.inner, "ae");
                };
                case 20 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 0, 1 -> ReflectionUtil.invokeMethod(this.inner.getClass().getSuperclass(), this.inner, "af");
                    default -> throw new RuntimeException("Unsupported minor version");
                };
                default -> throw new RuntimeException("Unsupported major version");
            };
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public boolean isFlatWorld() throws ReflectException {
        try {
            return (boolean) switch(ReflectionUtil.getMajorVersion()) {
                case 16, 17 -> ReflectionUtil.invokeMethod(this.inner, "isFlatWorld");
                case 18 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 2 -> ReflectionUtil.invokeMethod(this.inner, "C");
                    default -> ReflectionUtil.invokeMethod(this.inner, "D");
                };
                case 19 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 4 -> ReflectionUtil.invokeMethod(this.inner, "z");
                    default -> ReflectionUtil.invokeMethod(this.inner, "A");
                };
                case 20 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 0, 1 -> ReflectionUtil.invokeMethod(this.inner, "z");
                    default -> throw new RuntimeException("Unsupported minor version");
                };
                default -> throw new RuntimeException("Unsupported major version");
            };
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
