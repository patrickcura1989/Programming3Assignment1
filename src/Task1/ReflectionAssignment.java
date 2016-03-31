package Task1;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionAssignment
{
	public static void main(String[] args)
	{

		// Obtain the class object if we know the name of the class
		Class className = SmartPhone.class;
		try
		{
			// get the absolute name of the class
			String classPackage = className.getName();
			System.out.println("Class Name is: " + classPackage);

			// get the simple name of the class (without package info)
			String classNoPackage = className.getSimpleName();
			System.out.println("Class Name without package is: " + classNoPackage);

			// get the package name of the class
			Package packageName = className.getPackage();
			System.out.println("Package Name is: " + packageName);

			// get declared constructors of the class
			Constructor[] constructors = className.getDeclaredConstructors();
			System.out.println("Declared Constructors are: " + Arrays.toString(constructors));

			// get constructor with specific argument
			Constructor constructor = className.getDeclaredConstructor(String.class);

			// initializing an object of the class
			SmartPhone objectClass = (SmartPhone) constructor.newInstance("Hello");

			// get all methods of the class including declared methods of
			// superclasses
			Method[] allmethods = className.getMethods();
			System.out.println("Methods are: " + Arrays.toString(allmethods));
			for (Method method : allmethods)
			{
				System.out.println("method = " + method.getName());
			}

			// get all methods declared in the class
			// but excludes inherited methods.
			Method[] declaredMethods = className.getDeclaredMethods();
			System.out.println("Declared Methods are: " + Arrays.toString(declaredMethods));
			for (Method dmethod : declaredMethods)
			{
				System.out.println("method = " + dmethod.getName());
			}

			// get method with specific name and parameters
			Method oneMethod = className.getMethod("respondToTouch", int[].class);
			System.out.println("Method is: " + oneMethod);

			// call method
			int[] input = {1,2};
			oneMethod.invoke(objectClass, input);

			// get all the parameters of method
			Class[] parameterTypes = oneMethod.getParameterTypes();
			System.out.println("Parameter types of respondToTouch are: " + Arrays.toString(parameterTypes));

			// get the return type of computeRentalCost
			Class returnType = oneMethod.getReturnType();
			System.out.println("Return type is: " + returnType);

			// gets all the public member fields of the class 
			Field[] fields = className.getFields();

			System.out.println("Public Fields are: ");
			for (Field oneField : fields)
			{
				// get public field name
				Field field = className.getField(oneField.getName());
				String fieldname = field.getName();
				System.out.println("Fieldname is: " + fieldname);

				// get public field type
				Object fieldType = field.getType();
				System.out.println("Type of field " + fieldname + " is: " + fieldType);

				// get public field value
				Object value = field.get(objectClass);
				System.out.println("Value of field " + fieldname + " is: " + value);

			}

			// How to access private member fields of the class

			// getDeclaredField() returns the private field
			Field privateField = SmartPhone.class.getDeclaredField("antenna");

			String name = privateField.getName();
			System.out.println("One private Fieldname is: " + name);
			// makes this private field instance accessible
			// for reflection use only, not normal code
			privateField.setAccessible(true);

			// get the value of this private field
			String fieldValue = (String) privateField.get(objectClass);
			System.out.println("fieldValue = " + fieldValue);

		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

}