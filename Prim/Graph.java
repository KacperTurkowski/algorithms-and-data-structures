import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
class Heap{
    private int size;
    private Vertex[] array;
    Heap(Vertex[] MainArguments) {
        array=new Vertex[MainArguments.length-1];
        System.arraycopy(MainArguments, 1, array, 0, MainArguments.length - 1);
        this.size = MainArguments.length - 2;
        for (int i = (MainArguments.length / 2); i >= 0; i--)  this.Min_Heapify(i);
    }
    private void Min_Heapify(int i) {
        int l = Left(i);
        int r = Right(i);
        int largest;
        if(l<this.size && array[l].getKey() < array[i].getKey()){
            largest = l;
        } else largest = i;
        if(r<=this.size && array[r].getKey() < array[largest].getKey()) largest = r;
        if (largest != i) {
            this.Swap(i, largest);
            Min_Heapify(largest);
        }
    }
    private void Heap_Increase_Key(int i, int priority){
        if(priority>this.array[i].getKey())
            System.out.print("new key is bigger than current key");

        this.array[i].setKey(priority);
        while(i>0 && this.array[this.Parent(i)].getKey()>this.array[i].getKey()){
            Swap(i,Parent(i));
            i=Parent(i);
        }
    }
    private void Swap(int a, int b) {
        Vertex temp=this.array[a];
        this.array[a]=this.array[b];
        this.array[b]=temp;
    }
    private int Left(int i) { return 2 * i; }
    private int Right(int i) { return (2 * i) + 1; }
    private int Parent(int i){ return i/2; }
    Vertex Pop() {
        Vertex temp = this.array[0];
        this.array[0]=this.array[this.size];
        this.size--;
        this.Min_Heapify(0);
        return temp;
    }
    boolean isEmpty() { return size == 0; }
    void change_key(Vertex v,int newPriority){
        int i;
        for(i=0; i<this.size; i++){
            if(v.getName()==this.array[i].getName()) break;
            else if(v.getName()==this.array[this.size -1-i].getName()){
                i=this.size -1-i;
                break;
            }
        }
        this.Heap_Increase_Key(i,newPriority);
    }
    boolean contain(Vertex v){
        for(int i=1;i<array.length;i++)
            if(array[i]==v) return true;
        return false;
    }
}
class Vertex{
    private int name,key=Integer.MAX_VALUE;
    private Vertex parent=null;
    Vertex(int name){
        this.name=name;
    }
    int getKey(){return this.key;}
    int getName() { return name; }
    Vertex getParent(){return parent;}
    void setKey(int key){this.key=key;}
    void setParent(Vertex parent) { this.parent = parent; }
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
    Vertex[] getHead(){return this.head;}
    ArrayList<Vertex> getList(int i) { return list[i]; }
}
public class Graph {
    private static int size;
    private static My_List NList;
    private static int [][] edge;

    private static void read_graph_list(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String temp;
        String[] data;
        int[] intdata=new int[3];
        NList=new My_List(size);
        edge=new int[size][size];
        while(scanner.hasNextLine()){
            temp = scanner.nextLine();//read line
            data = temp.split(",");//divide temp to array
            for (int i = 0; i < 3; i++){
                data[i] = data[i].trim();//remove unnecessary space
                intdata[i] = Integer.parseInt(data[i]);
            }
            NList.getList(intdata[0]).add(NList.getHead(intdata[1]));
            NList.getList(intdata[1]).add(NList.getHead(intdata[0]));
            edge[intdata[0]][intdata[1]]=intdata[2];
            edge[intdata[1]][intdata[0]]=intdata[2];
        }
        scanner.close();
    }
    private static int w(Vertex u, Vertex v){
        return edge[u.getName()][v.getName()];
    }
    private static void MST_Prim(Vertex r){
        r.setKey(0);
        Heap Q=new Heap(NList.getHead());
        Vertex u;
        while(!Q.isEmpty()){
            u=Q.Pop();
            for(Vertex v: NList.getList(u.getName())){
                if(Q.contain(v) && w(u,v)<v.getKey() && u.getParent()!=v ){
                    v.setKey(w(u,v));
                    Q.change_key(v,v.getKey());
                    v.setParent(u);
                }
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        String str=args[0].split("c")[0].substring(0,args[0].split("c")[0].length()-1);
        size=Integer.parseInt(str)+1;

        read_graph_list(args[0]);
        Vertex s=NList.getHead(Integer.parseInt(args[1]));
        MST_Prim(s);
        int weight=0;
        for(int i=1;i<size;i++){
                weight += NList.getHead(i).getKey();
        }
        System.out.println("Weight: "+weight);
    }
}
