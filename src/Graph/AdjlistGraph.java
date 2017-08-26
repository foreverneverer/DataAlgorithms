package Graph;

import Queue.LineQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by js982 on 2017/8/21.
 */
public class AdjlistGraph {

    private VertexNode[] Adjlist = new VertexNode[10];
    private int numVertexes, numEdges;
    Boolean[] visited = new Boolean[10];
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void createAlGraph (String[] vertexNode, int numEdges) throws IOException {
        this.numVertexes =  vertexNode.length;
        this.numEdges = numEdges;
        //创建顶点
        for (int i = 0; i < numVertexes; i++){
            Adjlist[i] = new VertexNode();
            Adjlist[i].setData(vertexNode[i]);
            Adjlist[i].setFirstEdgeNode(null);
        }
        //创建边表：采用头插法
        for(int i = 0; i < numEdges; i++){
            int j, k;
            j = Integer.parseInt(bufferedReader.readLine());
            k = Integer.parseInt(bufferedReader.readLine());
            EdgeNode jnode = new EdgeNode();
            jnode.setAdjvex(k);
            jnode.setNextEdgeNode(Adjlist[j].getFirstEdgeNode());
            Adjlist[j].setFirstEdgeNode(jnode);

            EdgeNode knode = new EdgeNode();
            knode.setAdjvex(j);
            knode.setNextEdgeNode(Adjlist[k].getFirstEdgeNode());
            Adjlist[k].setFirstEdgeNode(knode);
        }
    }

    public void DFSTraverse(){
        int i;
        for(i = 0; i < numVertexes; i++){
            visited[i] = false;
        }
        for(i = 0; i < numVertexes; i++){
            if(!visited[i])
                DFS(i);
        }
    }

    private void DFS(int i){
        EdgeNode p;
        visited[i] = true;
        p = Adjlist[i].getFirstEdgeNode();
        System.out.println("深度遍历节点："+ Adjlist[i].getData());
        while(p != null){
            if(!visited[p.getAdjvex()]){
                DFS(p.getAdjvex());
            }
            p = p.getNextEdgeNode();
        }
    }

    public void BFSTraverse(){
        int i = 0;
        EdgeNode p;
        for (int j = 0; j < numVertexes; j++){
            visited[j] = false;
        }
        LineQueue<Integer> graphQueue = new LineQueue<Integer>();
        System.out.println("广度遍历节点："+Adjlist[i].getData());
        visited[i] = true;
        graphQueue.enQueue(i);
        while(!graphQueue.isEmpty()){
            i = graphQueue.deQueue();
            p = Adjlist[i].getFirstEdgeNode();
            while(p != null){
                if(!visited[p.getAdjvex()]){
                    visited[p.getAdjvex()] = true;
                    System.out.println("广度遍历节点："+Adjlist[p.getAdjvex()].getData());
                    graphQueue.enQueue(p.getAdjvex());
                }
                p = p.getNextEdgeNode();
            }
        }
    }

    static public void main(String[] args) throws IOException {
        AdjlistGraph adjlistGraph = new AdjlistGraph();
        String[] vertexNode = new String[]{"V0", "V1", "V2", "V3", "V4"};
        adjlistGraph.createAlGraph(vertexNode,6);
        adjlistGraph.DFSTraverse();
        adjlistGraph.BFSTraverse();
    }
}

class EdgeNode {
    private int adjvex, weight;
    private EdgeNode nextEdgeNode;

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

    public EdgeNode getNextEdgeNode() {
        return nextEdgeNode;
    }

    public void setNextEdgeNode(EdgeNode nextEdgeNode) {
        this.nextEdgeNode = nextEdgeNode;
    }
}

class VertexNode {
    private String data;
    private EdgeNode firstEdgeNode;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public EdgeNode getFirstEdgeNode() {
        return firstEdgeNode;
    }

    public void setFirstEdgeNode(EdgeNode firstEdgeNode) {
        this.firstEdgeNode = firstEdgeNode;
    }
}


