package Stack.SeqStack;

import jdk.jfr.events.ErrorThrownEvent;

/**
 * Created by js982 on 2017/6/25.
 */
public class ShareSeqStack {
    private String[] datas;
    int top1;
    int top2;
    int N = 5;

    public ShareSeqStack(int size) {
        if (size <= N) {
            datas = new String[size];
            top1 = -1;
            top2 = N ;
        } else {
            System.out.println("超出预设栈空间范围");
        }
    }

    public void push(String data, int stacknum) {
        if (top1 + 1 != top2) {
            if (stacknum == 1) {
                this.datas[++top1] = data;
            }
            if (stacknum == 2) {
                this.datas[--top2] = data;
            }
            if (stacknum != 1 && stacknum != 2) {
                System.out.println("没有此栈");
            }
        } else {
            System.out.println("栈已经满");
        }
    }

    public String pop(int stacknum) {
        String popdata;
        switch (stacknum) {
            case 1:
                if (top1 != -1) {
                    popdata = this.datas[top1--];
                    return popdata;
                } else {
                    System.out.println("选择的栈已经为空");
                }
                break;
            case 2:
                if (top2 != N + 1) {
                    popdata = this.datas[top2++];
                    return popdata;
                } else {
                    System.out.println("选择的栈已经为空");
                }
                break;
            default:
                return "没有此栈";
        }
        return null;
    }

    public static void main(String[] args){
        ShareSeqStack shareSeqStack = new ShareSeqStack(5);
        shareSeqStack.push("0A",1);
        shareSeqStack.push("1B",1);
        shareSeqStack.push("2C",1);
        shareSeqStack.push("3D",2);
        shareSeqStack.push("4E",2);
        shareSeqStack.push("5F",1);
        String popdata1 = shareSeqStack.pop(1);
        String popdata2 = shareSeqStack.pop(2);
    }
}
