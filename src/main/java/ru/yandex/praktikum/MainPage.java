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
    private final WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By BLOCK_LIST_OF_QUESTION = By.xpath(".//*[@class = 'Home_FourPart__1uthg']");
    private final int questionNumber;
    // Номер вопроса в  выпадающем списке "Вопросы о важном"
    private final String expectedText;
    // Ожидаемый текст вопроса в  выпадающем списке "Вопросы о важном"
    private final String answerText;
    // Ожидаемый текст вопроса в  выпадающем списке "Вопросы о важном"

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
        WebElement buttonInBlockListOfQuestion = driver.findElement(By.id("accordion__heading-" + questionNumber));
        WebElement answersInBlockList = driver.findElement(By.id("accordion__panel-" + questionNumber));
        buttonInBlockListOfQuestion.click();
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + questionNumber)));
        boolean isDisplayed = answersInBlockList.isDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    public void compareTextQuestionsInBlockListOfQuestion() {
        WebElement buttonInBlockListOfQuestion = driver.findElement(By.id("accordion__heading-" + questionNumber));
        String actualTextQuestion = buttonInBlockListOfQuestion.getText();
        assertEquals("Текст вопроса ошибочный", expectedText, actualTextQuestion);
    }
    public void compareTextAnswersInBlockListOfQuestion() {
        WebElement answersInBlockList = driver.findElement(By.id("accordion__panel-" + questionNumber));
        String actualTextAnswer = answersInBlockList.getText();
        assertEquals("Текст ответа ошибочный", answerText, actualTextAnswer);
    }
}
