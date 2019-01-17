package blind_test;
import java.util.Scanner;

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
