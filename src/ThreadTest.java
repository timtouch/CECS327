import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class ThreadTest
{
    private Lock lock = new ReentrantLock();
    private Condition t1 = lock.newCondition();

    public static void main(String[] args)
    {
        ThreadTest t = new ThreadTest();
        EvenThread even = new EvenThread(t.lock, t.t1);
        OddThread odd =  new OddThread(t.lock, t.t1);
        System.out.println("Warning: Ordering is not correct");
        even.start();
        odd.start();
    }
}
