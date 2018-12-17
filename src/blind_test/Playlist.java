package blind_test;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Playlist
{	
	private int currentIndex = 0;
	private String title;
	private ArrayList<Track> tracks;

	
	public Track getTrack()
	{
		return tracks.get(currentIndex);
	}
	
	public void nextTrack()
	{
		currentIndex ++;
	}
	
	public boolean over()
	{
		return currentIndex == tracks.size();
	}
}
