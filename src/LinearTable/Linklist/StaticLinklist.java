package LinearTable.Linklist;


/**
 * Created by js982 on 2017/6/22.
 */
public class StaticLinklist {

    private int data;
    private int cur;

    public StaticLinklist(){}

    public StaticLinklist[] inintStaticLinklist(int size){
        StaticLinklist[] staticLinklists = new StaticLinklist[size];
        int i;
        for(i = 0; i < size-1; i++){
            staticLinklists[i] = new StaticLinklist();
            staticLinklists[i].cur = i+1;
        }
        staticLinklists[size-1] = new StaticLinklist();
        staticLinklists[size-1].cur = 0;
        return staticLinklists;
    }

    public void insert(int pos, StaticLinklist[] staticLinklists, int data){
        int k = staticLinklists.length-1;
        if (pos < 1 || pos > staticLinklists.length+1){
            System.out.println("越界！");
        }
        int j = getCurrentfirstspace(staticLinklists);
        staticLinklists[j].data = data;
        for (int i=1; i <= pos-1; i++){
            k = staticLinklists[k].cur;//k位置（数组下标）就是实际插入位置前一个数据的位置，即单链表中插入位置的前一个位置
        }
        staticLinklists[j].cur = staticLinklists[k].cur;//新数据的的cur是它前一个数据的指向
        staticLinklists[k].cur = j;//前一个数据的指向就是插入数据的位置（数组下标）

        //按数组下标顺序输出数据
        System.out.println("插入第"+" "+pos+" "+"位置"+"["+data+"]"+"后静态数组内容：");
        for (int i=0; i < staticLinklists.length; i++){
            System.out.print(staticLinklists[i].data+" ");
        }
        System.out.println();
        //按静态链表顺序输出：
        int p = staticLinklists.length-1;
        System.out.println("插入第"+" "+pos+" "+"位置"+"["+data+"]"+"后静态单链表内容：");
        System.out.print(staticLinklists[0].data+" ");
        for (int i=1; i < staticLinklists.length; i++){
            p = staticLinklists[p].cur;
            System.out.print(staticLinklists[p].data+" ");
        }
        System.out.println();
    }

    public void delete(int pos,  StaticLinklist[] staticLinklists){
        int k = staticLinklists.length-1;
        if (pos < 1 || pos > staticLinklists.length+1){
            System.out.println("越界！");
        }
        for (int i=1; i <= pos-1; i++){
            k = staticLinklists[k].cur;//k位置（数组下标）就是实际删除位置前一个数据的位置，即单链表中插入位置的前一个位置
        }
        int j = staticLinklists[k].cur;//定位到pos所在的数组下标
        staticLinklists[k].cur = staticLinklists[j].cur;
        freespace(staticLinklists,j);
        //按数组下标顺序输出数据
        System.out.println("删除第"+" "+pos+" "+"位置后静态数组内容：");
        for (int i=0; i < staticLinklists.length; i++){
            System.out.print(staticLinklists[i].data+" ");
        }
        System.out.println();
        //按静态链表顺序输出：
        int p = staticLinklists.length-1;
        System.out.println("删除第"+" "+pos+" "+"位置后静态单链表内容：");
        System.out.print(staticLinklists[0].data+" ");
        for (int i=1; i < staticLinklists.length; i++){
            p = staticLinklists[p].cur;
            System.out.print(staticLinklists[p].data+" ");
        }
        System.out.println();
    }

    private void freespace(StaticLinklist[] staticLinklists,int j) {
        staticLinklists[j].data = 0;
        staticLinklists[j].cur = staticLinklists[0].cur;
        staticLinklists[0].cur = j;
    }

    private int getCurrentfirstspace(StaticLinklist[] staticLinklists) {
        int i = staticLinklists[0].cur;
        staticLinklists[0].cur = staticLinklists[i].cur;
        return i;
    }

    public static void main(String[] args){
        int MAXSIZE = 10;
        StaticLinklist list = new StaticLinklist();
        StaticLinklist[] staticLinklists = list.inintStaticLinklist(MAXSIZE);
        list.insert(1, staticLinklists, 1);
        list.insert(2, staticLinklists, 2);
        list.insert(3, staticLinklists, 3);
        list.insert(3, staticLinklists, 4);
        list.delete(3, staticLinklists);
        list.delete(1, staticLinklists);
    }
}
