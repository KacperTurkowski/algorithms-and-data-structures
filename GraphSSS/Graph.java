import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Vertex{
    // White 0, Grey 1,Black 2
    int d=0,f=0,name,color=0;
    boolean have_edge=false;
    Vertex parent;
    Vertex(int name){ this.name=name; }
    @Override
    public String toString(){return this.name+"";}
}
class My_List{
    Vertex[] head;
    ArrayList<Vertex>[] list;
    @SuppressWarnings("unchecked")
    My_List(int size){//Initialize neighborhood list
        this.head=new Vertex[size];
        this.list=new ArrayList[size];
        for(int i = 0; i<size; i++){
            this.head[i]=new Vertex(i);
            list[i]=new ArrayList<>();
        }
    }
}
public class Graph{
    private final static int size=500;
    private static int time;
    private My_List NList;
    private Graph(My_List list){
        this.NList=list;
    }
    private static void QS(Vertex[] array, int left,int right){
        int i=left,j=right;
        Vertex x=array[(left+right)/2],y;
        do{
            while(array[i].f > x.f  &&  i<right) i++;
            while(x.f > array[j].f && j>left) j--;
            if(i<=j){
                y=array[i];
                array[i]=array[j];
                array[j]=y;
                i++;j--;
            }
        }while(i<=j);
        if(left<j) QS(array,left,j);
        if(i<right) QS(array,i,right);
    }
    private static My_List read_graph_list(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String[] data;
        My_List NList=new My_List(size);
        while(scanner.hasNextLine()){
            data =  scanner.nextLine().split(",");//divide line to array
            for (int i = 0; i < 3; i++) data[i] = data[i].trim();//remove unnecessary space
            NList.head[Integer.parseInt(data[0])].have_edge=true;
            NList.head[Integer.parseInt(data[1])].have_edge=true;
            NList.list[Integer.parseInt(data[0])].add(NList.head[Integer.parseInt(data[1])]);
        }
        scanner.close();
        return NList;
    }
    private static My_List read_graph_list_T(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String[] data;
        My_List NList=new My_List(size);
        while(scanner.hasNextLine()){
            data = scanner.nextLine().split(",");//divide line to array
            for (int i = 0; i < 3; i++) data[i] = data[i].trim();//remove unnecessary space
            NList.head[Integer.parseInt(data[0])].have_edge=true;
            NList.head[Integer.parseInt(data[1])].have_edge=true;
            NList.list[Integer.parseInt(data[1])].add(NList.head[Integer.parseInt(data[0])]);
        }
        scanner.close();
        return NList;
    }
    private static Vertex[] DFS(Graph graph){
        time=0;
        for(Vertex v:graph.NList.head)
            if(v.color==0)  DFS_Visit(graph,v);
        Vertex[] result=graph.NList.head;
        QS(result,0,result.length-1);
        return result;
    }
    private static int DFS_T(Graph graph,Vertex[] array){
        time=0;
        int count=0;
        for (Vertex vertex : array)
            if (graph.NList.head[vertex.name].color == 0) {
                DFS_Visit(graph, graph.NList.head[vertex.name]);
                count++;
            }
        for(Vertex v:graph.NList.head)
            if(!v.have_edge) count--;
        return count;
    }
    private static void DFS_Visit(Graph graph,Vertex u){
        time++;
        u.d=time;
        u.color=1;//Gray
        for(Vertex v: graph.NList.list[u.name])
            if(v.color==0){//WHITE
                v.parent=u;
                DFS_Visit(graph,v);
            }
        u.color=2;//BLACK
        time++;
        u.f=time;
    }
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length!=1) {
            System.out.println("Not enough arguments");
            System.exit(-1 );
        }
        Graph graph=new Graph(read_graph_list(args[0]));
        Vertex[] result=DFS(graph);
        Graph graph_T=new Graph(read_graph_list_T(args[0]));
        System.out.println(DFS_T(graph_T,result));
    }
}