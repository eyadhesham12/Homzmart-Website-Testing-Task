package org.example.stepDefs;
import com.google.common.base.CharMatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class New_class {
        private WebDriver driver;

        @BeforeTest
        public void setup() {
            driver = new ChromeDriver();
            driver.get("https://homzmart.com/en");
            driver.manage().window().maximize();
        }

        @Test
        public void verifyTotalPriceInCheckoutPage() throws InterruptedException {

            /// Step-1: Open website in chrome browser
            driver.get("https://homzmart.com/en");

            /// Step-2: wait until website to responsable and click on furniture dropdown
            Thread.sleep(5000);
            WebElement furnitureDropdown = driver.findElement(By.id("HEADER_MENU_BAR_CATEGORY_0"));
            Actions actions = new Actions(driver);
            actions.moveToElement(furnitureDropdown).build().perform();

            /// Step-3: click on subcategory outdoor
            WebElement subcategoryElement = driver.findElement(By.xpath("//*[@id=\"HEADER_MENU_BAR_SUBCATEGORY_7\"]/div[1]/a"));
            subcategoryElement.click();

            /// Step-4: refresh page to remove dropdown of furniture and wait until loading
            driver.navigate().refresh();
            Thread.sleep(5000);

            /// Step-5: Open second outdoor item
            WebElement secondOutdoorItem = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div/div[1]/div[2]/section/div[3]/div/section/div[1]/ol/li[2]"));
            secondOutdoorItem.click();
            Thread.sleep(5000);

            // Step-6: Get price of first item in outdoor
            WebElement firstItemOutdoorPrice = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/a/div/div[2]/div[2]/div[2]/div/p"));
            String firstItemPrice = firstItemOutdoorPrice.getText();
            Thread.sleep(3000);

            // Step-7: click on outdoor first item.
            WebElement firstItemOutdoor = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]"));
            firstItemOutdoor.click();
            Thread.sleep(5000);

            /// Step-8: Click to add cart button.
            WebElement addToCartAction = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div/section/div[2]/div[2]/div[1]/div[2]/div"));
            addToCartAction.click();
            Thread.sleep(3000);

            /// Step-9: click on cart icon
            WebElement cartAction = driver.findElement(By.id("HEADER_CART_BRIEF_ICON"));
            cartAction.click();

            /// Step-10: Open cart action all items
            WebElement openCartAction = driver.findElement(By.id("HEADER_CART_BRIEF_GO_TO_CART_BUTTON"));
            openCartAction.click();
            Thread.sleep(3000);

            /// Step-11: Get myCart total price
            WebElement cartPriceElement = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div/div[1]/div[1]/div/div/div[2]/p"));
            String cartPrice = cartPriceElement.getText();

            /// Step12: Assert digits values that selected with total price in my card
            Assert.assertEquals(CharMatcher.digit().retainFrom(firstItemPrice), CharMatcher.digit().retainFrom(cartPrice));
        }

        @AfterTest
        public void tearDown() {
            // Close the browser after

        }
}
