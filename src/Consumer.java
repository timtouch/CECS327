import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class Consumer extends Thread
{
    private int index = 0;
    private Lock lock;
    private Condition consumer, producer;
    private Buffer buf;

    public Consumer (Lock l, Condition c1, Condition c2, Buffer  buf)
    {
        lock = l;
        consumer = c1;
        producer = c2;
        this.buf = buf;
    }

    public char takeItem ()
    {
        char item = ' ';
        lock.lock();
        try
        {
            while (buf.getNum() == 0)
            {
                consumer.await();
            }
            item = buf.getChar(index % buf.getSize());
            producer.signal();
        }
        catch (InterruptedException ex)
        {
            System.exit(1);
        }
        finally
        {
            lock.unlock();
        }
        index++;
        return item;
    }

    public void run ()
    {
        for (int k = 0; k < 10; k++)
        {
            System.out.println(takeItem());
        }
    }
}
