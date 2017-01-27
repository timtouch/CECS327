import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class EvenThread extends Thread
{
    private int counter = 0;
    private final int TURNS = 10;
    private Lock lock;
    private Condition t1;

    public EvenThread (Lock l, Condition c)
    {
        lock = l;
        t1 = c;
    }

    public void run ()
    {
        System.out.println("Even id: " + ThreadID.get());
        lock.lock();
        try
        {
            t1.await(); // coordinate with odd thread
        }
        catch (InterruptedException e)
        {
            System.exit(-1);
        }
        finally
        {
            lock.unlock();
        }
        for (int k = 0; k < TURNS; k++) //consecutive evens
        {
            counter += 2;
            System.out.println(ThreadID.get() + ":" + counter);
        }
    }
}
