package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderingScooterTest {
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

    public OrderingScooterTest(int numberOrderButton, String name, String surname, String address, String metroStation,
                               String telephone, int data, String duration, String color, String comment) {
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
    }
    @Parameterized.Parameters
    public static Object[][] getTextButton() {
        return new Object[][]{
                {0, "Елена", "Иванова", "Москва", "Лубянка", "+79151098322", 5, "трое суток", "black", "Не звонить, в квартире меленький ребенок"},
                {1, "Сергей", "Петров", "Долгопрудный", "Перово", "+79052255874", 15, "сутки", "grey", "После 21 не привозить - сплю"},

        };
    }
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
    }
    @Test // with POM Заказ самоката - позитивный сценарий
    public void orderingScooterPositive_withPom() {
        BlockOrder blockOrder = new BlockOrder(numberOrderButton, name, surname, address, metroStation, telephone, data, duration, color, comment, driver);
        blockOrder.open();
        blockOrder.selectButton();

        blockOrder.waitOrderContentBlock();
        blockOrder.fillNameField();
        blockOrder.fillSurnameField();
        blockOrder.fillAddressField();
        blockOrder.fillMetroStationField();
        blockOrder.fillTelephoneField();
        blockOrder.clickNext();

        blockOrder.waitBlockAboutRent();
        blockOrder.fillDataField();
        blockOrder.fillDurationField();
        blockOrder.fillCheckColorField();
        blockOrder.fillCommentField();
        blockOrder.clickOrderMiddleButton();
        blockOrder.waitBlockOrderConfirmation();
        blockOrder.clickYes();
        blockOrder.waitBlockOrderHasBeenPlased();
    }
    @After
    public void cleanUp() {
        driver.quit();
    }
}
