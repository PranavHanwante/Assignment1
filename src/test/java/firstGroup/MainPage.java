package firstGroup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    Def_Method_Page obj = new Def_Method_Page();

    @BeforeMethod
    public void setup() {
        Configuration.baseUrl = "https://www.flipkart.com";
    }

    @Test
    public void test1(){
        obj.open();
        Boolean b= startOperation();
        Assert.assertTrue(b);
    }
    @Test
    public void test2(){
        obj.open();
        Boolean b= startOperationForTestCaseTwo();
        Assert.assertTrue(b);
    }

    public Boolean doStartingOpeartionForTest1(String product,String sortItem) {
        Def_Method_Page obj1 = new Def_Method_Page();
//        System.out.println("product = " + product);
        String sortItemTrim = sortItem.trim();
//        System.out.println("sortItem = " + sortItemTrim);
        product = product.trim();


//**********************Pop up cancel*********************


        if (obj1.isPopUpDisplayed()) {
            obj1.popUpCancel();
        }

//******************Search Shoes****************************

        obj1.setShoes(product);
        obj1.clickSearch();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


////****************Sorting low to high filter***************************
        ElementsCollection allSortBy = $$x("//div[@class='_3ywJNQ']/div");
        Iterator<SelenideElement> itSortBy = allSortBy.iterator();
        int count = 0;
        int position = 0;
        while (itSortBy.hasNext()) {
            count++;
            String temp = itSortBy.next().getText();
            temp = temp.trim();

            if (temp.contains(sortItemTrim)) {
                position = count;
                $x("//div[@class='_3ywJNQ']/div[" + position + "]").click();
                System.out.println("temp UI Filter = " + temp);
                System.out.println("temp EXCEL Filter = " + sortItem);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
////****************Checking order of price List of Page 1***************************
        CheckSortPrice obj2 = new CheckSortPrice();
        obj2.checkPriceListOrder();
////****************Clicking on page 2**************************
        obj1.paginationCLick();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
////****************Checking order of price List of Page 2***************************
        Boolean b = obj2.checkPriceListOrder();
        System.out.println("Last function, last line");

////***Returning Boolean(true/false) if price list of page 1 and price list of page 2 is in order *****

        return b;

    }

    public Boolean doStartingOpeartionForTest2(String product,String sortItem) {
        Def_Method_Page obj3 = new Def_Method_Page();
//        System.out.println("product = " + product);
        String sortItemTrim = sortItem.trim();
//        System.out.println("sortItem = " + sortItemTrim);
        product = product.trim();


//**********************Pop up cancel*********************


        if (obj3.isPopUpDisplayed()) {
            obj3.popUpCancel();
        }

//******************Search Shoes****************************

        obj3.setShoes(product);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj3.clickSearch();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


////****************Sorting low to high filter***************************
        ElementsCollection allSortBy = $$x("//div[@class='_3ywJNQ']/div");
        Iterator<SelenideElement> itSortBy = allSortBy.iterator();
        int count = 0;
        int position = 0;
        while (itSortBy.hasNext()) {
            count++;
            String temp = itSortBy.next().getText();
            temp = temp.trim();

            if (temp.contains(sortItemTrim)) {
                position = count;
                $x("//div[@class='_3ywJNQ']/div[" + position + "]").click();
                System.out.println("temp UI Filter = " + temp);
                System.out.println("temp EXCEL Filter = " + sortItem);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
////****************Checking order of price List of Page 1***************************


        CheckSortPrice obj4 = new CheckSortPrice();
        obj4.checkPriceListOrderForTestCases2();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obj3.paginationCLick();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
////****************Checking order of price List of Page 2***************************
        Boolean b = obj4.checkPriceListOrderForTestCases2();
        System.out.println("Last function, last line");

////***Returning Boolean(true/false) if price list of page 1 and price list of page 2 is in order *****

        return b;

    }

    public Boolean startOperation(){
        ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") +"/src/test/resource/assignment_test.xlsx");
        String product =configuration.getData(0, 0, 0);
        String sortItem= configuration.getData(0, 0, 1);
        return doStartingOpeartionForTest1(product,sortItem);
    }

    public Boolean startOperationForTestCaseTwo(){
        ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") +"/src/test/resource/assignment_test.xlsx");
        String product =configuration.getData(0, 0, 0);
        String sortItem= configuration.getData(0, 0, 1);
        return doStartingOpeartionForTest2(product,sortItem);
    }

}


