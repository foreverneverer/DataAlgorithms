package LinearTable.Linklist;

/**
 * Created by js982 on 2017/6/23.
 */
public class LoopLinklist {
    private int data;
    private LoopLinklist nextnode;
    LoopLinklist p;

    //循环链表只是收尾连接的方式不同，所以增删都不在重复，这里演示一下两个循环链表合成一个的例子

    public LoopLinklist(){
    }

    public LoopLinklist(LoopLinklist headnode){
        headnode.nextnode = headnode;
        p = headnode;//p是尾节点的指标，尾节点在哪他就指向哪
        for(int i = 1; i < 5; i++){
            LoopLinklist node = new LoopLinklist();
            node.data = i;
            //node.nextnode = headnode;//19行和23行任写其一即可，前者是每来一个新节点，首先连接到头结点，然后再把上一次的尾节点连接到此节点上，然后把此节点置成尾节点！后者是首先把上一次的尾节点和所有的新节点连接，并把新节置成尾节点，随后把最后一次的新节点置成尾节点后连接到头结点
            p.nextnode = node;
            p = node;
        }
        p.nextnode = headnode;
    }

    public void merge(LoopLinklist p1, LoopLinklist p2){
        LoopLinklist head1 = p1.nextnode;
        LoopLinklist head2 = p2.nextnode;
        p1.nextnode = head2.nextnode;
        p2.nextnode = head1;
        head2 = null;
    }

    public static void main (String[] args){
        LoopLinklist head1 = new LoopLinklist();
        LoopLinklist head2 = new LoopLinklist();
        LoopLinklist list1 = new LoopLinklist(head1);
        LoopLinklist list2 = new LoopLinklist(head2);
        list1.merge(list1.p, list2.p);
    }
}
