package sours.testmk.events;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.TrapDoor;

import sours.testmk.testmk;
import sours.testmk.utils.c;

public class opendoorEVNT implements Listener{

	private testmk plugin = testmk.getInstance();
	
    @EventHandler
    public void onPlayerDoorOpen(PlayerInteractEvent event)
    {
    	
    	if(!plugin.getConfig().getBoolean("Enabled")) {
    		return;
    	}
    	
        Action action = event.getAction();
        Block clicked = event.getClickedBlock();
             
        //Left or Right click?
        if ((action == Action.RIGHT_CLICK_BLOCK))
        {
            //Door Block?
            if(clicked.getType() == Material.TRAP_DOOR)
            {

                
 
                BlockState blockState = clicked.getState();
                TrapDoor door = (TrapDoor) blockState.getData();
                
                if(!door.isOpen()) {
                	// add sql
               try { 	
            		Statement mysql = plugin.connection.createStatement();
            		ResultSet rs = mysql.executeQuery("SELECT * FROM " + plugin.sDatabase + " WHERE x=" + blockState.getLocation().getX() + " AND y=" + blockState.getLocation().getY() + " AND z=" +  blockState.getLocation().getZ());
            		if(!rs.next()) {    			
            			update("INSERT INTO " + plugin.sDatabase + " VALUES (" +  blockState.getLocation().getX() + ", " + blockState.getLocation().getY() + ", " + blockState.getLocation().getZ() + ")");	   			
            			Bukkit.getConsoleSender().sendMessage(c.f("&a[TrapDoor added!]"));
            		}
            		rs.close(); 		
            	}catch(Exception ee) {
            		ee.printStackTrace();
            	}
                	
                	
                	
                }
               
             
            }
            else{    }
        }
        else{    }
    }

	public  void update(String cmd) {	
    	try {
    		Statement mysql = plugin.connection.createStatement();		
    		mysql.executeUpdate(cmd);
    		return;	
    	}catch(Exception ee) {
    		ee.printStackTrace();    	
    	}		
	}
	
	public  double query(String cmd) {
		try {
    		Statement mysql = plugin.connection.createStatement();		
    		ResultSet rs = mysql.executeQuery(cmd);
    		if(rs.next()) {
    			return rs.getDouble(1);	    			
    		}
    		rs.close();

    	}catch(Exception ee) {
    		ee.printStackTrace();
    	
    	}
		return 0.01;
	}

    
}
