//Program przyjmuje argumenty w postaci:
//./program (name,priority) (name,priority)
class Pair{
        int priority;
        String name;
        Pair(int priority,String name){
        this.priority=priority;
        this.name=name;
        }
@Override
public String toString() {
        return name+" "+priority;
        }
}
public class Heap {
    private int Size;
    private Pair[] array=new Pair[100];
    private Heap(Pair[] MainArguments){
        System.arraycopy(MainArguments, 0, this.array, 0, MainArguments.length);
        this.Size=MainArguments.length-1;
        for(int i=(MainArguments.length/2);i>=0;i--){
            this.Max_Heapify(i);
        }
     }
    private void Max_Heapify(int i) {
        int l=Left(i);
        int r=Right(i);
        int largest;

        if(l<=this.Size && array[l].priority>array[i].priority){ largest=l;}
        else largest=i;

        if(r<=this.Size && array[r].priority>array[largest].priority) largest=r;
        if(largest!=i) {
            this.Swap(i,largest);
            Max_Heapify(largest);
        }
    }
    private void Max_Heap_Insert(Pair e){
        this.Size++;
        int temp=e.priority;
        this.array[this.Size]=e;
        this.array[this.Size].priority=-10000;
        this.Heap_Increase_Key(this.Size,temp);
    }
    private void Heap_Increase_Key(int i, int priority){
        if(priority<this.array[i].priority){
            System.out.print("new key is smaller than current key");
        }
        this.array[i].priority=priority;
        while(i>0 && this.array[this.Parent(i)].priority<this.array[i].priority){
            Swap(i,Parent(i));
            i=Parent(i);
        }
    }
    private void Swap(int a, int b){
        Pair temp=this.array[a];
        this.array[a]=this.array[b];
        this.array[b]=temp;
    }
    private int Left(int i){
        return 2*i;
    }
    private int Right(int i){
         return (2*i)+1;
    }
    private int Parent(int i){
        return i/2;
    }
    private Pair Pop(){
        Pair temp=this.array[0];
        this.array[0]=this.array[this.Size];
        this.Size--;
        this.Max_Heapify(0);
        return temp;
    }
    private void Print(){
        System.out.print("[");
        for(int i=0;i<=this.Size;i++){
            System.out.print(this.array[i].name+" "+this.array[i].priority+", ");
        }
        System.out.print("]");
    }
    private void InsertElement(Pair e){
        this.Max_Heap_Insert(e);
    }
    private int getMaxPriority(){
        return this.array[0].priority;
    }
    public static void main(String[] args) {
        char[] actualString;
        int index=0;
        String name;
        int priority;
        Pair[] temp=new Pair[args.length];
        for(int i=0;i<args.length;i++){
            actualString = args[i].toCharArray();
            for(int j=0;j<actualString.length;j++){
                if(actualString[j]==','){
                    index=j;
                }
            }
            name=args[i].substring(1,index);
            priority=Integer.parseInt(args[i].substring(index+1,actualString.length-1));
            temp[i]=new Pair(priority, name);
        }
        Heap heap=new Heap(temp);
        System.out.print("Queue: ");
        heap.Print();
        System.out.print("\nPop: ");
        Pair buf=heap.Pop();
        heap.Print();
        System.out.print("\nInsert: ");
        heap.InsertElement(buf);
        heap.Print();
        System.out.print("\nIncrease Priority");
        heap.Heap_Increase_Key(2 ,25);
        heap.Print();
        System.out.println("\n"+"Max Priority: "+heap.getMaxPriority());
    }
}
