package Search;

/**
 * Created by js982 on 2017/9/17.
 */
public class GeneralSearch {
  //默认0位置不填充数据

  public int sequence(int[] strings, int string){
    for(int i =  1; i < strings.length; i++){
      if(string == strings[i])
        return i;
    }
    return -1;
  }

  public int sequencePlus(int[] strings, int string){
    strings[0] = string;
    int i = strings.length - 1;
    while(string != strings[i]){
      i--;
    }
    strings[0] = -1;
    return i;
  }

  public int binarybyHalf(int[] strings, int string){
    int high = strings.length - 1;
    int low = 1;
    int mid = (low + high)/2;

    while(string != strings[mid]){
      if(string > strings[mid]){
        low = mid + 1;
      }else{
        high = mid - 1;
      }
      mid = (high + low)/2;
    }
    return mid;
  }

  public int interPolation(int[] strings, int string){
    int high = strings.length - 1;
    int low = 1;
    int inter = low + (string - strings[low])/(strings[high] - strings[low]);

    while(string != strings[inter]){
      if(string > strings[inter]){
        low = inter + 1;
      }else{
        high = inter - 1;
      }
      inter = low + (string - strings[low])/(strings[high] - strings[low]);
    }
    return inter;
  }

  public int FibonacciSearch(int[] strings, int string){
    int[] F = generateFibonacci(strings.length + 1);
    int n = strings.length - 1;
    int k = 0;

    int low = 1;
    int high = n;
    int fibmid = 0;

    while(n > F[k] - 1){
      k++;
    }
    int[] newstrings = new int[F[k] - 1];
    for(int i = 0; i < n + 1; i ++){
      newstrings[i] = strings[i];
    }
    for(int i = n; i < F[k] - 1; i++){
      newstrings[i] = strings[n];
    }

    while(low <= high){
      fibmid = low + (F[k-1] -1);
      if(string < newstrings[fibmid]){
        high = fibmid - 1;
        k = k - 1;
      }else if(string > newstrings[fibmid]){
        low = fibmid + 1;
        k = k - 2;
      }else{
        if(fibmid <= n){
          return fibmid;
        }else{
          return n;
        }
      }
    }
    return -1;
  }

  private int[] generateFibonacci(int length){
    int[] F = new int[length];
    F[0] = 0;
    F[1] = 1;
    for(int i = 2; i < length; i++){
      F[i] = F[i-1] + F[i-2];
    }
    return F;
  }

  public static void main(String[] args){
    int[] search = new int[]{-1, 1, 4, 7, 9, 15, 20, 45, 56, 78, 89, 90, 105, 156};
    GeneralSearch generalSearch = new GeneralSearch();
    System.out.println(
            generalSearch.sequence(search, 105) +" " +
                    generalSearch.sequencePlus(search, 105) + " " +
                    generalSearch.binarybyHalf(search, 105) + " " +
                    generalSearch.interPolation(search, 105) + " " +
                    generalSearch.FibonacciSearch(search, 105));
  }
}
