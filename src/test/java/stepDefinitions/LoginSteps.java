package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	WebDriver driver;
	

	@Given("the user is logged in to SauceDemo")
	public void the_user_is_logged_in_to_sauce_demo() throws InterruptedException {
		driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();     
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        Thread.sleep(2000);
	    
	}
	
	@When("the user adds a product to the cart")
	public void the_user_adds_a_product_to_the_cart() throws InterruptedException {
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
	    Thread.sleep(2000);
	}
	
	@Then("the user views the cart with the selected product")
	public void the_user_views_the_cart_with_the_selected_product() throws InterruptedException {
		WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        cartBadge.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));
        Thread.sleep(2000);
	}
	
	@Then("the cart should display the correct product name")
	public void the_cart_should_display_the_correct_product_name() throws InterruptedException {
		WebElement productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        Assert.assertTrue(productInCart.isDisplayed(), "The product is not displayed in the cart");
        String expectedProduct = "Sauce Labs Bike Light";
        String actualProduct = productInCart.getText();
        Assert.assertTrue(actualProduct.contains(expectedProduct),
            "Expected product not found in cart. Found: " + actualProduct);
        Thread.sleep(2000);
        driver.quit();
	   
	}

}

