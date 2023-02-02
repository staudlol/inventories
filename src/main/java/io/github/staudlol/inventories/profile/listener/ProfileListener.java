package io.github.staudlol.inventories.profile.listener;

import io.github.staudlol.inventories.profile.PlayerProfile;
import io.github.staudlol.inventories.profile.ProfileHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class ProfileListener implements Listener {

    private final ProfileHandler profileHandler;

    @EventHandler
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        this.profileHandler.findOrCreateProfile(
                event.getUniqueId(),
                event.getName()
        );
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final UUID uniqueId = event.getPlayer().getUniqueId();
        final PlayerProfile playerProfile = this.profileHandler.findOrCreateProfile(uniqueId, null);

        this.profileHandler.saveProfile(playerProfile);
    }
}
