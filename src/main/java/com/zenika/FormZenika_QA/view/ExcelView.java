/*
package com.zenika.FormZenika_QA.view;

import com.zenika.FormZenika_QA.model.User;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
    {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        Set<String> strings = model.keySet();
        @SuppressWarnings("unchecked")
       List<User> users = (List<User>) model.get("users");
        Sheet sheet = createExcelSheey(workbook);

        CellStyle style = getCellStyle(workbook);

        CreateHeaderOfTableExcel(sheet, style);


        int rowCount = 1;

    */
/*    for(User user : users){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(user.getName());
            userRow.createCell(1).setCellValue(user.getLastName());
            userRow.createCell(2).setCellValue(user.getPassword());
            userRow.createCell(3).setCellValue(user.getName());
            userRow.createCell(4).setCellValue(user.getActive());
            userRow.createCell(5).setCellValue(user.getEmail());


            }*//*


    }

    private void CreateHeaderOfTableExcel(Sheet sheet, CellStyle style) {
        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("LastName");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Password");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Active");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Email");
        header.getCell(4).setCellStyle(style);

    }

    private Sheet createExcelSheey(Workbook workbook) {
        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);
        return sheet;
    }

    private CellStyle getCellStyle(Workbook workbook) {
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        return style;
    }

}
*/
