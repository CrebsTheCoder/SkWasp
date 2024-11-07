package com.crebsthecoder.skwasp.api.scoreboard;

import com.crebsthecoder.skwasp.SkBee;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoardManager implements Listener {

    private static final Map<UUID, FastBoardWrapper> BOARDS = new HashMap<>();
    private static final SkBee PLUGIN = SkBee.getPlugin();

    public BoardManager() {
        reload();
    }

    @Nullable
    public static FastBoardWrapper getBoard(Player player) {
        if (!player.isOnline()) return null;
        UUID uuid = player.getUniqueId();
        if (BOARDS.containsKey(uuid)) {
            return BOARDS.get(uuid);
        }
        FastBoardWrapper board = new FastBoardWrapper(player);
        BOARDS.put(uuid, board);
        return board;
    }

    public static void removeBoard(UUID uuid) {
        if (BOARDS.containsKey(uuid)) {
            BOARDS.get(uuid).deleteFastboard();
            BOARDS.remove(uuid);
        }
    }

    public static void reload() {
        Bukkit.getOnlinePlayers().forEach(BoardManager::getBoard);
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Bukkit.getScheduler().runTaskLater(PLUGIN, () -> removeBoard(uuid), 1);
    }

}
