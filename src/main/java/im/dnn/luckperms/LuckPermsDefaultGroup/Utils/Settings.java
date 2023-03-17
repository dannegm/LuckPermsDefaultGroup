package im.dnn.luckperms.LuckPermsDefaultGroup.Utils;

import im.dnn.luckperms.LuckPermsDefaultGroup.Constants.Keys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    static Settings singleton = null;
    static JavaPlugin plugin = null;

    private static FileConfiguration config;

    public static void setupConfig (JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        singleton = new Settings();

        Settings.config = plugin.getConfig();

        Settings.plugin = plugin;

        setupDefaults();
        plugin.saveConfig();
    }

    private static void setupDefaults () {
        Settings.config.addDefault(Keys.CONFIG_DEBUG, false);

        Settings.config.addDefault(Keys.DEFAULT_GROUP, "player");

        Settings.config.addDefault(Keys.STRATEGY, "replace");

        List<String> groupsToFind = new ArrayList<>();
        groupsToFind.add("player");
        Settings.config.addDefault(Keys.GROUPS_TO_FIND, groupsToFind);
    }

    public static void reloadConfig () {
        Settings.plugin.reloadConfig();ﬁ
    }

    public static boolean isDebug () {
        return Settings.config.getBoolean(Keys.CONFIG_DEBUG);
    }

    public static String getString (String path) {
        return Settings.config.getString(path);
    }

    public static List<String> getStringList (String path) {
        return Settings.config.getStringList(path);
    }
}
