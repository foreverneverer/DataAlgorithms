package Stack.LineStack;

/**
 * Created by js982 on 2017/6/25.
 * 从此，所有"结构"将有单独的类，结构里的"节点"会再新建一个类
 */
public class LineStack<T> {
    private int length = 0;
    private Node<T> topnode;

    public LineStack(){
        topnode = null;
    }

    public void push(T data){
        Node<T> linestacknode = new Node<T>();
        linestacknode.setData(data);
        linestacknode.setNextnode(this.topnode);
        this.topnode = linestacknode;
        length++;
    }

    public T pop(){
        T data = null;
        if(this.topnode != null){
            Node<T> linestacknode = this.topnode;
            data = linestacknode.getData();
            this.topnode = linestacknode.getNextnode();
            linestacknode = null;
            length--;
        }else{
            System.out.println("当前链栈为空");
        }
        return data;
    }

    public Boolean isEmpty(){
        if(topnode == null)
            return true;
        return false;
    }

    public static void main(String[] args){
        LineStack<String> lineStack = new LineStack<String>();
        lineStack.push("0A");
        lineStack.push("1B");
        lineStack.push("2C");
        String popdata = lineStack.pop();
    }
}

class Node<T>{
    private T data;
    private Node<T> nextnode;

    public void setData(T data){
        this.data = data;
    }

    public void setNextnode(Node node){
        this.nextnode = node;
    }

    public Node<T> getNextnode(){
        return nextnode;
    }

    public T getData(){
        return data;
    }
}
