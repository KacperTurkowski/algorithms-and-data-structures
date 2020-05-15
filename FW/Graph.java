import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Graph {
    private static int size;
    private static int [][] edge;
    private static void read_graph(String filename) throws FileNotFoundException, LessThanZeroException {
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
            edge[intdata[0]][intdata[1]]=intdata[2];
        }
        scanner.close();
    }
    private static int[][] Floyd_Warshall(){
        for(int k=0;k<size;k++)
            for(int i=0;i<size;i++)
                for(int j=0;j<size;j++)
                    edge[i][j]=Math.min(edge[i][j],edge[i][k]+edge[k][j]);
        return edge;
    }
    public static void main(String[] args) throws FileNotFoundException, LessThanZeroException {
        String str=args[0].split("c")[0].substring(0,args[0].split("c")[0].length()-1);
        size=Integer.parseInt(str)+1;
        edge=new int[size][size];
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                edge[i][j]=1000000000;
        read_graph(args[0]);
        String[] temp;
        int[][] result =Floyd_Warshall();
        for(int i=1;i<args.length;i++){
		temp=args[i].split(",");
            System.out.print(result[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]+" ");
        }
	System.out.println();
    }
}
class LessThanZeroException extends Exception{
    private String statment;
    @Override
    public String getMessage(){return statment;}
    LessThanZeroException(int v1, int v2){this.statment="Weight is less than zero "+v1+" "+v2;}
}
