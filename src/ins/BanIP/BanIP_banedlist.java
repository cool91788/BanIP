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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BanIP_banedlist {
	public boolean write(String ip) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss E");
		Date date = new Date();
		try {
			if(findip(ip)) {
				BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(BanIP_main.banlist, true), "UTF-8"));
				wr.append(ip + "  ---時間--->  " + dateFormat.format(date));
				wr.newLine();
				wr.flush();
				wr.close();
				return true;
			}
		} catch (UnsupportedEncodingException e) {e.printStackTrace();
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		return false;
	}
	
	public boolean findip(String ip) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(BanIP_main.banlist), "UTF-8"));
			String str;
			while((str = br.readLine())!=null) {
				if(str.substring(0, str.indexOf('-')-2).equals(ip)) {
					br.close();
					return false;
				}
			}
			br.close();
		} catch (UnsupportedEncodingException e) {e.printStackTrace();
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		return true;
	}
	
	public void remove(String ip) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(BanIP_main.banlist), "UTF-8"));
			File tmp = File.createTempFile("banlist", ".yml.tmp");
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(tmp, true), "UTF-8"));
			String str;
			while((str = br.readLine())!=null) {
				if(str.substring(0, str.indexOf('-')-2).equals(ip))
					continue;
				else {
					wr.append(str);
					wr.newLine();
				}
			}
			wr.flush();
			wr.close();
			br.close();
			BanIP_main.banlist.delete();
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(tmp), "UTF-8"));
			wr = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(BanIP_main.banlist, true), "UTF-8"));
			while((str = br.readLine())!=null) {
				wr.append(str);
				wr.newLine();
			}
			wr.flush();
			wr.close();
			br.close();
			tmp.delete();
		} catch (UnsupportedEncodingException e) {e.printStackTrace();
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
	}
}
