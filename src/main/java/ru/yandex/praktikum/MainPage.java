package ru.yandex.praktikum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    private static final By BLOCK_LIST_OF_QUESTION = By.xpath(".//*[@class = 'Home_FourPart__1uthg']");
    // Блок "Вопросы о важном"
    private static final By ORDER_HEADER_BUTTON = By.xpath(".//*[@id='root']/div/div/div[1]/div[2]/button[1]");
    // Кнопка "Заказать" в шапке главной страницы
    private static final By ORDER_MIDDLE_BUTTON_MAINPAGE = By.xpath(".//*[@id='root']/div/div[1]/div[4]/div[2]/div[5]/button");
    // Кнопка "Заказать" в середине главной страницы
    private final int questionNumber;
    // Номер вопроса в  выпадающем списке "Вопросы о важном"
    private final String expectedText;
    // Ожидаемый текст вопроса в  выпадающем списке "Вопросы о важном"
    private final String answerText;
    // Ожидаемый текст вопроса в  выпадающем списке "Вопросы о важном"
    private final WebDriver driver;

    public MainPage(WebDriver driver, int questionNumber, String expectedText, String answerText) {
        this.driver = driver;
        this.questionNumber = questionNumber;
        this.expectedText = expectedText;
        this.answerText = answerText;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void findBlockListOfQuestion() {
        WebElement element = driver.findElement(BLOCK_LIST_OF_QUESTION);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.elementToBeClickable(BLOCK_LIST_OF_QUESTION));// явное ожидание
    }

    public void checkClickButtonInBlockListOfQuestion() {
        driver.findElement(By.id("accordion__heading-" + questionNumber)).click();
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + questionNumber)));
        boolean isDisplayed = driver.findElement(By.id("accordion__panel-" + questionNumber)).isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    public void compareTextQuestionsInBlockListOfQuestion() {
        driver.findElement(By.id("accordion__heading-" + questionNumber));
        String actualTextQuestion = driver.findElement(By.id("accordion__heading-" + questionNumber)).getText();
        assertEquals("Текст вопроса ошибочный", expectedText, actualTextQuestion);
    }

    public void compareTextAnswersInBlockListOfQuestion() {
        driver.findElement(By.id("accordion__panel-" + questionNumber));
        String actualTextAnswer = driver.findElement(By.id("accordion__panel-" + questionNumber)).getText();
        assertEquals("Текст ответа ошибочный", answerText, actualTextAnswer);
    }

    public void clickOrderHeaderButton() {
        driver.findElement(ORDER_HEADER_BUTTON).click();
    }

    public void clickOrderMiddleButtonMainPage() {
        WebElement element = driver.findElement(ORDER_MIDDLE_BUTTON_MAINPAGE);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath(".//*[@id='rcc-confirm-button']")).click();
        driver.findElement(ORDER_MIDDLE_BUTTON_MAINPAGE).click();


    }

}
