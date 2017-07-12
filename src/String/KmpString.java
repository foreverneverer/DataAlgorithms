package String;

/**
 * Created by js982 on 2017/7/9.
 */
public class KmpString {

    public int[] getNext(String T){

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
                next[i] = j;
            }else if(j == 0){
                ++i;
            }else if(j != 0){
                j = next[j];
            }
        }
        return next;
    }

    public int Kmp(String S, String T, int S_pos){
        int i = S_pos;
        int j =  0;
        int[] next;
        next = this.getNext(T);
        while(i <= S.length()-1 && j <= T.length()-1){
            if(S.charAt(i) == T.charAt(j)){
                ++i;
                ++j;
            }else if(j != 0){
                j = next[j];
            }else if(j == 0){
                i++;
            }
        }
        if(j == T.length()){
            return i - T.length();
        }else{
        return -1;
        }
    }

    public static void main(String[] args){

        KmpString KMP = new KmpString();
        int pos = KMP.Kmp("aaaaaaeraty", "aaaera", 0);
        System.out.println(pos);
    }
}
