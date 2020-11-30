package datastructure.linkedlist.singlelinkedlist;

import org.junit.Test;

import java.util.Stack;

public class SingleLinkedList {

    private HeroNode headNode = new HeroNode(0,"","");//不存放任何数据，仅表示链表的头部

    @Test
    public void test(){
        add(new HeroNode(1,"宋江","及时雨"));
        add(new HeroNode(2,"卢俊义","玉麒麟"));
        add(new HeroNode(3,"吴用","智多星"));
        add(new HeroNode(4,"公孙胜","入云龙"));

        reversePrint();
    }

    //添加节点值单向链表(不考虑编号顺序)
    public void add(HeroNode node){
        HeroNode temp = headNode;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }

        temp.setNext(node);
    }

    //遍历链表
    public void show(){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode temp = headNode.getNext();
        while (temp != null){
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    //添加节点(考虑编号顺序)
    public void addByOrder(HeroNode node){
        HeroNode temp = headNode;
        while (true){
            if (temp.getNext() == null){
                break;
            }

            if (node.getId() == temp.getNext().getId()){
                throw new RuntimeException("该节点已存在！");
            }

            if (node.getId() < temp.getNext().getId()){
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }

            temp = temp.getNext();
        }

        add(node);
    }

    //修改数据
    public void update(HeroNode node){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode temp = headNode.getNext();
        while (true){
            if (temp == null){
                throw new RuntimeException("您查询的数据不存在！");
            }

            if (temp.getId() == node.getId()){
                temp.setName(node.getName());
                temp.setNickName(node.getNickName());
                return;
            }

            temp = temp.getNext();
        }
    }

    //删除节点
    public void delete(int index){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode temp = headNode;
        while (true){
            if (temp.getNext() == null){
                throw new RuntimeException("查询的节点不存在");
            }

            if (temp.getNext().getId() == index){
                temp.setNext(temp.getNext().getNext());
                return;
            }

            temp = temp.getNext();
        }
    }

    //查询节点个数（不包括头结点）
    public int sum(){
        HeroNode temp = headNode;
        int sum = 0;

        while (temp.getNext() != null){
            sum++;
            temp = temp.getNext();
        }

        return sum;
    }

    //新浪面试：查找单链表中倒数第k个节点
    public HeroNode queryReverse(int index){
        int sum = sum();
        if (sum == 0){
            throw new RuntimeException("链表为空！");
        }

        if (index > sum || index <= 0){
            throw new RuntimeException("输入数据不合法！");
        }

        HeroNode temp = headNode.getNext();
        for (int i = 1;i < sum - index + 1;i++){
            temp = temp.getNext();
        }

        return temp;
    }

    //腾讯面试：单链表反转
    public void reverse(){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode newHeadNode = new HeroNode(0,"","");

        HeroNode temp = headNode.getNext();
        HeroNode cache = headNode.getNext();
        while (temp != null){
            cache = cache.getNext();
            temp.setNext(newHeadNode.getNext());
            newHeadNode.setNext(temp);
            temp = cache;
        }

        headNode.setNext(newHeadNode.getNext());
    }

    //百度面试：逆序打印链表
    //方式1：先反转，在打印（不推荐，会破坏链表本身的结构）
    //方式2：利用栈（先进后出）
    public void reversePrint(){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = headNode.getNext();
        while (temp != null){
            stack.add(temp);
            temp = temp.getNext();
        }

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
