package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private Sheet sheet;

    public ExcelUtils(String fileName, String sheetName) {

        try {

            FileInputStream fis = new FileInputStream("C:\\Users\\harsh\\Documents\\workspace-spring-tool-suite-4-4.29.1.RELEASE\\testingproject\\src\\test\\resources\\testdata\\MakeMyTrip.xlsx");

            Workbook workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            throw new RuntimeException("Excel loading failed: " + e.getMessage());
        }
    }

    public Map<String, String> getRowData(int rowIndex) {

        Map<String, String> data = new HashMap<>();

        Row header = sheet.getRow(0);
        Row row = sheet.getRow(rowIndex);

        for (int i = 0; i < header.getLastCellNum(); i++) {

            String key = header.getCell(i).getStringCellValue();
            String value = row.getCell(i).toString();

            data.put(key, value);
        }

        return data;
    }
}
