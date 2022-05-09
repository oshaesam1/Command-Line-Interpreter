class Parser {
    String commandName;
    String[] args;


    //This method will divide the input into commandName and args
//where "input" is the string command entered by the user

    public boolean parse(String input){
        String[] splitinput = input.split(" ");
        commandName=splitinput[0].trim();
        args = new String[splitinput.length-1];

        for (int i=1; i< splitinput.length;i++)
        {
            args[i-1]=splitinput[i].trim();
        }
        return true;

    }
    public String getCommandName(){
        return commandName;
    }
    public String[] getArgs(){
        return args;}
    public void displayArgs ()
    {
        for (int i=0; i<args.length;i++){
            System.out.println(args[i].trim());}
    }
}
