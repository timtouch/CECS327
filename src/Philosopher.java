import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by timtouch on 2/1/17.
 *
 * 1. Chopsticks are assigned to the Philosopher with the lower ID
 *      - In this case, the Philosopher with the largest ID value gets no chopstick and the lowest ID gets both sticks
 * 2. There has to be a way to know about neighboring Philosophers as well as requesting sticks from them
 *      - Could pass array of Philosophers, could reference the neighbors directly
 *      - Has to have function that messages neighboring Philosophers
 * 3. When the philosopher has a stick and receives a message
 *      -If the stick is CLEAN
 *          - They keep it
 *          - Condition await() while stick is CLEAN
 *      -If the stick is DIRTY
 *          - CLEAN the stick and then "send" the stick over
 *          - Signal() that stick is dirty
 * 4. After the philosopher is done eating, BOTH STICKS are DIRTY (Set sticks state to DIRTY)
 *      - If philosopher has both sticks
 *          - They can eat
 *      - Otherwise
 *          - They request the sticks they need from their respective neighbors
 *              - If they need the right one, request that
 *              - If they need the left one, request that
 *      -If another philosopher had previously requested one of the sticks
 *          - The philosopher that finished eating cleans the stick and sends it
 */
public class Philosopher extends Thread
{
    private int bites = 20;
    private Chopstick lower, higher;
    private int index;                  // ID of Philosopher
    public Philosopher(int i, Chopstick left, Chopstick right)
    {
        index = i;
        // Assign appropriate chopsticks to correct side
        if(left.getNumber() < right.getNumber())
        {
            this.lower = left;
            this.higher = right;
        } else {
            this.lower = right;
            this.higher = left;
        }
    }

    public void eat()
    {
        pickUp();
        chew();
        putDown();
    }

    public void pickUp()
    {
        lower.pickUp();
        higher.pickUp();
    }

    public void chew()
    {
        try
        {
            System.out.println("Philosopher " + index + " Chewing");
            sleep(100);
        } catch (InterruptedException e)
        {
            System.err.println("Philosopher " + index + " threw an InterruptedException");
        }
    }

    public void putDown()
    {
        higher.putDown();
        lower.putDown();
    }

    public void think()
    {
        try
        {
            System.out.println("Philosopher " + index + " Thinking");
            sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public void run()
    {
        for (int i = 0; i < bites; i++)
        {
            think();
            eat();
        }
    }
}
