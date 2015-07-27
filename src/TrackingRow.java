import java.util.ArrayList;

public class TrackingRow {
	private Range range;
	private Character statusCode;
	private Integer transferCode;
	private boolean deleted;

	public TrackingRow(Range range, Character statusCode, Integer transferCode) {
		this.range = range;
		this.statusCode = statusCode;
		this.transferCode = transferCode;
		deleted =  false ; 
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public Character getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Character statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(Integer transferCode) {
		this.transferCode = transferCode;
	}

	public Range.Relation classify(TrackingRow tr) {
		return getRange().classify(tr.getRange());
	}
	
	public void addRow(ArrayList<TrackingRow> table,TrackingRow newEntry) {
		for( TrackingRow tableEntry : table) {
			 Range.Relation relation = tableEntry.classify(newEntry);
			 
			 if( relation == Range.Relation.SUBSET ) {
				 
			 }
			 
			 
		}
	}
}
