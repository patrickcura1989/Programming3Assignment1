package Task1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;

public class GenerateJavaFile
{

	public static void main(String[] args)
	{
		PrintWriter pw = null;

		// name of class to generate a copy of
		String nameOfClass = "SmartPhone";

		// Obtain the class object if we know the name of the class
		Class className = SmartPhone.class;

		try
		{
			// print the output with word Copy at the end
			pw = new PrintWriter(new FileWriter(nameOfClass + "Copy.java"));

			// get the package name of the class
			Package packageName = className.getPackage();

			pw.println(packageName + ";\n");

			pw.print("public class " + nameOfClass);

			// get superclasses
			Type superClass = className.getGenericSuperclass();

			pw.print(superClass.toString().replace("class ", " extends "));

			// get interfaces
			Type[] allInterfaces = className.getGenericInterfaces();

			for (Type anInterface : allInterfaces)
			{
				pw.print(anInterface.toString().replace("interface", " implements"));
			}

			pw.println("\n{");

			// gets all the public member fields of the class
			Field[] fields = className.getDeclaredFields();

			for (Field oneField : fields)
			{
				// get public field name
				String fieldname = oneField.getName();

				// get public field type
				Object fieldType = oneField.getType();
				
				pw.println(Modifier.toString(oneField.getModifiers()) + " " + fieldType.toString().replace("class ", "")
						+ " " + fieldname + ";");
			}

			// get declared constructors of the class
			Constructor[] constructors = className.getDeclaredConstructors();

			for (Constructor aConstructor : constructors)
			{
				pw.print("public " + aConstructor.getName().replace(packageName.getName() + ".", "") + "(");
				
				// parameter counter
				int counter = 0;
				for (Class paramType : aConstructor.getParameterTypes())
				{
					pw.print(paramType.getName() + " param" + counter);
					
					counter++;
					// if not the last parameter add a comma
					if (counter < aConstructor.getParameterTypes().length)
					{
						pw.print(",");
					}
				}
				pw.println("){}");
			}

			// get all methods declared in the class
			Method[] declaredMethods = className.getDeclaredMethods();

			for (Method dmethod : declaredMethods)
			{
				pw.print(Modifier.toString(dmethod.getModifiers()) + " "
						+ dmethod.getReturnType().toString().replace("class ", "") + " " + dmethod.getName() + "(");
				
				int counter = 0;
				for (Class paramType : dmethod.getParameterTypes())
				{
					pw.print(paramType.getName() + " param" + counter);
					counter++;
					if (counter < dmethod.getParameterTypes().length)
					{
						pw.print(",");
					}
				}
				
				// generate method body with corresponding return type
				if ("void".equals(dmethod.getReturnType().toString()))
				{
					pw.println("){}");
				}
				else if (dmethod.getReturnType().equals(Integer.TYPE))
				{
					pw.println("){return 0;}");
				}
				else if (dmethod.getReturnType().equals(String.class))
				{
					pw.println("){return null;}");
				}
			}
			pw.print("}");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}

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
			Method oneMethod = className.getMethod("respondToTouch", Long.TYPE);
			System.out.println("Method is: " + oneMethod);

			// call method
			oneMethod.invoke(objectClass, 1);

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
			String fieldValue = "" + privateField.get(objectClass);
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
