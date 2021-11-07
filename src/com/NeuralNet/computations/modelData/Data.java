package modelData;

import java.util.ArrayList;
import java.util.Random;

public class Data {


    private   double[][] Ddata = {{5.1, 3.5, 1.4, 0.2},
            {4.9, 3.0, 1.4, 0.2},
            {4.7, 3.2, 1.3, 0.2},
            {4.6, 3.1, 1.5, 0.2},
            {5.0, 3.6, 1.4, 0.2},
            {5.4, 3.9, 1.7, 0.4},
            {4.6, 3.4, 1.4, 0.3},
            {5.0, 3.4, 1.5, 0.2},

            {5.5, 2.3, 4.0, 1.3},
            {6.5, 2.8, 4.6, 1.5},
            {5.7, 2.8, 4.5, 1.3},
            {6.3, 3.3, 4.7, 1.6},
            {4.9, 2.4, 3.3, 1.0},
            {6.6, 2.9, 4.6, 1.3},
            {5.2, 2.7, 3.9, 1.4},
            {5.0, 2.0, 3.5, 1.0},

            {5.5, 2.3, 4.0, 1.3},
            {6.5, 2.8, 4.6, 1.5},
            {5.7, 2.8, 4.5, 1.3},
            {6.3, 3.3, 4.7, 1.6},
            {4.9, 2.4, 3.3, 1.0},
            {6.6, 2.9, 4.6, 1.3},
            {5.2, 2.7, 3.9, 1.4},
            {5.0, 2.0, 3.5, 1.0}};
    private   double[][] Dlabel =
            {{1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0},
                    {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0},
                    {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}};

    public final double[][]nData=new double[Ddata.length][Ddata[0].length];
    public final double[][]nlabel=new double[Ddata.length][Ddata[0].length];

    ArrayList<Integer>added=new ArrayList<>();


    Random random=new Random();
    public void process(){
        for(int i=0;i<Ddata.length;i++) {
           perform();
        }
    }
    public void perform(){
        int index = random.nextInt(24);

        if (!added.contains(index)) {
            added.add(index);
            nData[index] = Ddata[index];
            nlabel[index] = Dlabel[index];
        } else {
            perform();
        }
    }

    public double[][] getData() {
        return nData;
    }

    public double[][] getLabel() {
        return nlabel;
    }
}
