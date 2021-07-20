
/*
javac JavaInheritance.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaInheritance
*/



/**
1. A subclass can inherit or override methods from the superclass, provided they are not private .
2. Use the super keyword to invoke a superclass method or constructor. 
3. A final super keyword to invoke a superclass method or constructor.method cannot be overridden; 
    a final class cannot be extended.
4. An abstract method has no implementation; an abstract class cannot be instantiated.
5. A protected member of a superclass is accessible in a subclass method, but only when applied to 
    objects of the same subclass. It is also accessible in its package.
6. Every class is a subclass of 'Object' which provides the toString, equals, hashCode and clone methods.
7. Each enumerated type is a subclass of 'Enum' which provides instance methods toString and compareTo , 
and a static valueOf methode. 

*/
package learnJava;

import java.util.Objects;
import java.util.ArrayList;

// Type Employee
class Employee {
    private String name;
    private double salary;
        
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;    
    }
    
    public final String getName() {
        return name;
    }
    
    public double getSalary() { return salary; }
}

//Type Mamager : It's SubType of Employee
class Manager extends Employee {
    private double bonus;
    
    public Manager(String name, double salary) {
        super(name, salary); // Constructor From Parent Class
        bonus = 0;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() { // Overrides superclass method
        return super.getSalary() + bonus;
    }
} 

class InheritanceDemo {

    public static void playWithInheritance() {
        Manager boss = new Manager("Fred", 200000);
        boss.setBonus(10000); // Defined in subclass
        System.out.println(boss.getSalary());

        boss.raiseSalary(5); // Inherited from superclass
        System.out.println(boss.getSalary());        

        Employee employee = boss; // Ok to convert to superclass
        employee.raiseSalary(5); // Can still apply superclass methods
        // employee.setBonus(898989); // Parent Type Setting The Percpective
     
        System.out.println( employee.getSalary() ); // Calls Manager.getSalary
        
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            manager.setBonus(20000);
            System.out.println(manager.getSalary());
        }
        //  here, employee and manager both are references to the same object.
        // thus, have the same values.
        System.out.println( employee.getSalary() );
    }
}

// ___________________________________________________________

interface Named {
    default String getName() { return "Named$default"; }
}

abstract class Person {
    private String name;

    public Person(String name) { this.name = name; }
    public final String getName() { return name; }

    public abstract int getId();
}
/** 
1. Any class extending Person must either supply an implementation of the getId() method 
   or be itself declared as abstract .
2. Note that an abstract class can have non-abstract methods, such as the getName() method in this example.
*/

class Student extends Person implements Named {
    private int id;
    public Student(String name, int id) { 
    	super(name); 
    	this.id = id;
    }
    public int getId() { return id; }
}

class StudentDemo {
    public static void playWithStudentDemo() {
        Person p = new Student("Fred", 1729);
        System.out.println(p.getName());
        Student s = (Student) p;
        System.out.println(s.getName());
        Named n = s;
        System.out.println(n.getName());
    }
}

// ___________________________________________________________
// import java.util.Objects;

class Item { //extends Object {
    private String description;
    private double price;
        
    public Item(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public boolean equals(Object otherObject) {
        // A quick test to see if the objects are identical
        if (this == otherObject) return true;
        // Must return false if the explicit parameter is null
        if (otherObject == null) return false;
        // Check that otherObject is a Item
        // if (this.getClass() != otherObject.getClass()) return false;
        if(!(otherObject instanceof Item)) return false;
        // Test whether the instance variables have identical values
        Item other = (Item) otherObject;
       
        return Objects.equals(description, other.description)
            && price == other.price;
    }
    
    public int hashCode() {
        return Objects.hash(description, price);
    }

    public String toString() {
    	return "Item(description=" + description + ", " + "price=" + price + ")";
    }
}

class ItemDemo {
	public static void playWithItemsEquality() {
		Item item1 = new Item("Lux Soap", 60);
		Item item2 = new Item("Lux Soap", 60);
		Item item3 = new Item("Pears Soap", 100);
		
		System.out.println( item1 == item2 ); // Comparing References
		System.out.println( item1.equals(item2) );	
		
		System.out.println( item1 == item3 ); // Comparing References
		System.out.println( item1.equals(item3) );	

		// Compiler Optimisation In Action -> String Kerning
		String something = "Hello"; // "Hello" IMMUTABLE
		String somethingAgain = "Hello";
		System.out.println( something == somethingAgain ); // Comparing References
		System.out.println( something == "Hello" ); 
		System.out.println( somethingAgain == "Hello" );

		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);
	}
}

// ___________________________________________________________

class EmployeeAgain {
    private String name;
    private double salary;
        
    public EmployeeAgain(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;    
    }
    
    public final String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String toString() {
        return getClass().getName() + "[name=" + name
            + ",salary=" + salary + "]";
    }
    
    public EmployeeAgain clone() throws CloneNotSupportedException {
        // throw CloneNotSupportedException;
        return (EmployeeAgain) super.clone();
    }
}

class ManagerAgain extends EmployeeAgain {
    private double bonus;
    
    public ManagerAgain(String name, double salary) {
        super(name, salary);
        bonus = 0;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    public double getSalary() { // Overrides superclass method
        return super.getSalary() + bonus;
    }
    
    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
    
    public ManagerAgain clone() throws CloneNotSupportedException {
        return (ManagerAgain) super.clone();
    }
} 

// ___________________________________________________________

// import java.util.ArrayList;

final class Message {
    private String sender;
    private ArrayList<String> recipients;
    private String text;
        
    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        recipients = new ArrayList<>();
    }

    public void addRecipient(String recipient) { 
        recipients.add(recipient);
    };
    
    public Message clone() {
        try {
            Message cloned = (Message) super.clone();
            
            @SuppressWarnings("unchecked") 
            ArrayList<String> clonedRecipients 
                = (ArrayList<String>) recipients.clone();
            
            cloned.recipients = clonedRecipients; 
            return cloned;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    /*
    If either object changes the recipient list, the change is reflected in the other.
    Therefore, the Message class needs to override the clone method to make a deep copy.
    */
}

// ___________________________________________________________
// ___________________________________________________________
// ___________________________________________________________
// ___________________________________________________________
// ___________________________________________________________
// ___________________________________________________________

public class JavaInheritance {
    public static void main(String[] args) {
		System.out.println("\nFunction : InheritanceDemo.playWithInheritance");
		InheritanceDemo.playWithInheritance();

		System.out.println("\nFunction : StudentDemo.playWithStudentDemo");
		StudentDemo.playWithStudentDemo();

		System.out.println("\nFunction : ItemDemo.playWithItemsEquality");
		ItemDemo.playWithItemsEquality();
    }
}

