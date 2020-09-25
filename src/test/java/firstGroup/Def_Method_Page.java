package firstGroup;

import com.codeborne.selenide.Selenide;

public class Def_Method_Page {

    public Def_Method_Page open() {
        Selenide.open("/");
        return this;
    }
    public boolean isPopUpDisplayed(){
        return WebelementConstants.popUpCross.isDisplayed();
    }
    public Def_Method_Page popUpCancel(){
        WebelementConstants.popUpCross.click();
        return this;
    }
    public Def_Method_Page setShoes(String product){
        WebelementConstants.setShoes.sendKeys(product);
        return this;
    }
    public Def_Method_Page clickSearch(){
        WebelementConstants.searchShoes.click();
        return this;
    }
    public Def_Method_Page paginationCLick(){
        WebelementConstants.pagination.click();
        return this;
    }


}
