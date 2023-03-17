package im.dnn.luckperms.LuckPermsDefaultGroup.Commands;

import im.dnn.luckperms.LuckPermsDefaultGroup.Constants.Commands;
import im.dnn.luckperms.LuckPermsDefaultGroup.Constants.Permissions;
import im.dnn.luckperms.LuckPermsDefaultGroup.Utils.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please, enter a valid command:");
            return this.help(sender);
        }

        if (args[0].equalsIgnoreCase(Commands.HELP)) {
            return this.help(sender);
        }

        if (args[0].equalsIgnoreCase(Commands.RELOAD)) {
            return this.reload(sender);
        }

        return false;
    }

    private boolean help(CommandSender sender) {
        sender.sendMessage("/lpdg reload -> Reload configuration");

        return true;
    }

    private boolean reload(CommandSender sender) {
        if (sender instanceof Player player) {
            if (!player.hasPermission(Permissions.ADMIN_RELOAD)) {
                player.sendMessage("You don't have permissions to do this");
                return false;
            }
        }

        Settings.reloadConfig();

        return true;
    }
}
