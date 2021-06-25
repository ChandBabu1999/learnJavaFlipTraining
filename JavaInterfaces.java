
/*
javac JavaInterfaces.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaInterfaces
*/

package learnJava;
import java.util.Random;

import java.util.Arrays;
import java.util.Comparator;

// Mathematical Abstract Type = {} { hasNext, next }, Phi }

// Interfaces
// What It Will Do
interface IntSequence {
    // Random gen = new Random();
    int seq_id = 1 + new Random().nextInt();
    
    // private String tostring(){return "Interface IntSequence";};
        // error: toString() in IntSequence cannot override toString() in Object;
        // attempting to assign weaker access privileges; was public
    
    private String tostring(){return "Interface IntSequence";};
    // but this is fine, but this fun can't be overriden in implementing class. but can be called by instance.
    
    // String tostring(){return "Interface IntSequence";};
        // This is also not allowed. only private members, can have body.
    boolean hasNext();
    int next();
    // int test(); 
        // Only abstract classes implementinf interfaces have permission to skip some metohds of interface to override.
        // Otherwise class implimenting interface nust override all the mehods except default defined above (in interface).
}

// Implementation
// How It Will Do
// Class Implementing Interface
class DigitSequence implements IntSequence {
    private int number;
    // String toString(){ return "DigitSeq, rem="+number;}
        // not allowed, attempting to assign weaker access privileges; was public
        //  error: toString() in DigitSequence cannot override toString() in Object

    public String toString(){ return "DigitSeq, Sequnece Id : "+IntSequence.seq_id;}
    // perfectly fine, this public method can be overriden with public access privilege.

    public DigitSequence(int n) {number = n;}

    public boolean hasNext() {return number != 0;}

    public int next() {
        int result = number % 10;
        number /= 10;
        return result;
    }
    
    public int rest() {return number;}
}

class SquareSequence implements IntSequence {
    private int i;

    public boolean hasNext() {return true;}

    public int next() {i++;    return i * i;}
}


// ___________________________________________________________


interface Identified {
    // int getId();
    default int getId() { return Math.abs(hashCode()); } 
}

interface Person {
    String getName();
    default int getId() { 
    	System.out.println("Person's getID Invoked");
    	return 0; 
    }
}

class Employee1 implements Person, Identified {
    private String name;
    private double salary;
        
    public Employee1(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;    
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }

    // Employee1 both parent interfaces have getId(), so we must have to override it. otherwise CTE.
    // public int getId() {return Person.super.getId();}
    public int getId() {Person.super.getId(); return Identified.super.getId();}
    
}


// ___________________________________________________________


// import java.util.Arrays;
// import java.util.Comparator;

class LengthComparator implements Comparator<String> {
    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}

// ___________________________________________________________


public class JavaInterfaces {

    public static void playWithLengthComparator() {
        String[] friends = { "Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary" };
        Arrays.sort(friends); // friends is now ["Mary", "Paul", "Peter"]

        System.out.println(Arrays.toString(friends));
        
        friends = new String[] { "Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary" };
        Arrays.sort(friends, new LengthComparator() );
        // Arrays.sort(friends, (x, y)-> x.length() - y.length()); 

        System.out.println(Arrays.toString(friends));
    }

	public static void playWithEmployee1() {
		Employee1 emp = new Employee1("Alice", 88888);

		System.out.println( emp.getName() );
		System.out.println( emp.getSalary() );

		System.out.println( emp.getId() );		
		// Identified.getId();
	}


    public static double average(IntSequence seq, int n) {
        int count = 0;
        double sum = 0;
        while (seq.hasNext() && count < n) {
            count++;
            sum += seq.next();
        }
        return count == 0 ? 0 : sum / count;
    }

    public static void playWithIntSequence() {

        SquareSequence squares = new SquareSequence();
        System.out.println(squares.toString());
        // System.out.println(squares.tostring());

        double avg = average(squares, 100);
        System.out.println("Average of first 100 squares: " + avg);
        
        IntSequence sq = squares;       // doesn't need type caste.
        // squares = (SquareSequence) sq;  // this needs type cast. BAD practice.
        if (sq instanceof SquareSequence) {
            System.out.println("safe class casting...!");
            squares = (SquareSequence) sq;
        }
        
        
        IntSequence digits = new DigitSequence(34567543);
        System.out.println(digits.toString());

        while (digits.hasNext()) System.out.print(digits.next() + " ");
        System.out.println();

        // digits = (DigitSequence) sq; // RTE: java.lang.ClassCastException;
        
        digits = new DigitSequence(163543729);
        avg = average(digits, 5); // Will only look at the last 5 sequence values
        System.out.println("Average of the digits: " + avg);
    }

	public static void main(String[] args) {

		System.out.println("\nFunction : playWithIntSequence");
		playWithIntSequence();

		System.out.println("\nFunction : playWithEmployee1");
		playWithEmployee1();

		System.out.println("\nFunction : playWithLengthComparator");
		playWithLengthComparator();

		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");

	}	
}

/**
=> Extending Interfaces :
        An interface can extend another, requiring or providing additional methods
        on top of the original ones. ex.

        public interface Closeable {
            void close();
        }
        public interface Channel extends Closeable {
            boolean isOpen();
        }

        A class that implements the Channel interface must provide both methods, and
        its objects can be converted to both interface types.
*/