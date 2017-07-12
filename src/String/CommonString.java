package String;

/**
 * Created by js982 on 2017/7/10.
 */
public class CommonString {

    public int Common(String S, String T, int S_pos){
        int i,j;
        i = 0;
        j = 0;
        while(i <= S.length()-1 && j <= T.length()-1){
            if(S.charAt(i) == T.charAt(j)){
                ++i;
                ++j;
            }else{
                j = 0;
                i = i - j + 1;
            }
        }
        if(j == T.length()){
            return i - T.length();
        }else{
            return -1;
        }
    }

    public static void main(String[] args){
        CommonString commonString = new CommonString();
        int pos = commonString.Common("acvbacvbacv","bacv",0);
        System.out.println(pos);
    }

}
