package datastructure.queue;

/**
 * 数组模拟队列
 */
public class ArrayQueue {
    private static int front = -1;//队列头，指向第一个元素的前一位
    private static int rear = -1;//队列尾，指向最后一个元素
    private static int maxSize;

    public static void main(String[] args) throws Exception {

        //创建队列
        int[] queue = createArray(3);
        add(queue,1);
        add(queue,2);

        //print(queue);

        add(queue,3);
        print(queue);

        add(queue,4);
        System.out.println();

        int i1 = get(queue);
        System.out.println(i1);
        int i2 = get(queue);
        System.out.println(i2);
        int i3 = get(queue);
        System.out.println(i3);

        add(queue,4);
        print(queue);

        /*int i4 = get(queue);
        System.out.println(i4);*/
    }

    //创建（初始化）队列
    public static int[] createArray(int length) throws Exception {
        if (length <= 0){
            throw new Exception("队列长度不满足要求！");
        }

        maxSize = length;
        int[] queue = new int[maxSize];

        return queue;
    }

    //判断队列是否空
    public static boolean isEmpty(){
        return rear == front;
    }

    //判断队列是否满
    public static boolean isFull(){
        return rear == maxSize - 1;
    }

    //添加数据
    public static void add(int[] queue,int ele){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列已满！");
            return;
        }

        //队列未满，可以添加数据
        rear++;//队列尾向后移动一位
        queue[rear] = ele;
    }

    //取数据
    public static int get(int[] queue){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        front++;//队列头向后移动一位
        return queue[front];
    }

    //打印队列
    public static void print(int[] queue){
        //判断队列是否为空
        if (isEmpty()){
            System.out.println("队列为空！");
            return;
        }

        for (int i = front + 1;i <= rear;i++){
            System.out.printf("%d\t",queue[i]);
        }
    }

    //显示队列的头数据
    public static void showHead(int[] queue){
        //判断队列是否为空
        if (isEmpty()){
            System.out.println("队列为空！");
            return;
        }

        System.out.println(queue[front + 1]);
    }

}
