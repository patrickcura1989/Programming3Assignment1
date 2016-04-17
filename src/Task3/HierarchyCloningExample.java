package Task3;

// This class will clone correctly
class CloneableClassSample implements Cloneable
{
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}

// This child class of a cloneable class can still do a clone
class StillCloneable extends CloneableClassSample
{
	
}

// This class won't be able to clone and will throw an exception when clone is called
class NotCloneableAnymore extends CloneableClassSample
{
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}

// This class still won't be able to clone and will throw an exception as it calls NotCloneableAnymore's clone
class StillNotCloneableAnymore extends NotCloneableAnymore
{
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}

class TurnOnCloningViaADifferentMethod extends NotCloneableAnymore
{
	private TurnOnCloningViaADifferentMethod cloneAttempt(TurnOnCloningViaADifferentMethod t)
	{
		// Do something here that will do a clone
		return new TurnOnCloningViaADifferentMethod();
	}

	public Object clone()
	{
		// Re-enable cloning by implementing a new way of cloning
		return cloneAttempt(this);
	}
}

// Cloning is disabled completely as this class cannot be extended to implement a new clone method 
// unlike TurnOnCloningViaADifferentMethod
final class DisableCloneCompletely extends NotCloneableAnymore
{
}


public class HierarchyCloningExample
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		StillCloneable obj1 = new StillCloneable();
		obj1.clone();
	}
} 