package blind_test;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javazoom.jl.player.Player;
import me.xdrop.fuzzywuzzy.*;

public class BlindTest 
{
	private static DeezerClient client = new DeezerClient();
	private static final TextEntry input = new TextEntry();
	
	public static void main(String[] args) 
	{
		Playlist playlist = client.getPlaylist("muse");
		input.start();
		
		while(!playlist.over())
		{
			playlist.getTrack().play();
			String answer = "";
			
			do
			{
				answer = getInput();
			}
			while (!playlist.getTrack().matchName(answer));
			
			System.out.println("Well done ! Song: " + playlist.getTrack().title);
			playlist.nextTrack();
		}
	}
	
	private static synchronized String getInput()
	{
		try {
			input.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input.getLine();
	}
}
