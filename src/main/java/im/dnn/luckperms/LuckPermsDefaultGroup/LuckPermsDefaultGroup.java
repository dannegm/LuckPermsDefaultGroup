package im.dnn.luckperms.LuckPermsDefaultGroup;

import im.dnn.luckperms.LuckPermsDefaultGroup.Listeners.UserListener;
import im.dnn.luckperms.LuckPermsDefaultGroup.Managers.UserManager;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Logger;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Settings;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LuckPermsDefaultGroup extends JavaPlugin {
    @Override
    public void onEnable() {
        Settings.setupConfig(this);
        Logger.setPlugin(this);
        Logger.info("Enabled plugin DefaultGroup for LuckPerms");
        setup();
    }

    public void setup() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms luckPerms = provider.getProvider();
            UserManager userManager = new UserManager(luckPerms);
            new UserListener(this, userManager);
        } else {
            Logger.warning("It is required to have " + ChatColor.YELLOW + "LuckPerms" + ChatColor.RESET + " installed in order to use this plugin");
        }

    }
}
