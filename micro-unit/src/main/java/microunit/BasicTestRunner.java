package microunit;

import example.ExampleTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasicTestRunner extends TestRunner{
    public BasicTestRunner(Class<?> testClass){
        super((testClass));
    }
    @Override
    protected void invokeTestMethod(Method method, Object instance, TestResultAccumulator accumulator) throws IllegalAccessException{
        try {
            method.invoke(instance);
            accumulator.onSccess(method);
        }catch (InvocationTargetException e){
            Throwable cause = e.getCause();
            cause.printStackTrace(System.out);
            if(cause instanceof AssertionError){
                accumulator.onFailure(method);
            }else {
                accumulator.onError(method);
            }
        }
    }

    public static void main(String[] args){
        Class<?> testClass = ExampleTest.class;
        new BasicTestRunner(testClass).runTestMethods();
    }

}
