package Search;

/**
 * Created by js982 on 2017/9/17.
 */
public class TreeSearch {

  public Node searchBST(Node T, int key, Node f, Node p) {//Node searchBST(BiTree T, int key, BiTree, BiTree *p)
    if (T == null) {                                       //上述为C语言的函数原型，这里BiTree *p实际是二阶指针
      p = f;                                             //所以该行实际为：*p = f
      return p;
    } else if (key == T.getData()) {
      p = T;                                            //所以该行实际为：*p = T,这个时候实际由于传入的是地址，所以
      return p;                                         //形参的修改，将会使实参改变。所以如果实参传入的是null，
    } else if (key > T.getData()) {                        //经过这个函数，即使不返回形参的值，实参也会发生变化。但是在java中
      return searchBST(T.getRightNode(), key, T, p);    //是不存在这种用法的，所以insertBST将会修改
    } else {
      return searchBST(T.getLeftnode(), key, T, p);
    }
  }

  public Node insertBST(Node T, int key) {
    Node s = new Node(key);
    if (T == null) {
      T = s;
      return T;
    } else {
      Node p = null;
      p = searchBST(T, key, null, p);
      if (p.getData() == key) {//由于p在查找成功时，代表该key所在的节点，不成功时代表双亲节点！所以如果此时p.getData() != key，则意味查找不成功
        return T;
      } else if (key < p.getData()) {
        p.setLeftnode(s);
        return T;
      } else {
        p.setRightNode(s);
        return T;
      }
    }
  }

  public Node getParentNode(Node T, int key) {
    Node p = null; // 此处可得到所要找到节点的父节点
    while (T != null && T.getData() != key) { // 当前树非空并且所找节点不是当前树的根节点
      p = T;
      if (key < T.getData()) {
        T = T.getLeftnode();
      } else {
        T = T.getRightNode();
      }
    }
    if (T != null) {
      return p;//如果找到，则返回父节点
    } else {
      return null;
    }
  }


  private Node deleteBST(Node T, int key) {
    Node p = null;
    Node prev = null;
    Node q = null;
    Node s = null;
    if (T == null) {
      return T;
    } else {
      prev = getParentNode(T, key);
      if (prev != null) {
        p = searchBST(T, key, null, p);
        if (p.getData() == key) {
          if (prev.getRightNode() == p) {
            if (p.getLeftnode() == null) {
              prev.setRightNode(p.getRightNode());
              p = null;
              return T;
            } else if (p.getRightNode() == null) {
              prev.setRightNode(p.getLeftnode());
              p = null;
              return T;
            }
          } else if (prev.getLeftnode() == p) {
            if (p.getLeftnode() == null) {
              prev.setLeftnode(p.getRightNode());
              p = null;
              return T;
            } else if (p.getRightNode() == null) {
              prev.setLeftnode(p.getLeftnode());
              p = null;
              return T;
            }
          }
          if (p.getRightNode() != null && p.getRightNode() != null) {
            q = p;
            s = p.getLeftnode();
            while (s.getRightNode() != null) {
              q = s;
              s = s.getRightNode();
            }
            p.setData(s.getData());
            if (q != p) {
              q.setRightNode(s.getLeftnode());
            } else {
              q.setLeftnode(s.getLeftnode());
            }
            s = null;
            return T;
          }
        }
      }
      return T;
    }
  }

  public static void main(String[] args) {
    int[] treedate = new int[]{62, 88, 58, 47, 35, 73, 51, 99, 37, 93, 29, 37, 36};
    Node treeNode = null;
    TreeSearch treeSearch = new TreeSearch();
    for (int i = 0; i < treedate.length; i++) {
      treeNode = treeSearch.insertBST(treeNode, treedate[i]);
    }
    treeNode = treeSearch.insertBST(treeNode, 95);
    treeNode = treeSearch.deleteBST(treeNode, 99);
    treeNode = treeSearch.deleteBST(treeNode, 47);
    treeNode = treeSearch.deleteBST(treeNode, 35);
  }
}

class Node {
  private int data;
  private Node leftnode;
  private Node rightNode;

  public Node(int data) {
    this.data = data;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public Node getLeftnode() {
    return leftnode;
  }

  public void setLeftnode(Node leftnode) {
    this.leftnode = leftnode;
  }

  public Node getRightNode() {
    return rightNode;
  }

  public void setRightNode(Node rightNode) {
    this.rightNode = rightNode;
  }
}
