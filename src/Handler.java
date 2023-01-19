import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface Handler {
    Map<String, Method> methodMap = new HashMap<String, Method>();

    default Method getMethod(String name){
        return methodMap.get(name);
    };
}
