package blind_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeezerClient 
{
	private static final String searchURL = "http://api.deezer.com/search/playlist";
	private static final ObjectMapper mapper = new ObjectMapper();

	public Playlist getPlaylist(String title)
	{
		ArrayList<Playlist> playlists = fromJSON(searchPlaylists(title));
		if(playlists != null && playlists.size() > 0)
		{
			return playlists.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public static ArrayList<Playlist> fromJSON(String json)
	{
		ArrayList<Playlist> playlists = null;
		try {
			playlists = mapper.readValue(json, ArrayList.class);
		} 
		catch (JsonParseException e) 
		{
			e.printStackTrace();
		} 
		catch (JsonMappingException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return playlists;
	}
	
	public String searchPlaylists(String title)
	{
		String json = null;
		BufferedReader reader = null;

		try {
			// create the HttpURLConnection
			String strURL = searchURL + "?limit=1&q=" + URLEncoder.encode(title, "UTF-8");

			URL url = new URL(strURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			// give it 15 seconds to respond
			connection.setReadTimeout(15*1000);
			connection.connect();

			// read the output from the server
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}
			json = stringBuilder.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {reader.close();} catch(Exception e) {}
		}

		return json;
	}
}
