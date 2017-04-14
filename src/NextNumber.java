import java.util.Random;
import java.util.Scanner;

/**
 * Created by ttouc on 4/10/2017.
 */

public class NextNumber {

    private int nextPrimeNumber = 1;
    private int nextEvenFibNumber = 1;
    private int nextLargerRandomNumber = 0;

    public String processInput(String input)
    {
        String output;
        System.out.println(input);
        if(input.equalsIgnoreCase("Fib"))
        {
            nextEvenFibNumber = nextEvenFib(nextEvenFibNumber);
            output = String.format("%d", nextEvenFibNumber);
        }
        else if(input.equalsIgnoreCase("Rand"))
        {
            nextLargerRandomNumber = nextLargerRand(nextLargerRandomNumber);
            output = String.format("%d", nextLargerRandomNumber);
        }
        else if(input.equalsIgnoreCase("Prime"))
        {
            nextPrimeNumber = nextPrime(nextPrimeNumber);
            output = String.format("%d", nextPrimeNumber);
        }
        else
        {
            output ="Not a valid input";
        }
        return output;
    }

    /**
     * Gets the next even fibonacci number
     * @param   highest the previous even fibonacci number
     * @return          the next even fibonacci number
     */
    public int nextEvenFib(int highest)
    {
        int fibNum = 1,
            prevFibNum = 1,
            temp;
        while(!(fibNum > highest && fibNum % 2 == 0)){
            temp = fibNum;
            fibNum = fibNum + prevFibNum;
            prevFibNum = temp;
        }
        return  fibNum;
    }

    /**
     * Gets the next larger random integer, if it hits the max, it wraps back around
     * @param   highest previous random number
     * @return          a somewhat random number larger than the previous one
     */
    public int nextLargerRand(int highest)
    {
        Random rand = new Random();
        return (highest + (int)Math.abs(Math.random()*1000)) % Integer.MAX_VALUE;
    }

    /**
     * Gets the next prime number
     * @param   highest previous prime number
     * @return          the next prime number
     */
    public int nextPrime(int highest)
    {
        int number = highest + 1;
        while(!isPrime(number))
        {
            number++;
        }

        return number;
    }

    /**
     * Checks if number is prime
     * @param   number  to be checked if prime
     * @return          true if prime, false if not prime
     */
    public boolean isPrime(int number)
    {
        if (number < 2)
        {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0)
            {
                return false;
            }
        }
        return true;
    }


}
