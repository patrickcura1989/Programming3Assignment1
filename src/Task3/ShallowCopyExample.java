package Task3;

/* References:
 * Eckel, B. Thinking in Enterprise Java, 3rd Edition (http://www.mindviewinc.com/Books/) 
 */

class ObjectToClone implements Cloneable
{
	public String[] value = {"old"};
	public String surevalue = "sureOld";
	
	public Object clone(ObjectToClone o)
	{
		o = null;
		try
		{
			o = (ObjectToClone)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			System.err.println("ObjectToClone cannot perform clone");
		}
		o.value = o.value.clone();
		return o;
	}
}

public class ShallowCopyExample
{
	public static void main(String[] args)
	{
		ObjectToClone obj1 = new ObjectToClone();
		ObjectToClone obj2 = (ObjectToClone)obj1.clone(obj1);
		obj2.value[0] = "new";
		obj2.surevalue = "sureNew";
		System.out.println("obj1.surevalue = " + obj1.surevalue + "\nobj2.surevalue = " + obj2.surevalue);
		System.out.println("obj1.value = " + obj1.value[0] + "\nobj2.value = " + obj2.value[0]);
	}

}
