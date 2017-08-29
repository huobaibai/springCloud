package com.huobaibai.poi;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Base {

	public abstract void execute();

	// 取单元格内的值;
	public String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null)
			return cellValue;
		int cType = cell.getCellType();
		switch (cType) {
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		}
		return cellValue.trim();
	}
	
	public File saveAsFile(Workbook wb, String path) {
		File fout = null;
		try {
			fout = new File(path);
			FileOutputStream fileout = new FileOutputStream(fout);
			wb.write(fileout);
			fileout.flush();
			fileout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fout;
	}

	public Workbook getWorkBook(String path) {
		Workbook workbook = null;
		try {
			File file = new File(path);
			workbook = WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
	}
}
