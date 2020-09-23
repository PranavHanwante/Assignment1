package firstGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Assignment1Definition {

    public WebDriver driver;

    By popUpCross = By.xpath("//div[@class='mCRfo9']/div/div/button");
    By setShoes= By.xpath("//form/div/div/input");
    By searchShoes= By.xpath("//button[@class='vh79eN']");

    By pagination = By.xpath("//div[@class='_2zg3yZ']/nav/a[2]");

    By selectFirstShoe = By.xpath("(//div[@class='_1vC4OE'])[2]");
    By selectSecondShoe = By.xpath("(//div[@class='_1vC4OE'])[3]");

    By getTextFirstShoe = By.xpath("(//div[@class='_1vC4OE'])[2]/parent::div/parent::a/preceding-sibling::a");
    By getTextSecondShoe = By.xpath("(//div[@class='_1vC4OE'])[3]/parent::div/parent::a/preceding-sibling::a");

    By clickShoeSize = By.xpath("//li[@id='swatch-0-size']/a");


    public Assignment1Definition(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement popUpCancel() {
        return driver.findElement(popUpCross);
    }

    public WebElement setShoes() {
        return driver.findElement(setShoes);
    }
    public WebElement clickSearch() {
        return driver.findElement(searchShoes);
    }
    public WebElement clickPagination() {return driver.findElement(pagination);}
    public WebElement clickFirstShoe() {return driver.findElement(selectFirstShoe);}
    public WebElement clickSecondShoe() {return driver.findElement(selectSecondShoe);}

    public WebElement getTextOfShoeFirst() {return driver.findElement(getTextFirstShoe);}
    public WebElement getTextOfShoeSecond() {return driver.findElement(getTextSecondShoe);}

    public WebElement selectShoeSize() {return driver.findElement(clickShoeSize);}


}
