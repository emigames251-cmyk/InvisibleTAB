package fr.emi.invisibletab;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class InvisibleTab implements ModInitializer {
    private static final Map<UUID, Boolean> LAST_INVISIBILITY_STATE = new HashMap<>();

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(InvisibleTab::updatePlayers);
    }

    private static void updatePlayers(MinecraftServer server) {
        Set<UUID> online = new HashSet<>();

        for (ServerPlayerEntity target : server.getPlayerManager().getPlayerList()) {
            UUID uuid = target.getUuid();
            online.add(uuid);

            boolean invisible = target.hasStatusEffect(StatusEffects.INVISIBILITY);
            Boolean previous = LAST_INVISIBILITY_STATE.put(uuid, invisible);

            // N'envoie un paquet que lorsque l'effet apparaît ou disparaît.
            if (previous != null && previous != invisible) {
                PlayerListS2CPacket packet = new PlayerListS2CPacket(
                        PlayerListS2CPacket.Action.UPDATE_LISTED,
                        target
                );

                for (ServerPlayerEntity viewer : server.getPlayerManager().getPlayerList()) {
                    viewer.networkHandler.sendPacket(packet);
                }
            }
        }

        LAST_INVISIBILITY_STATE.keySet().retainAll(online);
    }
}
