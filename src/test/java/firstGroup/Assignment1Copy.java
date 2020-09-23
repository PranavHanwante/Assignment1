package firstGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Assignment1Copy {




        @Test
        public void test1(){

            Boolean b =  getDetails();
            System.out.println("test1 = ");
            Assert.assertTrue(b);
        }
        @Test
        public void test2(){

            Boolean b =  getDetails();
            System.out.println("test2 = ");
            Assert.assertTrue(b);
        }

        public void doStartingOpeartion(){

        }

        public  Boolean performOpeation(String product,String sortItem){

            product = product.trim();
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resource\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriver driver=new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://www.flipkart.com/");
//**********************Pop up cancel*********************

            Assignment1Definition obj1 = new Assignment1Definition(driver);
            if (obj1.popUpCancel().isDisplayed()){
                obj1.popUpCancel().click();
            }

//Pop up cancel
//        if(driver.findElement(By.xpath("//div[@class='mCRfo9']/div/div/button")).isDisplayed()){
//            driver.findElement(By.xpath("//div[@class='mCRfo9']/div/div/button")).click();
//        }


//******************Search Shoes****************************
//        driver.findElement(By.xpath("//form/div/div/input")).sendKeys(product);
//        driver.findElement(By.xpath("//button[@class='vh79eN']")).click();

            obj1.setShoes().sendKeys(product);
            obj1.clickSearch().click();



//****************Sorting low to high filter***************************
            List<WebElement> total = driver.findElements(By.xpath("//div[@class='_3ywJNQ']/div"));
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
                    driver.findElement(By.xpath("//div[@class='_3ywJNQ']/div["+position+"]")).click();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));



                }
//            System.out.println("temp = "+temp);
            }
            System.out.println("product = "+product);
            System.out.println("position = "+position);
            System.out.println(sortItem);


            //////////////////////////******************************//////////////////////////////

            //**********price xpath, checking price is in asecding order*****************
            Assignment1SortPrice obj = new Assignment1SortPrice(driver);
            Boolean orderBoolean;
            obj.getBoolean();
//        Assert.assertTrue(orderBoolean);



//        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='_1vC4OE']"));
//        Iterator<WebElement> iterate_priceList = priceList.iterator();
//        while(iterate_priceList.hasNext()){
////            System.out.println("iterate_priceList = "+(iterate_priceList.next()).getText());
//            WebElement tempPriceList = iterate_priceList.next();
//            String singlePriceList = tempPriceList.getText();
//            String price_without_rupees = singlePriceList.split("\u20B9")[1];
////            System.out.println("price_without_rupees = "+price_without_rupees);
//            a1.add(price_without_rupees);
//        }
//
//        a2 = (ArrayList) a1.clone();
//        Collections.sort(a2);
//
//        System.out.println("a1 = "+a1);
//        System.out.println("a2 = "+a2);
//        Assert.assertTrue(a1.equals(a2));



//****pagination page 2***********


            obj1.clickPagination().click();

//driver.findElement(By.xpath("//div[@class='_2zg3yZ']/nav/a[2]")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_1vC4OE'])[1]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_1vC4OE'])[1]")));



            orderBoolean =obj.getBoolean();
            System.out.println("orderBoolean = "+orderBoolean);

//        Assert.assertTrue(orderBoolean);

            return orderBoolean;
        }

        public Boolean getDetails(){

            ReadExcelFile configuration = new ReadExcelFile(System.getProperty("user.dir") +"\\src\\test\\resource\\assignment_test.xlsx");
            //  int rows = configuration.getRowCount(0);
            Object[][]signin_credentials = new Object[1][2];
            String product =configuration.getData(0, 0, 0);
            String sortItem= configuration.getData(0, 0, 1);

            return performOpeation(product,sortItem);


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





