import java.util.ArrayList;
import java.util.ListIterator;

public class TrackingRow {
	private Range range;
	private char statusCode;
	private int transferCode;
	private boolean deleted;

	public TrackingRow(Range range, Character statusCode, Integer transferCode) {
		this.range = range;
		this.statusCode = statusCode;
		this.transferCode = transferCode;
		deleted = false;
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

	public void addRow(ArrayList<TrackingRow> table, TrackingRow newEntry) {

		ListIterator<TrackingRow> listIterator = table.listIterator();
		while (listIterator.hasNext()) {
			TrackingRow tableEntry = listIterator.next();
			Range.Relation relation = tableEntry.classify(newEntry);

			if (relation == Range.Relation.SUBSET) {
				if (isEqual(newEntry))
					newEntry.deleted = true;

				else {
					tableEntry.getRange().hi = newEntry.getRange().lo - 1;
					Range newRange = new Range(newEntry.getRange().hi + 1,
							tableEntry.getRange().hi);
					TrackingRow splitEntry = new TrackingRow(newRange,
							tableEntry.statusCode, tableEntry.transferCode);

					copyList.add(splitEntry);
					copyList.add(newEntry);

				}

			}
			if (relation == Range.Relation.SUPERSET) {
			}
			if (relation == Range.Relation.LESSOVERLAP) {
				if (isEqual(newEntry)) {
					listIterator.set(new TrackingRow(newRange(
							tableEntry.getRange().lo, newEntry.getRange().hi),
							newEntry.statusCode, newEntry.transferCode));
				} else {
					listIterator.set(new TrackingRow(newRange(
							tableEntry.getRange().lo,
							newEntry.getRange().lo - 1), tableEntry.statusCode,
							tableEntry.transferCode));
					listIterator.add(newEntry);
				}
			}
			if (relation == Range.Relation.SAME) {
				if (!isEqual(newEntry)) {
					listIterator.set(new TrackingRow(newEntry.getRange(),
							newEntry.statusCode, newEntry.transferCode));
				}
			}
		}
	}

	public boolean isEqual(TrackingRow tr) {
		return statusCode == tr.statusCode && transferCode == tr.transferCode;
	}

	public Range newRange(int lo, int hi) {
		return new Range(lo, hi);
	}
}
