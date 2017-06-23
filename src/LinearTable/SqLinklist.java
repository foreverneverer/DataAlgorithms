package LinearTable;

/**
 * Created by js982 on 2017/6/21.
 */
public class SqLinklist {

    private int lenth;
    private String[] sqstring;
    private int Maxsize;

    public SqLinklist(int Maxsize){
        this.lenth = 0;
        this.Maxsize = Maxsize;
        this.sqstring = new String[Maxsize];
        System.out.println("已经创建空顺序表");
    }

    //插入操作
    public void insert(int pos, String string) {
        if(this.lenth == this.Maxsize) {
            System.out.println("线性表已经满了");
        }
        if(this.lenth == 0 ){
                System.out.println("顺序表为空，强制插入第0个位置");
                pos = 0;
                this.sqstring[0] = string;
                this.lenth = this.lenth+1;
        }else {
            if (pos < 0 | pos > lenth + 1) {
                System.out.println("插入位置不在范围内");
            }
            if (pos <= this.lenth) {
                for (int k = this.lenth-1; k > pos-1; k--) {
                    this.sqstring[k+1] = this.sqstring[k];
                }
                this.sqstring[pos] = string;
                this.lenth++;
            }
        }
        System.out.println("插入第"+" "+pos+" "+"位置"+"["+string+"]"+"后顺序表内容：");
        for(int i = 0; i < this.lenth; i++){
            System.out.print(sqstring[i]+" ");
        }
        System.out.println("");
    }
    
    public void delete(int pos){
        if(pos < 0 | pos > this.lenth-1){
            System.out.println("不存在该位置");
        }
        for(int k = pos+1; k < this.lenth+1; k++ ){
            this.sqstring[k-1] = this.sqstring[k];
        }
        this.lenth--;
        System.out.println("删除第"+" "+pos+" "+"位置"+"后顺序表内容：");
        for(int i = 0; i < this.lenth; i++){
            System.out.print(sqstring[i]+" ");
        }
        System.out.println("");
    }


    public static void main(String[] args){

        SqLinklist sqLinklist = new SqLinklist(10);
        sqLinklist.insert(8,"0");
        sqLinklist.insert(1,"1");
        sqLinklist.insert(2,"2");
        sqLinklist.insert(3,"3");
        sqLinklist.delete(3);
    }

}
