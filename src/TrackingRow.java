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
		
		 ArrayList<TrackingRow> copyList = new ArrayList<TrackingRow>();
		 copyList = table;
		for( TrackingRow tableEntry : table) {
			 Range.Relation relation = tableEntry.classify(newEntry);
			 
			 if( relation == Range.Relation.SUBSET ) {
				 if(isEqual(newEntry))
					 newEntry.deleted = true;
					 
					 else{
						 tableEntry.getRange().hi = newEntry.getRange().lo - 1;
						 Range newRange = new Range(newEntry.getRange().hi+1,tableEntry.getRange().hi)
						 TrackingRow splitEntry = new TrackingRow(newRange,tableEntry.statusCode,tableEntry.transferCode);
						
						 copyList.add(splitEntry);
						 copyList.add(newEntry);
						 
					 }		
				 
			 }
			 if( relation == Range.Relation.SUPERSET ) {
				  
				 
			 }
			 if( relation == Range.Relation.LESSOVERLAP ){
				 
			 }	
			 if(relation == Range.Relation.SAME){
				 if(isEqual(newEntry)){
					 statusCode=newEntry.statusCode;
					 transferCode=newEntry.transferCode;
				 }
			 }
		}	
	}
	public boolean isEqual(TrackingRow tr){
		return statusCode == tr.statusCode  &&  transferCode == tr.transferCode;
	}
}
