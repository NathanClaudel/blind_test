package blind_test;
import java.util.ArrayList;
import java.util.Collections;

public class Playlist
{	
	static class TrackList
	{
		public ArrayList<Track> data;
	}
	
	private int currentIndex = 0;
	public String title;
	public TrackList tracks;
	
	public void shuffle()
	{
		if(tracks != null) Collections.shuffle(tracks.data);
	}

	public Track getTrack()
	{
		if(currentIndex < tracks.data.size()) return tracks.data.get(currentIndex);
		else return null;
	}
	
	public void nextTrack()
	{
		currentIndex ++;
	}
	
	public boolean over()
	{
		return currentIndex == tracks.data.size();
	}
	
	@Override
	public String toString()
	{
		String str = "Playlist " + title + ":\n";
		if(tracks == null)
		{
			str += "Null tracks";
		}
		else if(tracks.data == null)
		{
			str += "Null tracks.data";
		}
		else
		{
			for(Track track : tracks.data)
			{
				str += track.toString() + "\n";
			}
		}
		return str;
	}
}
