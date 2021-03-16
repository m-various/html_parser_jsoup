package com.kholyman.zipy.test.parser;

import com.kholyman.zipy.test.product.Product;

import java.io.IOException;
import java.util.List;

public interface Parser {
    public List<Product> parseCategory(String categoryUrl) throws IOException, InterruptedException;
}
