import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		String input;
		ArrayList<TrackingRow> table = new ArrayList<TrackingRow>();
		for (int i = 0; i < 5; i++) {
			Scanner scanner = new Scanner(System.in);
			input = scanner.next();
			TrackingRow row = getTrackingRow(input);
			row.addRow(table, row);
		}
	}

	private static TrackingRow getTrackingRow(String input) {
		String[] inputValues = input.split(" ");
		int lo = Integer.parseInt(inputValues[0]);
		int hi = Integer.parseInt(inputValues[1]);
		char statusCode = inputValues[2].charAt(0);
		int transferCode = Integer.parseInt(inputValues[3]);
		TrackingRow row = new TrackingRow(new Range(lo, hi), statusCode,
				transferCode);
		return row;
	}

}
