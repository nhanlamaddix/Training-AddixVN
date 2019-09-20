import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Task14 {
    public static void main(String[] args) {
		
		Task14  excelWriter = new Task14();
		 
		List<Book> listBook = excelWriter.getListBook();
		String excelFilePath = "D:\\NiceBooks.xls";
		 
		try {
			excelWriter.writeExcel(listBook, excelFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
    public class Book {
    private String title;
    private String author;
    private double price;
 
    public Book() {
    }
 
    public Book(String title, String author, double price) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String toString() {
        return String.format("%s - %s - %f", title, author, price);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
 
    
}
private Workbook getWorkbook(String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	public void writeExcel(List<Book> listBook, String excelFilePath) throws IOException {
	    Workbook workbook = getWorkbook(excelFilePath);
	    Sheet sheet = workbook.createSheet();
	    createHeaderRow(sheet);
	    int rowCount = 0;
	 
	    for (Book aBook : listBook) {
	        Row row = sheet.createRow(++rowCount);
	        writeBook(aBook, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private void writeBook(Book aBook, Row row) {
	    Cell cell = row.createCell(1);
	    cell.setCellValue(aBook.getTitle());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(aBook.getAuthor());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(aBook.getPrice());
	}
	
    //Có thể format được như in đậm, set font
	private void createHeaderRow(Sheet sheet) {
		 
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 16);
	    cellStyle.setFont(font);
	 
	    Row row = sheet.createRow(0);
	    Cell cellTitle = row.createCell(1);
	 
	    cellTitle.setCellStyle(cellStyle);
	    cellTitle.setCellValue("Title");
	 
	    Cell cellAuthor = row.createCell(2);
	    cellAuthor.setCellStyle(cellStyle);
	    cellAuthor.setCellValue("Author");
	 
	    Cell cellPrice = row.createCell(3);
	    cellPrice.setCellStyle(cellStyle);
	    cellPrice.setCellValue("Price");
	}
	
	private List<Book> getListBook() {
	    Book book1 = new Book("Head First Java", "Kathy Serria", 79);
	    Book book2 = new Book("Effective Java", "Joshua Bloch", 36);
	    Book book3 = new Book("Clean Code", "Robert Martin", 42);
	    Book book4 = new Book("Thinking in Java", "Bruce Eckel", 35);
	 
	    List<Book> listBook = Arrays.asList(book1, book2, book3, book4);
	 
	    return listBook;
	}
}
