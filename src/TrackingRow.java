import java.util.ArrayList;

public class TrackingRow {
	private Range range;
	private char statusCode;
	private int transferCode;
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
			 Range.Relation relation = newEntry.classify(tableEntry);
			 
			 if( relation == Range.Relation.SUBSET ) {
				 if(newEntry.statusCode == tableEntry.statusCode  &&  newEntry.transferCode == tableEntry.transferCode)
					 newEntry.deleted = true;
					 
					 else{
						 tableEntry.range.hi = newEntry.range.lo - 1;
						 Range newRange = new Range(newEntry.range.hi+1,tableEntry.hi)
						 TrackingRow splitEntry = new TrackingRow(newRange,tableEntry.statusCode,tableEntry.transferCode);
						 ArrayList<TrackingRow> copyList = new 
					 }		
				 
			 }
			 if( relation == Range.Relation.SUPERSET ) {
				  
				 
			 }
			 if( relation == Range.Relation.LESSOVERLAP ){
				 add
			 }
			 
		}
	}
}
