package PRO1;

import java.util.List;
import java.util.stream.Collectors;

public class Bayes {
    private List<float[]> trainingData;
    private long[] cnt;

    public Bayes(List<float[]> trainingData){
        this.trainingData = trainingData;
        cnt = new long[5];
        for(int i = 0; i < cnt.length; i++) {
            int finalI = i;
            cnt[i] = trainingData.stream().filter(n->n[13]== finalI).count();
        }
    }

    public int test(float[] data){
        double[] res = new double[5];
        for(int i = 0; i < res.length; i++)
            res[i] = (double)cnt[i]/trainingData.size();
        for(int i = 0; i < res.length; i++){
            int finI = i;
            List<float[]> samples = trainingData.stream().filter(n -> n[13]==finI).collect(Collectors.toList());
            for(int j = 0; j < 13; j++) {
                int finalJ = j;
                double c = samples.stream().filter(n->n[finalJ]<=data[finalJ]*1.2 && n[finalJ]>=data[finalJ]*0.8).count();
                if(c==0.0)
                    res[i]*=(1.0/cnt[i]);
                else
                    res[i]*=(c/cnt[i]);
            }
        }
        int in = 0;
        double max = 0.0;
        for(int i = 0; i < res.length; i++){
            System.out.print(res[i]+"\t");
            if(res[i] > max){
                max = res[i];
                in = i;
            }
        }
        System.out.println();
        return in;
    }

}
