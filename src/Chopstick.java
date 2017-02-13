import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Timothy on 2/11/2017.
 *
 * Chopsticks each have their own lock
 * When a Philosopher picks up a Chopstick, no other Philosopher can until the owner puts the Chopstick down
 */
public class Chopstick
{
    private Lock lock;
    private int number;

    public Chopstick(int n)
    {
        lock = new ReentrantLock();
        this.number = n;
    }

    public void pickUp()
    {
        lock.lock();
        //System.out.println("Chopstick " + number + " picked up");
    }

    public void putDown()
    {
        //System.out.println("Chopstick " +  number + " put down");
        lock.unlock();
    }

    public int getNumber()
    {
        return number;
    }
}
