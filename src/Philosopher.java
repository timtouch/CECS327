import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by timtouch on 2/1/17.
 *
 * What if I keep track of the number of times a philosopher gets fed in relation to it's neighbors?
 * Give higher priority to those that have not been feed as much
 */
public class Philosopher extends Thread
{
    private static int WAITING=0, EATING=1, THINKING=2;     // Represents the states that a philosopher can take
    private Lock lock;                                      // The shared lock between philosophers
    private Condition phil[];                               // Array of Conditions to target a philosopher
    private int states [];                                  // Array of the states of philosophers
    private int NUM_PHILS;                                  // Number of philosophers
    private int id;                                         // Thread ID of philosopher to differentiate between all of them
    private final int TURNS = 20;                           // Number of times philosopher will eat

    public Philosopher (Lock l, Condition p[], int st[], int num)
    {
        lock = l;
        phil = p;
        states = st;
        NUM_PHILS = num;
    }

    public void run()
    {
        id = ThreadID.get();
        for (int k = 0; k < TURNS; k++)
        {
            try {sleep(100);} catch (Exception ex) {} /*Thinking*/
            takeSticks(id);
            try { sleep(20);} catch (Exception ex) {} /*Eating*/
            putSticks(id);
        }
    }

    /*
        Philosopher attempts to either take both sticks or none at all
     */
    public void takeSticks(int id)
    {
        lock.lock();
        try
        {
            // Checks if philosophers to the left and right of the this one are eating
            // If both of them are not, then both sticks are available
            if(states[leftof(id)] != EATING && states[rightof(id)] != EATING)
                states[id] = EATING;
            else
            {
                // Otherwise, they will wait until they receive a signal that their neighbor is done
                // Once signalled, this philosopher's state will be Eating
                // This part is to avoid deadlock
                states[id] = WAITING;
                phil[id].await();
            }
        }
        catch (InterruptedException e)
        {
            System.exit(-1);
        }
        finally
        {
            lock.unlock();
        }
    }

    /*
        This function is to print out the state of the philosophers on the table
        WAITING = 0
        EATING = 1
        THINKING = 2
     */
    public void output(String s)
    {
        lock.lock();
        for (int k = 0; k < states.length; k++)
        {
            System.out.print(states[k] + ",");
        }
        lock.unlock();
        System.out.println();
        System.out.println();
    }

    /*
        This function should be called after the philosopher is done eating
     */
    public void putSticks(int id)
    {
        lock.lock();
        try
        {
            states[id] = THINKING;
            // Check if left neighbor is waiting, and the left neighbor's left neighbor is NOT Eating
            // Which means that this left neighbor has a chance to Eat!
            if(states[leftof(id)] == WAITING && states[leftof(leftof(id))] != EATING)
            {
                // Signals this left neighbor and set their state to EATING
                phil[leftof(id)].signal();
                states[leftof(id)] = EATING;
            }
            // Similar check with right neighbor
            if(states[rightof(id)] == WAITING && states[rightof(rightof(id))] != EATING)
            {
                // Signals this right neighbor and set their state to EATING
                phil[rightof(id)].signal();
                states[rightof(id)] = EATING;
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    private int leftof (int id) //clockwise
    {
        int retval = id-1;
        if(retval < 0)
            retval = NUM_PHILS-1;
        return retval;
    }

    private int rightof (int id)
    {
        int retval = id+1;
        if (retval == NUM_PHILS) //not valid id
            retval = 0;
        return retval;
    }
}
