package com.kholyman.zipy.test;

import com.kholyman.zipy.test.parser.Parser;
import com.kholyman.zipy.test.parser.ParserElectronics;
import com.kholyman.zipy.test.parser.ParserHouseAndGarden;
import com.kholyman.zipy.test.parser.ParserSupermarket;
import com.kholyman.zipy.test.product.Category;
import com.kholyman.zipy.test.product.Product;
import com.kholyman.zipy.test.writer.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
Брать данные с сайта можно через апи сайта, но на сколько я понял этот способ не подходит для выполнения тестового задания
Для выполнения тестового я использовал две библиотеки Jsoup и CSVWriter
Я надеялся выполнить задание намного быстрее
изначально идея была следующей:
1)распарсить эту страницу https://allegro.pl/strefaokazji/ и закинуть в лист все категории
2) потом случайным образом выбрать 3 категории и пройтись по каждой категории добавляя в соответсвующие листы по 100
товаров из каждой
3) далее пройтись по листам с товарами и распарсить страницу каждого товара, чтоб взять максимальное количество данных
тут я столкнулся с проблемой: после около 100-200 запросов сервер сайта банил мою программу определяя ее как бота выкидывая
то 403-ю то 429-ю ошибку.
Я долго с этим боролся пытался обхитрить сервер, но в итоге понял что теряю время и решил вытаскивать данные только из
артиклов на страницах категорий.
Здесь встреилась еще одна проблемка - один общий парсер не справлялся с задачей, по скольку от категории к категории
структуры html документов слегка отличаются.
В итоге я выбрал хардкорно 3 категории, использовал для них общую модель парсера и только сегодня завершил тестовое в
последний день срока
 */

public class ExecuteApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Product> allProductsToCsv = new ArrayList<>();

        Parser electronic = new ParserElectronics();
        allProductsToCsv.addAll(electronic.parseCategory(Category.ELECTRONICS.getUrl()));

        Thread.sleep(1000);

        Parser supermarket = new ParserSupermarket();
        allProductsToCsv.addAll(supermarket.parseCategory(Category.SUPERMARKET.getUrl()));

        Thread.sleep(1000);

        Parser houseGarden = new ParserHouseAndGarden();
        allProductsToCsv.addAll(houseGarden.parseCategory(Category.HOUSE_AND_GARDEN.getUrl()));

        CsvWriter.writeToFile(new CsvWriter(allProductsToCsv));

    }
}
