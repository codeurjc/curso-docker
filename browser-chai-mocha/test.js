var chai = require("chai");
var assert = require("chai").assert;
var webdriver = require("selenium-webdriver");
var chrome = require("selenium-webdriver/chrome");
const { Key, By, until } = require("selenium-webdriver");

var driver;
var eusURL;
var sutURL;

before(() => {
  var sutHost = process.env.ET_SUT_HOST;
  if (!sutHost) {
    sutURL = "http://localhost:8080/";
  } else {
    sutURL = "http://" + sutHost + ":8080/";
  }
  console.log("App url: " + sutURL);

  eusURL = process.env.ET_EUS_API;
});

beforeEach(() => {
  var options = new chrome.Options();
  options.addArguments("start-maximized");

  var browserBuilder = new webdriver.Builder().forBrowser("chrome");
  browserBuilder.setChromeOptions(options);

  if (eusURL) {
    console.log("Using ElasTest browser ("+eusURL+")");
    browserBuilder.usingServer(eusURL);
  } else {
    console.log("Using local browser");      
  }
  driver = browserBuilder.build();

  console.log("Chrome browser created");
});

describe("Web page", () => {
  it("should add message", async function() {
    this.timeout(30000);

    driver.get(sutURL);

    var newTitle = "MessageTitle";
    var newBody = "MessageBody";

    driver.findElement(By.id("title-input")).sendKeys(newTitle);
    driver.findElement(By.id("body-input")).sendKeys(newBody);

    driver.findElement(By.id("submit")).click();

    var title = driver.findElement(By.id("title")).getText();
    var body = driver.findElement(By.id("body")).getText();

    assert.equal(await title, newTitle);
    assert.equal(await body, newBody);

    driver.quit();
  })
});
