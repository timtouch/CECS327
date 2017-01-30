/**
 * Created by timtouch on 1/25/17.
 */
public class DepositThread extends Thread
{
    private BankAcct acct;
    private int turns;

    public DepositThread (BankAcct acct, int turns)
    {
        this.acct = acct;
        this.turns = turns;
    }

    public void run ()
    {
        for (int k = 0; k < turns; k++)
        {
            acct.deposit(5);
        }
        System.out.println("ID: " + acct.getId());
        System.out.println("Deposit Balance: " + acct.getBalance());
    }
}
