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

    public WebElement clickPagination() {
        return driver.findElement(pagination);
    }

}
