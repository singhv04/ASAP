package in.co.socioedu.asap_app;

import java.io.Serializable;

/**
 * Created by user on 2/13/2018.
 */

public class ListItem implements Serializable{

    private String name;
    private String price;
    private String quantity;
    int    CartQuantity=0;

    public ListItem(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.CartQuantity=0;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
