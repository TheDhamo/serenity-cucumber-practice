package com.lisihocke.serenitypractice.pages;

import com.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequest;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Cookie;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DefaultUrl("/index.php?controller=order")
public class ShoppingCartPage extends PageObject {
    public void checkCartContainsProduct(String productId) {
        String productReference = Serenity.getCurrentSession().get("productReference").toString();
        String productPrice = Serenity.getCurrentSession().get("productPrice").toString();

        assertThat(find(By.jquery("#product_" + productId + " .cart_ref")).getText()).isEqualToIgnoringCase(("SKU : "+productReference));
        assertThat(find(By.jquery("#product_" + productId + " .cart_quantity_input")).getAttribute("value")).isEqualToIgnoringCase("1");
        assertThat(find(By.jquery("#product_" + productId + " .price")).getText()).isEqualToIgnoringCase(productPrice);
    }

    public void checkCartDoesNotContainProduct(String productId) {
        waitForRenderedElementsToDisappear(By.id("product_" + productId));
    }

    public void removeProduct(String productId) {
        find(By.jquery(".cart_delete #" + productId)).click();
    }

    public void productHadBeenAddedToCart(String productId) throws IOException {
        String id_product = productId.substring(0,1);

        /*none of the following options solve the problem; probably it is not solvable like this
        and we would have to sit with the team to increase testability*/
        //addItemToCartRestAssuredVersion(id_product);
        //addItemToCartJavaVersion(id_product);
        //addItemToCartOKHttpClientVersion(id_product);
        //addItemToCartXmlHttpRequestVersion(id_product);
        //addItemToCartCookieVersion();
    }

    private void addItemToCartRestAssuredVersion(String id_product) {
        RestAssured.baseURI = "http://automationpractice.com";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        JsonObject productItem = createProductJsonItem(id_product);
        httpRequest.body(productItem.toString());

        Response response = httpRequest.post("/index.php?rand="+new Date().getTime());
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    private void addItemToCartJavaVersion(String id_product) throws IOException {
        URL url = new URL("http://automationpractice.com/index.php?rand="+new Date().getTime());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        Map<String, String> parameters = setProductParameters(id_product);

        urlConnection.disconnect();
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Cookie", "PrestaShop-a30a9934ef476d11b6cc3c983616e364=Ma2zVqzKt%2Blo9jQHjxa%2ByoI81wKQBk60tOENxInZkmyuVGa61504FBDvu5sOuHUrsBMMMOTlmTykWmR3Iq%2FtER7%2FnzXED%2BiE4d36h1cFNk9JscRmN3Jiv1ycMGctIEwLS01eETyziDNST2hmK8nZZvkBnFmxqdyRAz8YoHriwKwh%2Bb%2BS9CMAcNCjubGVCHdU1DDds5nlBS4Tm3BptpUPmGetV3WOFE8xOcVfOKKx3w4%3D000168");

        urlConnection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        out.writeBytes(getParamsString(parameters));
        out.flush();
        out.close();
    }

    private void addItemToCartOKHttpClientVersion(String id_product) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JsonObject productItem = createProductJsonItem(id_product);

        Request request = createProductJsonRequest(JSON, productItem);
        okhttp3.Response response = client.newCall(request).execute();

        assertThat(response.code()).isEqualTo(200);
    }

    // throwing "java.lang.RuntimeException: Unable to find window associated with com.gargoylesoftware.htmlunit.javascript.host.xml.XMLHttpRequest"
    private void addItemToCartXmlHttpRequestVersion(String id_product) {
        JsonObject productItem = createProductJsonItem(id_product);

        XMLHttpRequest xhr = new XMLHttpRequest();
        xhr.open("POST", "http://automationpractice.com/index.php?rand="+ new Date().getTime(), true, null, null);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        xhr.send(productItem.toString());
    }

    // have to set this session cookie and add the expected item to the cart manually before test run
    private void addItemToCartCookieVersion() {
        this.getDriver().manage().deleteAllCookies();
        this.getDriver().manage().addCookie(new Cookie(
                "PrestaShop-a30a9934ef476d11b6cc3c983616e364",
                "Ma2zVqzKt%2Blo9jQHjxa%2ByoI81wKQBk60tOENxInZkmyuVGa61504FBDvu5sOuHUrsBMMMOTlmTykWmR3Iq%2FtER7%2FnzXED%2BiE4d36h1cFNk9JscRmN3Jiv1ycMGctIEwLS01eETyziDNST2hmK8nZZvkBnFmxqdyRAz8YoHriwKwh%2Bb%2BS9CMAcNCjubGVCHdU1DDds5nlBS4Tm3BptpUPmGetV3WOFE8xOcVfOKKx3w4%3D000168"));
    }

    @NotNull
    private JsonObject createProductJsonItem(String id_product) {
        JsonObject productItem = new JsonObject();
        productItem.addProperty("controller", "cart");
        productItem.addProperty("add", "1");
        productItem.addProperty("ajax", "true");
        productItem.addProperty("qty", "1");
        productItem.addProperty("id_product", id_product);
        productItem.addProperty("token", "e817bb0705dd58da8db074c69f729fd8");
        productItem.addProperty("ipa", id_product);
        return productItem;
    }

    private Request createProductJsonRequest(MediaType JSON, JsonObject productItem) {
        RequestBody body = RequestBody.create(JSON, productItem.toString());

        return new Request.Builder()
                .url("http://automationpractice.com/index.php?rand=1548537747390")
                .post(body)
                .addHeader("Cookie", "PrestaShop-a30a9934ef476d11b6cc3c983616e364=Ma2zVqzKt^%^2Blo9jQHjxa^%^2ByiSwifaRa0Flc6J5MwtLMXiuVGa61504FBDvu5sOuHUr7d97yINe6lVNz3rLvYkF^%^2BAfKy4MqgG7s7m5oMwJb5a6ay6KY4ix42HH4S6YVOzi5lR7DO0t1GjuPix^%^2FGIlnZao5irGXH63DTHPm^%^2BqJtFSj55EtG4e6qehkFWoRvr7C3XufcHjQF30KqqDvMn4p7iZB7NwjpDBLwH9SSVGZzXAU2IdGxv^%^2BoTntOfnO01A^%^2FNgN000178")
                .addHeader("Origin", "http://automationpractice.com")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "en-US,en;q=0.9,de-DE;q=0.8,de;q=0.7")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("cache-control", "no-cache,no-cache")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Connection", "keep-alive")
                .addHeader("Referer", "http://automationpractice.com/index.php?id_product=1^&controller=product^&search_query=shirt^&results=1")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Postman-Token", "6102b8fa-4656-4afa-9203-09bd51bc9c4f")
                .build();
    }

    private static String getParamsString(@NotNull Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    @NotNull
    private Map<String, String> setProductParameters(String id_product) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("controller", "cart");
        parameters.put("add", "1");
        parameters.put("ajax", "true");
        parameters.put("qty", "1");
        parameters.put("id_product", id_product);
        parameters.put("token", "e817bb0705dd58da8db074c69f729fd8");
        parameters.put("ipa", id_product);
        return parameters;
    }
}