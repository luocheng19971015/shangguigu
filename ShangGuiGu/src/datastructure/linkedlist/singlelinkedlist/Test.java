package datastructure.linkedlist.singlelinkedlist;
//合并两个有序的单链表，使其合并后依旧有序
public class Test {

    @org.junit.Test
    public void test(){
        //初始化头结点
        HeroNode heroNode1 = createHeadNode();
        HeroNode heroNode2 = createHeadNode();

        //添加数据并打印
        addByOrder(heroNode1,new HeroNode(1,"吕布","奉先"));
        addByOrder(heroNode1,new HeroNode(3,"赵云","子龙"));
        addByOrder(heroNode1,new HeroNode(5,"关羽","云长"));

        addByOrder(heroNode2,new HeroNode(2,"张飞","益德"));
        addByOrder(heroNode2,new HeroNode(4,"徐晃","公明"));
        addByOrder(heroNode2,new HeroNode(6,"马超","孟起"));

        show(heroNode1);
        System.out.println();
        show(heroNode2);
        System.out.println();

        HeroNode combine = combine(heroNode1, heroNode2);
        show(combine);
    }

    //初始化单链表
    public HeroNode createHeadNode(){
        HeroNode headNode = new HeroNode(0,"","");
        return headNode;
    }

    //添加数据（考虑顺序）
    public void addByOrder(HeroNode headNode,HeroNode node){
        HeroNode temp = headNode;
        while (true){
            if (temp.getNext() == null){
                break;
            }

            if (node.getId() == temp.getNext().getId()){
                throw new RuntimeException("节点"+ node.getId() + "已存在！");
            }

            if (node.getId() < temp.getNext().getId()){
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }

            temp = temp.getNext();
        }

        add(headNode,node);
    }

    //添加节点值单向链表(不考虑编号顺序)
    public void add(HeroNode headNode,HeroNode node){
        HeroNode temp = headNode;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }

        temp.setNext(node);
    }

    //遍历链表
    public void show(HeroNode headNode){
        if (headNode.getNext() == null){
            throw new RuntimeException("链表为空！");
        }

        HeroNode temp = headNode.getNext();
        while (temp != null){
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    //合并单链表并使其有序
    public HeroNode combine(HeroNode headNode1,HeroNode headNode2){
        if (headNode1.getNext() == null){
            throw new RuntimeException("链表1为空，合并结果即链表2");
        }else if (headNode2.getNext() == null){
            throw new RuntimeException("链表2为空，合并结果即链表1");
        }

        HeroNode newHeadNode = createHeadNode();
        HeroNode temp1 = headNode1.getNext();
        HeroNode temp2 = headNode2.getNext();
        HeroNode cache1 = headNode1.getNext();
        HeroNode cache2 = headNode2.getNext();
        while (temp1 != null || temp2 != null){
            if (temp1 == null){
                cache2 = cache2.getNext();
                temp2.setNext(null);
                addByOrder(newHeadNode,temp2);
                temp2 = cache2;
                continue;
            }

            if (temp2 == null){
                cache1 = cache1.getNext();
                temp1.setNext(null);
                addByOrder(newHeadNode,temp1);
                temp1 = cache1;
                continue;
            }

            //若1和2id相同默认加入1在前
            if (temp2.getId() >= temp1.getId()){
                cache1 = cache1.getNext();
                temp1.setNext(null);
                addByOrder(newHeadNode,temp1);
                temp1 = cache1;
                continue;
            }

            if (temp1.getId() > temp2.getId()){
                cache2 = cache2.getNext();
                temp2.setNext(null);
                addByOrder(newHeadNode,temp2);
                temp2 = cache2;
                continue;
            }
        }

        return newHeadNode;
    }
}
