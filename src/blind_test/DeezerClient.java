package blind_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeezerClient 
{	
	private static final String searchURL = "http://api.deezer.com/search/playlist";
	private static final String playListURL = "http://api.deezer.com/playlist/";
	private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	static class PlaylistId
	{
		public long id;
	}
	
	static class PlaylistSearchResults
	{
		public ArrayList<PlaylistId> data;
	}

	public Playlist getPlaylist(String title)
	{
		long id = 0;
		try {
			id = getPlaylistId(title);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fromJSON(getPlaylistJson(id));
	}
	
	public long getPlaylistId(String title) throws JsonParseException, JsonMappingException, IOException
	{
		PlaylistSearchResults results = mapper.readValue(searchPlaylists(title), PlaylistSearchResults.class);
		if(results.data != null && results.data.size() > 0)
		{
			return results.data.get(0).id;
		}
		return 0;
	}
	
	public Playlist fromJSON(String json)
	{
		Playlist playlist = null;
		try {
			playlist = mapper.readValue(json, Playlist.class);
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
		return playlist;
	}
	
	public String getPlaylistJson(long id)
	{
		String json = null;
		BufferedReader reader = null;

		try {
			// create the HttpURLConnection
			String strURL = playListURL + id;

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
