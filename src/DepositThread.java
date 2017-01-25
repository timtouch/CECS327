import java.util.concurrent.locks.Lock;
/**
 * Created by timtouch on 1/25/17.
 */
public class DepositThread extends Thread
{
    private BankAcct acct;
    private int turns;
    private Lock lock;

    public DepositThread (BankAcct acct, int turns, Lock lock)
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
            acct.deposit(5);
            lock.unlock();
        }
        System.out.println("ID: " + acct.getId());
        lock.lock();
        System.out.println("Deposit Balance: " + acct.getBalance());
        lock.unlock();
    }
}
