package firstGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Assignment1WithOutBase {

//*********Normal without base*********************
    ThreadLocal<WebDriver> driver=new ThreadLocal<>();

    @BeforeTest
    public void begin(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/src/test/resource/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver.set(new ChromeDriver(options));
        driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get().manage().window().maximize();
        driver.get().get("https://www.flipkart.com/");
    }
    @Test
    public void test1(){
        startOperationBegin();
        System.out.println("test1 = ");
        Boolean b =   mainOperation();
        Assert.assertTrue(b);
    }
    @Test
    public void test2(){
        startOperationBegin();
        System.out.println("test2 = ");
        Boolean b =   mainOperation();
        Assert.assertTrue(b);
    }
    //***************Validate Add to Cart Functionality***************
    @Test
    public void test3(){
        startOperationBegin();
        System.out.println("Done startOperationBegin");
        addProduct();

    }

    public void addProduct(){

        Assignment1Definition obj1 = new Assignment1Definition(driver.get());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String shoeOneTextFromList = obj1.getTextOfShoeFirst().getText();
        System.out.println("shoeOneTextFromList = "+shoeOneTextFromList);
        obj1.clickFirstShoe().click();

//        Set<String> windows =	driver.getWindowHandles();
//Thread.sleep

        Set<String> windows =   driver.get().getWindowHandles();

        Iterator<String> itrWindows = windows.iterator();
        String parentWindow=itrWindows.next();
        String child1Window=itrWindows.next();



        driver.get().switchTo().window(child1Window);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj1.selectShoeSize().click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj1.selectAddToCart().click();

        System.out.println("Done");


////***************switch to parent window***************

        driver.get().switchTo().window(parentWindow);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//***********Selecting 2nd shoe*******************
        String shoeTowTextFromList = obj1.getTextOfShoeSecond().getText();
        System.out.println("shoeTowTextFromList = "+shoeTowTextFromList);
        obj1.clickSecondShoe().click();

//***********switching to child window***********
        Set<String> windows2 =   driver.get().getWindowHandles();
        Iterator<String> itrWindows2 = windows2.iterator();
        String parentWindow1=itrWindows2.next();
        String childWindow1=itrWindows2.next();
        String childWindow2=itrWindows2.next();

        driver.get().switchTo().window(childWindow2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        obj1.selectShoeSize().click();
        obj1.selectAddToCart().click();
        System.out.println("Done");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

////***************Checking if the shoe are selected correct in add to cart***************

        String addToCartGetShoeOneText =    obj1.getAddToCartShoeOneText().getText();
        String addToCartGetShoeSecondText =    obj1.getAddToCartShoeTwoText().getText();
        System.out.println("************** = ");
        System.out.println("addToCartGetShoeOneText = "+addToCartGetShoeOneText);
        System.out.println("shoeOneTextFromList = "+shoeOneTextFromList);
        System.out.println("addToCartGetShoeSecondText = "+addToCartGetShoeSecondText);
        System.out.println("shoeTowTextFromList = "+shoeTowTextFromList);

        Assert.assertTrue(addToCartGetShoeOneText.contains(shoeOneTextFromList));
        Assert.assertTrue(addToCartGetShoeSecondText.contains(shoeTowTextFromList));


////****************Now checking total price of the two shoe**************************

        String shoePriceOne = (obj1.getAddToCartShoePriceOne().getText().split("\u20B9")[1]);
        int shoePriceOneInt=Integer.parseInt(shoePriceOne);
        String shoePriceTwo = obj1.getAddToCartShoePriceTwo().getText().split("\u20B9")[1];
        int shoePriceTwoInt=Integer.parseInt(shoePriceTwo);
        String getTotal = obj1.getAddToCartTotalPrice().getText().split("\u20B9")[1];
        int getTotalInt=Integer.parseInt(getTotal);

        int shoeTotalOfParticualrShoe = shoePriceOneInt+shoePriceTwoInt;

        Assert.assertTrue(getTotalInt==shoeTotalOfParticualrShoe);

    }


    public void doStartingOpeartion(String product,String sortItem){
        Assignment1Definition obj1 = new Assignment1Definition(driver.get());

        product = product.trim();


//**********************Pop up cancel*********************


        if (obj1.popUpCancel().isDisplayed()){
            obj1.popUpCancel().click();
        }

//******************Search Shoes****************************

        obj1.setShoes().sendKeys(product);
        obj1.clickSearch().click();



//****************Sorting low to high filter***************************
        List<WebElement> total = driver.get().findElements(By.xpath("//div[@class='_3ywJNQ']/div"));
        Iterator<WebElement> itTotal = total.iterator();
        ArrayList a1 = new ArrayList();
        ArrayList a2 = new ArrayList();
        int count=0;int position=0;
        while(itTotal.hasNext()){
            count++;
            String temp=itTotal.next().getText();
            temp=temp.trim();
            if(sortItem.equalsIgnoreCase(temp)){
                position=count;
                driver.get().findElement(By.xpath("//div[@class='_3ywJNQ']/div["+position+"]")).click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebDriverWait wait = new WebDriverWait(driver.get(), 10);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));

            }
//            System.out.println("temp = "+temp);
        }
        System.out.println("product = "+product);
        System.out.println("position = "+position);
        System.out.println(sortItem);

    }




    public  Boolean mainOperation(){

        //**********price xpath, checking price is in asecding order*****************
        Assignment1SortPrice obj = new Assignment1SortPrice(driver.get());
        Boolean orderBoolean;
        obj.getBoolean(false);

//****pagination page 2***********
        Assignment1Definition obj1 = new Assignment1Definition(driver.get());

        obj1.clickPagination().click();

//driver.findElement(By.xpath("//div[@class='_2zg3yZ']/nav/a[2]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_1vC4OE'])[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));



        orderBoolean =obj.getBoolean(true);
        System.out.println("orderBoolean = "+orderBoolean);

//        Assert.assertTrue(orderBoolean);

        return orderBoolean;
    }

    public void startOperationBegin(){

        ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") +"/src/test/resource/assignment_test.xlsx");
        //  int rows = configuration.getRowCount(0);
        Object[][]signin_credentials = new Object[1][2];
        String product =configuration.getData(0, 0, 0);
        String sortItem= configuration.getData(0, 0, 1);

        doStartingOpeartion(product,sortItem);


    }












/**********USING DATA PROVIDER*********/



//
//    @Test(dataProvider="testdata")
//    public void demoClass(String product, String sortItem) throws InterruptedException {
//        product = product.trim();
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resource\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        WebDriver driver=new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("https://www.flipkart.com/");
////**********************Pop up cancel*********************
//
//        Assignment1Definition obj1 = new Assignment1Definition(driver);
//        if (obj1.popUpCancel().isDisplayed()){
//            obj1.popUpCancel().click();
//        }
//
////Pop up cancel
////        if(driver.findElement(By.xpath("//div[@class='mCRfo9']/div/div/button")).isDisplayed()){
////            driver.findElement(By.xpath("//div[@class='mCRfo9']/div/div/button")).click();
////        }
//
//
////******************Search Shoes****************************
////        driver.findElement(By.xpath("//form/div/div/input")).sendKeys(product);
////        driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
//
//        obj1.setShoes().sendKeys(product);
//        obj1.clickSearch().click();
//
//
//
////****************Sorting low to high filter***************************
//        List<WebElement> total = driver.findElements(By.xpath("//div[@class='_3ywJNQ']/div"));
//        Iterator<WebElement> itTotal = total.iterator();
//        ArrayList a1 = new ArrayList();
//        ArrayList a2 = new ArrayList();
//        int count=0;int position=0;
//        while(itTotal.hasNext()){
//            count++;
//            String temp=itTotal.next().getText();
//            temp=temp.trim();
//            if(sortItem.equalsIgnoreCase(temp)){
//                position=count;
//                driver.findElement(By.xpath("//div[@class='_3ywJNQ']/div["+position+"]")).click();
////                Thread.sleep(5000);
//
//
//                WebDriverWait wait = new WebDriverWait(driver, 10);
//                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));
//
//
//
//            }
//            System.out.println("temp = "+temp);
//        }
//        System.out.println("product = "+product);
//        System.out.println("position = "+position);
//        System.out.println(sortItem);
//
//
//        //**********price xpath, checking price is in asecding order*****************
//        Assignment1SortPrice obj = new Assignment1SortPrice(driver);
//        Boolean orderBoolean;
//        obj.getBoolean();
////        Assert.assertTrue(orderBoolean);
//
//
//
////        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='_1vC4OE']"));
////        Iterator<WebElement> iterate_priceList = priceList.iterator();
////        while(iterate_priceList.hasNext()){
//////            System.out.println("iterate_priceList = "+(iterate_priceList.next()).getText());
////            WebElement tempPriceList = iterate_priceList.next();
////            String singlePriceList = tempPriceList.getText();
////            String price_without_rupees = singlePriceList.split("\u20B9")[1];
//////            System.out.println("price_without_rupees = "+price_without_rupees);
////            a1.add(price_without_rupees);
////        }
////
////        a2 = (ArrayList) a1.clone();
////        Collections.sort(a2);
////
////        System.out.println("a1 = "+a1);
////        System.out.println("a2 = "+a2);
////        Assert.assertTrue(a1.equals(a2));
//
//
//
////****pagination page 2***********
//
//
//        obj1.clickPagination().click();
////driver.findElement(By.xpath("//div[@class='_2zg3yZ']/nav/a[2]")).click();
////Thread.sleep(5000);
//
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
////        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_1vC4OE'])[1]")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));
//
//
//
//        orderBoolean =obj.getBoolean();
//        System.out.println("orderBoolean = "+orderBoolean);
//
//        Assert.assertTrue(orderBoolean);
//
//
//    }
//
//    @DataProvider
//    public Object[][] testdata(){
//        ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") +"\\src\\test\\resource\\assignment_test.xlsx");
//        //  int rows = configuration.getRowCount(0);
//
//
//
//        Object[][]signin_credentials = new Object[1][2];
//        signin_credentials[0][0] = configuration.getData(0, 0, 0);
//        signin_credentials[0][1] = configuration.getData(0, 0, 1);
//        return signin_credentials;
//    }


}





