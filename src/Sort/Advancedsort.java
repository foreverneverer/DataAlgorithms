package Sort;

/**
 * Created by js982 on 2017/9/24.
 */
public class Advancedsort {

  public int[] Heapsort(int[] a){
    /**
     * 堆排序：指的是把一个无序序列构建成一个堆序列，这样每个子树的根节点将是最大节点，这样我们在通过取出这个
     * 最大的根节点（其实就是全树的更节点），放到最末的子节点上（层序遍历的最后一个节点），这样就得到了最大节点
     * 把剩余的节点再堆排序，再取出最大节点，放到次最末子节点，这样得到第二大节点，以次类推，直至得到倒数第一大节点，
     * 也就是最小节点。所以关键就是：如何堆排序！
     * 其实就是找到每个子树的最大节点，然后交换就行！
     */
    int i;
    int length = a.length - 1;
    for(i = length/2; i > 0; i--){
      a = Heapadjust(a, i, length);
    }

    for(i = length; i > 1; i--){
      a = swap(a, i, 1);
      a = Heapadjust(a, 1, i-1);
    }

    return a;
  }

  private int[] Heapadjust(int[] a, int i, int length) {
    int temp,j;
    temp = a[i];
    for(j = 2*i; j <= length; j*=2){
      if(j < length && a[j] < a[j+1]){
        ++j;
      }
      if(temp >= a[j]){
        break;
      }
      a[i] = a[j];
      i = j;
    }
    a[i] = temp;
    return a;
  }

  //归并排序略
  public int[] Mergesort(int[] a){return a;}

  public int[] Qucksort(int[] a){
    int length =  a.length - 1;
    return qsort(a, 1, length);
  }

  private int[] qsort(int[] a, int low, int high) {
    int pivot;
    if(low < high){
      pivot = Partition(a, low, high);
      a = qsort(a, 1, pivot-1);
      a = qsort(a, pivot+1, high);
    }
    return a;
  }

  private int Partition(int[] a, int low, int high) {
    int pivotkey;
    pivotkey = a[low];
    while(low < high){
      while(low < high && a[high] > a[low])
        high--;
      a = swap(a, high, low);
      while(low < high && a[low] < a[high])
        low++;
      a = swap(a, high, low);
    }
    return low;
  }

  private int[] swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
    return a;
  }

  public static void main(String[] args){
    int[] a = new int[]{-1, 50, 10, 90, 30, 70, 40, 80, 60, 20};//默认0位置不存储数据
    int[] b = new int[]{-1, 50, 10, 90, 30, 70, 40, 80, 60, 20};//默认0位置不存储数据
    Advancedsort advancedsort = new Advancedsort();
    a = advancedsort.Heapsort(a);
    b = advancedsort.Qucksort(a);
  }
}
