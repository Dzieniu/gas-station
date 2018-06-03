package com.dzieniu2.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

class DateConverterServiceTest {

    @Test
    public void formatDate() {
        Date date = new Date();
        date.setTime(1528030976517l);
        String formattedDate = DateConverterService.formatDate(date);
        Assert.assertEquals("2018-06-03 15:02:56", formattedDate);
    }

}