/**
 * Created by timtouch on 1/25/17.
 */
public class WithdrawThread extends Thread
{
    private BankAcct acct;
    private int turns;

    public WithdrawThread (BankAcct acct, int turns)
    {
        this.acct = acct;
        this.turns = turns;
    }

    public void run ()
    {
        for (int k = 0; k < turns; k++)
        {
            acct.withdraw(5);
        }
        System.out.println("ID: " + acct.getId());
        System.out.println("Withdraw Balance: " + acct.getBalance());
    }
}
