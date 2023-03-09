package microunit;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingTestResultAccumulator implements TestResultAccumulator{

    private AtomicInteger numberOfErrors = new AtomicInteger();
    private AtomicInteger numberOfFails = new AtomicInteger();
    private AtomicInteger numberOfSuccesses = new AtomicInteger();

    @Override
    public void onError(Method method) {
        numberOfErrors.incrementAndGet();
    }

    @Override
    public void onFailure(Method method) {
        numberOfFails.incrementAndGet();
    }

    @Override
    public void onSccess(Method method) {
        numberOfSuccesses.incrementAndGet();
    }

    public String toString(){
        return String.format("""
            Tests run: %d
            Errors: %d
            Failures: %d    
            """,numberOfErrors.get()+numberOfSuccesses.get()+numberOfFails.get(),numberOfErrors.get(),numberOfFails.get()
        );
    }
}
