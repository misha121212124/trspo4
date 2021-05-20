import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Brain extends Remote {
    String enableShowIntermediateResultsMode() throws RemoteException;
    String disableShowIntermediateResultsMode() throws RemoteException;
    void getL30(double[][] a) throws RemoteException;
    void getL40() throws RemoteException;
    void getL12(int n, double min, double max) throws RemoteException;
    double[][] getL50() throws RemoteException;
}
