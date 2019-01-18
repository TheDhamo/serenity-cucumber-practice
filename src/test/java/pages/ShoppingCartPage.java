package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/index.php?controller=order")
public class ShoppingCartPage extends PageObject {
    public void checkCartForItem(String productId) {
        assert find(By.id(productId)) != null;
        assert find(By.jquery("#product_1_1_0_0 .cart_quantity_input")).getAttribute("value").equalsIgnoreCase("1");
    }
}
