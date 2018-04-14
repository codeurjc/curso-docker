package es.codeurjc.test.tablonanuncios;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testcontainers.containers.GenericContainer;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class AnuncioTest {

	@ClassRule
	public static GenericContainer mysql = new GenericContainer<>("mysql:latest").withExposedPorts(3306)
			.withEnv("MYSQL_ROOT_PASSWORD", "pass").withEnv("MYSQL_DATABASE", "test");

	private static String sutURL;

	WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		sutURL = "http://localhost:8080/";
		ChromeDriverManager.getInstance().setup();

		System.setProperty("spring.datasource.url",
				"jdbc:mysql://" + mysql.getContainerIpAddress() + ":" + mysql.getMappedPort(3306) + "/test");

		WebApp.start();
	}

	@Before
	public void setupTest() throws MalformedURLException {
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void teardownApp() {
		WebApp.stop();
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void createTest() throws InterruptedException {

		driver.get(sutURL);

		Thread.sleep(2000);

		driver.findElement(By.linkText("Nuevo anuncio")).click();

		Thread.sleep(2000);

		driver.findElement(By.name("nombre")).sendKeys("Anuncio nuevo con Selenium");
		driver.findElement(By.name("asunto")).sendKeys("Vendo moto");
		driver.findElement(By.name("comentario")).sendKeys("Un comentario muy largo...");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.linkText("Volver al tablón")).click();

		assertNotNull(driver.findElement(By.partialLinkText("Selenium")));
	}

}
