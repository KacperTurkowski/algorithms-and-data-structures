import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class Vertex{
    int name;
    int color;// White 0, Grey 1,Black 2
    int d,f;
    Vertex parent;
    Vertex(int name){
        this.name=name;
    }
    @Override
    public String toString() {
        return this.name+"";
    }
}
class My_List{
    Vertex[] head;
    ArrayList<Vertex>[] list;
    @SuppressWarnings("unchecked")
    My_List(int size){//Initialize neighbrhood list
        this.head=new Vertex[size];
        this.list=new ArrayList[size];
        for(int i = 1; i<size; i++){
            this.head[i]=new Vertex(i);
            list[i]=new ArrayList<>();
        }
    }
}
public class Graph{
    private final static int size=7;
    private static int time;
    private My_List NList;
    private Graph(My_List list){
        this.NList=list;
    }
    private void to_dot(String filename) throws FileNotFoundException {
        this.NList=read_graph_list(filename);
        PrintWriter writer = new PrintWriter(new File("result.dot"));
        writer.println("graph{");
        for(int i=1;i<size;i++){
            writer.print("      "+this.NList.head[i].name+" -- {");
            for(Vertex value: this.NList.list[i]){
                writer.print(" "+value.name);
            }
            writer.println(" };");
        }
        writer.println("}");
        writer.close();
    }
    private static My_List read_graph_list(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String temp;
        String[] data;
        My_List NList=new My_List(size);
        while(scanner.hasNextLine()){
            temp = scanner.nextLine();//read line
            data = temp.split(",");//divide temp to array
            for (int i = 0; i < 3; i++) data[i] = data[i].trim();//remove unnecessary space
            NList.list[Integer.parseInt(data[0])].add(NList.head[Integer.parseInt(data[1])]);
            //NList.list[Integer.parseInt(data[1])].add(NList.head[Integer.parseInt(data[0])]); //If undirected graph
        }
        scanner.close();
        return NList;
    }
    private static Vertex[] DFS(Graph graph){
        for(int i=1;i<size;i++) {
            graph.NList.head[i].color=0;
            graph.NList.head[i].parent=null;
        }
        time=0;
        for(int i=1;i<size;i++){
            if(graph.NList.head[i].color==0){
                DFS_Visit(graph, graph.NList.head[i]);
            }
        }
        Vertex[] result=new Vertex[size];
        result[0]=new Vertex(1000000000);
        result[0].f=-1;
        for(int i=1;i<size;i++){
            result[i]=graph.NList.head[i];
            for(int j=i;j>1;j--){
                if(result[j].f<result[j-1].f){
                    break;
                }
                Vertex temp=result[j-1];
                result[j-1]=result[j];
                result[j]=temp;
            }
        }
        return result;
    }
    private static void DFS_Visit(Graph graph,Vertex u){
        time++;
        u.d=time;
        u.color=1;//Gray
        for(Vertex v: graph.NList.list[u.name]){
            if(v.color==0){//WHITE
                v.parent=u;
                DFS_Visit(graph,v);
            }
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
        for(int i=1;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
    }

}
