package firstGroup;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static com.codeborne.selenide.Selenide.$$x;

public class CheckSortPrice {
    ArrayList a1 = new ArrayList();
    ArrayList a2 = new ArrayList();
    ArrayList a3 = new ArrayList();
    ArrayList a4 = new ArrayList();

//********Checking If Price List is asecding or not for First Testcase*******

    public Boolean checkPriceListOrder(){
        ElementsCollection priceList = $$x("//div[@class='_1vC4OE']");
        Iterator<SelenideElement> iterate_priceList = priceList.iterator();

        while(iterate_priceList.hasNext()){
            SelenideElement tempPriceList = iterate_priceList.next();
            String singlePriceList = tempPriceList.getText();
            String price_without_rupees = singlePriceList.split("\u20B9")[1];
            a1.add(price_without_rupees);
        }
        a2 = (ArrayList) a1.clone();
        Collections.sort(a2);
        System.out.println("a1 = "+a1);
        System.out.println("a2 = "+a2);
        System.out.println("Size of A1 = "+a1.size());
        System.out.println("Size of A2 = "+a2.size());
        Boolean isTwoArraySame = a1.equals(a2);
        return isTwoArraySame;
    }

//********Checking If Price List is asecding or not for Second Testcase*******

    public Boolean checkPriceListOrderForTestCases2(){
        ElementsCollection priceList = $$x("//div[@class='_1vC4OE']");
        Iterator<SelenideElement> iterate_priceList = priceList.iterator();

        while(iterate_priceList.hasNext()){
            SelenideElement tempPriceList = iterate_priceList.next();
            String singlePriceList = tempPriceList.getText();
            String price_without_rupees = singlePriceList.split("\u20B9")[1];
            a3.add(price_without_rupees);
        }
        a4 = (ArrayList) a3.clone();
        Collections.sort(a4);
        System.out.println("a3 = "+a3);
        System.out.println("a4 = "+a4);
        System.out.println("Size of A3 = "+a3.size());
        System.out.println("Size of A4 = "+a4.size());
        Boolean isTwoArraySame = a3.equals(a4);
        return isTwoArraySame;
    }
}
