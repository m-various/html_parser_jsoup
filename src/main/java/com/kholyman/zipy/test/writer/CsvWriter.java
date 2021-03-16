package com.kholyman.zipy.test.writer;

import com.kholyman.zipy.test.product.Product;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {

    //fields
    private List<String[]> listForCswWriter = new ArrayList<>();
    private List<Product> productList;

    //constructor
    public CsvWriter(List<Product> productList) {
        this.productList = productList;
    }

    //getters and setters
    public List<String[]> getListForCswWriter() {
        return listForCswWriter;
    }

    public void setListForCswWriter(List<String[]> listForCswWriter) {
        this.listForCswWriter = listForCswWriter;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    //writer method
    public static void writeToFile(CsvWriter csvWriter) throws IOException {

        List<String[]> listForCswWriter = csvWriter.getListForCswWriter();
        List<Product> productList = csvWriter.getProductList();

        for (Product product : productList) {
            String[] properties = new String[14];

            properties[0] = product.getCategory();
            properties[1] = product.getName();
            properties[2] = product.getPrice();
            properties[3] = product.getOldPrice();
            properties[4] = product.getPriceWithDelivery();
            properties[5] = product.getDiscount();
            properties[6] = product.getBonusCoins();
            properties[7] = product.getSeller();
            properties[8] = product.getOptions();
            properties[9] = product.getNumberOfSales();
            properties[10] = product.getSmartDelivery();
            properties[11] = product.getSmartDeliveryMethod();
            properties[12] = product.getUrl();

            listForCswWriter.add(properties);
        }

        File filePath  = new File ("src" + File.separator + "main" +
                File.separator + "resultFile" + File.separator + "result.csv");

        String absolutePath = filePath.getAbsolutePath();

        FileWriter fileWriter = new FileWriter(absolutePath);

        try (CSVWriter writer = new CSVWriter(fileWriter)) {
            writer.writeAll(listForCswWriter);
        }
    }

    public void createFile() {

    }


}

