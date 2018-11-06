package framework;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), USE("use"), TAKE("take"), REST("rest"), YES("yes"), NO("no"), CRAFT("craft"); // added inventory, use and take as commands.
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
