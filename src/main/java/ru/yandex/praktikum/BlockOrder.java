package ru.yandex.praktikum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlockOrder {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Кнопка "Заказать" в шапке главной страницы
    private static final By ORDER_HEADER_BUTTON = By.xpath(".//*[@id='root']/div/div/div[1]/div[2]/button[1]");
    // Кнопка "Заказать" в середине главной страницы
    private static final By ORDER_MIDDLE_BUTTON_MAINPAGE = By.xpath(".//*[@id='root']/div/div[1]/div[4]/div[2]/div[5]/button");
    // Блок "Для кого самокат"
    private static final By BLOCK_ORDER_CONTENT = By.xpath(".//*[@class = 'Order_Content__bmtHS']");
    private static final By NAME_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div:nth-child(1) > input");
    private static final By SURNAME_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div:nth-child(2) > input");
    private static final By ADDRESS_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div:nth-child(3) > input");
    private static final By METRO_STATION_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div:nth-child(4) > div > div > input");
    private static final By METRO_STATION_LIST = By.cssSelector("div.select-search__select");
    private static final By TELEPHONE_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div:nth-child(5) > input");
    private static final By NEXT_BUTTON = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Блок "Про аренду"
    private static final By BLOCK_ABOUT_RENT = By.xpath(".//*[@id='root']/div/div[2]");
    private static final By DATA_FIELD_ENTER = By.cssSelector("div.react-datepicker-wrapper > div > input");
    private static final By CALENDAR = By.xpath(".//*[@class='react-datepicker']");
    private static final By DURATION_ARROW_ENTER = By.cssSelector("div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Dropdown-root");
    private static final By DURATION_LIST_ENTER = By.cssSelector("div.Dropdown-menu > div:nth-child(2)");

    private static final By COMMENT_FIELD_ENTER = By.cssSelector("div.Order_Form__17u6u > div.Input_InputContainer__3NykH > input");

    private static final By ORDER_MIDDLE_BUTTON = By.cssSelector("div.Order_Content__bmtHS > div.Order_Buttons__1xGrp > button:nth-child(2)");
    private static final By BLOCK_ORDER_CONFIRMATION = By.xpath(".//*[(@class='Order_ModalHeader__3FDaJ') and (text()='Хотите оформить заказ?')]");
    private static final By YES_BUTTON = By.cssSelector("div > div.Order_Content__bmtHS > div.Order_Modal__YZ-d3 > div.Order_Buttons__1xGrp > button:nth-child(2)");
    private static final By BLOCK_ORDER_HAS_BEEN_PLASED = By.xpath(".//*[(@class = 'Order_Text__2broi') and (text()='Номер заказа: ')]");
    private WebDriver driver;
    private final int numberOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String telephone;
    private final int data;
    private final String duration;
    private final String color;
    private final String comment;

    public BlockOrder(int numberOrderButton, String name, String surname, String address, String metroStation,
                      String telephone, int data, String duration, String color, String comment, WebDriver driver) {
        this.numberOrderButton = numberOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.telephone = telephone;
        this.data = data;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void selectButton() {
        if (numberOrderButton == 0) {
            driver.findElement(ORDER_HEADER_BUTTON).click();
        } else {
            driver.findElement(By.xpath(".//*[@id='rcc-confirm-button']")).click();// кнопка куки
            WebElement element = driver.findElement(ORDER_MIDDLE_BUTTON_MAINPAGE);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(ORDER_MIDDLE_BUTTON_MAINPAGE).click();
        }
    }

    //Блок "Для кого самокат"
    public void waitOrderContentBlock() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(BLOCK_ORDER_CONTENT));// явное ожидание
        boolean blockOrderContent = driver.findElement(BLOCK_ORDER_CONTENT).isDisplayed();
        Assert.assertTrue("Ошибка", blockOrderContent);
    }

    public void fillNameField() {
        WebElement nameField = driver.findElement(NAME_FIELD_ENTER);
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void fillSurnameField() {
        WebElement surnameField = driver.findElement(SURNAME_FIELD_ENTER);
        surnameField.clear();
        surnameField.sendKeys(surname);
    }

    public void fillAddressField() {
        WebElement addressField = driver.findElement(ADDRESS_FIELD_ENTER);
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void fillMetroStationField() {
        WebElement metroStationField = driver.findElement(METRO_STATION_FIELD_ENTER);
        metroStationField.clear();
        metroStationField.click();
        metroStationField.sendKeys(metroStation);
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(METRO_STATION_LIST));
        driver.findElement(By.xpath(".//*[text()='" + metroStation + "']")).click();
    }

    public void fillTelephoneField() {
        WebElement telephoneField = driver.findElement(TELEPHONE_FIELD_ENTER);
        telephoneField.clear();
        telephoneField.click();
        telephoneField.sendKeys(telephone);
    }

    public void clickNext() {
        driver.findElement(NEXT_BUTTON).click();
    }

    // Блок "Про аренду"
    public void waitBlockAboutRent() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(BLOCK_ABOUT_RENT));// явное ожидание
        boolean blockAboutRent = driver.findElement(BLOCK_ABOUT_RENT).isDisplayed();
        Assert.assertTrue("Ошибка", blockAboutRent);
    }

    public void fillDataField() {
        WebElement dataField = driver.findElement(DATA_FIELD_ENTER);
        dataField.clear();
        dataField.click();
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(CALENDAR));
        driver.findElement(By.xpath(".//*[text()='" + data + "']")).click();
    }

    public void fillDurationField() {
        WebElement durationField = driver.findElement(DURATION_ARROW_ENTER);
        durationField.click();
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(DURATION_LIST_ENTER));
        driver.findElement(By.xpath(".//*[text()='" + duration + "']")).click();
    }

    public void fillCheckColorField() {
        WebElement checkcolor = driver.findElement(By.xpath(".//*[@class='Order_Title__3EKne']"));
        checkcolor.click();
        driver.findElement(By.id(color)).click();
    }

    public void fillCommentField() {
        WebElement commentField = driver.findElement(COMMENT_FIELD_ENTER);
        commentField.clear();
        commentField.click();
        commentField.sendKeys(comment);
    }

    public void clickOrderMiddleButton() {
        driver.findElement(ORDER_MIDDLE_BUTTON).click();
    }

    public void waitBlockOrderConfirmation() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(BLOCK_ORDER_CONFIRMATION));
        boolean blockOrderConfirmation = driver.findElement(BLOCK_ORDER_CONFIRMATION).isDisplayed();
        Assert.assertTrue("Ошибка", blockOrderConfirmation);
    }

    public void clickYes() {
        driver.findElement(YES_BUTTON).click();
    }

    public void waitBlockOrderHasBeenPlased() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(BLOCK_ORDER_HAS_BEEN_PLASED));
        boolean blockOrderHasBeenPlased = driver.findElement(BLOCK_ORDER_HAS_BEEN_PLASED).isDisplayed();
        Assert.assertTrue("Ошибка", blockOrderHasBeenPlased);
    }

}
