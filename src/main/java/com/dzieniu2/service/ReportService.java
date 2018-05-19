package com.dzieniu2.service;

import com.dzieniu2.entity.Product;
import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;
import com.dzieniu2.repository.FuelRepository;
import com.dzieniu2.repository.FuelTransactionRepository;
import com.dzieniu2.repository.ProductRepository;
import com.dzieniu2.repository.ProductTransactionRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportService {

    private FuelTransactionRepository fuelTransactionRepository  = new FuelTransactionRepository();
    private ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();
    private FuelRepository fuelRepository = new FuelRepository();
    private ProductRepository productRepository = new ProductRepository();

    public void generateReport(LocalDate date) throws FileNotFoundException, DocumentException {
        generatePDF(generateDateString(date));
    }

    private String generateDateString(LocalDate date) {
        return date.toString().substring(0, 7);
    }

    private void generatePDF(String month) throws FileNotFoundException, DocumentException {
        List<TransactionFuel> transactionFuel = fuelTransactionRepository.findByDate(month);
        List<TransactionProduct> transactionProduct = productTransactionRepository.findByDate(month);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("reports/" + month + ".pdf"));

        document.open();
        Font headerFont = FontFactory.getFont(FontFactory.COURIER, 22,Font.BOLD, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph title = new Paragraph("Raport miesięczny stacji paliw", headerFont);
        Paragraph monthLabel = new Paragraph("Miesiac: " + month, headerFont);
        monthLabel.setSpacingAfter(20);
        Paragraph soldFuel = new Paragraph("Sprzedane paliwo", font);
        Paragraph soldProducts = new Paragraph("Sprzedane produkty", font);

        PdfPTable tableFuel = new PdfPTable(3);
        tableFuel.setSpacingBefore(20);

        addTableHeader(tableFuel, new String[]{"Rodzaj", "Litraz[l]", "Laczna Cena[zl]"});
        addFuelRows(tableFuel, transactionFuel);

        PdfPTable tableProduct = new PdfPTable(3);
        tableProduct.setSpacingBefore(20);

        addTableHeader(tableProduct, new String[]{"Nazwa", "Ilosc Sztuk", "Laczna Cena[zl]"});
        addProductRows(tableProduct, transactionProduct);

        document.add(title);
        document.add(monthLabel);
        document.add(soldFuel);
        document.add(tableFuel);
        document.add(soldProducts);
        document.add(tableProduct);
        document.close();
    }

    private void addTableHeader(PdfPTable table, String[] text) {
        Stream.of(text)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addFuelRows(PdfPTable table, List<TransactionFuel> transactionFuel) {
        getSoldFuel(transactionFuel).forEach((k, v) -> {
            table.addCell(v.fuelType);
            table.addCell(v.quantity.toString());
            table.addCell(v.sumPrice.toString());
        });

        table.addCell("");
        table.addCell("Suma");
        Double sum = getSoldFuel(transactionFuel).entrySet().stream().mapToDouble(i -> i.getValue().sumPrice.doubleValue()).sum()*100.00/100.0;
        table.addCell(String.valueOf(sum));
    }

    private Map<String, SoldFuel> getSoldFuel(List<TransactionFuel> transactionFuel) {
        Map<String, SoldFuel> mapSoldFuel = new HashMap<>();
        getFuelTypes().forEach(x -> mapSoldFuel.put(x, new SoldFuel(x, 0d, 0d)));

        transactionFuel.forEach(x -> {
            String fuelName = x.getFuel().getName();
            Double quantity = mapSoldFuel.get(x.getFuel().getName()).quantity += x.getFuelQuantity();
            Double price = mapSoldFuel.get(x.getFuel().getName()).sumPrice += x.getTotalPrice();
            mapSoldFuel.put(fuelName, new SoldFuel(fuelName, quantity, price));
        });

        return mapSoldFuel;
    }

    private List<String> getFuelTypes() {
        return fuelRepository.findAll().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    private void addProductRows(PdfPTable table, List<TransactionProduct> transactionProducts) {
        getSoldProducts(transactionProducts).forEach((k, v) -> {
            table.addCell(v.name);
            table.addCell(v.quantity.toString());
            table.addCell(v.sumPrice.toString());
        });

        table.addCell("");
        table.addCell("Suma");
        Double sum = getSoldProducts(transactionProducts).entrySet().stream().mapToDouble(i -> i.getValue().sumPrice.doubleValue()).sum()*100.00/100.0;
        table.addCell(String.valueOf(sum));
    }

    private Map<String, SoldProducts> getSoldProducts(List<TransactionProduct> transactionProducts) {
        Map<String, SoldProducts> mapSoldProducts = new HashMap<>();
        getProductTypes().forEach(x -> mapSoldProducts.put(x, new SoldProducts(x, 0l, 0d)));

        transactionProducts.forEach(x -> {
            Set<Product> products = x.getProducts();
            products.forEach(y -> {
                String name = y.getName();
                Long quantity = mapSoldProducts.get(y.getName()).quantity + 1;
                Double price = mapSoldProducts.get(y.getName()).sumPrice + y.getPrice();
                mapSoldProducts.put(name, new SoldProducts(name, quantity, price));
            });
        });

        return mapSoldProducts;
    }

    private List<String> getProductTypes() {
        return productRepository.findAll().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    @ToString
    @AllArgsConstructor
    private class SoldFuel {
        public String fuelType;
        public Double quantity;
        public Double sumPrice;
    }

    @ToString
    @AllArgsConstructor
    private class SoldProducts {
        public String name;
        public Long quantity;
        public Double sumPrice;
    }
}
