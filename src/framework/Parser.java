package framework;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                // Added nextLine to read the rest of the String instead of only the next word.
                //Trim is used to remove spaces in the beginning and end of the String.
                word2 = tokenizer.nextLine().trim();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
