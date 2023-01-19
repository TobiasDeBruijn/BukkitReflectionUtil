package dev.array21.bukkitreflectionlib.abstractions.packet;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.abstractions.chat.IChatBaseComponent;
import dev.array21.bukkitreflectionlib.abstractions.chat.RemoteChatSessionData;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.CraftPlayer;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.EnumGamemode;
import dev.array21.bukkitreflectionlib.abstractions.entity.player.GameProfile;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public record ClientboundPlayerInfoUpdatePacket(Object inner) implements Packet {

    @Override
    public Object getInner() {
        return this.inner;
    }

    public static ClientboundPlayerInfoUpdatePacket getInstance(CraftPlayer craftPlayer, PlayerInfoUpdateAction action) throws ReflectException {
        try {
            PlayerInfoUpdateActionConstant actionConstant = PlayerInfoUpdateActionConstant.getInstance(action);
            Object enumSet = ReflectionUtil.invokeMethod(
                    EnumSet.class,
                    null,
                    "of",
                    new Class<?>[] { Enum.class },
                    new Object[] { actionConstant.inner }
            );

            Object inner = ReflectionUtil.invokeConstructor(
                    getMinecraftClass(),
                    new Class<?>[] { EnumSet.class, Collection.class },
                    new Object[] { enumSet, List.of(craftPlayer.inner()) }
            );

            return new ClientboundPlayerInfoUpdatePacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> getMinecraftClass() throws ReflectException {
        try {
            return ReflectionUtil.getMinecraftClass("network.protocol.game.ClientboundPlayerInfoUpdatePacket");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static ClientboundPlayerInfoUpdatePacket createPlayerInitializing(CraftPlayer craftPlayer) throws ReflectException {
        try {
            Object inner = ReflectionUtil.invokeMethod(
                    getMinecraftClass(),
                    null,
                    "a",
                    new Class<?>[] { Collection.class },
                    new Object[] { List.of(craftPlayer.inner()) }
            );

            return new ClientboundPlayerInfoUpdatePacket(inner);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private record PlayerInfoUpdateEntry(Object inner) {
        public static PlayerInfoUpdateEntry getInstance(CraftPlayer player) throws ReflectException {
            try {
                Class<?> clazz = ReflectionUtil.getMinecraftClass("network.protocol.game.ClientboundPlayerInfoUpdatePacket$b");

                GameProfile gameProfile = player.getGameProfile();
                EnumGamemode enumGamemode = player.getPlayerInteractManager().getEnumGamemode();
                IChatBaseComponent iChatBaseComponent = player.getChatBaseComponent();
                RemoteChatSessionData remoteChatSessionData = player.getChatSession().getData();

                Object inner = ReflectionUtil.invokeConstructor(
                        clazz,
                        new Class<?>[] {
                                UUID.class,
                                gameProfile.inner().getClass(),
                                boolean.class,
                                int.class,
                                enumGamemode.inner().getClass(),
                                IChatBaseComponent.getInterfaceClass(),
                                remoteChatSessionData.inner().getClass(),
                        },
                        new Object[] {
                                player.bukkitPlayer().getUniqueId(),
                                gameProfile.inner(),
                                true, // TODO figure out real value for 'listed'
                                1, // TODO figure out real value for 'latency',
                                enumGamemode.inner(),
                                iChatBaseComponent.inner(),
                                remoteChatSessionData.inner()
                        }
                );

                return new PlayerInfoUpdateEntry(inner);
            } catch (Exception e) {
                throw new ReflectException(e);
            }
        }
    }

    private record PlayerInfoUpdateActionConstant(Object inner) {
        public static PlayerInfoUpdateActionConstant getInstance(PlayerInfoUpdateAction action) throws ReflectException {
            try {
                String constantName = switch(action) {
                    case ADD_PLAYER -> "ADD_PLAYER";
                };

                Object inner = ReflectionUtil.getEnum(
                        ReflectionUtil.getMinecraftClass("network.protocol.game.ClientboundPlayerInfoUpdatePacket"),
                        "a",
                        constantName
                );

                return new PlayerInfoUpdateActionConstant(inner);
            } catch (Exception e) {
                throw new ReflectException(e);
            }
        }
    }


    public enum PlayerInfoUpdateAction {
        ADD_PLAYER,
    }

}
