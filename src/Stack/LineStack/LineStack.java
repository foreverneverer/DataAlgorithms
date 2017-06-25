package Stack.LineStack;

/**
 * Created by js982 on 2017/6/25.
 * 从此，所有"结构"将有单独的类，结构里的"节点"会再新建一个类
 */
public class LineStack {
    private int length = 0;
    private Node topnode;

    public LineStack(){
        topnode = null;
    }

    public void push(String data){
        Node linestacknode = new Node();
        linestacknode.setData(data);
        linestacknode.setNextnode(this.topnode);
        this.topnode = linestacknode;
        length++;
    }

    public String pop(){
        String stringdata = null;
        if(this.topnode != null){
        Node linestacknode = this.topnode;
        stringdata = linestacknode.getData();
        this.topnode = linestacknode.getNextnode();
        linestacknode = null;
        length--;
        }else{
            System.out.println("当前链栈为空");
        }
        return stringdata;
    }

    public static void main(String[] args){
        LineStack lineStack = new LineStack();
        lineStack.push("0A");
        lineStack.push("1B");
        lineStack.push("2C");
        String popdata = lineStack.pop();
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
