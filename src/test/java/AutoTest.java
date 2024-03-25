import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutoTest {

    @Test
    public void checkingGuestRoleTest () {
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//div[@class='header_inner d-flex justify-content-between align-items-center']")).text().toString().equals("Logged in as guest, any changes will be lost!"); // Guest role opened
    }
    @Test
    public void addingNewCarTest (){
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//header/p")).shouldHave(text("Logged in as guest, any changes will be lost!")); // Guest role opened
        $(By.xpath("//button[@class='btn btn-primary']")).click();
        $(By.xpath("//div[@class='modal-content']")).isDisplayed(); // checking is modal add new car opened
        $(By.xpath("//input[@name='mileage']")).sendKeys("100"); // entering mileage
        $(By.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-primary']")).click(); // clicking add new car
        $(By.xpath("//div[contains(@class, 'jumbot1ron')]")).isDisplayed(); // checking if a car was added
        $(By.xpath("//div[@class='car-group']/p")).shouldHave(text("Audi TT")); // assertion on car name
    }
    @Test
    public void removingNewlyAddedCarTest (){
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//header/p")).shouldHave(text("Logged in as guest, any changes will be lost!")); // Guest role opened
        $(byText("Add car")).click();
        $(By.xpath("//div[@class='modal-content']")).isDisplayed(); // checking is modal add new car opened
        $(By.xpath("//input[@name='mileage']")).sendKeys("100"); // entering mileage
        $(By.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-primary']")).click(); // clicking add new car
        $(By.xpath("//div[contains(@class, 'jumbot1ron')]")).isDisplayed(); // checking if a car was added -- div with this class won't be in the DOM if a car is not present
        $(By.xpath("//div[@class='car-group']/p")).shouldHave(text("Audi TT")); // assertion on car name
        $(By.xpath("//div[@class='car_actions']/button[@class='car_edit btn btn-edit']")).click(); // opening edit
        $(By.xpath("//button[contains(@class, 'btn btn-outline-danger')]")).click(); // clicking delete
        $(By.xpath("//div[@class='modal-body']/p[1]")).shouldHave(Condition.text("Do you really want to remove Audi TT from your account?")); // opened modal window
        $(By.xpath("//button[contains(@class, 'btn btn-danger')]")).click(); // clicking delete in modal
        $(By.xpath("//div[contains(@class , 'empty')]/p")).shouldHave(text("You don’t have any cars in your garage")); // checking is the car really deleted
    }

    @Test
    public void editingNewlyAddedCarTest (){
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//header/p")).shouldHave(text("Logged in as guest, any changes will be lost!")); // Guest role opened
        $(byText("Add car")).click();
        $(By.xpath("//div[@class='modal-content']")).isDisplayed(); // checking is modal add new car opened
        $(By.xpath("//input[@name='mileage']")).sendKeys("100"); // entering mileage
        $(By.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-primary']")).click(); // clicking add new car
        $(By.xpath("//div[contains(@class, 'jumbot1ron')]")).isDisplayed(); // checking if a car was added -- div with this class won't be in the DOM if a car is not present
        $(By.xpath("//div[@class='car-group']/p")).shouldHave(text("Audi TT")); // assertion on car name
        $(By.xpath("//div[@class='car_actions']/button[@class='car_edit btn btn-edit']")).click(); // opening edit
        $(By.xpath("//input[@name='mileage']")).clear(); // changing mileage value
        $(By.xpath("//input[@name='mileage']")).sendKeys("32000"); // changing mileage value
        $(byText("Save")).click();
        $(By.xpath("//input[@name='miles']")).shouldHave(value("32000")); // checking is the car really deleted
    }

    @Test
    public void addingFuelExpenseTest (){
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//header/p")).shouldHave(text("Logged in as guest, any changes will be lost!")); // Guest role opened
        $(byText("Add car")).click();
        $(By.xpath("//div[@class='modal-content']")).isDisplayed(); // checking is modal add new car opened
        $(By.xpath("//input[@name='mileage']")).sendKeys("100"); // entering mileage
        $(By.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-primary']")).click(); // clicking add new car
        $(By.xpath("//div[contains(@class, 'jumbot1ron')]")).isDisplayed(); // checking if a car was added -- div with this class won't be in the DOM if a car is not present
        $(By.xpath("//div[@class='car-group']/p")).shouldHave(text("Audi TT")); // assertion on car name
        $(byText("Add fuel expense")).click();
        $(byText("Add an expense")).isDisplayed();
        $(By.xpath("//input[@name='mileage']")).clear();
        $(By.xpath("//input[@name='liters']")).sendKeys("150");
        $(By.xpath("//input[@name='mileage']")).sendKeys("150");
        $(By.xpath("//input[@name='totalCost']")).sendKeys("100");
        $(byText("Add")).click();
        $(By.xpath("//button[@id='carSelectDropdown']")).shouldHave(text("Audi TT")); // checking is the car really audi tt
        $(byText("150L"));
        $(byText("100")); // i know that this is not good idea to assert the values by this way, but okay. Stuck with issues with tr/td locators and its values
    }

    @Test
    public void failedTestForScreenshotTest (){
        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        $(By.xpath("//div[@class='container']//div[contains(@class, 'header')]//button[contains(@class, 'guest')]")).shouldBe(Condition.visible).click();
        $(By.xpath("//header/p")).shouldHave(text("Logged in as guest, any changes will be lost!")); // Guest role opened
        $(byText("Hillel best school ever")).click();
    }
}
