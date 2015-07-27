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
		for( TrackingRow tableEntry : table) {
			 Range.Relation relation = tableEntry.classify(row);
			 
			 if( relation == Range.Relation.SUBSET ) {
				 
			 }
			 
			 
		}
	}

	public void displayTable() {
		for (TrackingRow row : table) {
			System.out.println(row.getRange().toString() + " "
					+ row.getStatusCode() + " " + row.getTransferCode());
		}
	}

}
