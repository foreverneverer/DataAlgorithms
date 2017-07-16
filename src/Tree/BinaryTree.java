package Tree;

/**
 * Created by js982 on 2017/7/12.
 */
public class BinaryTree {

    private int i = 0;
    private int j = -1;
    private Node[] node = new Node[13];//Node的设置纯粹为了作为每个节点的临时存储点，用于debug时观察每个节点值是否正确
    Node preNode = null;

    public void preInit(Node BinaryNode, String[] Treedata){
        if(i < Treedata.length-1){
            if(Treedata[i].equals("#")){
                BinaryNode = null;//此处设置该节点=null，只是设置“引用”BinaryNode = null。而实际传递过来的节点还是非空，所以有必要在退栈时，设置孩子节点为空
                i++;
                j++;
                node[j] = BinaryNode;//纯粹为了Debug调试设置的变量，不然每次传递来BinaryNode实参后，BinaryNode引用改变，无法看到一个列表，可实际断点此处测试以理解我所说的
            }else {
                BinaryNode.setData(Treedata[i]);

                j++;
                node[j] = BinaryNode;//纯粹为了Debug调试设置的变量，不然每次传递来BinaryNode实参后，BinaryNode引用改变，无法看到一个列表，可实际断点此处测试以理解我所说的

                BinaryNode.setLchild(new Node());
                BinaryNode.setRchild(new Node());
                i++;
                preInit(BinaryNode.getLchild(), Treedata);//一旦退栈出来（跳出该函数）时，都要判断，该节点的左孩子（右孩子）是否被赋值，没有赋值意为#，则改孩子节点置为空结点
                if(BinaryNode.getLchild().getData() == null)
                    BinaryNode.setLchild(null);
                preInit(BinaryNode.getRchild(), Treedata);
                if(BinaryNode.getRchild().getData() == null)
                    BinaryNode.setRchild(null);
            }
        }
    }

    public void preTraverse(Node BinaryNode){
        if(BinaryNode == null){
            return;
        }
        System.out.print(BinaryNode.getData()+" ");
        preTraverse(BinaryNode.getLchild());
        preTraverse(BinaryNode.getRchild());
    }

    public void midTraverse(Node BinaryNode){
        if(BinaryNode == null){
            return;
        }
        midTraverse(BinaryNode.getLchild());
        System.out.print(BinaryNode.getData()+" ");
        midTraverse(BinaryNode.getRchild());
    }

    public void postTraverse(Node BinaryNode){
        if(BinaryNode == null){
            return;
        }
        postTraverse(BinaryNode.getLchild());
        postTraverse(BinaryNode.getRchild());
        System.out.print(BinaryNode.getData()+" ");
    }

    public void midThread(Node BinaryNode){//中序索引
        if(BinaryNode != null){
            midThread(BinaryNode.getLchild());
            if(BinaryNode.getLchild() == null){
                BinaryNode.setLTag(Node.PointerTag.Thread);
                BinaryNode.setLchild(preNode);
            }
            if(preNode == null){
                preNode = new Node();
            }
            if(preNode.getRchild() == null){
                preNode.setRTag(Node.PointerTag.Thread);
                preNode.setRchild(BinaryNode);
            }
            preNode = BinaryNode;//始终指向刚刚访问的节点，则n+1个“轮回”，就可以设置n+1节点的前节点为pre，同时在n+1个轮回可再次设置pre的节点为n+1节点，作为n+2节点的pre节点
            midThread(BinaryNode.getRchild());
        }
    }

    public void midThreadTraverse(Node BinaryNode){//中序索引遍历
        while(BinaryNode != null) {
            while (BinaryNode.getLTag() != Node.PointerTag.Thread) {
                BinaryNode = BinaryNode.getLchild();
            }
            System.out.print(BinaryNode.getData()+" ");
            while (BinaryNode.getRTag() == Node.PointerTag.Thread) {
                BinaryNode = BinaryNode.getRchild();
                System.out.print(BinaryNode.getData()+" ");
            }
            BinaryNode = BinaryNode.getRchild();
        }
    }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        Node binaryNode = new Node();
        String[] Treedata = new String[]{"A", "B", "#", "D", "#", "#", "C", "#"};
        binaryTree.preInit(binaryNode, Treedata);
        System.out.println("前序遍历：");
        binaryTree.preTraverse(binaryNode);
        System.out.println("\n"+"中序遍历：");
        binaryTree.midTraverse(binaryNode);
        System.out.println("\n"+"后序遍历：");
        binaryTree.postTraverse(binaryNode);
        System.out.println("\n中序索引遍历");
        binaryTree.midThread(binaryNode);
        binaryTree.midThreadTraverse(binaryNode);
    }
}

class Node{
    public enum PointerTag {Link, Thread}

    private String data;
    private Node lchild;
    private Node rchild;
    PointerTag LTag;
    PointerTag RTag;

    public void setData(String data){
        this.data = data;
    }

    public void setLchild(Node lchild){
        this.lchild = lchild;
    }

    public void setRchild(Node rchild){
        this.rchild = rchild;
    }

    public void setLTag(PointerTag LTag) {
        this.LTag = LTag;
    }

    public void setRTag(PointerTag RTag) {
        this.RTag = RTag;
    }

    public String getData(){
        return data;
    }

    public Node getLchild(){
            return lchild;
    }

    public Node getRchild(){
            return rchild;
    }

    public PointerTag getLTag() {
        return LTag;
    }

    public PointerTag getRTag() {
        return RTag;
    }

}
