package Task3;

//: appendixa:Cloning.java
//The clone() operation works for only a few
//items in the standard Java library.
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

class Int
{
	private int i;

	public Int(int ii)
	{
		i = ii;
	}

	public void increment()
	{
		i++;
	}

	public String toString()
	{
		return Integer.toString(i);
	}
}

public class Cloning
{

	public static void main(String[] args)
	{
		ArrayList v = new ArrayList();
		for (int i = 0; i < 10; i++)
			v.add(new Int(i));
		System.out.println("v:  " + v);
		System.out.println("-------------------------------------");
		ArrayList v2 = (ArrayList) v.clone();
		// Increment all v2's elements:
		for (Iterator e = v2.iterator(); e.hasNext();)
			((Int) e.next()).increment();
		// See if it changed v's elements:
		System.out.println("v:  " + v);
		System.out.println("v2: " + v2);
	}
} /// :~
