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
    private int bites = 20;
    private Chopstick lower, higher;
    private int index;

    public Philosopher(int i, Chopstick left, Chopstick right)
    {
        index = i;
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
