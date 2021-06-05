package org.jresearch.threetenbp.gwt.emu.java.text;

public class ParsePosition {

	int index = 0;
	int errorIndex = -1;

	public ParsePosition(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setErrorIndex(int errorIndex) {
		this.errorIndex = errorIndex;
	}

	public int getErrorIndex() {
		return errorIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + errorIndex;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParsePosition other = (ParsePosition) obj;
		if (errorIndex != other.errorIndex)
			return false;
		if (index != other.index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParsePosition [index=" + index + ", errorIndex=" + errorIndex + "]";
	}

}
