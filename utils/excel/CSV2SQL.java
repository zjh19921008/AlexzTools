package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;

public class CSV2SQL {

    private static final String UPDATE_SQL_TEMPLATE="update branch_info set branch_code_fcy='%s' where branch_code='%s';";

    private static final String INSERT_SQL_TEMPLATE="insert into branch_info (id,branch_code,branch_name,adminis_id,telnum,branch_code_fcy) values('%s','%s','%s','%s','%s','%s');";

    private static final String UPDATE_SQL_TELNUM_TEMPLATE="update branch_info set telnum='%s' where branch_code='%s';";

    public static void main(String[] args) {

        File file = new File("D:\\渣打\\tfms\\对公自动化\\data\\BRANCH_INFO_TELNUM.xlsx");

        try (Workbook workbook = new XSSFWorkbook(Files.newInputStream(file.toPath()))) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = 0;
            while (rowNum < sheet.getPhysicalNumberOfRows()) {
                Row row = sheet.getRow(rowNum);
                /*String fcy = ""+(int)row.getCell(0).getNumericCellValue();
                String branchCode = ""+(int)row.getCell(1).getNumericCellValue();
                System.out.println(String.format(UPDATE_SQL_TEMPLATE, fcy, branchCode));*/

                /*String branchCodeFcy = ""+(int)row.getCell(0).getNumericCellValue();
                String branchCode = ""+(int)row.getCell(1).getNumericCellValue();
                String branchName = row.getCell(2).getStringCellValue();
                String adminisId = ""+(int)row.getCell(3).getNumericCellValue();
                String telNum = "";
                System.out.println(String.format(INSERT_SQL_TEMPLATE, rowNum+1,branchCode,branchName,adminisId,telNum,branchCodeFcy));*/

                String branchCode = row.getCell(0).getStringCellValue();
                String telnum = row.getCell(1).getStringCellValue();
                System.out.printf((UPDATE_SQL_TELNUM_TEMPLATE) + "%n", telnum, branchCode);

                rowNum++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
