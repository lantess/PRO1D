package PRO1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    private Field[] fields;
    private List<float[]> trainingData;
    private List<float[]> testData;

    public Main(){
        fields = new Field[]{
                new Field("Age", n-> ""+n+" years"),
                new Field("Sex", n-> n==1 ? "male" : "female"),
                new Field("Chest pain type", n -> n == 1 ? "typical angina" : n == 2 ? "atypical angina" : n == 3 ? "non-anginal pain" : "asymptomatic"),
                new Field("Resting blood presure", n-> ""+n+"mm Hg"),
                new Field("Serum cholestoral", n -> ""+n+"mg/dl"),
                new Field("Fasting blood sugar", n -> n ==1 ? ">120mg/dl" : "<120mg/dl"),
                new Field("Resting EKG result", n-> n==0 ? "normal" : n==1 ? "ST-T wave abnormality" : "left verticular hypertrophy"),
                new Field("Max heart rate", n-> ""+n),
                new Field("Exercise induced angina", n-> n==1 ? "yes" : "no"),
                new Field("ST depression induced by test", n-> ""+n),
                new Field("Slope od the peak exercise ST segment", n-> n==1 ? "upsloping" : n==2 ? "flat" : "downsloping"),
                new Field("Number of major vesels", n -> ""+n),
                new Field("Thal", n -> n==3 ? "normal" : n==6 ? "fixed defect" : "reversable defect"),
                new Field("Result", n-> n==0 ? "very low" : n==1 ? "low" : n==2 ? "moderate" : n==3 ? "high" : "very high")
        };
    }

    public void execute() throws Exception{
        loadData();
        //showData();
        Node tree = createTree();
        double correct = 0;
        for(float[] d : testData) {
            int res = tree.test(d);
            if(d[13]==res)
                correct++;
        }
        System.out.println("Poprawność: "+((int)correct)+"/"+ testData.size()+" - "+(correct/ testData.size()*100)+"%");
        //analyzeData();
    }

    private Node createTree() {
        Node tree = new Node(0);
        for(float[] d : trainingData)
            tree.train(d);
        tree.removeEmptyChildren();
        return tree;
    }

    private void loadData() throws Exception{
        BufferedReader bf = new BufferedReader(new FileReader("processed.cleveland.data"));
        trainingData = new ArrayList<>();
        String line;
        while((line = bf.readLine())!=null){
            String[] splData = line.split(",");
            float[] dataLine = new float[splData.length];
            for(int i = 0; i < dataLine.length; i++){
                dataLine[i] = Float.parseFloat(splData[i]);
            }
            trainingData.add(dataLine);
        }
        bf.close();
        int end = trainingData.size()/4;
        testData = trainingData.subList(0, end);
        trainingData = trainingData.subList(end, trainingData.size());
    }

    private void showData(){
        for(Field f : fields)
            System.out.print(f.getName()+" | ");
        System.out.println();

        for(float[] d : trainingData){
            for(int i = 0; i < fields.length; i++)
                System.out.print(fields[i].getValue(d[i])+" | ");
            System.out.println();
        }
    }

    private void analyzeData() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        System.out.println("=============================================================================================================");
        System.out.println("1) "+fields[0].getName());
        for(int i : new int[]{30,40,50,60,70,80})
            System.out.println(printResult(""+(i-9)+"-"+i, percentageAnalysis(n-> n[0] <= i && n[0] > i-10)));
        System.out.println("2) "+fields[1].getName());
        System.out.println(printResult("Male: \t",percentageAnalysis(n -> n[1]==1)));
        System.out.println(printResult("Female: ",percentageAnalysis(n -> n[1]==0)));
        System.out.println("3) "+fields[2].getName());
        for(int i : new int[]{1,2,3,4})
            System.out.println(printResult(fields[2].getValue(i), percentageAnalysis(n -> n[2]==i)));
        System.out.println("4) "+fields[3].getName());
        for(int i : new int[]{100,120,140,160,180,200})
            System.out.println(printResult(""+(i-19)+"-"+i, percentageAnalysis(n-> n[3] <= i && n[3] > i-20)));
        System.out.println("5) "+fields[4].getName());
        for(int i : new int[]{140,180,220,260,300,340,380,420})
            System.out.println(printResult(""+(i-39)+"-"+i, percentageAnalysis(n-> n[4] <= i && n[4] > i-40)));
        System.out.println("6) "+fields[5].getName());
        for(int i : new int[]{0,1})
            System.out.println(printResult(fields[5].getValue(i), percentageAnalysis(n -> n[5]==i)));
        System.out.println("7) "+fields[6].getName());
        for(int i : new int[]{0,1,2})
            System.out.println(printResult(fields[6].getValue(i), percentageAnalysis(n -> n[6]==i)));
        System.out.println("8) "+fields[7].getName());
        for(int i : new int[]{90,110,130,150,170,190,210})
            System.out.println(printResult(""+(i-19)+"-"+i, percentageAnalysis(n-> n[7] <= i && n[7] > i-20)));
        System.out.println("9) "+fields[8].getName());
        for(int i : new int[]{0,1})
            System.out.println(printResult(fields[8].getValue(i), percentageAnalysis(n -> n[8]==i)));
        System.out.println("10) "+fields[9].getName());
        for(float i : new float[]{0.0f,1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f})
            System.out.println(printResult(fields[9].getValue(i), percentageAnalysis(n -> n[9]>=i && n[9] < i+1.0f)));
        System.out.println("11) "+fields[10].getName());
        for(int i : new int[]{1,2,3})
            System.out.println(printResult(fields[10].getValue(i), percentageAnalysis(n -> n[10]==i)));
        System.out.println("12) "+fields[11].getName());
        for(int i : new int[]{0,1,2,3})
            System.out.println(printResult(""+i, percentageAnalysis(n -> n[11]==i)));
        System.out.println("13) "+fields[12].getName());
        for(int i : new int[]{3,6,7})
            System.out.println(printResult(""+i, percentageAnalysis(n -> n[12]==i)));
        System.out.println("=============================================================================================================");
    }

    private double[] percentageAnalysis(Predicate<float[]> filter){
        long[] cntRes = new long[]{
                trainingData.stream().filter(filter).filter(n -> n[13]==0).count(),
                trainingData.stream().filter(filter).filter(n -> n[13]==1).count(),
                trainingData.stream().filter(filter).filter(n -> n[13]==2).count(),
                trainingData.stream().filter(filter).filter(n -> n[13]==3).count(),
                trainingData.stream().filter(filter).filter(n -> n[13]==4).count()
        };
        double sum = 0.0;
        for(long l : cntRes)
            sum+=l;
        if(sum==0.0)
            sum=1.0;
        return new double[]{cntRes[0]/sum, cntRes[1]/sum, cntRes[2]/sum, cntRes[3]/sum, cntRes[4]/sum, sum};
    }

    private String printResult(String label, double[] data){
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder res = new StringBuilder(label)
                .append("\t");
        for(double d : data)
            res.append(df.format(d)).append("\t");
        return res.toString();
    }

    public static void main(String[] args) throws Exception {
	new Main().execute();
    }

}
