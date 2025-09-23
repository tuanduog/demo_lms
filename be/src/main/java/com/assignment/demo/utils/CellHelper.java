package com.assignment.demo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Component
public class CellHelper {
    public String getCellValue(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> null;
        };
    }
}
