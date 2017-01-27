import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class Producer extends Thread
{
    private int index = 0;
    private Lock lock;
    private Condition consumer, producer;
    private Buffer buf;
    private int pos = 0;
    private  String in;

    public Producer (Lock l, Condition c1, Condition c2, Buffer buf, String in )
    {
        lock = l;
        consumer = c1;
        producer = c2;
        this.buf = buf;
        this.in = in;
    }

    public void putItem ()
    {
        char item = in.charAt(pos++);
        lock.lock();
        try
        {
            while (buf.getNum() == buf.getSize())
            {
                producer.await();
            }
            buf.putChar(index,item);
            consumer.signal();
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
        index %= buf.getSize();
    }

    public void run () {
        for (int k = 0; k < 10; k++)
        {
            putItem();
            try
            {
                sleep(1);
            }
            catch (InterruptedException ex)
            {
            }
        }
    }
}
