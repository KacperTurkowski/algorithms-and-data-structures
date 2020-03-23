import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
class Vertex{
    int name;
    int color;// White 0, Grey 1,Black 2
    int d;
    Vertex parent;
    Vertex(int name){
        this.name=name;
    }
    @Override
    public String toString() {
        return this.name+"";
    }
}