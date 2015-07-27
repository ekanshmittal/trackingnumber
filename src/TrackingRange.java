public class TrackingRange {
	private Range range;
	private Character statusCode;
	private Integer transferCode;

	public TrackingRange(Range range, Character statusCode, Integer transferCode) {
		this.range = range;
		this.statusCode = statusCode;
		this.transferCode = transferCode;
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

	public Range.Relation classify(TrackingRange tr) {
		return getRange().classify(tr.getRange());
	}
}
