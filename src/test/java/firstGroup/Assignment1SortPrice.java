package firstGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Assignment1SortPrice {
    WebDriver driver;
    ArrayList a1 = new ArrayList();
    ArrayList a2 = new ArrayList();

    public Assignment1SortPrice(WebDriver driver) {
        this.driver=driver;
    }

    public Boolean getBoolean(){

        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='_1vC4OE']"));
        Iterator<WebElement> iterate_priceList = priceList.iterator();
        while(iterate_priceList.hasNext()){
//            System.out.println("iterate_priceList = "+(iterate_priceList.next()).getText());
            WebElement tempPriceList = iterate_priceList.next();
            String singlePriceList = tempPriceList.getText();
            String price_without_rupees = singlePriceList.split("\u20B9")[1];
//            System.out.println("price_without_rupees = "+price_without_rupees);
            a1.add(price_without_rupees);
        }

        a2 = (ArrayList) a1.clone();
        Collections.sort(a2);

        System.out.println("a1 = "+a1);
        System.out.println("a2 = "+a2);
        System.out.println("Size of A1 = "+a1.size());
//        System.out.println(a1.equals(a2));
        Boolean isTwoArraySame = a1.equals(a2);
        return isTwoArraySame;
    }

}
