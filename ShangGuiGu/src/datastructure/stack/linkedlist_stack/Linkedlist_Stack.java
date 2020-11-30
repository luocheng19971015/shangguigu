package datastructure.stack.linkedlist_stack;

import datastructure.linkedlist.singlelinkedlist.HeroNode;
import org.junit.Test;

import java.util.Stack;

/**
 * 链表模拟栈
 */
public class Linkedlist_Stack {

    private Node headNode = new Node(0,"");
    private int maxSize;
    private int top = 0;

    @Test
    public void test(){
        createNode(5);

        push("关羽");
        push("张飞");
        push("赵云");
        push("马超");
        push("黄忠");
        print();
        //push("魏延");
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.println(pop());
        }


    }

    //初始化栈
    public void createNode(int size){
        if (size < 1){
            throw new RuntimeException("栈长度不合法");
        }

        maxSize = size;
        Node temp = headNode;
        Node node;
        for (int i = 1;i <= maxSize;i++){
            node = new Node(i,"");
            node.setNext(temp.getNext());
            temp.setNext(node);
            temp = temp.getNext();
        }
    }

    //栈满
    public boolean isFull(){
        return top == maxSize;
    }

    //栈空
    public boolean isEmpty(){
        return top == 0;
    }

    //压栈
    public void push(String name){
        if (isFull()){
            throw new RuntimeException("栈满！");
        }

        Node temp = headNode.getNext();
        while (temp != null){
            if (temp.getId() == (top + 1)){
                temp.setName(name);
                top++;
                return;
            }
            temp = temp.getNext();
        }
    }

    //出栈
    public String pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空！");
        }

        Node temp = headNode.getNext();
        String value = null;
        while (temp != null){
            if (temp.getId() == top){
                top--;
                value = temp.getName();
                break;
            }

            temp = temp.getNext();
        }
        return value;
    }

    //遍历栈
    public void print(){
        if (isEmpty()){
            throw new RuntimeException("栈空！");
        }

        Node temp = headNode.getNext();
        Stack<Node> stack = new Stack<>();
        while (true){
            if (temp == null){
                break;
            }

            if (temp.getId() > top){
                break;
            }

            stack.push(temp);
            temp = temp.getNext();
        }

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

}
