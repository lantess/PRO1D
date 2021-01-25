package PRO1;

import java.util.function.Function;

public class Node {
    private static int[] counts = new int[]{4,2,4,4,5,2,3,5,2,3,3,4,3};
    private static Function<float[], Integer>[] func = Functions.get();
    private int level;
    private int[][] count;
    private Node[] next;
    private Function<float[], Integer> mapper;

    public Node(int level){
        this.mapper = func[level];
        this.level = level;
        count = new int[counts[level]][5];
        next = new Node[counts[level]];
        if(level < 12){
            for(int i = 0; i < counts[level]; i++)
                next[i] = new Node(level+1);
        }
        else
            next = null;
    }

    public void train(float[] data){
        int x = mapper.apply(data);
        count[x][(int)data[13]]++;
        if(next != null)
            next[x].train(data);
    }

    public void removeEmptyChildren(){
        if(next!= null){
            for(int i = 0; i < next.length; i++){
                int sum = 0;
                for(int c : count[i])
                    sum+=c;
                if(sum==0) {
                    next[i] = null;
                }
                else {
                    next[i].removeEmptyChildren();
                }
            }
        }
    }

    public int test(float[] data){
        int x = mapper.apply(data);
        int max = maxIndex(count[x]);
        if(next==null || next[x] == null){
            /*System.out.print((data[13]==max ?"+" :max==-1 ? " ":"-")+level+"("+data[13]+"|"+max+")\t");
            for(int c : count[x])
                System.out.print(c+"\t");
            System.out.println();*/
            return max;
        }
        else{
            int index = next[x].test(data);
            if(index==-1){
                /*System.out.print((data[13]==max ?"+" :max==-1 ? " ":"-")+level+"("+data[13]+"|"+max+")\t");
                for(int c : count[x])
                    System.out.print(c+"\t");
                System.out.println();*/
                return max;
            }
            return index;
        }

    }

    private int maxIndex(int[] arr){
        int max = 0,
                index = -1;
        for(int i = 0; i < arr.length; i++)
            if(arr[i] > max){
                max = arr[i];
                index = i;
            }
        return index;
    }



}
