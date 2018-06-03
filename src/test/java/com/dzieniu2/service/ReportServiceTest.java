package com.dzieniu2.service;

import com.itextpdf.text.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportServiceTest {

    @Test
    public void testGenerateReport() throws FileNotFoundException, DocumentException {
        String directory = System.getProperty("user.dir") + "/reports";
        ReportService reportService = new ReportService();
        reportService.generateReport(LocalDate.now());
        File file = new File(directory);
        List<File> files = Arrays.asList(file.listFiles());
        files.stream().map(x -> x.getName().endsWith(DateConverterService.getCurrentDate().substring(0, 6) + ".pdf"));
        Assert.assertNotEquals(new ArrayList<>(), files);
    }

}