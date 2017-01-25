import java.util.concurrent.locks.Lock;

/**
 * Created by timtouch on 1/25/17.
 */
public class WithdrawThread extends Thread
{
    private BankAcct acct;
    private int turns;
    private Lock lock;

    public WithdrawThread (BankAcct acct, int turns, Lock lock)
    {
        this.acct = acct;
        this.turns = turns;
        this.lock = lock;
    }

    public void run ()
    {
        for (int k = 0; k < turns; k++)
        {
            lock.lock();
            acct.withdraw(5);
            lock.unlock();
        }
        System.out.println("ID: " + acct.getId());
        lock.lock();
        System.out.println("Withdraw Balance: " + acct.getBalance());
        lock.unlock();
    }
}
