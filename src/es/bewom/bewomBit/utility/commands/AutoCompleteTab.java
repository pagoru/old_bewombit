package es.bewom.bewomBit.utility.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;

import es.bewom.bewomBit.groups.amigos.CommandAutoAmigos;
import es.bewom.bewomBit.groups.ban.CommandAutoBan;
import es.bewom.bewomBit.groups.gm.CommandAutoGm;
import es.bewom.bewomBit.groups.home.CommandAutoDelHome;
import es.bewom.bewomBit.groups.home.CommandAutoHome;
import es.bewom.bewomBit.groups.lag.CommandAutoLag;
import es.bewom.bewomBit.groups.p.CommandAutoP;
import es.bewom.bewomBit.groups.save.CommandAutoSave;
import es.bewom.bewomBit.groups.spawn.CommandAutoSpawn;
import es.bewom.bewomBit.groups.spawner.CommandAutoSpawner;
import es.bewom.bewomBit.groups.tp.CommandAutoTpAll;
import es.bewom.bewomBit.groups.tp.tpa.CommandAutoTpa;
import es.bewom.bewomBit.groups.tp.tpa.CommandAutoTpaHere;
import es.bewom.bewomBit.groups.v.CommandAutoV;
import es.bewom.bewomBit.groups.whitelist.CommandAutoWhitelist;
import es.bewom.bewomBit.groups.xray.CommandAutoXray;

public class AutoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		try {
			if(CommandAutoP.commandautop(sender, cmd, alias, args) != null){
				return CommandAutoP.commandautop(sender, cmd, alias, args);
			}
			if(CommandAutoGm.commandautogm(sender, cmd, alias, args) != null){
				return CommandAutoGm.commandautogm(sender, cmd, alias, args);
			} 			
			if(CommandAutoV.commandautov(sender, cmd, alias, args) != null){			
				return CommandAutoV.commandautov(sender, cmd, alias, args);			
			}			
			if(CommandAutoTpa.commandautotpa(sender, cmd, alias, args) != null){			
				return CommandAutoTpa.commandautotpa(sender, cmd, alias, args);			
			} 			
			if(CommandAutoSpawner.commandautospawner(sender, cmd, alias, args) != null){
				return CommandAutoSpawner.commandautospawner(sender, cmd, alias, args);
			} 			
			if(CommandAutoTpaHere.commandautotpahere(sender, cmd, alias, args) != null){			
				return CommandAutoTpaHere.commandautotpahere(sender, cmd, alias, args);			
			} 			
			if(CommandAutoHome.commandautohome(sender, cmd, alias, args) != null){			
				return CommandAutoHome.commandautohome(sender, cmd, alias, args);			
			} 
			if(CommandAutoDelHome.commandautodelhome(sender, cmd, alias, args) != null){			
				return CommandAutoDelHome.commandautodelhome(sender, cmd, alias, args);			
			} 
			if(CommandAutoSpawn.commandautospawn(sender, cmd, alias, args) != null){			
				return CommandAutoSpawn.commandautospawn(sender, cmd, alias, args);			
			} 
			if(CommandAutoLag.commandautolag(sender, cmd, alias, args) != null){			
				return CommandAutoLag.commandautolag(sender, cmd, alias, args);			
			}
			if (CommandAutoTpAll.commandautotpall(sender, cmd, alias, args) != null){
				return CommandAutoTpAll.commandautotpall(sender, cmd, alias, args);
			}
			if (CommandAutoBan.commandautoban(sender, cmd, alias, args) != null){
				return CommandAutoBan.commandautoban(sender, cmd, alias, args);
			}
			if (CommandAutoSave.commandautosave(sender, cmd, alias, args) != null){
				return CommandAutoSave.commandautosave(sender, cmd, alias, args);
			}
			if (CommandAutoXray.commandautoxray(sender, cmd, alias, args) != null){
				return CommandAutoXray.commandautoxray(sender, cmd, alias, args);
			}
			if (CommandAutoAmigos.commandautogm(sender, cmd, alias, args) != null){
				return CommandAutoAmigos.commandautogm(sender, cmd, alias, args);
			}
			if(CommandAutoWhitelist.commandautowhitelist(sender, cmd, alias, args) != null){
				return CommandAutoWhitelist.commandautowhitelist(sender, cmd, alias, args);
			}

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return null;
	}
}