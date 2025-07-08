package org.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public File file;

	public FileInputStream fileInputStream;

	public FileOutputStream fileOutputStream;

	public Workbook workbook;

	public Sheet sheet;

	public Row row;

	public Cell cell;

	public CellStyle style;

	public String xlpath;

	public ExcelUtils(String xlpath) {

		this.xlpath = xlpath;

	}

	public int getRowCount(String sheetName) throws IOException {

		file = new File(xlpath);

		fileInputStream = new FileInputStream(file);

		workbook = new XSSFWorkbook(fileInputStream);

		sheet = workbook.getSheet(sheetName);

		int noOfRows = sheet.getPhysicalNumberOfRows(); // Both method will return total no of rows

		// int lastRowNum = sheet.getLastRowNum(); // Both method will return total no
		// of rows

		workbook.close();

		fileInputStream.close();

		return noOfRows;

	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {

		file = new File(xlpath);

		fileInputStream = new FileInputStream(file);

		workbook = new XSSFWorkbook(fileInputStream);

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);

		int noOfCells = row.getPhysicalNumberOfCells();

		// short lastCellNum = row.getLastCellNum();

		workbook.close();

		fileInputStream.close();

		return noOfCells;


	}

	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {

		file = new File(xlpath);

		fileInputStream = new FileInputStream(file);

		workbook = new XSSFWorkbook(fileInputStream);

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);

		Cell cell = row.getCell(cellNum);

		CellType cellType = cell.getCellType();

		String value = null;

		switch (cellType) {

		case STRING:

			value = cell.getStringCellValue();

			break;

		case NUMERIC:

			if (DateUtil.isCellDateFormatted(cell)) {

				Date dateCellValue = cell.getDateCellValue();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

				value = simpleDateFormat.format(dateCellValue);
			}

			else {

				double numericCellValue = cell.getNumericCellValue();

				BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);

				value = valueOf.toString();
			}

			break;

		default:

			System.out.println("Un Supported data type");

			break;
		}

		// String data = cell.toString();

		// DataFormatter formatter = new DataFormatter();

		// String data = formatter.formatCellValue(cell);

		workbook.close();

		fileInputStream.close();

		return value;

	}

	public void setCellData(String sheetName, int rowNum, int cellNum, String value) throws IOException {

		file = new File(xlpath);

		workbook = new XSSFWorkbook();

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);

		cell = row.createCell(cellNum);

		cell.setCellValue(value);

		fileOutputStream = new FileOutputStream(file);

		workbook.write(fileOutputStream);

		workbook.close();

		fileOutputStream.close();

		fileInputStream.close();

	}

	public void fillGreenColor(String sheetName, int rowNum, int cellNum) throws IOException {

		file = new File(xlpath);

		workbook = new XSSFWorkbook();

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);

		cell = row.getCell(cellNum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		fileOutputStream = new FileOutputStream(file);

		workbook.write(fileOutputStream);

		workbook.close();

		fileOutputStream.close();

		fileInputStream.close();

	}

	public void fillRedColor(String sheetName, int rowNum, int cellNum) throws IOException {

		file = new File(xlpath);

		workbook = new XSSFWorkbook();

		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);

		cell = row.getCell(cellNum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		fileOutputStream = new FileOutputStream(file);

		workbook.write(fileOutputStream);

		workbook.close();

		fileOutputStream.close();

		fileInputStream.close();

	}

}
