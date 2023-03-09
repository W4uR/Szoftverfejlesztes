package microunit;

import java.lang.reflect.Method;

public interface TestResultAccumulator {
    void onError(Method method);
    void onFailure(Method method);
    void onSccess(Method method);

}
