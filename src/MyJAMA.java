import java.util.Random;

public class MyJAMA {
    public static double[][] create(int n, double max, double min) {
        Random r = new Random();
        double[][] ab = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ab[i][j] = min + (max - min) * r.nextDouble();
        return ab;
    }

    public static double[][] create(boolean column, int n, double max, double min) {
        Random r = new Random();
        double[][] ab = new double[n][n];
        for (int i = 0; i < n; i++)
            ab[i][0] = min + (max - min) * r.nextDouble();
        return ab;
    }

    public static double[][] multiplication(double[][] a, double[][] b) {
        double[][] ab;
        if(!(b.length == 1 && b[0].length == 1)) {
            ab = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < b[0].length; j++)
                    for (int k = 0; k < b.length; k++)
                        ab[i][j] += a[i][k] * b[k][j];
        } else {
            ab = new double[a.length][a[0].length];
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a[0].length; j++)
                    ab[i][j] = a[i][j] * b[0][0];
        }
        return ab;
    }

    public static double[][] transp(double[][] a) {
        double[][] ab = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                ab[j][i] = a[i][j];
        return ab;
    }

    public static double[][] add(double[][] a, double[][] b) {
        double[][] ab = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                ab[i][j] = a[i][j] + b[i][j];
        return ab;
    }

    public static double[][] subtraction(double[][] a, double[][] b) {
        double[][] ab = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                ab[i][j] = a[i][j] - b[i][j];
        return ab;
    }

    public static void show(double[][] a) {
        System.out.print("________________________________________________________________________________________________________________");
        System.out.println("________________________________________________________________________________________________________________");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++)
                if(a[i][j] > 0)
                    System.out.print("+" + a[i][j] + " \t\t");
                else
                    System.out.print(a[i][j] + "\t\t");
            System.out.println();
        }
        System.out.print("---------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------\n");
    }
}
