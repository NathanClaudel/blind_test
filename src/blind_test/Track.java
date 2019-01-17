package blind_test;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Track
{
	
	private static final int MATCH_VALUE = 75;
	
	public String title;
	public String preview;
	
	public boolean matchName(String title)
	{
		int match = FuzzySearch.weightedRatio(title, this.title);
		return  match >= MATCH_VALUE;
	}
	
	@Override
	public String toString()
	{
		return title;
	}
}
