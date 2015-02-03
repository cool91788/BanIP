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
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class BanIP_command extends Command {
	
	public BanIP_command() {
		super("bbanip", "bungeecordbanip", "bi");
	}
	
	public void add(CommandSender commandSender, String ip, boolean argIsNull) {
		if(argIsNull) {
			commandSender.sendMessage(new ComponentBuilder("好像少了些什麼東西，"
					+ "詳情請輸入/bbanip help查看").color(ChatColor.RED).create());
			return;
		}
		BanIP_banedlist list = new BanIP_banedlist();
		if(list.write(ip)) {
			commandSender.sendMessage(new ComponentBuilder("IP:").color(ChatColor.GREEN)
					.append(ip).color(ChatColor.RED).append("已經被BAN！")
					.color(ChatColor.GREEN).create());
		}
		else
			commandSender.sendMessage(new ComponentBuilder("IP:").color(ChatColor.GREEN)
					.append(ip).color(ChatColor.RED).append("已經在名單中！")
					.color(ChatColor.GREEN).create());
			
	}
	
	
	public void remove(CommandSender commandSender, String ip, boolean argIsNull) {
		if(argIsNull) {
			commandSender.sendMessage(new ComponentBuilder("好像少了些什麼東西，"
					+ "詳情請輸入/bbanip help查看").color(ChatColor.RED).create());
			return;
		}
		BanIP_banedlist list = new BanIP_banedlist();
		list.remove(ip);
		commandSender.sendMessage(new ComponentBuilder("IP:").color(ChatColor.GREEN)
				.append(ip).color(ChatColor.RED).append("已經解BAN！")
				.color(ChatColor.GREEN).create());
	}
	
	@Override
	public void execute(CommandSender commandSender, String[] cmdargs) {
		if(cmdargs.length == 0) {
			commandSender.sendMessage(new ComponentBuilder("BanIP 版本：")
				.color(ChatColor.YELLOW).append(BanIP_main.version).color(ChatColor.RED).create());
			commandSender.sendMessage(new ComponentBuilder("請輸入/bbanip help來查看說明。")
				.color(ChatColor.YELLOW).create());
		}else {
			switch(cmdargs.length) {
			case 1:
				switch(cmdargs[0]) {
				case "?":
				case "help":
					commandSender.sendMessage(new ComponentBuilder("--------->BanIP說明檔<---------")
						.color(ChatColor.RED).create());
					commandSender.sendMessage(new ComponentBuilder("=============================")
						.color(ChatColor.BLUE).create());
					commandSender.sendMessage(new ComponentBuilder("前綴指令可以打：").color(ChatColor.YELLOW).append("\"bbanip\"")
						.color(ChatColor.GREEN).append(" or ").color(ChatColor.YELLOW).append("\"bi\"").color(ChatColor.GREEN)
						.append("，示範以\"bi\"簡寫。").color(ChatColor.YELLOW).create());
					commandSender.sendMessage(new ComponentBuilder("/bi add <ip>")
						.color(ChatColor.GREEN).create());
					commandSender.sendMessage(new ComponentBuilder("--->將所輸入的IP加入黑名單。")
						.color(ChatColor.YELLOW).create());
					commandSender.sendMessage(new ComponentBuilder("/bi remove <ip>")
						.color(ChatColor.GREEN).create());
					commandSender.sendMessage(new ComponentBuilder("--->將所輸入IP從黑名單移除。")
						.color(ChatColor.YELLOW).create());
					commandSender.sendMessage(new ComponentBuilder("/bi \"help\"").color(ChatColor.GREEN).append(" or ")
						.color(ChatColor.YELLOW).append("/bi \"?\"").color(ChatColor.GREEN).create());
					commandSender.sendMessage(new ComponentBuilder("--->幫助說明。")
						.color(ChatColor.YELLOW).create());
					commandSender.sendMessage(new ComponentBuilder("=============================")
						.color(ChatColor.BLUE).create());
					break;
				case "add":
					add(commandSender, null, true);
					break;
				case "remove":
					remove(commandSender, null, true);
					break;
				default:
					commandSender.sendMessage(new ComponentBuilder("此指令不存在！ "
							+ "請輸入/bbanip help來查看說明。").color(ChatColor.RED).create());
				}
				break;
			default:
				switch(cmdargs[0]) {
				case "add":
					add(commandSender, cmdargs[1], false);
					break;
				case "remove":
					remove(commandSender, cmdargs[1], false);
					break;
				default:
					commandSender.sendMessage(new ComponentBuilder("此指令不存在！ "
							+ "請輸入/bbanip help來查看說明。").color(ChatColor.RED).create());
				}
			}
		}
	}
}
