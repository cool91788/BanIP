/*
 * 	BanIP - A BungeeCord plugin
 *	Copyright (C) 2015  Install
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ins.BanIP;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class BanIP_main extends Plugin {
	
	static File banlist;
	static Plugin mainPluginObj;
	static String version = "v1.0"; 
	
	@Override
	public void onEnable() {
		mainPluginObj = this;
		checkfile();
		getProxy().getPluginManager().registerCommand(this, new BanIP_command());
		getProxy().getPluginManager().registerListener(this, new BanIP_listener());
	}
	
	public void checkfile() {
		getLogger().info("檢查運行環境中。。。");
		if(!getDataFolder().exists())
			getDataFolder().mkdir();
		
		banlist = new File(getDataFolder() + "/banedlist.yml");
		if(!banlist.exists()) {
			getLogger().info("發現問題，正在自動處理中。。。");
			try {
				banlist.createNewFile();
			}
			catch (IOException e) {e.printStackTrace();}
		}
		getLogger().info("完成！");
		getLogger().info(ChatColor.RED + "版本：v1.0");
	}
}
