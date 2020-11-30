package datastructure.linkedlist.roundsinglelinkedlist;

import org.junit.Test;
//约瑟夫问题，即定长丢手绢问题
public class Josephu {

    //初始化头节点指针
    private Boy first = null;

    @Test
    public void test(){
        create(5);
        print();
        System.out.println();

        breakRound(1,2);

    }

    //创建环形单向链表
    public void create(int sum){
        if (sum < 2){
            throw new RuntimeException("输入数据不合法！");
        }

        Boy current = null;
        for (int i = 1;i <= sum;i++){
            Boy boy = new Boy(i);

            if (i == 1){
                first = boy;
                first.setNext(first);
                current = first;
            }else {
                current.setNext(boy);
                boy.setNext(first);
                current = boy;
            }
        }
    }

    //遍历环形链表
    public void print(){
        if (first == null){
            throw new RuntimeException("环形链表为空！");
        }

        Boy temp = first;
        while (true){
            System.out.println(temp);
            if (temp.getNext() == first){
                return;
            }
            temp = temp.getNext();
        }
    }

    //环形链表中总节点数
    public int count(){
        if (first == null){
            throw new RuntimeException("环形链表为空！");
        }
        int count = 0;
        Boy temp = first;
        while (true){
            count++;
            if (temp.getNext() == first){
                return count;
            }
            temp = temp.getNext();
        }
    }

    //节点出圈
    public void breakRound(int start,int slot){
        if (first == null){
            throw new RuntimeException("环形链表为空！");
        }

        if (start < 1 || start > count() || slot < 1){
            throw new RuntimeException("输入数据不合法！");
        }

        //重置指针位置
        //temp指向环形链表的最后一位，即first的前一位
        Boy temp = first;
        while (true){
            if (temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
        for (int i = 0;i < start - 1;i++){
            first = first.getNext();
            temp = temp.getNext();
        }

        while (true){
            if (first == temp){
                System.out.println(first);
                break;
            }

            for (int i = 0;i < slot - 1;i++){
                first = first.getNext();
                temp = temp.getNext();
            }

            System.out.println(first);
            first = first.getNext();
            temp.setNext(first);
        }
    }
}
