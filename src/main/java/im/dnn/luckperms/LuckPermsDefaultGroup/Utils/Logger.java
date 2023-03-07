package im.dnn.luckperms.LuckPermsDefaultGroup.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Logger {

    static Logger singleton = null;
    static JavaPlugin plugin;

    public static void setPlugin (JavaPlugin plugin) {
        Logger.plugin = plugin;
    }

    private static void createInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
    }

    public static void info (String msg) {
        Logger.createInstance();
        if (Settings.isDebug()) {
            plugin.getLogger().info(msg);
        }
    }

    public static void info (String msg, Player player) {
        String message = msg
                .replaceAll("<player>", ChatColor.BLUE + player.getDisplayName() + ChatColor.RESET);

        Logger.createInstance();
        if (Settings.isDebug()) {
            plugin.getLogger().info(message);
        }
    }

    public static void info (String msg, Player player, String group) {
        String message = msg
                .replaceAll("<player>", ChatColor.BLUE + player.getDisplayName() + ChatColor.RESET)
                .replaceAll("<group>", ChatColor.RED + group + ChatColor.RESET);

        Logger.createInstance();
        if (Settings.isDebug()) {
            plugin.getLogger().info(message);
        }
    }

    public static void warning (String msg) {
        Logger.createInstance();
        plugin.getLogger().warning(msg);
    }

    public static void importantInfo (String msg) {
        Logger.createInstance();
        plugin.getLogger().info(msg);
    }
}
