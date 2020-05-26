import java.io.*;
import java.util.*;
class HashTable{
    private LinkedList[]array;
    private final int maxWeight=1000;//array size
    private int max=-1;//temporary max weight;
    HashTable(){
        array=new LinkedList[maxWeight];
        for(int i=0;i<maxWeight;i++){
            array[i]=new LinkedList<Edge>();
        }
    }
    @SuppressWarnings("unchecked")
    void add(Edge edge){
        if(edge.getWeight()>max){
            max=(int)edge.getWeight();
        }
        if(edge.getWeight()<maxWeight) {
            this.array[(int)edge.getWeight()].add(edge);
        }
    }
    Edge pop(){
        for(int i=0;i<max;i++){
            if(this.array[i].isEmpty())  continue;
            return (Edge) this.array[i].pop();
        }
        return null;
    }
}
class Vertex{
    private int name;
    Vertex(int name){ this.name=name; }
    @Override
    public String toString() {  return this.name+""; }
}
class Edge{
    private Vertex first, second;
    private double weight;
    Edge(Vertex first,Vertex second,double weight){
        this.first=first;
        this.second=second;
        this.weight=weight;
    }
    Vertex getFirst(){ return this.first; }
    Vertex getSecond(){ return this.second; }
    double getWeight(){return this.weight;}
    @Override
    public String toString() { return this.first+","+this.second+","+this.weight; }
}
class Set{
    private Vector<Vertex> list;
    Set(){ list=new Vector<>(); }
    void add(Vertex v){ list.add(v); }
    boolean haveVertex(Vertex v){
        for (Vertex vertex : list)
            if (v == vertex) return true;
        return false;
    }
    static Set Union(Set first,Set second){
        Set result=new Set();
        result.addList(first.getList());
        for(Vertex v:second.getList()){
            if(!result.haveVertex(v))
                result.add(v);
        }
        return result;
    }
    private Vector<Vertex> getList(){ return this.list; }
    private void addList(Vector<Vertex> list){
        for(Vertex v:list)
            this.add(v);
    }
}
public class Graph {
    private static int size;
    private static HashTable L;//self sorting structure like Hashtable
    private static Vertex[] vertices;//array with reference to Vertex
    private static void read_graph_list(String filename) throws FileNotFoundException, LessThanZeroException {
        Scanner scanner = new Scanner(new File(filename));
        String temp;
        String[] data;
        int[] intdata=new int[3];
        while(scanner.hasNextLine()){
            temp = scanner.nextLine();//read line
            data = temp.split(",");//divide temp to array
            for (int i = 0; i < 3; i++){
                data[i] = data[i].trim();//remove unnecessary space
                intdata[i] = Integer.parseInt(data[i]);
            }
            if(intdata[2]<0) throw new LessThanZeroException(intdata[0],intdata[1]);
            L.add(new Edge(vertices[intdata[0]],vertices[intdata[1]],intdata[2]));
        }
        scanner.close();
    }
    private static int FIND_SET(Vertex v, Vector<Set> array){
        for(int i=0;i<array.size();i++){
            if(array.get(i).haveVertex(v)) return i;
        }
        return -1;
    }
    private static Stack<Edge> MST_Kruskal(){
        Stack<Edge> A=new Stack<>();
       Vector<Set> temp=new Vector<>(size);
       for(int i=0;i<size;i++){//MakeSet
           temp.add(new Set());
           temp.get(i).add(vertices[i]);
       }
       Set set1,set2;
       int set1Index,set2Index;
       Edge edge=L.pop();
        while(edge!=null){
            set1Index=FIND_SET(edge.getFirst(),temp);
            set1=temp.get(set1Index);
            set2Index=FIND_SET(edge.getSecond(),temp);
            set2=temp.get(set2Index);
            if(set1!=set2){
                A.push(edge);
                temp.remove(set1);
                temp.remove(set2);
                temp.add(Set.Union(set1,set2));
            }
            edge=L.pop();
        }
        return A;
    }
    public static void main(String[] args) throws IOException, LessThanZeroException {
        String str=args[0].split("c")[0].substring(0,args[0].split("c")[0].length()-1);
        size=Integer.parseInt(str)+1;
        vertices = new Vertex[size];
        for (int i = 0; i < size; i++) vertices[i] = new Vertex(i);
        L=new HashTable();
        read_graph_list(args[0]);
        Stack temp=MST_Kruskal();
        File file=new File("result.csv");
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        while(!temp.isEmpty()){
            bw.write(String.valueOf(temp.pop()));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        fileWriter.close();
    }
}
class LessThanZeroException extends Exception{
    private String statment;
    @Override
    public String getMessage(){return statment;}
    LessThanZeroException(int v1, int v2){this.statment="Weight is less than zero "+v1+" "+v2;}
}
