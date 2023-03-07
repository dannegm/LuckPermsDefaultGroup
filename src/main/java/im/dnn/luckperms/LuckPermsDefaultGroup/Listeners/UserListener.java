package im.dnn.luckperms.LuckPermsDefaultGroup.Listeners;

import im.dnn.luckperms.LuckPermsDefaultGroup.Managers.UserManager;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class UserListener implements Listener {
    private final UserManager userManager;

    public UserListener (JavaPlugin context, UserManager userManager) {
        context.getServer().getPluginManager().registerEvents(this, context);
        this.userManager = userManager;
    }

    @EventHandler
    public void onUserJoin (PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        Logger.info("Joined user <player>", player);
        userManager.assignDefaultGroup(player);
    }
}
