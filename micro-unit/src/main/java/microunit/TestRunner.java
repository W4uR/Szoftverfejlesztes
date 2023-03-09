package microunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public abstract class TestRunner {
    protected  Class<?> testClass;

    public TestRunner(Class<?> testClass){
        this.testClass = testClass;
    }

    protected List<Method> getAnnotationMethods(Class<? extends Annotation> annotationCLass){
        return Arrays.stream(testClass.getDeclaredMethods()).filter(method-> method.isAnnotationPresent(annotationCLass)).toList();
    }
    public void runTestMethods(){
        try {

            TestResultAccumulator accumulator = new CountingTestResultAccumulator();
            for (Method method : getAnnotationMethods(Test.class)){
                System.out.println(method);
                Object instance = testClass.getConstructor().newInstance();
                invokeTestMethod(method,instance,accumulator);
            }
            System.out.println(accumulator);

        }catch (ReflectiveOperationException | IllegalArgumentException exception){
            throw new InvalidTestClassException(exception);
        }
    }
    protected abstract void invokeTestMethod(Method method, Object instance, TestResultAccumulator accumulator) throws IllegalAccessException;


}
