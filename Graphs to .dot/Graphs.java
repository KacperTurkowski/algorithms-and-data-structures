import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Graphs {
    private int[][] matrix;
    private static final int size=1001;

    private static void to_dot(String filename) throws FileNotFoundException {
        int [][] graph=read_graph_matrix(filename);
        PrintWriter writer = new PrintWriter(new File("result.dot"));
        writer.println("digraph{");
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph.length;j++){
                if(graph[i][j]!=0){
                    writer.println("     "+i+" -> "+j+"[label="+graph[i][j]+",weight="+graph[i][j]+"];");
                }
            }
        }
        writer.println("}");
        writer.close();
    }
    private static int[][] read_graph_matrix(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] graph = new int[size][size];
        String temp;
        String[] data;
        while(scanner.hasNextLine()){
            temp = scanner.nextLine();//read line
            data = temp.split(",");//divide temp to array
            for (int i = 0; i < 3; i++) data[i] = data[i].trim();//remove unnecessary space
            graph[Integer.parseInt(data[0])][Integer.parseInt(data[1])] = Integer.parseInt(data[2]);//write weight to neighborhood matrix
        }
        scanner.close();
        return graph;
    }
    private boolean is_regular(int d){
        int number=0;
        for (int[] ints : this.matrix) {
            for (int j = 0; j < this.matrix.length; j++) {
                if (ints[j] != 0) number++;
            }
            if (number != d) return false;
            number = 0;
        }
     return true;
    }
    private Graphs(int[][] array){
        matrix=array;
    }
    public static void main(String[] args) throws FileNotFoundException {
if(args.length!=1){
            System.out.println("Not enough arguments");
            return;
        }
        Graphs graph=new Graphs(read_graph_matrix(args[0]));
        System.out.print(graph.is_regular(5));
        to_dot(args[0]);
    }
}

