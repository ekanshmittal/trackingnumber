import java.util.*;

public class TrackingTable {
	private List<TrackingRow> table;

	public TrackingTable() {
		table = new ArrayList<TrackingRow>();
	}

	public void addRow(TrackingRow row) {
		table.add(row);
	}
	public void displayTable(){
		Iterator<TrackingRow> it = table.iterator();
		while(it.hasNext()){
			TrackingRow row = it.next();
			System.out.println(row.getRange().toString()+" "+row.getStatusCode()+" "+row.getTransferCode());
		}
	}
	
}
