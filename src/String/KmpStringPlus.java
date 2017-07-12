package String;

/**
 * Created by js982 on 2017/7/10.
 */
public class KmpStringPlus {

    public int[] getNextPlus(String T){

        int[] next = new int[T.length()];

        int i, j;
        i = 1;
        j = 0;
        next[0] = -1;
        next[1] = 0;
        while(i < T.length()-1){
            if( T.charAt(j) == T.charAt(i) ){
                ++j;
                ++i;
                if(T.charAt(j) != T.charAt(i)){
                    next[i] = j;
                }else{
                    next[i] = next[j];
                }
            }else if(j == 0){
                ++i;
            }else if(j != 0){
                j = next[j];
            }
        }
        return next;
    }

   public int Kmplus(String Splus, String Tplus, int Splus_pos){
        int i = Splus_pos;
        int j =  0;
        int[] next;
        next = this.getNextPlus(Tplus);
        while(i <= Splus.length()-1 && j <= Tplus.length()-1){
            if(Splus.charAt(i) == Tplus.charAt(j)){
                ++i;
                ++j;
            }else if(j != 0){
                j = next[j];
            }else if(j == 0){
                i++;
            }
        }
        if(j == Tplus.length()){
            return i - Tplus.length();
        }else{
            return -1;
        }
    }

    public static void main(String[] args){

        KmpStringPlus KMPlus = new KmpStringPlus();
        int pos = KMPlus.Kmplus("aabbaaeraaaety", "aaae", 0);
        System.out.println(pos);
    }
}
