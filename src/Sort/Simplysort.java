package Sort;

/**
 * Created by js982 on 2017/9/21.
 */
public class Simplysort {

  public int[] Bublesort(int[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = a.length - 1; j >= i; j--) {
        if (a[j] < a[j - 1])
          a = swap(a, j, j - 1);
      }
    }
    return a;
  }

  public int[] BublesortPlus(int[] a) {
    Boolean flag = true;
    for (int i = 1; i < a.length && flag; i++) {
      flag = false;
      for (int j = a.length - 1; j >= i; j--) {
        if (a[j] < a[j - 1]) {
          a = swap(a, j, j - 1);
          flag = true;//假设i = 1时，已经比较完成，进入第二轮i = 2 的循环，此时如果发现每相邻数字都是正常顺序a[j] > a[j-1]，也就是说明此时已经排序完成。所以flag将还是false，代表此时没有交换数据。所以直接退出循环就行了!
        }
      }
    }
    return a;
  }

  public int[] Selectsort(int[] a){
    int min = 0;
    for(int i = 1; i < a.length; i++){
      min = i;
      for(int j = i+1; j < a.length; j++){
        if(a[j] < a[min])
          min = j;
      }
      if(i != min)
        a = swap(a, i, min);
    }
    return a;
  }

  public int[] Insertsoert(int[] a){
    /**
     * 插入操作原理：
     * 1、选定一个位置值作为插入基准，因为首项是1位置，所以默认首先选定位置i = 2；
     * 2、如果相邻位置a[i] > a[i-1],则不做任何处理，他们两个的位置是正确的！否则，缓存a[0] = a[i]，同时作为哨兵，
     * 作后续比较的判断和插入操作
     * 3、从离a[i]最近的a[i-1]开始比较，如果>a[0](也就是a[i])，则后移（因为此时a[i]已经缓存到a[0]了,所以不必担心a[i-1]
     * 的后移会覆盖a[i]）为a[i]腾出空间；否则不做处理
     * 4、当所有的a[j](j < i)都判断好是否后移后，将留出一个合适的空位置，把a[0]（也就是a[i]）插入到这个位置，一定能保证
     * a[i-1] >= a[i] >= a[i+1]
     * /**
     * 插入排序的的数据变化是这样的
     * 假如起始数据是：-1 5 3 4 6 2
     * -1 5 3 4 6 2
     *  3 5 3 4 6 2//缓存3
     *  3 5 5 4 6 2//后移5，后移只是复制，原数据不变
     *  3 3 5 4 6 2//插入3
     *  4 3 5 4 6 2//缓存4
     *  4 3 5 5 6 2//后移5
     *  //3<4,所以不后移
     *  4 3 4 5 6 2//插入4
     *  6 3 4 5 6 2
     *
     *
     *  2 3 4 5 6 2
     *  2 3 4 5 6 6
     *  2 3 4 5 5 6
     *  2 3 4 4 5 6
     *  2 3 3 4 5 6
     *  2 2 3 4 5 6
     * -1 2 3 4 5 6
     */
    int i, j;
    for(i = 2; i < a.length; i++){//1、选定一个位置值作为插入基准，因为首项是1位置，所以默认首先选定位置i = 2；
      if(a[i] < a[i-1]){
        a[0] = a[i];//2、如果相邻位置a[i] > a[i-1],则不做任何处理，他们两个的位置是正确的！否则，缓存a[0] = a[i]，同时作为哨兵，作后续比较的判断和插入操作
        for(j = i-1; a[j] > a[0]; j--)
          a[j+1] = a[j];//3、从离a[i]最近的a[i-1]开始比较，如果>a[0](也就是a[i])，则后移（因为此时a[i]已经缓存到a[0]了,所以不必担心a[i-1]的后移会覆盖a[i]）为a[i]腾出空间；否则不做处理
        a[j+1] = a[0];//4、当所有的a[j](j < i)都判断好是否后移后，将留出一个合适的空位置，把a[0]（也就是a[i]）插入到这个位置，一定能保证a[i-1] >= a[i] >= a[i+1]
      }
    }
    a[0] = -1;//恢复a[0]原始数据
    return a;
  }

  public int[] Shellsort(int[] a){
    /**
     * 希尔排序就是插入排序的变形，插入排序默认后移到相邻位置，然后插入，希尔排序则是跳跃式后移和插入
     */
    int i, j;
    int increment = a.length - 1;
    do{
      increment = increment/3 + 1;
      for(i = increment + 1; i < a.length; i++){
       if(a[i] < a[i - increment]){
         a[0] = a[i];
         for(j = i-increment; j > 0 && a[j] > a[0] ; j-=increment)
           a[j+increment] = a[j];
         a[j+increment] = a[0];
       }
      }
    }
    while(increment > 1);
    a[0] = -1;
      return a;
  }

  private int[] swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
    return a;
  }

  public static void main(String[] args) {
    int[] a = new int[]{-1, 23, 45, 76, 34, 54, 20, 89, 9, 2, 4, 6, 7};//默认0位置不存储数据
    int[] aplus = new int[]{-1, 23, 45, 76, 34, 54, 20, 89, 9, 2, 4, 6, 7};//默认0位置不存储数据
    int[] b = new int[]{-1, 23, 45, 76, 34, 54, 20, 89, 9, 2, 4, 6, 7};//默认0位置不存储数据
    int[] c = new int[]{-1, 23, 45, 76, 34, 54, 20, 89, 9, 2, 4, 6, 7};//默认0位置不存储数据
    int[] d = new int[]{-1, 23, 45, 76, 34, 54, 20, 89, 9, 2, 4, 6, 7};//默认0位置不存储数据
    Simplysort simplysort = new Simplysort();
    a = simplysort.Bublesort(a);
    aplus = simplysort.BublesortPlus(aplus);
    b = simplysort.Selectsort(b);
    c = simplysort.Insertsoert(c);
    d = simplysort.Shellsort(d);
  }
}
