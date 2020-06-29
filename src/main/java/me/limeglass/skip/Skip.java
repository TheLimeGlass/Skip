package me.limeglass.skip;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public final class Skip extends JavaPlugin {

	private static Skip instance;
	private SkriptAddon addon;
	private Metrics metrics;

	@Override
	public void onEnable() {
		instance = this;
		File configFile = new File(getDataFolder(), "config.yml");
		//If newer version was found, update configuration.
		int version = 1;
		if (version != getConfig().getInt("configuration-version", version)) {
			if (configFile.exists())
				configFile.delete();
		}
		saveDefaultConfig();
		metrics = new Metrics(this);
		try {
			addon = Skript.registerAddon(this)
					.loadClasses("me.limeglass.skip", "elements")
					.setLanguageFileDirectory("lang");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getConsoleSender().sendMessage("[Skip] Skip has been enabled!");
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Skip getInstance() {
		return instance;
	}

	public Metrics getMetrics() {
		return metrics;
	}

}
