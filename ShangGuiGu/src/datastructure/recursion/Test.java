package datastructure.recursion;

public class Test {

    @org.junit.Test
    public void test1(){
        System.out.println(test2(5));
    }

    //递归打印问题
    public void test(int n){
        if(n > 2){
            test(n -1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public int test2(int n){
        if (n != 1){
            return n * test2(n - 1);
        }
        return 1;
    }
}
