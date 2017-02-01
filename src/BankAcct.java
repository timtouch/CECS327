import java.util.concurrent.locks.Lock;

/**
 * Class: BankAcct
 * Course: CECS 327
 * Author: Timothy Touch
 */

public class BankAcct
{
    private double balance;
    private String id;
    private Lock lock;  // Added the lock to the bank account

    public BankAcct (String id, Lock lock)
    {
        balance = 0;
        this.id = id;
        this.lock = lock;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit (double amt)
    {
        lock.lock(); // Lock the critical section for one thread at a time
        if (amt >= 0)
        {
            balance += amt;
        }
        lock.unlock(); // Unlock it after finished with critical section
    }

    public boolean withdraw (double amt)
    {
        lock.lock(); // Lock the critical section for one thread at a time
        boolean retval = false;
        if (amt <= balance)
        {
            balance -= amt;
            retval = true;
        }
        lock.unlock(); // Unlock it after finished with critical section
        return retval;
    }

    public String getId()
    {
        return id;
    }
}
