package Graph;

import Queue.LineQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by js982 on 2017/7/21.
 */
public class MGraph {

    private final static int INFINITY = 65536 ;
    private String[] vexs;
    private int[][] arc;
    private int numVertexes;
    private int numEdges;
    Boolean[] visited = new Boolean[10];
    Boolean[] isPrime = new Boolean[10];

    public MGraph (int numVertexes, int numEdges, String[] vexs) throws IOException {
        this.vexs = vexs;
        this.arc = new int[numVertexes][numVertexes];
        this.numVertexes = numVertexes;
        this.numEdges = numEdges;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int i, j, w;

        //邻接矩阵初始化
        for(i = 0; i < numVertexes; i++)
            for(j = 0; j < numVertexes; j++){
                if(i == j){
                    arc[i][j] = 0;
                }else{
                    arc[i][j] = INFINITY;
                }
            }
        //建立邻接矩阵
        for(int k = 0; k < numEdges; k++){
            i = Integer.parseInt(bufferedReader.readLine());
            j = Integer.parseInt(bufferedReader.readLine());
            w = Integer.parseInt(bufferedReader.readLine());
            arc[i][j] = w;
            arc[j][i] = arc[i][j];//无向图是对称的，否则有向图不对称则可以直接删除此行代码
        }
        System.out.println("邻接矩阵生成：");
    }

    public void DFSTraverse(){
        int i;
        for (i = 0; i < numVertexes; i++){
            visited[i] = false;
        }
        System.out.println("深度遍历节点：");
        for (i = 0; i < numVertexes; i++){
            if(!visited[i]);
            DFS(i);
        }
    }

    private void DFS(int i){
        visited[i] = true;
        System.out.print(vexs[i]+" ");
        for(int j = 0; j < numVertexes; j++){
            if(arc[i][j] > 0 && arc[i][j] !=INFINITY && !visited[i])
            DFS(j);//传递的参数j很巧妙，正好实现i*j算法复杂度的遍历！
        }
    }

    public void BFSTraverse(){
        for (int k = 0; k < numVertexes; k++){
            visited[k] = false;
        }
        int i = 0;
        LineQueue<Integer> graphQueue = new LineQueue<Integer>();
        System.out.println("\n广度遍历节点：");
        System.out.print(vexs[i]+" ");
        visited[i] = true;
        graphQueue.enQueue(i);
        while(!graphQueue.isEmpty()) {
            i = graphQueue.deQueue();
            for (int j = 0; j < numVertexes; j++) {
                if (!visited[j] && arc[i][j] > 0 && arc[i][j] !=INFINITY) {
                    System.out.print(vexs[j]+" ");
                    visited[j] = true;
                    graphQueue.enQueue(j);
                }
            }
        }
    }

    public void  MiniSpanTree_prim(){
        int i, j, temp1,temp2, k, t;
        temp1 = 0;
        temp2 = 0;
        i = 0;
        int MiniWight = INFINITY;
        int[] MiniTreeVex = new int[10];//miniTreeVex数组是用来存储已经确定为最小生成树节点的
        for (t = 0; t < numVertexes; t++){
            isPrime[t] = false;
        }
        MiniTreeVex[0] = 0;//默认从V0开始遍历
        isPrime[0] = true;
        System.out.println("\nPrim最小生成树节点对：");
        while (i < numVertexes-1) {
            for (k = 0; k <= i; k++) {//i指代当前有几个最小生成树节点（0代表一个，1代表两个。。。。），则通过k来遍历循环所有的生成树节点
                for (j = 0; j < numVertexes; j++) {//numVertexes是当前所有的节点，每个确定为生成树节点（一共i+1个）都要和所有的节点组合判断当前权值是否是最小
                    if (arc[MiniTreeVex[k]][j] < MiniWight && arc[MiniTreeVex[k]][j] != 0 && !isPrime[j]) {
                        //必须满足三个条件才认定此权值在当前循环是有效且最小的的：1.不等于0（因为初始化图的时候认为（n，n）=0）2.小于当前所有的权值3.没有纳入最小生成树节点列表里
                        MiniWight = arc[MiniTreeVex[k]][j];
                        //一旦确定当前循环中权值最小且有效，则记下该节点角标。如果后续循环结束此权值依然最小，退出循环时，此顶点对即为最小生成树顶点对。否则则后续循环中更新最小权值和最小权值对应的顶点对
                        temp1 = MiniTreeVex[k];
                        temp2 = j;
                    }
                }
            }
            System.out.print("(" + temp1 + " " + temp2 + ")=" + MiniWight+" ");//当前所有的最小生成树节点对应的权值已经遍历完（二层循环完成），获得最小权值和顶点对
            i++;
            MiniTreeVex[i] = temp2;//存下新增的顶点为最小生成树节点，在后续循环中再次作为判断最小权值的候选顶点
            isPrime[temp2] = true;//同时标记该顶点已经遍历，以后被确定为生成树节点的顶点到该顶点的权值无效
            MiniWight = INFINITY;
        }
    }

    public void ShortbestPath__Dijkstra(int begin){
        int i, j, k, v;
        j = k = 0;
        int[] ShortbestVex = new int[numVertexes];
        int[] Distance = new int[numVertexes];
        int[] PreShortVex = new int[numVertexes];
        Boolean[] isShort = new Boolean[numVertexes];
        //初始化邻接矩阵//
        for(v = 0; v < numVertexes; v++){
            isShort[v] = false;//所有的顶点默认未得出最短路径，即程序开始时，我们并不知道不知道到V1、V2、V3。。。的最短路径
            Distance[v] = arc[begin][v];//算出起始点到各个顶点的直接路径权值，由于邻接矩阵性质，所以默认到自身是0权值，没有直接连接是无穷大（65536）
            PreShortVex[v] = begin;//此数组存储begin到某个顶点最短路径时的该顶点的前驱顶点，默认设置为begin
        }
        ShortbestVex[j] = begin;//此数组存储已经确定出到最短路径的定点
        isShort[begin] = true;//默认begin是本身到本身，所以是已知到begin的最短路径的，置为true
        Distance[begin] = 0;//本身到本身的路径长度为0；
        //以上初始化完成//


        //开始循环：每次确定一个顶点的最短路径，并把该点纳入 ShortbestVex//
        for(v = 1; v < numVertexes; v++){
            //注意，开始算法的核心：①第一个for循环是找出以当前顶点V为最短路径节点时，到某个顶点X的路径最短，则把顶点X作为下一个最短路径节点
            //默认初始化状态时，从begin开始与之相邻的最短顶点就是最短路径节点。由于Distance[i]存储到Vi顶点的距离，所以程序本质就是求出Distance的
            //最小值罢了
            int min = INFINITY;
            for(i = 0; i < numVertexes; i++){
                if (Distance[i] < min && !isShort[i]){
                    min = Distance[i];
                    k = i;//把最小值对应的顶点角标i的值赋值给k，如果i依然是最短路径的顶点角标，则不变，否则更新k值，退出后标记该顶点已经确定为最短路径节点
                }
            }
            j++;
            ShortbestVex[j] = k;//存储第j个最短路径节点
            isShort[k] = true;//Vk确定为最短路径节点，以后循环时忽略到该节点的距离

            //注意，算法核心的核心：Distance[i]包含通过上一个最短路径节点时到i的距离，
            // 因为通过上面的循环计算出新的最短路径节点，所以就可以算出在通过该新的最短路径节点时新的路径长度，所以要比对新的路径长度和原来的路径长度的大小，如果小于则更新，否不变
            for(i = 0; i < numVertexes; i++){
                if(!isShort[i] && (min+arc[k][i]) < Distance[i]){
                    Distance[i] = min+arc[k][i];//可以看到，此时代表通过k——>i,比原先的距离要短，所以更新Distance
                    PreShortVex[i] = k;//同时注意到i的最短路径前驱是k
                }
                //此for循环结束后，则回到上一个for循环，看此时的最短路径时到哪一个顶点，然后以那个顶点作为基准继续到该循环求最短路径
                //附：理解链接http://www.cnblogs.com/biyeymyhjob/archive/2012/07/31/2615833.html
            }
        }
        System.out.println("\nDijkstra最短路径数组：表示从"+begin+"出发到某一顶点时该顶点的前驱顶点");
        for(i = 0; i < numVertexes; i++)
            System.out.print(PreShortVex[i]+" ");
        System.out.println("\nDijkstra最短路径长度数组：表示从"+begin+"出发到某一顶点时该顶点的最短路径");
        for(i = 0; i < numVertexes; i++)
            System.out.print(Distance[i]+" ");
    }

    public void ShortbestPath_Floyd(int begin, int end){
        int[][] ShortPathTable = new int[numVertexes][numVertexes];
        int[][] Pathmatirx = new int[numVertexes][numVertexes];

        int i, j, k;
        for(i = 0; i < numVertexes; ++i){
            for(j = 0; j < numVertexes; ++j){
                ShortPathTable[i][j] = arc[i][j];
                Pathmatirx[i][j] = j;
            }
        }

        for(k = 0; k < numVertexes; ++k){
            for(i = 0; i < numVertexes; ++i)
                for(j = 0; j < numVertexes; ++j){
                    if(ShortPathTable[i][j] > ShortPathTable[i][k] + ShortPathTable[k][j]){
                        ShortPathTable[i][j] = ShortPathTable[i][k] + ShortPathTable[k][j];
                        Pathmatirx[i][j] = Pathmatirx[i][k];
                    }
                }
        }

       k = Pathmatirx[begin][end];
       System.out.println("\nFloyd算法最短路径：从"+begin+"到"+end);
       System.out.print(begin+" "+k+" ");
        while(k != end){
            k = Pathmatirx[k][end];
            System.out.print(k+" ");
        }
    }

    public int[][] getArc() {
        return arc;
    }

    public static void main(String[] args) throws IOException{
        String[] vexs = new String[]{"V0", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};
        System.out.println("输入连接点和权值Vi，Vk，Wight：");
        MGraph mGraph = new MGraph(9, 16, vexs);
        int[][] arc = mGraph.getArc();
        for(int i = 0; i < mGraph.numVertexes; i++)
            for(int j = 0; j < mGraph.numVertexes; j++){
                System.out.printf("%10s",arc[i][j]+" ");
                if(j == mGraph.numVertexes - 1)
                    System.out.println();
            }
        mGraph.DFSTraverse();
        mGraph.BFSTraverse();
        mGraph.MiniSpanTree_prim();
        mGraph.ShortbestPath__Dijkstra(5);
        mGraph.ShortbestPath_Floyd(0,8);
    }
}
