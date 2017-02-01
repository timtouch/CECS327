import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by timtouch on 2/1/17.
 */
public class Philosopher extends Thread
{
    private static int WAITING=0, EATING=1, THINKING=2;
    private Lock lock;
    private Condition phil[];
    private int states [];
    private int NUM_PHILS;
    private int id;
    private final int TURNS = 20;

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

    public void takeSticks(int id)
    {
        lock.lock();
        try
        {
            if(states[leftof(id)] != EATING && states[rightof(id)] != EATING)
                states[id] = EATING;
            else
            {
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

    public void putSticks(int id)
    {
        lock.lock();
        try
        {
            states[id] = THINKING;
            if(states[leftof(id)] == WAITING && states[leftof(leftof(id))] != EATING)
            {
                phil[leftof(id)].signal();
                states[leftof(id)] = EATING;
            }
            if(states[rightof(id)] == WAITING && states[rightof(rightof(id))] != EATING)
            {
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
