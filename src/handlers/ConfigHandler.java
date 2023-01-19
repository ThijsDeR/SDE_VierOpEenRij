package handlers;

public class ConfigHandler implements Handler {
    static Config config = new Config();

    public static void showConfig()
    {
        System.out.println("Welcome to the config, here you can modify you playing board. Use the following commands \n" +
                "columnsize <number> \t Change the colum size to <number> \n" +
                "rowsize <number> \t Change the row size to <number>");
    }

    public static void setColumnSize(String[] args)
    {
        int number = Integer.parseInt(args[0]);
        if (number >= 4 && number < 20){
            config.setColumnSize(Integer.parseInt(args[0]));
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public static void setRowSize(String[] args)
    {
        config.setRowSize(Integer.parseInt(args[0]));
    }

    @Override
    public HandlerState invoke(String methodName, String[] args) {
        if (methodName == null) return HandlerState.IN_CONFIG;
        if (methodName.equals("exit")) return HandlerState.EXIT;
        if (methodName.equals("show"))
        {
            showConfig();
            return HandlerState.IN_CONFIG;
        }
        if (methodName.equals("columnsize"))
        {
            setColumnSize(args);
            return HandlerState.IN_CONFIG;
        }
        if (methodName.equals("rowsize"))
        {
            setRowSize(args);
            return HandlerState.IN_CONFIG;
        }
        this.cantFindCommand(methodName, args);
        return HandlerState.IN_CONFIG;
    }

    @Override
    public void beginScreen(String[] args) {
        
    }
}
