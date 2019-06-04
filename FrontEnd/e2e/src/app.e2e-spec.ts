import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { delay } from 'rxjs/internal/operators/delay';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual("NewsAppBoilerplateJava");
    browser.driver.sleep(2000);
  });
  it('should be directed to login page', () => {
    expect(browser.getCurrentUrl()).toContain("login");
    browser.driver.sleep(2000);
  });

  // it('should go to Register page', () => {
  //   browser.element(by.id('register')).click();
  //   expect(browser.getCurrentUrl()).toContain("register");
  //   browser.driver.sleep(2000);
  // });

  // it('should register the user', () => {
  //   browser.element(by.id('first_name')).sendKeys('Uday');
  //   browser.element(by.id('last_name')).sendKeys('Chatterjee');
  //   browser.element(by.id('user_id')).sendKeys('uday2');
  //   browser.element(by.id('password')).sendKeys('chatterjee2');
  //   expect(browser.getCurrentUrl()).toContain("register");
  //   browser.element(by.id('registerUser')).click();
  //   browser.driver.sleep(4000);
  //   expect(browser.getCurrentUrl()).toContain("login");
  //   browser.driver.sleep(2000);
   
  // });

  it('should be able to login user', () => {
    browser.element(by.name('userId')).sendKeys('uday2');
    browser.driver.sleep(1000);
    browser.element(by.name('password')).sendKeys('chatterjee2');
    browser.driver.sleep(1000);
    browser.element(by.id('login')).click();
    expect(browser.getCurrentUrl()).toContain("/headlines");
    browser.driver.sleep(2000);
  });

  it('should be able to search news', () => {
    browser.element(by.id('search_news')).click();
    browser.driver.sleep(1000);
    browser.element(by.id('search')).sendKeys('rohit');
    browser.element(by.id('search')).sendKeys(protractor.Key.ENTER);
    browser.driver.sleep(2000);
    const searchItems = element.all(by.id('news_title'));
    expect(searchItems.count()).toBe(20);
    for(let i = 0; i < 1; i += 1){
      expect(searchItems.get(i).getText()).toContain("Rohit");
    }
    browser.driver.sleep(2000);
  });

  it('should be able to add news in watch list', () =>{
      browser.driver.manage().window().maximize();
      browser.driver.sleep(2000);
      browser.element(by.id("watch_list")).click();
      expect(browser.getCurrentUrl()).toContain("watchList");
      browser.driver.sleep(2000);
      
  });

  it("should be navigate to Headlines", () => {
      page.navigateTo();
      browser.element(by.id("healines")).click();
      browser.driver.sleep(2000);
      const searchItems = element.all(by.id('news_title'));
      expect(browser.getCurrentUrl()).toContain("headlines");
      browser.driver.sleep(2000);
  });

  it("should be able logout", ()=>{
    page.navigateTo();
    browser.element(by.id("logout")).click();
    browser.driver.sleep(1000);
    expect(browser.getCurrentUrl()).toContain("/login");
  });
});
