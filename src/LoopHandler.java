import org.w3c.dom.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

import java.util.Set;

public class LoopHandler {
    public String state;
    public Map<String, Handler> handlerMap = Map.ofEntries(

    );

    public LoopHandler() {
        this.state = "idle";

    }
    public void loop() {
        while (this.state != "exit") {
            String input = ConsoleReader.readLine();
            System.out.println(input);
            Handler handler = this.getHandler();

        }
    }

    public Handler getHandler() {
        return handlerMap.get(this.state);
    }
}
