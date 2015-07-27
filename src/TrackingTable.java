import java.util.*;

public class TrackingTable {
	private List<TrackingRow> table;

	public TrackingTable() {
		table = new ArrayList<TrackingRow>();

		TrackingRow row = new TrackingRow(new Range(1, Integer.MAX_VALUE),
				null, null);
		table.add(row);
	}

	public void addRow(TrackingRow row) {
		table.add(row);
	}

	public void displayTable() {
		Iterator<TrackingRow> it = table.iterator();
		while (it.hasNext()) {
			TrackingRow row = it.next();
			System.out.println(row.getRange().toString() + " "
					+ row.getStatusCode() + " " + row.getTransferCode());
		}
	}

}
