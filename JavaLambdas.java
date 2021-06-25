/*
javac JavaLambdas.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaLambdas
*/

package learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

class LengthComparator implements Comparator<String> {
    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}

class JavaInterfaces {
    public static void playWithLengthComparator() {
        String[] friends = { "Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary" };
        Arrays.sort(friends); 

        System.out.println(Arrays.toString(friends));
        
        friends = new String[] { "Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary" };
        
        Arrays.sort(friends, new LengthComparator() );

        System.out.println(Arrays.toString(friends));
    }
}

// ___________________________________________________________


class Employee implements Comparable<Employee> {
    private String name;
    private double salary;
        
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public Employee(double salary) {
        this.name = "";
        this.salary = salary;
    }        
    
    public Employee(String name) {
        // salary automatically set to zero
        this.name = name;
    } 
    
    public Employee() {
        this("", 0);
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
    
    public int compareTo(Employee other) {
        return Double.compare(salary, other.salary);
    }
}

// ___________________________________________________________

class JavaLambdas {

    public static void  playWithFunctionReferences() {
        String[] strings = { "Mary", "had", "a", "little", "lamb" };
        Arrays.sort(strings, String::compareToIgnoreCase);
        // The expression String::compareToIgnoreCase is a method reference that is equivalent
        // to the lambda expression (x, y) -> x.compareToIgnoreCase(y) .
        
        System.out.println(Arrays.toString(strings));

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Malfoy", "Crabbe", "Goyle", null));
        
        // It is same as we did in C. Passing pointer or refs to the function in another function.
        list.removeIf(Objects::isNull);         // Function Objects.isNull() is passed by refs. as lambda function
        list.forEach(System.out::println);      // Function println() is passed by refs. as lambda function

        // Function : playWithFunctionReferences
        // [a, had, lamb, little, Mary]
        // Malfoy
        // Crabbe
        // Goyle

    }

    public static void playWithConstructorReferences() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Peter");
        names.add("Paul");
        names.add("Mary");

        Employee[] employees = names.stream().map( Employee::new ).toArray( Employee[]::new );
        // Stream<Employee> stream = names.stream().map(Employee::new);
        // Employee[] buttons = stream.toArray(Employee[]::new);
        // The toArray method invokes this constructor to obtain an array of the Employee type from the stream.
        
        for (Employee employee : employees) System.out.println(employee.getName());
        
        // Function : playWithConstructorReferences
        // Peter
        // Paul
        // Mary
    }

	public static void playWithLambdas() {
        String[] friends = { "Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary", "1", "11", "111", "1111", "11111"};
        					// Lambda Expression
        Arrays.sort(friends, (first, second) -> first.length() - second.length() ) ; 
        System.out.println(Arrays.toString(friends));
        
        ArrayList<String> people = new ArrayList<>( Arrays.asList("Dennish", "Alice", "Ganga Ram", "Peter", "Paul", "Mary", "1", "11", "111", "1111", "11111", null, null ) ) ;
        people.removeIf( person -> person == null ); // Lamda expression.
        System.out.println(people);

    // Function : playWithLambdas
    // [1, 11, 111, Paul, Mary, 1111, Alice, Peter, 11111, Dennish, Ganga Ram]
    // [Dennish, Alice, Ganga Ram, Peter, Paul, Mary, 1, 11, 111, 1111, 11111]
    
    }


	public static void main(String[] args) {

		System.out.println("\nFunction : playWithLambdas");
		playWithLambdas();

		System.out.println("\nFunction : playWithConstructorReferences");
		playWithConstructorReferences();

		System.out.println("\nFunction : playWithFunctionReferences");
		playWithFunctionReferences();

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

