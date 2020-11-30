package datastructure.queue;

//数组取模实现环形队列
public class CircleQueue {

    private static int maxSize;
    private static int front = 0;//队列头，指向第一个元素
    private static int rare = 0;//队列尾，指向最后一个元素的后一位

    public static void main(String[] args) {

        int[] queue = createQueue(4);
        add(queue,1);
        add(queue,2);
        add(queue,3);

        int head = head(queue);
        System.out.println(head);
        show(queue);

        //add(queue,4);

        int i1 = get(queue);
        int i2 = get(queue);
        int i3 = get(queue);
        System.out.println("1:" + i1 + ",2:" + i2 + ",3:" + i3);

        //get(queue);

        add(queue,4);
        add(queue,5);
        add(queue,6);

        head = head(queue);
        System.out.println(head);
        show(queue);

    }

    //创建（初始化）队列
    public static int[] createQueue(int length) {
        if (length <= 0){
            throw new RuntimeException("队列长度不满足要求！");
        }

        maxSize = length;
        int[] queue = new int[maxSize];

        return queue;
    }

    //判断队列是否满
    public static boolean isFull(){
        return (rare + 1) % maxSize == front;
    }

    //判断队列是否空
    public static boolean isEmpty(){
        return rare == front;
    }

    //添加数据
    public static void add(int[] queue,int ele){
        //判断队列是否满
        if (isFull()){
            throw new RuntimeException("队列已满！");
        }

        //添加数据
        queue[rare] = ele;
        rare = (rare + 1) % maxSize;//一定要取模，否则不能成环，且可能数组越界
    }

    //获得数据
    public static int get(int[] queue){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        int result = queue[front];
        front = (front + 1) % maxSize;

        return result;
    }

    //当前数组有效值个数
    public static int valid(){
        return (rare + maxSize - front) % maxSize;
    }

    //遍历环形队列
    public static void show(int[] queue){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        for (int i = front;i < front + valid();i++){
            System.out.printf("%d\t",queue[i % maxSize]);
        }

        System.out.println();
    }

    //返回队列头元素
    public static int head(int[] queue){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        return queue[front];
    }
}
