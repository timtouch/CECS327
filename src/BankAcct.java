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
    private Lock lock;

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
        lock.lock();
        if (amt >= 0)
        {
            balance += amt;
        }
        lock.unlock();
    }

    public boolean withdraw (double amt)
    {
        lock.lock();
        boolean retval = false;
        if (amt <= balance)
        {
            balance -= amt;
            retval = true;
        }
        lock.unlock();
        return retval;
    }

    public String getId()
    {
        return id;
    }
}
