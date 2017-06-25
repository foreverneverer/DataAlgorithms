package Stack.SeqStack;

/**
 * Created by js982 on 2017/6/25.
 */
public class SingleSeqStack {
    private String[] data;
    private int top;
    private int MAXSIZE = 10;

    public SingleSeqStack(int size){
        if (size <= MAXSIZE){
        data = new String[size];
        top = -1;
        }else{
        System.out.println("超出预设栈空间范围");
        }
    }

    public void push(String data){
        if (top <= MAXSIZE){
            top++;
            this.data[top] = data;
        }else{
        System.out.println("栈已经满了");
        }
    }

    public String pop(){
        String popdata;
        if (this.data[top] != null){
            popdata = this.data[top];
            top--;
            return popdata;
        }
        return ("数据为空");
    }

    public static void main(String[] args){
        SingleSeqStack seqStack = new SingleSeqStack(10);
        seqStack.push("1A");
        seqStack.push("2B");
        seqStack.push("3B");
        String stringdata = seqStack.pop();
    }
}
