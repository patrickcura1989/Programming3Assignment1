package Task3;

//: appendixa:LocalCopy.java
// Creating local copies with clone().
import java.util.*;

class MyObject implements Cloneable
{
	private int n;

	public MyObject(int n)
	{
		this.n = n;
	}

	public Object clone()
	{
		Object o = null;
		try
		{
			o = super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			System.err.println("MyObject can't clone");
		}
		return o;
	}

	public int getValue()
	{
		return n;
	}

	public void setValue(int n)
	{
		this.n = n;
	}

	public void increment()
	{
		n++;
	}

	public String toString()
	{
		return Integer.toString(n);
	}
}

public class LocalCopy
{

	public static MyObject noClone(MyObject v)
	{
		// Passing a reference, modifies outside object:
		v.increment();
		return v;
	}

	public static MyObject withClone(MyObject v)
	{
		v = (MyObject) v.clone(); // Local copy
		v.increment();
		return v;
	}

	public static void main(String[] args)
	{
		MyObject a = new MyObject(11);
		MyObject b = noClone(a);
		// Reference equivalence, not object equivalence:
		System.out.println("a == b: " + (a == b) + "\na = " + a + "\nb = " + b);
		MyObject c = new MyObject(47);
		MyObject d = withClone(c);
		System.out.println("c == d: " + (c == d) + "\nc = " + c + "\nd = " + d);
		System.out.println("--------------------------------------\n" + "a == b: true\n" + "a = 12\n" + "b = 12\n"
				+ "c == d: false\n" + "c = 47\n" + "d = 48\n");
	}
} /// :~