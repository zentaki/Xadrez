package entities;

public class Position {
	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public Position(Position position) {
		this.row = position.row;
		this.column = position.column;
	}

	public int getRow() {return row;}
	public int getColumn() {return column;}
	
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return row + ", " + column;
	}
	
	
}
