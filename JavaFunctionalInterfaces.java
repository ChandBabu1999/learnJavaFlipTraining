
/*
javac FunctionalInterfaces.java -d ClassFiles/
java -cp ClassFiles/ learnJava.FunctionalInterfaces
*/



/**
6. The Comparable and Comparator interfaces are used for comparing objects. 
7. A functional interface is an interface with a single abstract method.
8. A lambda expression denotes a block of code that can be executed at later point in time.
9. Lambda expressions are converted to functional interfaces.
10. Method and constructor references refer to methods or constructors without invoking them. 

-> You can supply a lambda expression whenever an object of an interface with a single abstract 
method is expected. Such an interface is called a functional interface.

*/
package learnJava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//___________________________________________________________________________

@FunctionalInterface
interface PixelFunction {
    Color apply(int x, int y);
}

class ImageDemo {
    public static BufferedImage createImage(int width, int height, PixelFunction f) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++)
			// for (int y = 0; y < height; y++) {
            for (int y = 0; y < Math.min(x, height); y++) {
                Color color = f.apply(x, y);
                image.setRGB(x, y, color.getRGB());
            }
        return image;
    } 
    
    public static void playWithImageDemo() throws IOException {
        BufferedImage frenchFlag = ImageDemo.createImage(450, 300,
            (x, y) -> x < 150 ? Color.BLUE : x < 250 ? Color.WHITE : Color.RED);
        Path path = Paths.get("flag.png");
        ImageIO.write(frenchFlag, "PNG", path.toFile());
        System.out.println("Image saved to " + path.toAbsolutePath());
    }
}

//___________________________________________________________________________

@FunctionalInterface
interface Operation {
    public int operate(int x, int y); // SAM Interfaces
}

class Sum implements Operation {
	public int operate(int x, int y) { return x + y; }
} 

class Sub implements Operation {
	public int operate(int x, int y) { return x - y; }
} 

class Calculator {
	public static int calculate(int x, int y, Operation operation) {
		return operation.operate(x, y);
	}
}

public class FunctionalInterfaces {
	public static void playWithCalcuator() {
		int result = 0;
		Sum sum = new Sum();
		Sub sub = new Sub();

		result =  Calculator.calculate( 10, 20, sum);
		System.out.println(result);

		result =  Calculator.calculate( 10, 20, sub);
		System.out.println(result);

		result =  Calculator.calculate( 10, 20, (x, y) -> x + y );
		// For this, Java does the same thing internally...
			// (x, y) -> x + y
			// class TemporaryClass implements Operation {
			// 		public int operate(int x, int y) { return x + y; }
			// }
			// TemporaryClass tempObject = new TemporaryClass();
			// result =  Calculator.calculate( 10, 20, tempObject );

		System.out.println(result);

		result =  Calculator.calculate( 10, 20, (x, y) -> x - y );
		System.out.println(result);
	}

	public static void main(String [] args) {
		System.out.println("\nFunction : playWithCalcuator");
		playWithCalcuator();

		System.out.println("\nFunction : ImageDemo.playWithImageDemo");
		try {
			ImageDemo.playWithImageDemo();
		} catch ( Exception e ) {
			System.out.println("Couldn't make Image, Exception Occured...!"+e.toString());
		}	

		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");
		// System.out.println("\nFunction : ");

	}
}


