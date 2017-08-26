package Queue;

/**
 * Created by js982 on 2017/6/25.
 */
public class LineQueue<T> {
    private Node<T> frontnode;
    private Node<T> rearnode;

    public LineQueue(){
        Node<T> headnode = new Node<T>();
        frontnode = headnode;
        rearnode = headnode;
    }

    public void enQueue(T data){
        Node<T> newnode = new Node<T>();
        newnode.setData(data);
        rearnode.setNextnode(newnode);
        rearnode = newnode;
    }

    public T deQueue(){
        if(frontnode.getNextnode() != null){
            T data;
            Node<T> p;
            p = frontnode.getNextnode();
            if(p == rearnode){
                rearnode = frontnode;
            }
            data = p.getData();
            frontnode.setNextnode(p.getNextnode());
            p = null;
            return data;
        }
        System.out.println("队列为空");
        return null;
    }

    public boolean isEmpty(){
        if(frontnode.getNextnode() == null)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        String stringdata;
        LineQueue<String> lineQueue = new LineQueue<String>();
        lineQueue.enQueue("1A");
        lineQueue.enQueue("2B");
        for(int i = 0; i < 3; i++){
            stringdata = lineQueue.deQueue();
        }
    }
}

class Node<T>{
    private T data;
    private Node nextnode;

    public void setData(T data){
        this.data = data;
    }

    public void setNextnode(Node node){
        this.nextnode = node;
    }

    public Node getNextnode(){
        return nextnode;
    }

    public T getData(){
        return data;
    }
}
