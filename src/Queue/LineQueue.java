package Queue;

/**
 * Created by js982 on 2017/6/25.
 */
public class LineQueue {
    private Node frontnode;
    private Node rearnode;

    public LineQueue(){
        Node headnode = new Node();
        frontnode = headnode;
        rearnode = headnode;
    }

    public void enQueue(String data){
        Node newnode = new Node();
        newnode.setData(data);
        rearnode.setNextnode(newnode);
        rearnode = newnode;
    }

    public String deQueue(){
        if(frontnode.getNextnode() != null){
            String data;
            Node p;
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

    public static void main(String[] args){
        String stringdata;
        LineQueue lineQueue = new LineQueue();
        lineQueue.enQueue("1A");
        lineQueue.enQueue("2B");
        for(int i = 0; i < 3; i++){
            stringdata = lineQueue.deQueue();
        }
    }
}

class Node{
    private String data;
    private Node nextnode;

    public void setData(String data){
        this.data = data;
    }

    public void setNextnode(Node node){
        this.nextnode = node;
    }

    public Node getNextnode(){
        return nextnode;
    }

    public String getData(){
        return data;
    }
}
