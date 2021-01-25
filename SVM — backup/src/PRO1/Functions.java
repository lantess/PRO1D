package PRO1;

import java.util.function.Function;

public class Functions {
    public static Function<float[], Integer>[] get(){
        Function<float[], Integer> fun0 = n -> n[0] <= 40 ? 0 : n[0] <= 50 ? 1 : n[0] <= 60 ? 2 : 3;
        Function<float[], Integer> fun1 = n -> (int)n[1];
        Function<float[], Integer> fun2 = n -> (int)n[2]-1;
        Function<float[], Integer> fun3 = n -> n[3] <= 120 ? 0 : n[3] <= 140 ? 1 : n[3] <= 160 ? 2 : 3;
        Function<float[], Integer> fun4 = n -> n[4] <= 180 ? 0 : n[4] <= 220 ? 1 : n[4] <= 260 ? 2 : n[4] <= 300 ? 3 : 4;
        Function<float[], Integer> fun5 = n -> (int)n[5];
        Function<float[], Integer> fun6 = n -> (int)n[6];
        Function<float[], Integer> fun7 = n -> n[7] <= 110 ? 0 : n[7] <= 130 ? 1 : n[7] <= 150 ? 2 : n[7] <= 170 ? 3 : 4;
        Function<float[], Integer> fun8 = n -> (int)n[8];
        Function<float[], Integer> fun9 = n -> n[9] < 1.0 ? 0 : n[9] < 2.0 ? 1 : 2;
        Function<float[], Integer> fun10 = n -> (int)n[10]-1;
        Function<float[], Integer> fun11 = n -> (int)n[11];
        Function<float[], Integer> fun12 = n -> n[12] == 3 ? 0 : n[12] == 6 ? 1 : 2;
        return new Function[]{fun0,fun1,fun2,fun3,fun4,fun5,fun6,fun7,fun8,fun9,fun10,fun11,fun12};
    }
}
