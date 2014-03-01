import java.io.*;
import java.lang.*;
import java.util.*;;

public class Journal {
	public static void entry() {
		Scanner scanner = new Scanner(System.in);
		
		String date;
		System.out.println("Date?");
		date = "" + scanner.next();		// Virtualization
		
		try {
			File entry = new File("/home/whitewizard/Projects/Journal/entries/" + date + ".txt");
			if (!entry.exists()) {
				entry.createNewFile();
			}

			FileWriter fw = new FileWriter(entry.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String content = "";
			while (!content.equals("END")) {
				content = scanner.nextLine();
				bw.write(content + "\n");
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("File errors have occured.");
		}
	}	//I didn't close the scanner here because that screws up System.in.

	public static void main(String[]args) {
		Console console = System.console();

		do {
			String input = console.readLine("$ ");
			switch(input) {
				case "entry" : entry();
					       break;
				case "quit" : System.exit(0);
					      break;
				default	: System.out.println("Command not recognized");
					  break;
			}
		} while (true);
	}
}
/* -TODO-
   Enable entry viewing functionality.
   Have the console clear the text on screen before and after an entry.
   Add the "help" command to show the functions of the other commands.
*/

