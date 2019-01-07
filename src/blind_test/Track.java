package blind_test;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Track
{
	
	private static final int MATCH_VALUE = 85;
	
	public String title;
	public String preview;
	private static final BlindTestPlayer player = new BlindTestPlayer();

	public void play()
	{
		if(!player.isAlive()) player.start();
		player.setURL(preview);
	}
	
	public boolean matchName(String title)
	{
		int match = FuzzySearch.ratio(title, this.title);
		System.out.println(match);
		return  match >= MATCH_VALUE;
	}
	
	@Override
	public String toString()
	{
		return title;
	}
}
