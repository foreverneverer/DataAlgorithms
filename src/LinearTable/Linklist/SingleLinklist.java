package LinearTable.Linklist;

/**
 * Created by js982 on 2017/6/22.
 */
public class SingleLinklist {

    private int data;
    private SingleLinklist nextnode;

    public SingleLinklist(SingleLinklist headnode){
        headnode.nextnode = null;
    }

    public SingleLinklist(){}

    public void insert(SingleLinklist singleLinklist, int pos, int data){
        int j = 1;
        SingleLinklist p, s;
        p = singleLinklist;
        while(p != null && j < pos){
            p = p.nextnode;
            ++j;
        }
        if (p == null || j > pos){
            System.out.println("该节点不存在");
        }

        s = new SingleLinklist();
        s.data = data;
        s.nextnode = p.nextnode;
        p.nextnode = s;

        //返回头结点，开始遍历打印
        p = singleLinklist;
        System.out.println("插入第"+" "+pos+" "+"位置"+"["+data+"]"+"后单链表内容：");
        while(p != null){
            System.out.print(p.data+" ");
            p = p.nextnode;
        }
        System.out.println();
    }

    public void delete(SingleLinklist singleLinklist, int pos){
        int j = 1;
        SingleLinklist p, s;
        p = singleLinklist;
        while(p != null && j < pos){
            p = p.nextnode;
            ++j;
        }
        if (p == null || j > pos){
            System.out.println("该节点不存在");
        }
        s = p.nextnode;
        p.nextnode = s.nextnode;
        s = null;

        //返回头结点，开始遍历打印
        p = singleLinklist;
        System.out.println("删除第"+" "+pos+" "+"位置后单链表内容：");
        while(p != null){
            System.out.print(p.data+" ");
            p = p.nextnode;
        }
        System.out.println();
    }

    public void clear(SingleLinklist node){
        SingleLinklist p, q;
        node.nextnode = null;

        p = node;
        System.out.println("已经清空：");
        while(p != null){
            System.out.print(p.data+" ");
            p = p.nextnode;
        }
        System.out.println();
    }

    public static void main(String[] args){
        SingleLinklist linklist = new SingleLinklist(new SingleLinklist());
        linklist.insert(linklist, 1, 1);
        linklist.insert(linklist, 2, 2);
        linklist.insert(linklist, 3, 3);
        linklist.insert(linklist, 4, 3);
        linklist.delete(linklist,2);
        linklist.delete(linklist,1);
        linklist.clear(linklist);
    }
}
