package handlers;

public interface Handler {
    HandlerState invoke(String methodName, String[] args);

    default void cantFindCommand(String methodName, String[] args)
    {
        System.out.println(String.format("Cannot find command '%s'", methodName));
    };
}
