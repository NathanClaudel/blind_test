package blind_test;

import java.io.InputStream;
import java.net.URL;

import javazoom.jl.player.Player;

public class BlindTestPlayer extends Thread
{
	private Player player = null;
	private String url = "";
	
	@Override
	public void run()
	{
		while(true) 
		{
			playURL();
			notifyAll();
		}
	}
	
	public void setURL(String url)
	{
		if(player != null) player.close();
		this.url = url;
	}
	
	public void playURL()
	{
		if(url == "") return;
		
		try 
		{
			InputStream is = new URL(url).openStream();
			player = new Player(is);
			player.play();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void stopPlay()
	{
		if(player != null) player.close();
	}
}
