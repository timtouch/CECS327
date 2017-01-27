import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class OddThread extends Thread
{
    private int counter = 1;
    private final int TURNS = 10;
    private Lock lock;
    private Condition t1;

    public OddThread (Lock l, Condition c)
    {
        lock = l;
        t1 = c;
    }

    public void run ()
    {
        System.out.println("Odd id: " + ThreadID.get());
        try
        {
            sleep(100); // small pause
        }
        catch (InterruptedException e)
        {
        }
        lock.lock();
        try
        {
            t1.signal();
        }
        finally
        {
            lock.unlock();
        }

        for (int k = 0; k < TURNS; k++) // consecutive odds
        {
            System.out.println(ThreadID.get() + ":" + counter);
            counter += 2;
        }
    }
}
