package com.kholyman.zipy.test.product;

public class Category {

    //fields
    private String name;
    private String url;

    public static final Category ELECTRONICS = new Category("ELEKTRONIKA", "https://allegro.pl/" +
            "kategoria/elektronika?string=bargain_zone&bmatch=cl-e2101-d3681-c3682-ele-1-5-0304");

    public static final Category SUPERMARKET = new Category("SUPERMARKET", "https://allegro.pl/" +
            "kategoria/supermarket?string=bargain_zone&bmatch=e2101-d3681-c3682-sup-1-5-0304");

    public static final Category HOUSE_AND_GARDEN = new Category("DOM I OGOROD", "https://allegro.pl/" +
            "kategoria/dom-i-ogrod?string=bargain_zone&bmatch=e2101-d3681-c3682-hou-1-5-0304");

    //constructor
    public Category(String name, String url) {
        this.name = name;
        this.url = url;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
