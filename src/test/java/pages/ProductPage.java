package pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;

@NamedUrls({@NamedUrl(name = "productPage", url = "/index.php?id_product={1}&controller=product")})
public class ProductPage extends PageObject {
}
