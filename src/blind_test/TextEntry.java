package blind_test;
import java.util.Scanner;

/*
 * This class is a thread used to input text. It can interrupt the game if the correct text
 * is entered.
 */
public class TextEntry extends Thread
{
	private String lastLine = "";
	private Scanner scanner = new Scanner(System.in);
	private BlindTest blindTest;
	
	public TextEntry(BlindTest blindTest)
	{
		super();
		this.blindTest = blindTest;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			String line = scanner.nextLine();
			blindTest.inputTitle(line);
		}
	}
}
