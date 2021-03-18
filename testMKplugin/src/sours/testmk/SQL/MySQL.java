package sours.testmk.SQL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;



import net.md_5.bungee.api.chat.TextComponent;
import sours.testmk.testmk;




public class MySQL {
	
	private testmk plugin = testmk.getInstance();
	
	private String host;
	private String port;
	private String username;
	private String password;
	private String database;
	

	public MySQL(String host, String port, String username, String password, String database) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	public synchronized void Connect() {		
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
             try {
            	 plugin.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            	 createTable();
            	 plugin.getLogger().info("[mkTrap] Connected with MySQL");
             }catch(Exception e) {
            	e.printStackTrace();
            	plugin.getLogger().info("[mkTrap] Error with MySQL can't connect!");
            	Bukkit.getPluginManager().disablePlugin(plugin);
             }
            	
            	
            	
            	
            }
        });

	}
	
	
	public void createTable() {
		 plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
	            @Override
	            public void run() {
	           
	            	try {
	            		Statement mysql = plugin.connection.createStatement();
	            		mysql.executeUpdate("CREATE TABLE IF NOT EXISTS " + plugin.sDatabase + " (x float, y float, z float)");
	            	}catch(Exception e) {
	            		e.printStackTrace();
	            	}
	       
	            	
	            }
	        });
	}
	
	
	public void update(String cmd) {	
    	try {
    		Statement mysql = plugin.connection.createStatement();		
    		mysql.executeUpdate(cmd);
    		return;	
    	}catch(Exception ee) {
    		ee.printStackTrace();    	
    	}		
	}
	
	
	
}
