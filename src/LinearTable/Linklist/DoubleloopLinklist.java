package LinearTable.Linklist;

/**
 * Created by js982 on 2017/6/23.
 */
public class DoubleloopLinklist {

    private int data;
    private DoubleloopLinklist prenode;
    private DoubleloopLinklist nextnode;

    public DoubleloopLinklist (){
        this.prenode = this;
        this.nextnode = this;
    }

    public void insert(DoubleloopLinklist doubleloopLinklist, int pos, int data){
        int j;
        DoubleloopLinklist p, s;
        p = doubleloopLinklist;
        for (j=1; j < pos; j++){//也叫尾插法，即找到被插入节点的前一个节点，由p指向！在其尾巴后插入，
            p = p.nextnode;
            if(p==null || pos<1){
                System.out.println("该节点越界插入，不存在");
            }
        }
        s = new DoubleloopLinklist();
        s.data = data;
        s.prenode = p;
        s.nextnode = p.nextnode;
        p.nextnode.prenode = s;
        p.nextnode = s;
    }

    public void delete(DoubleloopLinklist doubleloopLinklist, int pos){
        int j;
        DoubleloopLinklist p, s;
        p = doubleloopLinklist;
        for (j=1; j < pos; j++){//找到被删除节点的前一个节点，用p标记
            p = p.nextnode;
            if(p==null || pos<1){
                System.out.println("该节点越界删除，不存在");
            }
        }
        s = p.nextnode;
        p.nextnode = s.nextnode;
        s.nextnode.prenode = p;
        s = null;
    }

    public static void main (String[] args){
        DoubleloopLinklist list = new DoubleloopLinklist();
        list.insert(list, 1, 1);
        list.insert(list, 2, 2);
        list.insert(list, 3, 3);
        list.delete(list, 2);
    }
}
