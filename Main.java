package ru.progrmjarvis.findzblock;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

import ru.progrmjarvis.findzblock.arena.ArenaManager;
import ru.progrmjarvis.findzblock.commands.ArenaCommands;
import ru.progrmjarvis.findzblock.event.block.FZWBlockBreak;

/**
 * Created by PROgrm_JARvis on 18.09.2016.
 */
public class Main extends JavaPlugin{
    public void onEnable(){
        PluginDescriptionFile pdfFile = getDescription();


        registerEvents();
        registerCommands();
        registerConfig();
        enableArenaManager();

        Logger logger = getLogger();
        logger.info(pdfFile.getName() + " has been enabled. Version: " + pdfFile.getVersion());
    }
    public void onDisable(){
        saveConfig();
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = getLogger();
        logger.info(pdfFile.getName() + " has been disabled. Version: " + pdfFile.getVersion());
    }

    public void registerCommands(){
        //getCommand("fzbversion").setExecutor(new FZWCommand());
        getCommand("fzb").setExecutor(new ArenaCommands());

    }
    public void registerEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new FZWBlockBreak(), this);
    }
    public void registerConfig() {
        if (!getDataFolder().exists()) {
            saveConfig();
        }
        if (getConfig() == null) {
            saveDefaultConfig();
        }
        System.out.print("Configs registered");
    }
    public void enableArenaManager() {
        new ArenaManager(this);
        ArenaManager.getManager().loadGames();
        //getServer().getPluginManager().registerEvents(new GameListener(this), this);
    }
}
