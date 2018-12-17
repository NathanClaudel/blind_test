package blind_test;

import java.io.InputStream;
import java.net.URL;

import javazoom.jl.player.Player;
import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Track 
{
	private static final int MATCH_VALUE = 85;
	
	private String title;
	private String artist;
	private String preview;
	
	public Track(String title, String artist, String preview)
	{
		this.title = title;
		this.artist = artist;
		this.preview = preview;
	}

	public void play()
	{
		try 
		{
			InputStream is = new URL(preview).openStream();
			Player playMP3 = new Player(is);
			playMP3.play();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean matchName(String title)
	{
		return FuzzySearch.ratio(title, this.title) <= MATCH_VALUE;
	}
	
	@Override
	public String toString()
	{
		return title + " by " + artist;
	}
}
