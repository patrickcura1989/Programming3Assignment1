package Task3;

/* References:
 * Eckel, B. Thinking in Enterprise Java, 3rd Edition (http://www.mindviewinc.com/Books/) 
 */

import java.io.*;

class SerializableClass1 implements Serializable
{
}

class SerializableClass2 implements Serializable
{
	SerializableClass1 object1 = new SerializableClass1();
}

public class SerializationCloningExample
{

	public static void main(String[] args) throws Exception
	{
		// create the object to be cloned using serialization
		SerializableClass2[] original = new SerializableClass2[30000];
		for (int i = 0; i < original.length; i++)
			original[i] = new SerializableClass2();
		
		// setup the streams to store the original object
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ObjectOutputStream output = new ObjectOutputStream(buffer);
		
		// write the original object data to the stream
		for (int i = 0; i < original.length; i++)
			output.writeObject(original[i]);
		
		ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
		
		// copy the details from the stream to the clone object
		SerializableClass2[] clone = new SerializableClass2[30000];
		for (int i = 0; i < clone.length; i++)
			clone[i] = (SerializableClass2) input.readObject();
	}
}