
/*
javac JavaObjectOrientedProgramming.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaObjectOrientedProgramming
*/

package learnJava;

import java.util.Random;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// QUESTION 

// WHAT IS OBJECT ORIENTED PROGRAMMING???
	// ABSTRACTION ??
	// ENCAPSULATION ??
	// INHERITANCE ??
	// POLYMORPHISM ??

// class is Keyword to Create Type

class Employee {

	// STATE
	// Member Variables
	private String name;
	private double salary;

	// MESSAGES OR BEHAVIOUR
	// Member Functions : Constructor
	public Employee(String name, double salary) {
		this.name 	= name;
		this.salary = salary;
	}

	// Member Function
	public void raiseSalary(double byPercentage) {
		double raise = salary * byPercentage / 100;
		this.salary += raise;
	}

	// Member Function : Getters
	public String getName() {
		return name;
	}

	// Member Function : Getters
	public double getSalary() {
		return salary;
	}
}


// ___________________________________________________________

class EvilManager {
    private Random generator;
    
    public EvilManager() {
        generator = new Random();
    }
    
    public void giveRandomRaise(Employee e) {
        double percentage = 10 * generator.nextDouble();
        // this is calling Employee.raiseSalary() which can 
        // change the instance state (values).
        e.raiseSalary(percentage);
    }

    public void increaseRandomly1(double x) { // Pass By Value
        double amount = x * 10 * generator.nextDouble();
        x += amount;
    }

    public double increaseRandomly2(double x) { // Pass By Value
        double amount = x * 10 * generator.nextDouble();
        x += amount;
        return x;
    }

    // e is Pass By Value
    // e is Refering To Object Which Is Pass By Reference
    public void replaceWithZombie(Employee e) { 
    	// This won't change the state of e(passed by value).
        e = new Employee("Alice", 0);
        System.out.println("Employee Changed: " + e.getName());            
    }
}

// ____________________________________________________________

// EXPLORE AND REASON FOLLOWING CODE, MOMENT DONE, RAISE HAND!!


class Employee1 {
    // INSTANCE MEMBER VARIABLES : Store Instance States
    // BINDED WITH INSTANCE
    private String name = "";
    private double salary;


    private Random generator = new Random(); 
    private final int id = 1 + generator.nextInt(1_000_000);
    
    private final int id2;

    { // An initialization block : Instance Members
        Random generator = new Random(); 
        id2 = 1 + generator.nextInt(1_000_000);
    }
    
    // Function Overloading
    // Construcot Overloading
    //		Have Mulitple Member Functions With Same Name
    //		Number of Arguments And Type Of The Arguments
    public Employee1(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public Employee1(double salary) {
        // name already set to ""
        this.salary = salary;
    }        
    
    public Employee1(String name) {
        // salary automatically set to zero
        this.name = name;
    } 
    
    public Employee1() {
        this("", 0); // Employee1("", 0);
    }

    // INSTANCE MEMBER FUNCTIONS
    // BINDED WITH INSTANCE

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
    
    public String getId() {
        return "Id:"+id+" Id2:"+id2;
    }
}

// __________________________________________________________
// ____________________________________________________________

// EXPLORE AND REASON FOLLOWING CODE, MOMENT DONE, RAISE HAND!!

// Employee2 is Type

// Visibility Sperficiers : private, protected and public
class Employee2 {
	// TYPE MEMBER VARIABLE i.e. BINDED WITH TYPE
    private static int lastId = 2017000;
    // public static int lastId = 0;
  	
    // FOLLOWING THREE
    // INSTANNCE MEMBERS VARIABLEi.e. BINDED WITH INSTANCE
  	// private int lastId = 0;
    private int id;
    private String name;
    private double salary;
        
    public Employee2() {
        lastId++;
        id = lastId;
    }
    
    public Employee2(String name, double salary) {
        this(); // Employee2()
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;    
    }

    // FOLLOWING THREE
    // INSTANNCE MEMBERS FUNCTIONS i.e. BINDED WITH INSTANCE
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public int getId() {
        return id;
    }

    // FOLLOWING ONE
    // TYPE MEMBER FUNCTION i.e. BINDED WITH TYPE
    public static int getLastID() {
    	return lastId;
    }
}

// ____________________________________________________________

// EXPLORE AND REASON FOLLOWING CODE, MOMENT DONE, RAISE HAND!!

// import java.time.LocalDate;
// import java.util.ArrayList;

class CreditCardForm {
    // Default Values Of expirationYear Set To ArrayList Object
    private static final ArrayList<Integer> expirationYear = new ArrayList<>();

    static { // Type Memebers Initialistion
        // Add the next twenty years to the array list
        int year = LocalDate.now().getYear();
        for (int i = year; i <= year + 20; i++) {
            expirationYear.add(i);
        }   
    }
    // ...
}


// ____________________________________________________________

// EXPLORE AND REASON FOLLOWING CODE, MOMENT DONE, RAISE HAND!!

// NESTED CLASSES

class Invoice {

    private static void funWithOuterClassStaticMember1() {
        System.out.println("Privte Static Member Function Of Outer Class");
    }
    public static void funWithOuterClassStaticMember2() {
        System.out.println("Public Static Member Function Of Outer Class");
    }
    // Nested Class (make inner class static to make it nested)
    //      Defining Class Inside A Class
    public static class Item { // Item is nested inside Invoice
        String description;
        int quantity;
        double unitPrice;

        // This private constructor can be accessed within outer class Invoice only. (not outside (in main())).
        private Item(){}; 

        // This public constructor can be accessed within outer class Invoice only as well as outside (in main()).
        public Item(String desc, int q, double p){
            description = desc;
            quantity = q;
            unitPrice = p; 
        }
        private double price() { return quantity * unitPrice; }
        
        private String tostring() { 
            return quantity + " x " + description + " @ $" + unitPrice + " each";
        }

        public static void funWithInnerClassStaticMember() {
        // public void funWithInnerClassStaticMember() {
            System.out.println("Static Member Function Of Inner Class");
            Invoice.funWithOuterClassStaticMember1();
            Invoice.funWithOuterClassStaticMember2();
        }
    }

    private ArrayList<Item> items = new ArrayList<>();
    
    public void addItem(String description, int quantity, double unitPrice) {
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);

        // add replica of item again (to test the 2nd constructor access over here).
        newItem = new Item(description, quantity, (int)unitPrice);
        items.add(newItem);
    }
    
    public void print() {
        double total = 0;
        for (Item item : items) {
            System.out.println(item.tostring()+" -> "+item.toString());
            total += item.price();
        }
        System.out.println(total);
    }
}

// ____________________________________________________________

// INNER CLASS
//      CLASS DEFINED INSIDE A CLASS WITHOUT static MODIFIER
//      NESTED CLASSES WITH ACCESS TO OUTER CLASS CONTEXT
//      NESTED CLASSES (class inside class) ARE INNER BY DEFAULT

class Network {
    
    public class Member { // Member is an inner class of Network
        private String name;
        private ArrayList<Member> friends = new ArrayList<>();

        public Member(String name) {
            this.name = name;
        }

        public void deactivate() {
            members.remove(this);
        }

        public void addFriend(Member newFriend) {
            friends.add(newFriend);
        }

        public boolean belongsTo(Network n) {
            // System.out.println(Network.this.toString() + " " + n.toString());
            return Network.this == n;
        }
        
        public String toString() {
            StringBuilder result = new StringBuilder(name);
            result.append(" with friends ");
            for (Member friend : friends) {
                result.append(friend.name);
                result.append(", ");
            }
            // return result.subSequence(0, result.length() - 2).toString();
            return result.toString();

        }
    }

    private ArrayList<Member> members = new ArrayList<>();

    public Member enroll(String name) {
        Member newMember = new Member(name);
        members.add(newMember);
        return newMember;
    }

    public String toString() {
        return members.toString();
    }
}

// 1. A nested class is a member of its enclosing class. 
// 2. Non-static nested classes (inner classes) have access to other members of the enclosing class, even if they are declared private. 
// 3. Static nested classes do not have access to other members of the enclosing class but it can access public/private static method of enclosing class. 
// 4. As a member of the OuterClass, a nested class can be declared private, public, protected, or package private. 
//      (Recall that outer classes can only be declared public or package private.)

// 5. As with class methods and variables, a static nested class is associated with its outer class. 
// 6. And like static class methods, a static nested class cannot refer directly to instance variables or methods (non-static) defined in its enclosing class: 
// it can use them only through an object reference. 

// Note: A static nested class interacts with the instance members of its outer class 
// (and other classes) just like any other top-level class.
//  In effect, a static nested class is behaviorally a top-level class 
// that has been nested in another top-level class for packaging convenience

// ____________________________________________________________
// ____________________________________________________________


public class JavaObjectOrientedProgramming {

    public static void playWithInnerClasses() {
        Network myFace = new Network();
        Network tooter = new Network();
        Network.Member fred = myFace.enroll("Fred");
        
        Network.Member wilma = myFace.new Member("Wilma");
            // An object, but not enrolled
            // Make the constructor private to avoid this
        fred.addFriend(wilma);

        Network.Member barney = tooter.enroll("Barney");
        barney.addFriend(wilma);
        fred.addFriend(barney);
        System.out.println("Network myFace : "+myFace);
            // If it shouldn't be possible to add a friend
            // from another network, call belongsTo
        
        Network.Member chand = tooter.enroll("Chand");
        Network.Member babu = tooter.enroll("Babu");

        barney.addFriend(chand);
        chand.addFriend(babu);
        System.out.println("Network tooter : "+tooter);

        System.out.println(barney.belongsTo(myFace));
        System.out.println(barney.belongsTo(tooter));


        System.out.println(fred.belongsTo(tooter));
        System.out.println(fred.belongsTo(myFace));
    }

    public static void playWithNestedClasses() {
        Invoice invoice = new Invoice();
        invoice.addItem("Blackwell Toaster", 2, 24.95);
        invoice.addItem("ZapXpress Microwave Oven", 1, 49.95);
        invoice.print();


        // Invoice.funWithOuterClassStaticMember1(); // private. (can't be accessed in main).
        Invoice.funWithOuterClassStaticMember2();    // public. (can be accessed in main).
        // invoice.funWithOuterClassStaticMember2(); // BAD PRACTICE 
        
        // Invoice.Item item = new Invoice.Item(); // this private constructor can't be accessed.
        // System.out.println(item.tostring());
        
        Invoice.Item item = new Invoice.Item("Item from Main", 12, 32.45);
        System.out.println("newItemFromMain.toString :-> "+item.toString());
        
        Invoice.Item.funWithInnerClassStaticMember();

        // BAD CODING PRACTICE
        // item.funWithInnerClassStaticMember();
    }

    public static void playWithEmployee2() {       
    	// Cosntructor Call
    	// Initialise Objects
        Employee2 james = new Employee2("James Bond", 500000);
            // calls Employee1(String, double) constructor
        System.out.println( james.getName() );  
		System.out.println( james.getSalary() );
		System.out.println( james.getId() );
        
        Employee2 anonymous = new Employee2("", 40000);
            // calls Employee(double) constructor
        System.out.println( anonymous.getName() );  
		System.out.println( anonymous.getSalary() );
		System.out.println( anonymous.getId() );

        Employee2 e = new Employee2();
            // no-arg constructor
        System.out.println( e.getName() );  
		System.out.println( e.getSalary() );
		System.out.println( e.getId() );

        // System.out.println( Employee2.lastId );  
		System.out.println("lastId : "+ Employee2.getLastID() );  
    }	 

    
    public static void playWithEmployee1() {
       
    	// Cosntructor Call
    	// Initialise Objects
        Employee1 james = new Employee1("James Bond", 500000);
        // calls Employee1(String, double) constructor
        System.out.println( james.getName() );  
        System.out.println( james.getSalary() );
		System.out.println( james.getId() );
        

        Employee1 anonymous = new Employee1("", 40000);
            // calls Employee(double) constructor
        System.out.println( anonymous.getName() );  
		System.out.println( anonymous.getSalary() );
		System.out.println( anonymous.getId() );

        Employee1 unpaid = new Employee1("Igor Intern");
        System.out.println( unpaid.getName() );  
		System.out.println( unpaid.getSalary() );
		System.out.println( unpaid.getId() );

        Employee1 e = new Employee1();
            // no-arg constructor
        System.out.println( e.getName() );  
		System.out.println( e.getSalary() );
		System.out.println( e.getId() );

    }	 

	 public static void playWithEvilManager() {

	 	// Constructor Call
	 	// Creating Object of EvilManager Type

        EvilManager boss = new EvilManager();
        
        Employee fred = new Employee("Fred", 50000);

        System.out.println("Salary before: " + fred.getSalary());            
        boss.giveRandomRaise(fred);
        System.out.println("Salary after: " + fred.getSalary());


        double sales = 100000;
        System.out.println("Sales before: " + sales);
        boss.increaseRandomly1(sales);
        System.out.println("Sales after 1: " + sales);
        sales = boss.increaseRandomly2(sales);
        System.out.println("Sales after 2: " + sales);
        
        System.out.println("Employee before: " + fred.getName());            
        boss.replaceWithZombie(fred);
        System.out.println("Employee after: " + fred.getName());            
    
        // Function : playWithEvilManager
        // Salary before: 50000.0
        // Salary after: 53513.720500537886
        // Sales before: 100000.0
        // Sales after: 100000.0

        // Employee before: Fred
        // Employee after: Fred
    }


	public static void playWithEmployee() {
		
		// alice is Object/Instance of Type Employee
	 	// Constructor Call

		Employee alice = new Employee("Alice", 50000);
		// Empolyee * alice = (Employee *) malloc( sizeof( Employee) );

		// this is a pointer To Current Object
		// 		Current Object Is Object Currently Recieving The Message
		alice.raiseSalary(10); // Sending raiseSalary Message With Payload of 100

		System.out.println( alice.getName() );  
		System.out.println( alice.getSalary() );

		// System.out.println( alice.name );
	}

	public static void main(String[] args) {

		System.out.println("\nFunction : playWithEmployee");
		playWithEmployee();

		System.out.println("\nFunction : playWithEvilManager");
		playWithEvilManager();

		System.out.println("\nFunction : playWithEmployee1");
		playWithEmployee1();
		
		System.out.println("\nFunction : playWithEmployee2");
		playWithEmployee2();
		
		System.out.println("\nFunction : playWithNestedClasses");
		playWithNestedClasses();

		System.out.println("\nFunction : playWithInnerClasses");
		playWithInnerClasses();

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
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");

	}
}





/*

Doubts/discussion.
    
1.
    public double increaseRandomly2(double x) { // Pass By Value
        double amount = x * 10 * generator.nextDouble();
        x += amount;
        return x;
    }

    Here, x is passed by value. but we want to change it's value.
    one methode is return upadated value.
    any other method, like we do pass by refs in other langs. 


2. void fun(int, int),
   int fun(int, int). 

   1st one is just printing the value. and the later one is updating the value.
   is it function overloading based on return type or purpose of function.?

3. void fun(int...) //multiple paramters are allowed.
    this can be called as fun(int), fun(int, int), etc
   
    can we set default parameter in Java as in python.
    fun(int a, int b=30)
    this can be called as fun(int) or funt(int, int).


    
*/