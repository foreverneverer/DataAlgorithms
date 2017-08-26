package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by js982 on 2017/8/24.
 */
public class EdgeGraph {
    private int numEdges;
    private int numVexes;
    private EdgeArray[] edgeArrays;
    private String[] vexes;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public EdgeGraph(int numVexes, int numEdges, String[] vexes) throws IOException {
        int i;
        this.numEdges = numEdges;
        this.numVexes = numVexes;
        this.vexes = vexes;
        this.edgeArrays = new EdgeArray[numEdges];

        for(i = 0; i < numEdges; i++){//注意：暂时必须按照权值从小到大的顺序输入
            edgeArrays[i] = new EdgeArray();
            edgeArrays[i].setBegin(Integer.parseInt(bufferedReader.readLine()));
            edgeArrays[i].setEnd(Integer.parseInt(bufferedReader.readLine()));
            edgeArrays[i].setWight(Integer.parseInt(bufferedReader.readLine()));
        }
    }

    public void MiniSpanTree_Kruskal(){
        int i, n, m;
        int[] connection = new int[numVexes];
        for(i = 0; i < numVexes; i++){
            connection[i] = 0;
        }

        for(i = 0; i < numEdges; i++){
            n = find(connection, edgeArrays[i].getBegin());
            m = find(connection, edgeArrays[i].getEnd());
            if(n != m){
                connection[n] = m;
                System.out.println("Kruskal最小生成树节点：("+edgeArrays[i].getBegin()+" "+edgeArrays[i].getEnd()+")="+edgeArrays[i].getWight());
            }
        }
    }

    private int find(int[] connection, int f) {
        while(connection[f] > 0)
            f = connection[f];
        return f;
    }

    public static void main(String[] args) throws IOException {
        String[] vexes = new String[]{"V0", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};
        EdgeGraph edgeGraph = new EdgeGraph(9, 15, vexes);
        edgeGraph.MiniSpanTree_Kruskal();
    }
}

class EdgeArray {
    private int begin;
    private int end;
    private int wight;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }
}
