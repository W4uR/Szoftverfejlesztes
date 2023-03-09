package microunit;

public class Assert {
    public static void fail(String message){
        if(message == null)
            fail();
        throw new AssertionError(message);
    }

    public  static  void fail(){
        throw new AssertionError();
    }

    public static void assertTrue(boolean condition, String message){
        if(!condition){
            fail(message);
        }
    }
    public static void assertTrue(boolean condition){
        if(!condition){
            fail();
        }
    }
}
