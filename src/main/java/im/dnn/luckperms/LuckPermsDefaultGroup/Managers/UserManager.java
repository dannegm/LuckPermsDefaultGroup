package im.dnn.luckperms.LuckPermsDefaultGroup.Managers;

import im.dnn.luckperms.LuckPermsDefaultGroup.Constants.Keys;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Logger;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Settings;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;

import java.util.List;

public class UserManager {
    private final LuckPerms luckPerms;

    public UserManager (LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    public void assignDefaultGroup (Player player) {
        if (!this.hasGroupAssigned(player)) {
            if (Settings.getString(Keys.STRATEGY).equals(Keys.STRATEGY_REPLACE)) {
                this.replaceDefaultGroup(player);
            }
            if (Settings.getString(Keys.STRATEGY).equals(Keys.STRATEGY_REPLACE)) {
                this.appendDefaultGroup(player);
            }
        }
    }

    public boolean hasGroupAssigned (Player player) {
        Logger.info("Checking user <player> has a group assigned");
        List<String> groups = Settings.getStringList(Keys.GROUPS_TO_FIND);

        for (String group : groups) {
            Logger.info("Checking group <group>", player, group);
            if (player.hasPermission("group." + group)) {
                Logger.info("User <player> has group <group> assigned", player, group);
                return true;
            }
        }

        Logger.info("User <player> has not group assigned", player);
        return false;
    }

    public void replaceDefaultGroup (Player player) {
        String defaultGroup = Settings.getString(Keys.DEFAULT_GROUP);
        Logger.info("Assigning group <group> to user <player>", player, defaultGroup);

        Node node = Node.builder("group." + defaultGroup).build();
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);

        user.data().clear();
        user.data().add(node);
        luckPerms.getUserManager().saveUser(user);
    }

    public void appendDefaultGroup (Player player) {
        String defaultGroup = Settings.getString(Keys.DEFAULT_GROUP);
        Logger.info("Append group <group> to user <player>", player, defaultGroup);

        Node node = Node.builder("group." + defaultGroup).build();
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);

        user.data().add(node);
        luckPerms.getUserManager().saveUser(user);
    }
}
