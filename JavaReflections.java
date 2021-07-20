/*
javac JavaReflections.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaReflections
*/

package learnJava;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

class ClassDemo {
    public static void playWithReflections() throws ReflectiveOperationException {
        Object obj = System.out;

        // int and str type is type
        // Class is similar to type in python
        // All types defined in Java have type and that is Class
        Class<?> cl = obj.getClass();
        
        System.out.println("This object is an instance of " + cl.getName() + " $ " + cl.getDeclaredMethods());

        String className = "java.util.Scanner";
        cl = Class.forName(className);
        // System.out.println(cl.getName());

            // An object describing the java.util.Scanner class
        cl = java.util.Scanner.class;
        
        System.out.println(cl.getName());
        Class<?> cl2 = String[].class; // Describes the array type String[]
        System.out.println(cl2.getName());
        System.out.println(cl2.getCanonicalName());
        Class<?> cl3 = Runnable.class; // Describes the Runnable interface 
        System.out.println(cl3.getName());
        Class<?> cl4 = int.class; // Describes the int type
        System.out.println(cl4.getName());
        Class<?> cl5 = void.class; // Describes the void type
        System.out.println(cl5.getName());
    }
}
// Function : ClassDemo.playWithReflections
// This object is an instance of java.io.PrintStream
// java.util.Scanner
// [Ljava.lang.String; // USed By JVM
// java.lang.String[]
// java.lang.Runnable
// int
// void

// ____________________________________________________


class MethodPrinter {
    public static void playWithTypeMetadata() throws ReflectiveOperationException {
        System.out.print("Class name: ");
        Scanner in = new Scanner(System.in);
        String className = in.nextLine();
        System.out.println(className);
        Class<?> cl = Class.forName(className);

        while (cl != null) {
            for (Method m : cl.getDeclaredMethods()) { 
                System.out.println(
                    Modifier.toString(m.getModifiers()) + " " +
                    m.getReturnType().getCanonicalName() + " " +
                    m.getName() +
                    Arrays.toString(m.getParameters()));                    
            }
            cl = cl.getSuperclass();
        }
    }
}

// ____________________________________________________
// ____________________________________________________
// ____________________________________________________
// ____________________________________________________

public class JavaReflections {
    public static void main(String[] args) {

        System.out.println("\nFunction : ClassDemo.playWithReflections");
        try { ClassDemo.playWithReflections(); } catch(Exception ex) { };

        System.out.println("\nFunction : MethodPrinter.playWithTypeMetadata");
        try { MethodPrinter.playWithTypeMetadata(); } catch(Exception ex) {};

        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
        // System.out.println("\nFunction : ");
    }
}

