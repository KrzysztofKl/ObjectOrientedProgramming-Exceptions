
import prData.*;

import java.util.Arrays;

/*
run configuration -- arguments -- program arguments 
example: 0 10 1 2 3 4 
where first and second value are minimum and maximum
*/

public class DataTest {
	public static void main(String[] args) {
		try {
			double min = Double.parseDouble(args[0]);
			double max = Double.parseDouble(args[1]);
			Data data = new Data(Arrays.copyOfRange(args, 2, args.length), min, max);

			System.out.println();
			System.out.println(data);

			System.out.println();
			try {
				data.setRange("0 ; 4");
				System.out.println(data);
			} catch (DataException e) {
				System.err.println(e.getMessage());
			}
			
			System.out.println();
			try {
				data.setRange("15  25");
				System.out.println(data);
			} catch (DataException e) {
				System.err.println(e.getMessage());
			}
			
			System.out.println();
			try {
				data.setRange("15 ; hola");
				System.out.println(data);
			} catch (DataException e) {
				System.err.println(e.getMessage());
			}
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error, no enough values");
		} catch (NumberFormatException e) {
			System.err.println("Error, parsing string to real number ("+e.getMessage()+")");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
