package Queue;

/**
 * Created by js982 on 2017/6/25.
 * 规定循环队列满的标志是rear为空，rear的下一个是front不为空
 */
public class LoopQueue {
    private String[] datas;
    private int front;
    private int rear;
    private int size;

    public LoopQueue(int size){
        this.size = size;
        datas = new String[size];
        this.front = 0;
        this.rear = 0;
    }

    public int length(){
        return (rear - front + size) % size;
    }

    public boolean enQueue(String data){
        if((rear+1) % size == front){
            System.out.println("循环队列已满");
            return true;
        }
        datas[rear] = data;
        rear = (rear+1) % size;
        return false;
    }

    public String deQueue(){
        String data;
        if(rear == front){
            System.out.println("队列为空");
            return null;
        }
        data = datas[front];
        front = (front+1) % size;
        return data;
    }

    public static void main(String[] args){
        String stringdata;
        LoopQueue loopQueue = new LoopQueue(3);
        loopQueue.enQueue("0A");
        loopQueue.enQueue("1B");
        loopQueue.enQueue("2C");
        for (int i = 0; i < 3; i++){
            stringdata = loopQueue.deQueue();
        }
    }
}
