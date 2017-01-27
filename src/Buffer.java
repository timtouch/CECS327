/**
 * Created by Timothy on 1/26/2017.
 */
public class Buffer
{
    private char buf [];
    private int numItems = 0;
    private int size;

    public Buffer (int size) throws RuntimeException
    {
        if (size <= 0)
        {
            throw new RuntimeException();
        }
        buf = new char[size];
        this.size = size;
    }

    public char getChar (int index)
    {
        numItems--;
        return buf[index];
    }

    public void  putChar (int index, char item)
    {
        numItems++;
        buf[index] = item;
    }

    public int getNum ()
    {
        return numItems;
    }

    public int getSize()
    {
        return size;
    }

    public String toString()
    {
        return new String(buf);
    }
}
