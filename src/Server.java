import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

// Для роботи з вірткальної машиную потрібно поміняти Indound rule на All traffic і поставити там своє Ip з маскою 32

public class Server implements Brain {
    static double[][] L12, L30, L40, L50;
    static boolean showIntermediateResults = false;

    public static void main(String[] args) {
        try {
            System.out.println("Підготовка сервера RMI...");
//          Це потрібно, для того, щоб можна було знайти вірткальну машину у мережі, можна просто Ip, але тоді потрібно 
//          змінити в Client DEFAULT_HOST на Ip вірткальної машини
            System.setProperty("java.rmi.server.hostname","ec2-34-194-59-42.compute-1.amazonaws.com");
            Server server = new Server();
            Brain brainImplementation = server;
            try {
//              Встановлюємо реєстр на цьому порті
                Registry registry = LocateRegistry.createRegistry(1098);
                Brain stub = (Brain) UnicastRemoteObject.exportObject(brainImplementation, 1098);
                registry.rebind("Brain", stub);
                System.out.println("Підготовка пройшла успішно!\nСервер RMI готовий до роботи.");
            } catch (RemoteException e) {
                System.out.println("Регістр RMI на порті 1098 вже існує.");
            }
            new Scanner(System.in).nextLine();
            System.out.println("Сервер RMI зупинено.");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Помилка на сервері RMI: " + e.toString());
            e.printStackTrace();
        }
    }
    

    public void getL12(int n,double min, double max) {
        System.out.println("Обрахунок L12");
        L12 = MyJAMA.create(n, min, max);
        if(showIntermediateResults) {
            System.out.println("Результат обрахунку:");
            MyJAMA.show(L12);
        }
    }

    public void getL30(double[][] a) {
        System.out.println("Обрахунок L30");
        L30 =  MyJAMA.subtraction(a, L12);
        if(showIntermediateResults) {
            System.out.println("Результат обрахунку:");
            MyJAMA.show(L30);
        }
    }
    

    public void getL40() {
        System.out.println("Обрахунок L40");
        L40 =  MyJAMA.multiplication(L30, L30);
        if(showIntermediateResults) {
            System.out.println("Результат обрахунку:");
            MyJAMA.show(L40);
        }
    }

    public double[][] getL50() {
        System.out.println("Обрахунок L50");
        L50 =  MyJAMA.multiplication(L30, L40);
        if(showIntermediateResults) {
            System.out.println("Результат обрахунку:");
            MyJAMA.show(L50);
        }
        return L50;
    }

    public String enableShowIntermediateResultsMode() {
        showIntermediateResults = true;
        return "ShowIntermediateResultsMode активний";
    }
    public String disableShowIntermediateResultsMode() {
        showIntermediateResults = false;
        return "ShowIntermediateResultsMode неактивний";
    }
}
