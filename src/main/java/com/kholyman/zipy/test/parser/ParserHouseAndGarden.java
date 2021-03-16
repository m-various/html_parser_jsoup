package com.kholyman.zipy.test.parser;

import com.kholyman.zipy.test.product.Category;
import com.kholyman.zipy.test.product.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParserHouseAndGarden implements Parser {

    //user agents - для частой смены агентов и предотвращения бана со стороны сервера
    final String USER_AGENT1_MAC_FIREFOX = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.10; rv:75.0)" +
            " Gecko/20100101 Firefox/75.0";

    final String USER_AGENT2_WINDOWS_FIREFOX = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:77.0)" +
            " Gecko/20100101 Firefox/77.0";

    final String USER_AGENT3_MAC_CHROME = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2866.71 Safari/537.36";

    final String USER_AGENT4_WINDOWS_CHROME = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";

    final String USER_AGENT5_MAC_SAFARI = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3)" +
            " AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A";

    public List<String> createUserAgentsList() {

        List<String> userAgents = new ArrayList<>();

        userAgents.add(USER_AGENT1_MAC_FIREFOX);
        userAgents.add(USER_AGENT2_WINDOWS_FIREFOX);
        userAgents.add(USER_AGENT3_MAC_CHROME);
        userAgents.add(USER_AGENT4_WINDOWS_CHROME);
        userAgents.add(USER_AGENT5_MAC_SAFARI);

        return userAgents;
    }

    public List<Product> parseCategory(String categoryUrl) throws IOException, InterruptedException {

        List<Product> productList = new ArrayList<>();

        int page = 1;
        int userAgentCount = 0;
        List<String> userAgents = createUserAgentsList();
        String userAgent = null;

        while (productList.size() < 100) {

            if (userAgentCount < 5) {
                userAgent = userAgents.get(userAgentCount);
            } else {
                userAgentCount = 0;
                userAgent = userAgents.get(userAgentCount);
            }

            userAgentCount++;

            Document docForProducts = Jsoup.connect(categoryUrl)
                    .userAgent(userAgent)
                    .referrer("http://www.google.com")
                    .get();

            //находим товары со скидкой
            Elements products = docForProducts.getElementsByAttributeValue("class", "mpof_uk mqu1_ae " +
                    "_9c44d_18kEF m9qz_yp _9c44d_2BSa0  _9c44d_KrRuv");//по данному атрибуту ищем товары где указана старая цена - это значит что на товар есть скидка

            //проходимся по каждому продутку и достаем данные
            products.forEach(product -> {

                if (product != null) {

                    //1)берем имя
                    Element element = product.parent().parent().selectFirst("._w7z6o._uj8z7.meqh_en.mpof_z0" +
                            ".mqu1_16._9c44d_2vTdY");

                    String name = "can't take this field";
                    if (element.text() != null) {
                        name = element.text();
                    }

                    //2)берем ссылку
                    String url = "can't take this field";
                    if (element.attr("href") != null) {
                        url = element.attr("href");
                    }

                    //3)берем скидку
                    String discount = "can't take this field";
                    if ((element = product.parent().child(0)) != null) {
                        discount = element.text();
                    }

                    //4)берем цену
                    String price = "can't take this field";
                    if ((element = product.parent().parent().child(2).child(0).child(0).child(0)) != null){
                        price = element.attr("aria-label");
                    }

                    //5)берем старую цену без скидки
                    String oldPrice = "can't take this field";
                    if(product.text() != null) {
                        oldPrice = product.text();
                    }

                    //6)берем количесвто покупок
                    String numberOfSales = "can't take this field";
                    if ((element = product.parent().parent().selectFirst("._9c44d_3K52C")
                            .selectFirst(".msa3_z4")) != null) {
                        numberOfSales = element.text();
                    }

                    //7)берем продавца
                    String seller = "can't take this field";
                    if ((element = product.parent().parent()
                            .selectFirst(".mpof_ki.m389_6m.msa3_z4.mgn2_13")) != null) {
                        seller = element.text();
                    }

                    //8)берем цену с доставкой
                    String priceWithDelivery = "can't take this field";
                    if ((element = product.parent().parent().selectFirst(".mqu1_g3")) != null) {
                        priceWithDelivery = element.text();
                        if (priceWithDelivery.isEmpty() || priceWithDelivery == null) {
                            priceWithDelivery = "unknown";
                        }
                    }

                    //9)берем бонусные монеты
                    String bonusCoins = "can't take this field";
                    if ((element = product.parent().parent()
                            .selectFirst("._1y62o.mgn2_13.msa3_z4._9c44d_U1seB") ) != null) {
                        bonusCoins = element.text();
                    }

                    //10)берем доступность варинтов
                    String options = "can't take this field";
                    if ((element = product.parent().parent().parent()
                            .selectFirst(".mpof_vs._1y62o._9c44d_3TcIv")) != null) {
                        options = element.text();
                    }

                    //11)берем доступность услуги смарт
                    String smart  = "can't take this field";
                    if ((element = product.parent().parent().selectFirst("._9c44d_2UYuR")) != null) {
                        smart = "SMART delivery";
                    }

                    //12)берем способ доставки SMART
                    String smartDeliveryMethod  = "can't take this field";
                    if ((element = product.parent().parent().selectFirst(".mpof_92.mp7g_oh.m389_0a._9c44d_1BTID")) != null) {
                        smartDeliveryMethod = element.text();
                        if (smartDeliveryMethod.isEmpty() || smartDeliveryMethod == null )
                            smartDeliveryMethod = "unknown";
                    }


                    //проверяем не пусты ли поля, создаем новый товар и добаляем его в лист
                    if (
                            !options.isEmpty() && !bonusCoins.isEmpty()
                                    && !name.isEmpty() && !url.isEmpty()
                                    && !discount.isEmpty() && !price.isEmpty()
                                    && !oldPrice.isEmpty() && !numberOfSales.isEmpty()
                                    && !seller.isEmpty() && productList.size() < 100
                    )
                    {

                        productList.add(new Product(name, url, Category.HOUSE_AND_GARDEN.getName(), discount, price,
                                oldPrice, numberOfSales, seller, priceWithDelivery, bonusCoins, options,
                                smart, smartDeliveryMethod));
                    }
                }
            });

            page++;

            //переход на следующую страницу если не набралось еще 100 товаров
            Elements pages = docForProducts.getElementsByAttributeValue("class", "_w7z6o _jmjqf _1fkm6 _g1gnj" +
                    " _3db39_3i0GV _3db39_G2GaZ _uj8z7 _3db39_21sJS _3db39_3D7g2 _3db39_3_vQF");
            for (Element element : pages) {
                if (element.attr("data-page").equals(String.valueOf(page))) {
                    categoryUrl = element.attr("href");
                }
            }
        }

        //вывод листа товаров в консоль
        productList.forEach(System.out::println);
        System.out.println();

        //сон с целью предотвратить бан со стороны сервера
        Thread.sleep(700 + (int) (Math.random() * 300));

        return productList;
    }


}
