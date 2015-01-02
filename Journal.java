import java.io.*;
import java.lang.*;
import java.util.*;

public class Journal {
    private static String[] command;
    
    private static void entry() {
        Scanner scanner = new Scanner(System.in);

        try {
            File entry = new File("./entries/" + command[1] + ".txt");
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
            fw.close();
        } catch (Exception e) {
            System.out.println("File errors have occured.");
        }
    }	// I didn't close the scanner here because that screws up System.in.

    private static void ls() {
        File entries = new File("./entries");
        File[] listOfEntries = entries.listFiles();

        for(int i = 0; i < listOfEntries.length; i++) { 
            // This next if statement is to ensure that too many entries aren't listed on one line.
            if ((i != 0) && (i % 6) == 0) {
                System.out.print("\n");
            }
            
            if (listOfEntries[i].isFile()) {
                System.out.print("" + listOfEntries[i].getName() + "    ");
            }
        }
        System.out.print("\n");
    }

    private static void view() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            File file = new File("./entries/" + command[1]);
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {                            // .ready() is essentially the BufferedReader equivalent of .hasNext() 
                System.out.println("" + br.readLine());
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("File does not exist.");
        }
    }

    private static void help() {
        System.out.println("-- This section details the functions of the different commands. --");
        System.out.println("entry - creates a new entry in the journal");
        System.out.println("ls    - prints a list of all existing journal entries");
        System.out.println("view  - prints the contents of an existing journal entry");
        System.out.println("help  - prints the help section that you are currently reading");
        System.out.println("exit  - quits the program");
    }

    public static void main(String[]args) {
        Console console = System.console();

        do {
            String input = console.readLine("[journal]$ ");
            command = input.split(" ");
            switch(command[0]) {
                case "entry" : entry();
                               break;
                case "ls"    : ls();
                               break;
                case "dir"   : ls();
                               break;
                case "view"  : view();
                               break;
                case "help"  : help();
                               break;
                case "exit"  : System.exit(0);
                               break;
                default	: System.out.println("Command not recognized");
                          break;
            }
        } while (true);
    }
}
/* -TODO-
 * Add encryption capabilities for the files. Password protect them.
 */

