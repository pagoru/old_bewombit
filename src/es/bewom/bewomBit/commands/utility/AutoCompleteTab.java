package es.bewom.bewomBit.commands.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;

import es.bewom.bewomBit.commands.autocomplete.CommandAutoBan;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoDelHome;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoGm;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoHome;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoLag;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoP;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoSave;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoSpawn;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoSpawner;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoTpAll;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoTpa;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoTpaHere;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoV;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoXray;

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