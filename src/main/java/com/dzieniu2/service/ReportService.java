package com.dzieniu2.service;

import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;
import com.dzieniu2.repository.FuelTransactionRepository;
import com.dzieniu2.repository.ProductTransactionRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.ToString;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReportService {

    private FuelTransactionRepository fuelTransactionRepository  = new FuelTransactionRepository();
    private ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();

    public void generateReport(LocalDate date) {
        List<TransactionFuel> transactionFuel = fuelTransactionRepository.findByDate(generateDateString(date));
        List<TransactionProduct> transactionProduct = productTransactionRepository.findByDate(generateDateString(date));

        try {
            generatePDF(transactionFuel, transactionProduct, generateDateString(date));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private String generateDateString(LocalDate date) {
        return date.toString().substring(0, 7);
    }

    private void generatePDF(List<TransactionFuel> transactionFuel, List<TransactionProduct> transactionProduct, String month) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(month + ".pdf"));

        document.open();
        Font headerFont = FontFactory.getFont(FontFactory.COURIER, 22,Font.BOLD, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph title = new Paragraph("Raport miesięczny stacji paliw", headerFont);
        Paragraph monthLabel = new Paragraph("Miesiac: " + month, headerFont);
        monthLabel.setSpacingAfter(20);
        Paragraph soldFuel = new Paragraph("Sprzedane paliwo", font);
        Paragraph soldProducts = new Paragraph("Sprzedane produkty", font);

        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(20);
        addFuelTableHeader(table);
        addRows(table, transactionFuel);

        document.add(title);
        document.add(monthLabel);
        document.add(soldFuel);
        document.add(table);
        document.add(soldProducts);
        document.close();
    }

    private void addFuelTableHeader(PdfPTable table) {
        Stream.of("Rodzaj", "Litraz[l]", "Laczna cena[zl]")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<TransactionFuel> transactionFuel) {
        SoldFuel pb95 = get95(transactionFuel);
        SoldFuel pb98 = get98(transactionFuel);
        SoldFuel on = getON(transactionFuel);
        SoldFuel lpg = getLPG(transactionFuel);
        table.addCell(pb95.fuelType);
        table.addCell(pb95.quantity.toString());
        table.addCell(pb95.sumPrice.toString());
        table.addCell(pb98.fuelType);
        table.addCell(pb98.quantity.toString());
        table.addCell(pb98.sumPrice.toString());
        table.addCell(on.fuelType);
        table.addCell(on.quantity.toString());
        table.addCell(on.sumPrice.toString());
        table.addCell(lpg.fuelType);
        table.addCell(lpg.quantity.toString());
        table.addCell(lpg.sumPrice.toString());
    }

    private SoldFuel get95(List<TransactionFuel> transactionFuel) {
        SoldFuel soldFuel = new SoldFuel("Pb 95", 0d, 0d);
        transactionFuel.forEach(x -> {
            if (x.getFuel().getName().equals("Pb 95")) {
                soldFuel.quantity += x.getFuelQuantity();
                soldFuel.sumPrice += x.getTotalPrice();
            }
        });
        return soldFuel;
    }

    private SoldFuel get98(List<TransactionFuel> transactionFuel) {
        SoldFuel soldFuel = new SoldFuel("Pb 98", 0d, 0d);
        transactionFuel.forEach(x -> {
            if (x.getFuel().getName().equals("Pb 98")) {
                soldFuel.quantity += x.getFuelQuantity();
                soldFuel.sumPrice += x.getTotalPrice();
            }
        });
        return soldFuel;
    }

    private SoldFuel getON(List<TransactionFuel> transactionFuel) {
        SoldFuel soldFuel = new SoldFuel("ON", 0d, 0d);
        transactionFuel.forEach(x -> {
            if (x.getFuel().getName().equals("ON")) {
                soldFuel.quantity += x.getFuelQuantity();
                soldFuel.sumPrice += x.getTotalPrice();
            }
        });
        return soldFuel;
    }

    private SoldFuel getLPG(List<TransactionFuel> transactionFuel) {
        SoldFuel soldFuel = new SoldFuel("LPG", 0d, 0d);
        transactionFuel.forEach(x -> {
            if (x.getFuel().getName().equals("LPG")) {
                soldFuel.quantity += x.getFuelQuantity();
                soldFuel.sumPrice += x.getTotalPrice();
            }
        });
        return soldFuel;
    }

    @ToString
    private class SoldFuel {
        public SoldFuel(String fuelType, Double quantity, Double sumPrice) {
            this.fuelType = fuelType;
            this.quantity = quantity;
            this.sumPrice = sumPrice;
        }
        public String fuelType;
        public Double quantity;
        public Double sumPrice;
    }
}
