package sours.testmk;

import java.io.File;
import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import sours.testmk.SQL.MySQL;
import sours.testmk.events.opendoorEVNT;



public class testmk extends JavaPlugin{

	    public File file;
	    public static Connection connection;

	    
		public String sHost;
		public String sPort;
		public String sUser;
		public String sPassword;
		public String sDatabase;
	    
	    private static testmk instance;
	    
	    public testmk() {
	    	instance = this;
	    }
	    public static testmk getInstance() {
	        return instance;
	    }

		
		@Override
		public void onEnable() {
			instance = this;
			
			getSQLData();
			
			MySQL mysql = new MySQL(sHost, sPort, sUser, sPassword, sDatabase);
			mysql.Connect();
			
			
			events();
			commands();


			this.saveDefaultConfig();
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[mkTrap] mkTrap has been enabled");
			

			
		}
		
		@Override
		public void onDisable() {

			
			

			
			
			this.saveDefaultConfig();
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[mkTrap] mkTrap has been disabled");
		}
		
		
		
		public void events() {
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new opendoorEVNT(), this);
		}
		
		public void commands() {
		
		}
		
		

		public void getSQLData() {
			sHost = this.getConfig().getString("Host");
			sPort = this.getConfig().getString("Port");
			sUser = this.getConfig().getString("Username");
			sPassword = this.getConfig().getString("Password");
			sDatabase = this.getConfig().getString("Database");
			
			if (sUser.equalsIgnoreCase("root")) {
				sPassword = "";
			}
			
			Bukkit.getConsoleSender().sendMessage(sPassword); 
		}
		
		

		

		
	}
