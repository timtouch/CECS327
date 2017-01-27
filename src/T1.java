import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class T1 extends Thread
{
    Lock lockA;
    Condition cA, cB, c1;
    int count = 0;
    boolean AB0turn[];

    public T1 (Lock lA, Condition ca,Condition cb, Condition c1, boolean abo[])
    {
        lockA = lA;
        this.cA = ca;
        cB = cb;
        this.c1 = c1;
        AB0turn = abo;
    }

    public void run ()
    {
        for (int k = 0; k < 4; k++)
        {
            lockA.lock();
            try
            {
                if(!AB0turn[2])
                {
                    c1.await(); // goes after 'A'
                }
                System.out.print(count);
                AB0turn[1] = true;
                AB0turn[2] = false;
                cB.signal(); // to print 'B'

                if(!AB0turn[2])
                {
                    c1.await(); //goes after 'B'
                }
                System.out.print(count);
                AB0turn[2] = false;
                AB0turn[0] = true;
                cA.signal(); // to print 'A'
            }
            catch (InterruptedException e)
            {
                System.exit(-1);
            }
            finally
            {
                lockA.unlock();
            }
        }

        lockA.lock();
        try
        {
            if(!AB0turn[2])
            {
                c1.await();
            }
            System.out.print(count);
            AB0turn[2] = false;
            AB0turn[1] = true;
            cB.signal();
        }
        catch (InterruptedException e)
        {
            System.exit(-1);
        }
        finally
        {
            lockA.unlock();
        }
    }
}
