package in.co.socioedu.asap_app;

/**
 * Created by user on 2/14/2018.
 */


public class saras_menu_chaat {
    String name;
    String price;

    public saras_menu_chaat() {
    }

    public saras_menu_chaat(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

