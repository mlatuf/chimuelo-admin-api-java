package org.example.service.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.ExportStrategyName;
import org.example.model.Product;
import org.example.model.Variant;
import org.example.service.ExportStrategy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.example.model.ExportStrategyName.EMPRETIENDA;

@Component
@Slf4j
public class EmpretiendaExportStrategy implements ExportStrategy {

    private final ObjectMapper objectMapper;

    protected EmpretiendaExportStrategy(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExportStrategyName getStrategyName() {
        return EMPRETIENDA;
    }

    @Override
    public File toFile(List<Product> products) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = createSheet(workbook);
        createHeader(sheet, workbook);

        addInfoToSpreadsheet(products, sheet);
        File tempFile = null;
        try {
             tempFile = File.createTempFile(this.getStrategyName().name(), String.valueOf(System.currentTimeMillis()) + ".xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(tempFile);
            workbook.write(outputStream);
            workbook.close();
            return tempFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addInfoToSpreadsheet(List<Product> products, Sheet sheet) {
        var rowNumber = 1;
        for (Product product : products) {
            var cellNumber = 0;
            for (Variant variant : product.getVariants()) {
                Row row = sheet.createRow(rowNumber);
                row.createCell(cellNumber++).setCellValue(product.getName()); //nombre
                row.createCell(cellNumber++).setCellValue(variant.getStock()); //stock
                row.createCell(cellNumber++).setCellValue(variant.getSku()); //sku
                row.createCell(cellNumber++).setCellValue(variant.getPrice().getAmount().toString()); //precio
                row.createCell(cellNumber++).setCellValue("N/A"); // precio oferta
                row.createCell(cellNumber++).setCellValue("N/A"); //nom att 1
                row.createCell(cellNumber++).setCellValue("N/A"); //val att 1
                row.createCell(cellNumber++).setCellValue("N/A"); //nom att 2
                row.createCell(cellNumber++).setCellValue("N/A"); //val att 2
                row.createCell(cellNumber++).setCellValue("N/A"); //nom att 3
                row.createCell(cellNumber++).setCellValue("N/A"); //val att 3
                row.createCell(cellNumber++).setCellValue(product.getCategory().getFullName()); //categorias
                row.createCell(cellNumber++).setCellValue("N/A"); // peso
                row.createCell(cellNumber++).setCellValue("N/A"); // alto
                row.createCell(cellNumber++).setCellValue("N/A"); // ancho
                row.createCell(cellNumber++).setCellValue("N/A"); // profundidad
                row.createCell(cellNumber++).setCellValue("N/A"); // mostrar en tienda
                row.createCell(cellNumber++).setCellValue(product.getIdEmpretienda()); // idProducto
                row.createCell(cellNumber++).setCellValue(variant.getIdStock()); // idStock
            }
        }
    }

    private static void createHeader(Sheet sheet, Workbook workbook) {
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setBold(true);
        headerStyle.setFont(font);


        List<String> headers = List.of("Nombre", "Stock", "SKU",
                "Precio", "Precio oferta", "Nombre atributo 1",
                "Valor atributo 1", "Nombre atributo 2", "Valor atributo 2",
                "Nombre atributo 3", "Valor atributo 3", "Categorías", "Peso",
                "Alto", "Ancho", "Profundidad", "Mostrar en tienda", "IDProduct",
                "IDStock");
        Cell headerCell = null;
        for (int i = 0; i < headers.size(); i++) {
            headerCell = header.createCell(i);
            headerCell.setCellValue(headers.get(i));
            headerCell.setCellStyle(headerStyle);
        }
    }

    private static Sheet createSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Productos");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        return sheet;
    }
}
