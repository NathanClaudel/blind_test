package blind_test;

import java.util.Scanner;

public class BlindTest 
{
	private DeezerClient client = new DeezerClient();
	private TextEntry input = new TextEntry(this);
	private Playlist playlist;
	private BlindTestPlayer player = new BlindTestPlayer();
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a playlist name:");
		BlindTest blindTest = new BlindTest(scanner.nextLine());
		blindTest.play();
	}
	
	public BlindTest(String playlistTitle)
	{
		playlist = client.getPlaylist(playlistTitle);
		playlist.shuffle();
		System.out.println("Playlist : " + playlist.title);
		System.out.println("Try to guess which song this is :");
	}
	
	public void play()
	{
		input.start();
		
		while(!playlist.over())
		{
			player.setTrack(playlist.getTrack());
			player.playTrack();
			endOfTrack();
			nextTrack();
		}
		
		System.out.println("Game over");
		input.interrupt();
	}
	
	// Shared variable between the threads in order to know if the 
	// end of a track is a success or a timeout.
	private static boolean success = false;
	
	public synchronized void inputTitle(String title)
	{
		if(playlist.getTrack().matchName(title))
		{
			System.out.println("Well done, this is " + playlist.getTrack().title);
			success = true;
			player.stopPlay();
		}
		else
		{
			System.out.println("Try again !");
		}
	}
	
	private synchronized void nextTrack()
	{
		playlist.nextTrack();
		System.out.println("Guess the next song :");
	}
	
	public synchronized void endOfTrack()
	{
		if(!success)
		{
			System.out.println("Time out ! The track was " + playlist.getTrack().title);
		}
		else
		{
			success = false;
		}
	}
}
