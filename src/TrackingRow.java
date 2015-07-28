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
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Range.Relation classify(TrackingRow tr) {
		return getRange().classify(tr.getRange());
	}

	public void addRow(ArrayList<TrackingRow> table, TrackingRow newEntry) {

		ListIterator<TrackingRow> listIterator = table.listIterator();
		while (listIterator.hasNext()) {
			TrackingRow tableEntry = listIterator.next();
			if (tableEntry.deleted) {
				continue;
			}
			Range.Relation relation = tableEntry.classify(newEntry);

			if (relation == Range.Relation.SUBSET) {
				handleSubsetRelation(newEntry, listIterator, tableEntry);

			}
			if (relation == Range.Relation.SUPERSET) {
				handleSupersetRelation(newEntry, listIterator, tableEntry);
			}
			if (relation == Range.Relation.LESSOVERLAP) {
				handleLessOverlapRelation(newEntry, listIterator, tableEntry);
			}
			if (relation == Range.Relation.MOREOVERLAP) {
				handleMoreOverlapRelation(newEntry, listIterator, tableEntry);
			}
			if (relation == Range.Relation.LESSDISJOINT
					|| relation == Range.Relation.MOREDISJOINT) {
				handleDisjointRelation(newEntry, listIterator);
			}
			if (relation == Range.Relation.SAME) {
				handleSameRelation(newEntry, listIterator, tableEntry);
			}
		}
	}

	private void handleSubsetRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator, TrackingRow tableEntry) {
		if (!tableEntry.isEqual(newEntry)) {
			tableEntry.deleted = true;
			listIterator.add(new TrackingRow(newRange(
					tableEntry.getRange().lo,
					newEntry.getRange().lo - 1), tableEntry
					.getStatusCode(), tableEntry.getTransferCode()));
			listIterator.add(new TrackingRow(newRange(
					newEntry.getRange().hi + 1,
					tableEntry.getRange().hi), tableEntry
					.getStatusCode(), tableEntry.getTransferCode()));
			listIterator.add(newEntry);
		}
	}

	private void handleSupersetRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator, TrackingRow tableEntry) {
		tableEntry.deleted = true;
		listIterator.add(newEntry);
	}

	private void handleLessOverlapRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator, TrackingRow tableEntry) {
		if (tableEntry.isEqual(newEntry)) {
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

	private void handleMoreOverlapRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator, TrackingRow tableEntry) {
		if (tableEntry.isEqual(newEntry)) {
			listIterator.set(new TrackingRow(newRange(
					newEntry.getRange().lo, tableEntry.getRange().hi),
					newEntry.statusCode, newEntry.transferCode));
		} else {
			listIterator.set(new TrackingRow(newRange(
					newEntry.getRange().hi + 1,
					tableEntry.getRange().hi), tableEntry.statusCode,
					tableEntry.transferCode));
			listIterator.add(newEntry);
		}
	}

	private void handleDisjointRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator) {
		listIterator.add(newEntry);
	}

	private void handleSameRelation(TrackingRow newEntry,
			ListIterator<TrackingRow> listIterator, TrackingRow tableEntry) {
		if (!tableEntry.isEqual(newEntry)) {
			listIterator.set(new TrackingRow(newEntry.getRange(),
					newEntry.statusCode, newEntry.transferCode));
		}
	}

	public boolean isEqual(TrackingRow tr) {
		return statusCode == tr.statusCode && transferCode == tr.transferCode;
	}

	public Range newRange(int lo, int hi) {
		return new Range(lo, hi);
	}
}
