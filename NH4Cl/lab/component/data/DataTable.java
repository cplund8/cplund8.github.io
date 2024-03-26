package lab.component.data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lab.component.LabComponent;

public class DataTable<E> extends LabComponent {

	public static final int NO_TITLES = 0;
	public static final int ROW_TITLES_ONLY = 1;
	public static final int COLUMN_TITLES_ONLY = 2;
	public static final int ROW_AND_COLUMN_TITLES = 3;

	private final int rows;
	private final int columns;
	private final int type;
	private final E[][] data;
	private final Color[][] colors; // this should probably be combined into a
									// cell class with data
	private final String[] rowTitles;
	private final String[] columnTitles;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

	@SuppressWarnings("unchecked")
	public DataTable(int width, int height, int rows, int columns, int type) {
		super(width, height);

		this.rows = rows;
		this.columns = columns;

		this.type = type;

		data = (E[][]) new Comparable[columns][rows];
		colors = new Color[columns][rows];

		rowTitles = new String[rows];
		columnTitles = new String[columns];

	}

	public E[][] getData() {
		return data;
	}

	public void setCell(int x, int y, E e) {
		data[x][y] = e;
	}

	@SuppressWarnings("unchecked")
	public void setCell(int x, int y, double d) {
		data[x][y] = (E) new Double(d);
	}

	@SuppressWarnings("unchecked")
	public void setCell(int x, int y, int i) {
		data[x][y] = (E) new Integer(i);
	}

	public void setCellColor(int x, int y, Color color) {
		colors[x][y] = color;
	}

	public E getCell(int x, int y) {
		return data[x][y];
	}

	public void setAll(E e) {
		for (int x = 0; x < columns; x++) {
			setColumn(x, e);
		}
	}

	public void setAll(double d) {
		for (int x = 0; x < columns; x++) {
			setColumn(x, d);
		}
	}

	public void setAll(int i) {
		for (int x = 0; x < columns; x++) {
			setColumn(x, i);
		}
	}

	public void setAllCellColors(Color color) {
		for (int x = 0; x < columns; x++) {
			setColumnColor(x, color);
		}
	}

	public void setRow(int row, E e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e);
		}
	}

	public void setRow(int row, @SuppressWarnings("unchecked") E... e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e[i]);
		}
	}

	public void setRow(int row, int e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e);
		}
	}

	public void setRow(int row, int... e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e[i]);
		}
	}

	public void setRow(int row, double e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e);
		}
	}

	public void setRow(int row, double... e) {
		for (int i = 0; i < columns; i++) {
			setCell(i, row, e[i]);
		}
	}

	public void setRowColor(int row, Color color) {
		for (int i = 0; i < columns; i++) {
			setCellColor(i, row, color);
		}
	}

	public void setRowColors(int row, Color... colors) {
		for (int i = 0; i < columns; i++) {
			setCellColor(i, row, colors[i]);
		}
	}

	public void setColumn(int column, E e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e);
		}
	}

	public void setColumn(int column, @SuppressWarnings("unchecked") E... e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e[i]);
		}
	}

	public void setColumn(int column, int e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e);
		}
	}

	public void setColumn(int column, int... e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e[i]);
		}
	}

	public void setColumn(int column, double e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e);
		}
	}

	public void setColumn(int column, double... e) {
		for (int i = 0; i < rows; i++) {
			setCell(column, i, e[i]);
		}
	}

	public void setColumnColor(int column, Color color) {
		for (int i = 0; i < rows; i++) {
			setCellColor(column, i, color);
		}
	}

	public void setColumnColors(int column, Color... colors) {
		for (int i = 0; i < rows; i++) {
			setCellColor(column, i, colors[i]);
		}
	}

	public String getString(E e) {
		return e.toString();
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public void setRowTitle(int row, String title) {
		rowTitles[row] = title;
	}

	public void setColumnTitle(int column, String title) {
		columnTitles[column] = title;
	}

	public String getRowTitle(int row) {
		return rowTitles[row];
	}

	public String getColumnTitle(int column) {
		return columnTitles[column];
	}

	public int getColumnNumber(){
		return columns;
	}
	
	public int getRowNumber(){
		return rows;
	}
	
	private Color getColor(int x, int y) {
		if (x >= 0 && x < columns && y >= 0 && y < rows) {
			Color color = colors[x][y];

			if (colors[x][y] != null) {
				return color;
			}

		}

		return Color.black;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		double cellWidth = 1.0 / columns * width;
		double cellHeight = 1.0 / rows * height;

		if (type == NO_TITLES) {

			for (int yi = 0; yi < rows; yi++) {
				for (int xi = 0; xi < columns; xi++) {

					g.setColor(Color.black);

					g.drawRect((int) (xi * cellWidth) + x, (int) (yi * cellHeight) + y, (int) cellWidth, (int) cellHeight);

					g.setColor(getColor(xi, yi));

					if (data[xi][yi] != null) {
						drawCenteredString(g, getString(data[xi][yi]), (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
					}

				}
			}

		} else if (type == ROW_TITLES_ONLY) {

			cellWidth = 1.0 / (columns + 1) * width;

			for (int yi = 0; yi < rows; yi++) {
				for (int xi = 0; xi < columns + 1; xi++) {

					g.setColor(Color.black);

					g.drawRect((int) (xi * cellWidth) + x, (int) (yi * cellHeight) + y, (int) cellWidth, (int) cellHeight);

					g.setColor(getColor(xi - 1, yi));

					if (xi != 0) {

						if (data[xi - 1][yi] != null) {
							drawCenteredString(g, getString(data[xi - 1][yi]), (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}

					} else {
						if (rowTitles[yi] != null) {
							drawCenteredString(g, rowTitles[yi], (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}
					}

				}
			}

		} else if (type == COLUMN_TITLES_ONLY) {

			cellHeight = 1.0 / (rows + 1) * height;

			for (int yi = 0; yi < rows + 1; yi++) {
				for (int xi = 0; xi < columns; xi++) {

					g.setColor(Color.black);

					g.drawRect((int) (xi * cellWidth) + x, (int) (yi * cellHeight) + y, (int) cellWidth, (int) cellHeight);

					g.setColor(getColor(xi, yi - 1));

					if (yi != 0) {

						if (data[xi][yi - 1] != null) {
							drawCenteredString(g, getString(data[xi][yi - 1]), (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}

					} else {
						if (columnTitles[xi] != null) {
							drawCenteredString(g, columnTitles[xi], (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}
					}

				}
			}

		} else if (type == ROW_AND_COLUMN_TITLES) {
			cellWidth = 1.0 / (columns + 1) * width;
			cellHeight = 1.0 / (rows + 1) * height;

			for (int yi = 0; yi < rows + 1; yi++) {
				for (int xi = 0; xi < columns + 1; xi++) {

					g.setColor(Color.black);

					g.drawRect((int) (xi * cellWidth) + x, (int) (yi * cellHeight) + y, (int) cellWidth, (int) cellHeight);

					g.setColor(getColor(xi - 1, yi - 1));

					if (yi != 0 && xi != 0) {

						if (data[xi - 1][yi - 1] != null) {
							drawCenteredString(g, getString(data[xi - 1][yi - 1]), (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}

					} else if (xi == 0 && yi != 0) {

						if (rowTitles[yi - 1] != null) {
							drawCenteredString(g, rowTitles[yi - 1], (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}

					} else if (yi == 0 && xi != 0) {

						if (columnTitles[xi - 1] != null) {
							drawCenteredString(g, columnTitles[xi - 1], (int) (xi * cellWidth) + x + (int) (cellWidth / 2), (int) (yi * cellHeight) + y + (int) (cellHeight / 2));
						}
					}

				}
			}
		}
	}

}
