package es.codeurjc.test.tablonanuncios;

import static org.junit.Assert.assertNotNull;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;

public class AnuncioTest {

	@ClassRule
	public static GenericContainer mysql = new GenericContainer<>("mysql:latest").withExposedPorts(3306)
			.withEnv("MYSQL_ROOT_PASSWORD", "pass").withEnv("MYSQL_DATABASE", "test");

	@Rule
	public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer<>()
			.withDesiredCapabilities(DesiredCapabilities.chrome()).withRecordingMode(RECORD_ALL, new File("target"));

	private static String sutURL;

	WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		
		sutURL = "http://172.17.0.1:8080/";

		System.setProperty("spring.datasource.url",
				"jdbc:mysql://" + mysql.getContainerIpAddress() + ":" + mysql.getMappedPort(3306) + "/test");

		WebApp.start();
	}

	@Before
	public void setupTest() throws MalformedURLException {
		driver = chrome.getWebDriver();
	}

	@AfterClass
	public static void teardownApp() {
		WebApp.stop();
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
