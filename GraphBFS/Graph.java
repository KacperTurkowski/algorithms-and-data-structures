import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class Graph{
    private final static int size=501;
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
            NList.list[Integer.parseInt(data[1])].add(NList.head[Integer.parseInt(data[0])]); //If undirected graph
        }
        scanner.close();
        return NList;
    }
    private static Graph BFS(Graph graph,Vertex start){
        Graph temp=new Graph(graph.NList);
        start=temp.NList.head[start.name];
        for(int i=1;i<size;i++){//prepare graph
            temp.NList.head[i].color=0;//WHITE
            temp.NList.head[i].d=1000000000;
            temp.NList.head[i].parent=null;
        }
        start.color=1;//GREY
        start.d=0;
        start.parent=null;
        ArrayDeque<Vertex> queue=new ArrayDeque<>();
        queue.push(start);
        while(!queue.isEmpty()){
            Vertex u= queue.pop();
            for(Vertex v: temp.NList.list[u.name]){
                if(v.color == 0){
                    v.color=1;//GREY
                    v.d=u.d+1;
                    v.parent=u;
                    queue.add(v);
                }
            }
            u.color=2;//BLACK
        }
        return temp;
    }
    private List<Vertex> get_path(Vertex start,Vertex end){
        start=this.NList.head[start.name];
        Graph graph=BFS(this,start);
        ArrayList<Vertex> list=new ArrayList<>();
        end=graph.NList.head[end.name];
        while(end.name!=start.name){
            list.add(end);
            if(end.parent==null) return null;
            end = end.parent;
        }
        list.add(start);
        return list;
    }
    private int get_hops(Vertex start,Vertex end){
        if(start.name==end.name) return 0;
        start=this.NList.head[start.name];
        Graph graph=BFS(this,start);
        end=graph.NList.head[end.name];
        if(end.parent==null) return -1;
        return end.d;
    }
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length<2) System.out.println("Not enough arguments");
        Graph graph=new Graph(read_graph_list(args[0]));
        for(int i=2;i<args.length;i++){
            if(Integer.parseInt(args[i])>=size || Integer.parseInt(args[i])<1) System.out.print(-1);
            else{
                System.out.print(args[i]+ "  ");
                System.out.println(graph.get_hops(new Vertex(Integer.parseInt(args[1])),new Vertex(Integer.parseInt(args[i])))+" ");
            }
        }
    }

}
