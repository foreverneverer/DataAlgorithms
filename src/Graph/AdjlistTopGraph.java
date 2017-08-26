package Graph;

import Stack.LineStack.LineStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by js982 on 2017/8/26.
 */
public class AdjlistTopGraph {
    private TopVertexNode[] Adjtlist = new TopVertexNode[20];
    private int numVertexes, numEdges;
    Boolean[] visited = new Boolean[10];
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int[] earlyVtime; // 优化拓扑排序（专用于关键路径）：最早发生时间数组earlyVtime
    LineStack<Integer> LineStackSort = new LineStack<Integer>();;

    public void createAlTGraph (String[] topVertexNode, int numEdges, int[] in) throws IOException {
        this.numVertexes =  topVertexNode.length;
        this.numEdges = numEdges;
        int i;
        //创建顶点
        for (i = 0; i < numVertexes; i++){
            Adjtlist[i] = new TopVertexNode();
            Adjtlist[i].setData(topVertexNode[i]);
            Adjtlist[i].setIn(in[i]);
            Adjtlist[i].setFirstTopEdgeNode(null);
        }

        for(i = 0; i < numEdges; i++){
            int j, k, weight;
            j = Integer.parseInt(bufferedReader.readLine());
            k = Integer.parseInt(bufferedReader.readLine());
            weight = Integer.parseInt(bufferedReader.readLine());

            TopEdgeNode topEdgeNode = new TopEdgeNode();
            topEdgeNode.setAdjvex(k);
            topEdgeNode.setNextTopEdgeNode(Adjtlist[j].getFirstTopEdgeNode());
            topEdgeNode.setWeight(weight);
            Adjtlist[j].setFirstTopEdgeNode(topEdgeNode);
        }
    }

    public void ToplogicalSort(){
        int i, count = 0;
        int v = 0;
        LineStack<Integer> VbaseInLineStack = new LineStack<Integer>();
        for(i = 0; i < numVertexes; i++){
            if(Adjtlist[i].getIn() == 0){
                VbaseInLineStack.push(i);
            }
        }

        //优化拓扑排序（专用于关键路径）：此部分为初始化最早发生时间数组earlyVtime
        earlyVtime = new int[numVertexes];
        for(i = 0; i < numVertexes; i++){
            earlyVtime[i] = 0;
        }

        System.out.println("拓扑排序：");
        while(!VbaseInLineStack.isEmpty()){
            v = VbaseInLineStack.pop();
            System.out.print(Adjtlist[v].getData()+" ");
            count++;

            //优化拓扑排序（专用于关键路径）：此代码把拓扑排序的输出入栈
            LineStackSort.push(v);

            TopEdgeNode topEdgeNode;
            for(topEdgeNode = Adjtlist[v].getFirstTopEdgeNode();
                topEdgeNode != null;
                topEdgeNode = topEdgeNode.getNextTopEdgeNode()){

                int adjvex = topEdgeNode.getAdjvex();
                int in = Adjtlist[adjvex].getIn();
                Adjtlist[adjvex].setIn(--in);
                if(Adjtlist[adjvex].getIn() == 0)
                    VbaseInLineStack.push(adjvex);

                //优化拓扑排序（专用于关键路径）：此段代码求出每个顶点的最早发生时间
                if((earlyVtime[v] + topEdgeNode.getWeight()) > earlyVtime[adjvex])
                    earlyVtime[adjvex] = earlyVtime[v] + topEdgeNode.getWeight();
            }
        }

        if(count < numVertexes)
            System.out.println("\n存在环");
        System.out.println("\n已经排序完成");
    }

    public void CriticalPath(){
        int i, v;
        TopEdgeNode topEdgeNode;
        int[] laterVtime = new int[numVertexes];
        for(i = 0; i < numVertexes; i++)
            laterVtime[i] = earlyVtime[numVertexes-1];
        while(!LineStackSort.isEmpty()){
            v = LineStackSort.pop();
            for(topEdgeNode = Adjtlist[v].getFirstTopEdgeNode();
                topEdgeNode != null;
                topEdgeNode = topEdgeNode.getNextTopEdgeNode()){

                int adjvex = topEdgeNode.getAdjvex();
                if(laterVtime[adjvex] - topEdgeNode.getWeight() < laterVtime[v])
                    laterVtime[v] = laterVtime[adjvex] - topEdgeNode.getWeight();
            }
        }

        System.out.println("最佳路径：");
        for(i = 0; i < numVertexes; i++){
            for(topEdgeNode = Adjtlist[i].getFirstTopEdgeNode();
                topEdgeNode != null;
                topEdgeNode = topEdgeNode.getNextTopEdgeNode()){

                int adjvex = topEdgeNode.getAdjvex();
                int earlyVictive = earlyVtime[i];
                int laterVictive = laterVtime[adjvex] - topEdgeNode.getWeight();
                if(earlyVictive == laterVictive)
                    System.out.print("<"+Adjtlist[i].getData()+" "+Adjtlist[adjvex].getData()+">"+topEdgeNode.getWeight()+" ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] vexs = new String[]{"V0", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8", "V9"};
        int[] in = new int[]{0, 1, 1, 2, 2, 1, 1, 2, 1, 2};
        AdjlistTopGraph adjlistTopGraph = new AdjlistTopGraph();
        adjlistTopGraph.createAlTGraph(vexs, 13, in);
        adjlistTopGraph.ToplogicalSort();
        adjlistTopGraph.CriticalPath();
    }
}

class TopEdgeNode{
    private int adjvex;
    private int weight;
    private TopEdgeNode nextTopEdgeNode;

    public int getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public TopEdgeNode getNextTopEdgeNode() {
        return nextTopEdgeNode;
    }

    public void setNextTopEdgeNode(TopEdgeNode nextTopEdgeNode) {
        this.nextTopEdgeNode = nextTopEdgeNode;
    }
}

class TopVertexNode{
    private int in;
    private String data;
    private TopEdgeNode firstTopEdgeNode;

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TopEdgeNode getFirstTopEdgeNode() {
        return firstTopEdgeNode;
    }

    public void setFirstTopEdgeNode(TopEdgeNode firstTopEdgeNode) {
        this.firstTopEdgeNode = firstTopEdgeNode;
    }
}
