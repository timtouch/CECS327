/**
 * Class: AcctTest
 * Course: CECS 327
 * Author: Timothy Touch
 */
import java.util.*;
import java.util.concurrent.locks.*;

public class AcctTest
{
    private static final int NUM_ACCT = 500;
    private static final int TURNS = 10000000;
    private static int id = 1;
    private static Lock lock = new ReentrantLock();  // Create a ReentrantLock

    public static void init(List<BankAcct> accts)
    {
        for (int k = 0; k < NUM_ACCT; k++)
        {
            accts.add(new BankAcct("" + (id++), lock)); // Passed the lock into the BankAcct class
        }
    }

    public static void main(String[] args)
    {
        List<BankAcct> accts = new LinkedList<BankAcct>();
        init(accts);
        AcctTest acctTest = new AcctTest();

        Random rand = new Random();
        int id = rand.nextInt(NUM_ACCT);
        DepositThread one = new DepositThread((BankAcct)accts.get(id), TURNS);
        WithdrawThread two = new WithdrawThread((BankAcct)accts.get(id), TURNS);
        DepositThread three = new DepositThread((BankAcct)accts.get(id), TURNS);
        WithdrawThread four = new WithdrawThread((BankAcct)accts.get(id), TURNS);

        one.start();
        two.start();
        three.start();
        four.start();
    }
}
