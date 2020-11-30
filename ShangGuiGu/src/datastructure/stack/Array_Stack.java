package datastructure.stack;

import org.junit.Test;

public class Array_Stack {

    private int top = -1;
    private int[] stack;
    private int maxSize;

    @Test
    public void test(){
        int[] satck = createSatck(3);
        push(0);
        push(1);
        printStack();

        push(2);
        System.out.println();
        printStack();

        //push(3);

        System.out.println();
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());

        //System.out.println(pop());
        System.out.println();

        push(3);
        push(4);
        push(5);
        printStack();
    }

    //初始化栈
    public int[] createSatck(int size){
        maxSize = size;
        stack = new int[maxSize];
        return stack;
    }

    //判断是否满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判断是否空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int element){
        if (isFull()){
            throw new RuntimeException("栈满！");
        }

        top++;
        stack[top] = element;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空！");
        }

        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void printStack(){
        if (isEmpty()){
            throw new RuntimeException("栈空！");
        }

        for (int i = top;i > -1;i--){
            System.out.println(stack[i]);
        }
    }
}
