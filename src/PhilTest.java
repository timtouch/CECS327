import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by timtouch on 2/1/17.
 *
 * Chandy/Misra solution
 * ======================
 * 1. For every pair of philosophers contending for a resource,
 *  create a stick and give it to the philosopher with the lower ID. Each stick can either be
 *  DIRTY or CLEAN (States of the sticks). Initially, all sticks are dirty.
 *
 * 2. When a philosopher wants to use a set of resources, said philosopher must obtain the sticks
 *  from their contending neighbors. For all such sticks the philosopher does not have, they send
 *  a request message (Maybe use Condition here?)
 *
 * 3. When a philosopher with a stick receives a request message, they keep the stick if it is clean,
 *  but give it up if it is dirty. If the philosopher sends the stick over, they clean the stick
 *  before doing so (This way, sticks won't continually be passed back and forth indefinitely).
 *
 * 4. After a philosopher is done eating, all their sticks become dirt. If another philosopher had
 *  previously requested one of the sticks, the philosopher that has just finished eating cleans
 *  the stick and sends it.
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
        /*
        for (int i = 0; i < NUM_PHILS; i++)
        {
            chopsticks[i] = new Chopstick(i);
        }
        */
    }
    public static void main(String[] args)
    {
        /*
        init();
        Philosopher p[] = new Philosopher[NUM_PHILS];
        for (int k = 0; k < p.length; k++)
        {
            p[k] = new Philosopher(k, chopsticks[Math.floorMod(k,NUM_PHILS)], chopsticks[Math.floorMod(k - 1, NUM_PHILS)]);
            p[k].start();
        }
        */
    }
}
