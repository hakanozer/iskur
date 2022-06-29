package works;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelReadMain {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Set<Row> rows = new HashSet<>();
        try
        {
            FileInputStream file = new FileInputStream(new File("sample.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            String find = "";
            int i = 0;

            while (rowIterator.hasNext())
            {

                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    if ( cell.getCellType() == CellType.STRING ) {
                        find = cell.getStringCellValue();
                        if ( columnIndex == 3 && !find.equals("cat") ) {
                            i = set.size();
                            set.add(find);
                            rows.add(row);
                            if ( set.size() > i ) {
                                System.out.println( "Yeni data excell kayıt : " + find );
                                createExcel(rows, find);
                                rows.clear();
                            }
                        }
                    }
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            //System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case STRING:
                            //System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void createExcel( Set<Row> rows, String fileName ) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("page-1");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "AGE"});
        int i = 2;

        for ( Row item : rows ) {
            Iterator<Cell> cellIterator = item.cellIterator();
            Cell cellName = cellIterator.next();
            Cell cellAge = cellIterator.next();
            data.put( ""+i, new Object[] {1, cellName.getStringCellValue(), ""+ cellAge.getNumericCellValue() });
            i++;
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(fileName+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Create Excel successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
