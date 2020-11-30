package datastructure.linkedlist.doublelinkedlist;

import org.junit.Test;

public class DoubleLinkedList {

    @Test
    public void test(){
        HeroNode headNode = createHeadNode();
        add(headNode,new HeroNode(1,"吕布","无双"));
        add(headNode,new HeroNode(3,"赵云","龙胆"));
        add(headNode,new HeroNode(5,"典韦","恶来"));
        add(headNode,new HeroNode(7,"许褚","虎痴"));
        add(headNode,new HeroNode(8,"周瑜","周郎"));

        addByOrder(headNode,new HeroNode(2,"马超","锦衣"));
        addByOrder(headNode,new HeroNode(4,"黄忠","不死"));
        addByOrder(headNode,new HeroNode(9,"张海涛","傻逼"));

        frontPrint(headNode);

    }

    //初始化双向链表
    public HeroNode createHeadNode(){
        HeroNode headNode = new HeroNode(0,"","");
        return headNode;
    }

    //前向遍历双向链表
    public void frontPrint(HeroNode headNode){
        if (headNode.getNext() == null){
            throw new RuntimeException("前向遍历链表为空！");
        }

        HeroNode temp = headNode.getNext();
        while (temp != null){
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    //后向遍历双向链表
    public void behindPrint(HeroNode lastNode){
        if (lastNode.getPre() == null){
            throw new RuntimeException("后向遍历链表为空！");
        }

        HeroNode temp = lastNode;
        while (temp != null){
            if (temp.getId() != 0){
                System.out.println(temp);
            }
            temp = temp.getPre();
        }
    }

    //末尾添加
    public void add(HeroNode headNode,HeroNode elementNode){
        HeroNode temp = headNode;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }

        temp.setNext(elementNode);
        elementNode.setPre(temp);
    }

    //修改节点
    public void update(HeroNode headNode,HeroNode replaceNode){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode temp = headNode.getNext();
        while (temp != null){
            if (temp.getId() == replaceNode.getId()){
                temp.setName(replaceNode.getName());
                temp.setNickName(replaceNode.getNickName());
                return;
            }
            temp = temp.getNext();
        }

        throw new RuntimeException("您指定的节点不存在！");
    }

    //删除节点
    public void delete(HeroNode headNode,int index){
        HeroNode temp = headNode.getNext();

        while (temp != null){
            if (temp.getId() == index){
                temp.getPre().setNext(temp.getNext());
                if (temp.getNext() != null){
                    //细节：如果是删除最后一个节点则不用执行下面这句话，否则会报空指针异常
                    temp.getNext().setPre(temp.getPre());
                }
                return;
            }

            temp = temp.getNext();
        }

        throw new RuntimeException("您指定的节点不存在！");
    }

    //顺序添加
    public void addByOrder(HeroNode headNode,HeroNode elementNode){
        HeroNode temp = headNode;

        while (temp != null){
            if (temp.getNext() == null){
                add(headNode,elementNode);
                return;
            }

            if (temp.getNext().getId() == elementNode.getId()){
                throw new RuntimeException("要插入的节点序号已存在！");
            }

            if (temp.getNext().getId() > elementNode.getId()){
                elementNode.setNext(temp.getNext());
                temp.getNext().setPre(elementNode);
                temp.setNext(elementNode);
                elementNode.setPre(temp);
                return;
            }

            temp = temp.getNext();
        }
    }
}
