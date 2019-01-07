package blind_test;
import java.util.Scanner;

public class TextEntry extends Thread
{
	private String lastLine = "";
	private Scanner scanner = new Scanner(System.in);
	
	public void run()
	{
		while(true)
		{
			String line = scanner.nextLine();
			setLine(line);
			notifyAll();
		}
	}
	
	private synchronized void setLine(String line)
	{
		lastLine = line;
	}
	
	public synchronized String getLine()
	{
		return lastLine;
	}
}
