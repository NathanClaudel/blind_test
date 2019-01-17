package blind_test;

import java.io.InputStream;
import java.net.URL;
import javazoom.jl.player.Player;

public class BlindTestPlayer
{
	private Player player = null;
	private Track track = null;
	
	public synchronized void setTrack(Track track)
	{
		this.track = track;
	}
	
	public synchronized void playTrack()
	{
		if(track != null) 
		{
			playURL(track.preview);
		}
	}
	
	private synchronized void playURL(String url)
	{
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
