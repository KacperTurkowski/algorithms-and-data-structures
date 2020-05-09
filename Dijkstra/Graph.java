import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class Heap{
    private int Size;
    private Vertex[] array;
    Heap(Vertex[] MainArguments) {
        array=new Vertex[MainArguments.length];
        System.arraycopy(MainArguments, 0, array, 0, array.length);
        this.Size = MainArguments.length - 1;
        for (int i = (MainArguments.length / 2); i >= 0; i--) {
            this.Min_Heapify(i);
        }
    }
    private void Min_Heapify(int i) {
        int l = Left(i);
        int r = Right(i);
        int largest;
        if(l<this.Size && array[l].getD() < array[i].getD()){
            largest = l;
        } else largest = i;
        if(r<=this.Size && array[r].getD() < array[largest].getD()) largest = r;
        if (largest != i) {
            this.Swap(i, largest);
            Min_Heapify(largest);
        }
    }
    private void Heap_Increase_Key(int i, int priority){
        if(priority>this.array[i].getD()){
            System.out.print("new key is bigger than current key");
        }
        this.array[i].setD(priority);
        while(i>0 && this.array[this.Parent(i)].getD()>this.array[i].getD()){
            Swap(i,Parent(i));
            i=Parent(i);
        }
    }
    private void Swap(int a, int b) {
        Vertex temp=this.array[a];
        this.array[a]=this.array[b];
        this.array[b]=temp;
    }
    private int Left(int i) {
        return 2 * i;
    }
    private int Right(int i) {
        return (2 * i) + 1;
    }
    private int Parent(int i){
        return i/2;
    }
    Vertex Pop() {
        Vertex temp = this.array[0];
        this.array[0]=this.array[this.Size];
        this.Size--;
        this.Min_Heapify(0);
        return temp;
    }
    boolean isEmpty() {
        return Size == 0;
    }
    void change_D(Vertex v,int newPriority){
        int i;
        for(i=0;i<this.Size;i++){
            if(v.getName()==this.array[i].getName()) break;
            else if(v.getName()==this.array[this.Size-1-i].getName()){
                i=this.Size-1-i;
                break;
            }
        }
        this.Heap_Increase_Key(i,newPriority);
    }
}
class Vertex{
    private int name,d;
    private Vertex parent=null;
    Vertex(int name){
        this.name=name;
    }
    int getName() { return name; }
    int getD() { return d; }
    void setD(int d){this.d=d;}
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
public class Graph {
    private static int size;
    private My_List NList;
    private static int [][] edge;
    private Graph(My_List list){
        this.NList=list;
    }
    private static My_List read_graph_list(String filename) throws FileNotFoundException, LessThanZeroException {
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
            if(intdata[2]<0) throw new LessThanZeroException(intdata[0],intdata[1]);
            edge[intdata[0]][intdata[1]]=intdata[2];
        }
        scanner.close();
        return NList;
    }
    private static void Initialize_Single_Source(Graph graph, Vertex s){
        Vertex v;
        for(int i=1;i<size;i++){
            v=graph.NList.getHead(i);
            v.setD(Integer.MAX_VALUE);//infinity
            v.setParent(null);
        }
        s.setD(0);
        graph.NList.getHead(s.getName()).setD(0);
    }
    private static int w(Vertex u, Vertex v){
        return edge[u.getName()][v.getName()];
    }
    private static void Relax(Vertex u, Vertex v, int w,Heap Q){
        if(v.getD()>(u.getD()+w)&&(u.getD()+w)>0){
            Q.change_D(v,u.getD()+w);
            v.setParent(u);
        }
    }
    private static void Dijkstra(Graph graph, Vertex s){
        Initialize_Single_Source(graph,s);
        Vertex[] temp =new Vertex[size-1];
        for(int i=1;i<size;i++) temp[i-1]=graph.NList.getHead(i);
        Heap Q=new Heap(temp);
        Vertex u;
        while(!Q.isEmpty()){
            u=Q.Pop();
            for(Vertex v: graph.NList.getList(u.getName()))
                Relax(u,v,w(u,v),Q);
        }
    }
    public static void main(String[] args) throws FileNotFoundException, LessThanZeroException {
        String str=args[0].split("c")[0].substring(0,args[0].split("c")[0].length()-1);
        size=Integer.parseInt(str)+1;
        edge=new int[size][size];
        Graph graph = new Graph(read_graph_list(args[0]));
        Vertex s=graph.NList.getHead(Integer.parseInt(args[1]));
        Vertex e=graph.NList.getHead(Integer.parseInt(args[2]));
        Vertex e_2=graph.NList.getHead(Integer.parseInt(args[3]));
        long millisActualTime = System.currentTimeMillis();
        Dijkstra(graph, s);
        System.out.println(e.getD() + "  " + e_2.getD());
        long executionTime=System.currentTimeMillis() - millisActualTime;
        System.out.println(executionTime + " ms");
    }
}
class LessThanZeroException extends Exception{
    private String statment;
    @Override
    public String getMessage(){return statment;}
    LessThanZeroException(int v1, int v2){this.statment="Weight is less than zero "+v1+" "+v2;}
}
