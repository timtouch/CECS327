import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by timtouch on 2/1/17.
 */
public class PhilTest
{
    private static int WAITING=0,EATING=1,THINKING=2;
    private static final int NUM_PHILS = 5;
    private static Lock lock = new ReentrantLock();
    private static Condition phil[] = new Condition[NUM_PHILS];
    private static int states[] = new int[NUM_PHILS];
    private static Chopstick chopsticks[] = new Chopstick[NUM_PHILS];

    public static void init()
    {
        for (int i = 0; i < NUM_PHILS; i++)
        {
            chopsticks[i] = new Chopstick(i);
        }
    }
    public static void main(String[] args)
    {
        init();
        Philosopher p[] = new Philosopher[NUM_PHILS];
        for (int k = 0; k < p.length; k++)
        {
            p[k] = new Philosopher(k, chopsticks[Math.floorMod(k,NUM_PHILS)], chopsticks[Math.floorMod(k - 1, NUM_PHILS)]);
            p[k].start();
        }
    }
}
