import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
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