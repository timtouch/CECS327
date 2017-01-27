import javax.swing.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Timothy on 1/26/2017.
 */
public class BoundedBuf
{
    private static Lock lock = new ReentrantLock();
    private static Condition producer = lock.newCondition();
    private static Condition consumer = lock.newCondition();
    private static Buffer buf = new Buffer(3);

    public static void main(String[] args)
    {
        String in = JOptionPane.showInputDialog("Enter a string");
        Producer p = new Producer(lock, consumer, producer, buf, in);
        Consumer c = new Consumer(lock, consumer, producer, buf);
        p.start();
        c.start();
    }
}
