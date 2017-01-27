import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class A0Bmain
{
    static Lock lockA = new ReentrantLock();
    static Condition    cA = lockA.newCondition(),
                        cB = lockA.newCondition(),
                        c1 = lockA.newCondition();
    static boolean AB0turn[] = new boolean[3];

    public static void main(String[] args)
    {
        AB0turn[0] = true; // A printed first
        AB0turn[1] = AB0turn[2] = false;
        Ta ta = new Ta(lockA, cA, cB, c1, AB0turn);
        Tb tb = new Tb(lockA, cA, cB, c1, AB0turn);
        T1 t1 = new T1(lockA, cA, cB, c1, AB0turn);
        ta.start();
        tb.start();
        t1.start();
    }
}
