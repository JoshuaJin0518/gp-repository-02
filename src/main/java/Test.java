import anontation.ZouJin;

import java.lang.reflect.Method;

public class Test {
    @ZouJin
    public void MyMethod(){

    }

    public static void main(String[] args) throws Exception {
      Test test=new Test();
        Method myMethod = test.getClass().getMethod("MyMethod");
        ZouJin annotation = myMethod.getAnnotation(ZouJin.class);
        if (annotation!=null){
            System.out.println(annotation.value());

        }
    }
}
