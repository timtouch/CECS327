/**
 * Created by timtouch on 1/25/17.
 */

public class BankAcct
{
    private double balance;
    private String id;

    public BankAcct (String id)
    {
        balance = 0;
        this.id = id;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit (double amt)
    {
        if (amt >= 0)
        {
            balance += amt;
        }
    }

    public boolean withdraw (double amt)
    {
        boolean retval = false;
        if (amt <= balance)
        {
            balance -= amt;
            retval = true;
        }

        return retval;
    }

    public String getId()
    {
        return id;
    }
}
