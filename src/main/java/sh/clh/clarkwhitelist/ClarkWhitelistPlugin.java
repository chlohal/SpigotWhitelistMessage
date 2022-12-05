package sh.clh.clarkwhitelist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClarkWhitelistPlugin extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        String loginUrl = "https://mc.clh.sh/WL/" + event.getName();

        if(!Bukkit.getServer().getOfflinePlayer(event.getUniqueId()).isWhitelisted()) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, Component.join(JoinConfiguration.newlines(),
                    Component.text().content("Sorry! You're not whitelisted on this server.").color(NamedTextColor.RED),
                    Component.text().content("If you would like to be automatically whitelisted through your Clark credentials, please go to ")
                        .append(
                            Component.text().content(loginUrl).color(NamedTextColor.AQUA)
                                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, loginUrl))
                    )
            ));
        }
    }

}