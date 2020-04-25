import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Vertex{
    private int name,d;
    private Vertex parent;
    Vertex(int name){
        this.name=name;
    }
    int getName() { return name; }
    int getD() { return d; }
    void setD(int d){this.d=d;}
    Vertex getParent() { return parent; }
    void setParent(Vertex parent){this.parent=parent;}
    @Override
    public String toString() {
        return this.name+"";
    }
}
class My_List{
    private Vertex[] head;
    private ArrayList<Vertex>[] list;
    @SuppressWarnings("unchecked")
    My_List(int size){
        this.head=new Vertex[size];
        this.list=new ArrayList[size];
        for(int i = 1; i<size; i++){
            this.head[i]=new Vertex(i);
            list[i]=new ArrayList<>();
        }
    }
    Vertex getHead(int i) { return head[i]; }
    ArrayList<Vertex> getList(int i) { return list[i]; }
}
public class Graph{
    private final static int size=101;
    private My_List NList;
    private static int[][] edge=new int[size][size];
    private Graph(My_List list){
        this.NList=list;
    }
    private static My_List read_graph_list(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String temp;
        String[] data;
        int[] intdata=new int[3];
        My_List NList=new My_List(size);
        while(scanner.hasNextLine()){
            temp = scanner.nextLine();//read line
            data = temp.split(",");//divide temp to array
            for (int i = 0; i < 3; i++){
                data[i] = data[i].trim();//remove unnecessary space
                intdata[i] = Integer.parseInt(data[i]);
            }
            NList.getList(intdata[0]).add(NList.getHead(intdata[1]));
            edge[intdata[0]][intdata[1]]= intdata[2];
        }
        scanner.close();
        return NList;
    }
    private void Initialize_Single_Source(Graph graph, Vertex s){
        Vertex v;
        for(int i=1;i<size;i++){
            v=graph.NList.getHead(i);
            v.setD(2147483647);//infinity
            v.setParent(null);
        }
        s.setD(0);
    }
    private boolean Bellman_Ford(Graph graph,Vertex s){
        Initialize_Single_Source(graph,s);
        Vertex u;
        for(int k=1;k<size;k++)//for i=1 tp |G.V| -1
            for(int i=1;i<size;i++){//for each edge
                u=graph.NList.getHead(i);
                for(Vertex v: graph.NList.getList(i)){
                    Relax(u,v,w(u,v));
                }
            }
        for(int i=1;i<size;i++){//for each edge
            u=graph.NList.getHead(i);
            for(Vertex v: graph.NList.getList(i)){
                if(v.getD()>u.getD()+w(u,v) ) return false;
            }
        }
        return true;
    }
    private static int w(Vertex u, Vertex v){
        return edge[u.getName()][v.getName()];
    }
    private void Relax(Vertex u, Vertex v, int w){
        if(v.getD()>u.getD()+w){
            v.setD(u.getD()+w);
            v.setParent(u);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph =new Graph(read_graph_list(args[0]));
        Vertex s=graph.NList.getHead(Integer.parseInt(args[1]));
        System.out.println(graph.Bellman_Ford(graph,s));
        Vertex u;
        for(int i=1;i<size;i++){
            u=graph.NList.getHead(i);
            if(u.getD()>2147483640) {
                System.out.print("("+u+",infinity), ");
                continue;
            }
            System.out.print("("+u+","+u.getD()+"), ");
        }
        System.out.println();
        for(int i=1;i<size;i++){
            u=graph.NList.getHead(i);
            System.out.print("("+u+","+u.getParent()+"), ");
        }
    }
}