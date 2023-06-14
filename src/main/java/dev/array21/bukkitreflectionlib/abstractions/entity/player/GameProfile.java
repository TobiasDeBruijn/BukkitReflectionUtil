package dev.array21.bukkitreflectionlib.abstractions.entity.player;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

public record GameProfile(Object inner) {
    public static GameProfile getInstance(CraftPlayer craftPlayer) throws ReflectException {
        try {
            Class<?> entityHumanClass = getEntityHumanClass();

            Object inner = switch (ReflectionUtil.getMajorVersion()) {
                case 16, 17 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "getProfile");
                case 18 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 2 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fq");
                    default -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fp");
                };
                case 19 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 1, 2 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fy");
                    case 3 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fD");
                    case 4 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fI");
                    default -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fz");
                };
                case 20 -> switch(ReflectionUtil.getMinorVersion()) {
                    case 0, 1 -> ReflectionUtil.invokeMethod(entityHumanClass, craftPlayer.inner(), "fM");
                    default -> throw new RuntimeException("Unsupported minor version");
                };
                default -> throw new RuntimeException("Unsupported major version");
            };

            return new GameProfile(inner);

        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public PropertyMap getProperties() throws ReflectException {
        return PropertyMap.getInstance(this);
    }

    private static Class<?> getEntityHumanClass() throws ReflectException {
        try {
            return switch (ReflectionUtil.getMajorVersion()) {
                case 16 -> ReflectionUtil.getNmsClass("EntityHuman");
                case 17, 18, 19, 20 -> ReflectionUtil.getMinecraftClass("world.entity.player.EntityHuman");
                default -> throw new RuntimeException("Unsupported major version");
            };
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
