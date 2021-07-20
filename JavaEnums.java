/*
javac JavaEnums.java -d ClassFiles/
java -cp ClassFiles/ learnJava.JavaEnums
*/

package learnJava;

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() { return abbreviation; }
}

class EnumDemo {
    public static void playWithEnums() {
        Size notMySize = Size.valueOf("SMALL");
        System.out.println(notMySize + " " + notMySize.getAbbreviation() + " " + notMySize.ordinal());
        
        for (Size s : Size.values()) { System.out.println(s); }
        System.out.println(Size.MEDIUM.ordinal() + " $ " + Size.EXTRA_LARGE.hashCode());
    }
}

// ____________________________________________________

// ____________________________________________________
// ____________________________________________________
// ____________________________________________________
// ____________________________________________________

public class JavaEnums {
	public static void main(String[] args) {

		System.out.println("\nFunction : EnumDemo.playWithEnums");
		EnumDemo.playWithEnums();
	}
}

