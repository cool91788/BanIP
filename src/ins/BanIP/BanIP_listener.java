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

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BanIP_listener implements Listener {
	
	@EventHandler
	public void checkip(PreLoginEvent loginevent) {
		BanIP_banedlist list = new BanIP_banedlist();
		String ip = loginevent.getConnection().getAddress().toString();
		ip = ip.substring(1, ip.indexOf(':'));
		if(!list.findip(ip)) {
			loginevent.getConnection().disconnect(new ComponentBuilder("花生什麼事情惹！？  Σヽ(ﾟДﾟ○)ﾉ  偶進不去！！？？\n\n\n不好意思呦>///<\n")
			.color(ChatColor.YELLOW).bold(true).append("您的IP在本服黑名單中。").color(ChatColor.DARK_GREEN).create());
			BanIP_main.mainPluginObj.getLogger().info(ChatColor.GREEN+ "玩家：" + ChatColor.YELLOW + loginevent.getConnection().getName() 
					+ ChatColor.GREEN + "，來自：" + ChatColor.RED + ip + ChatColor.GREEN + "。因在IP黑名單內，故拒絕連線。");
		}
	}
}
