package dev.array21.bukkitreflectionlib.abstractions;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import org.bukkit.entity.Player;

import java.sql.Ref;

public class CraftPlayer {

    protected static Class<?> CLASS;

    static {
        try {
            CLASS = ReflectionUtil.getBukkitClass("entity.CraftPlayer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final Object craftPlayer;

    /**
     * Create a new CraftPlayer
     * @param player The Player
     */
    public CraftPlayer(Player player) {
        Object craftPlayer;
        try {
            craftPlayer = ReflectionUtil.invokeMethod(CLASS, player, "getHandle");
        } catch (Exception e) {
            e.printStackTrace();
            craftPlayer = null;
        }
        this.craftPlayer = craftPlayer;
    }

    /**
     * Get a connection to the Player
     * @return The PlayerConnection
     */
    public PlayerConnection getConnection() {
        try {
            return switch (ReflectionUtil.getMajorVersion()) {
                case 16 -> new PlayerConnection(ReflectionUtil.getObject(this.craftPlayer, "playerConnection"));
                default -> new PlayerConnection(ReflectionUtil.getObject(this.craftPlayer, "b"));
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
