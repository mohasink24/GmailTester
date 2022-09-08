/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotelogin;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 *
 * @author
 */
public class RemoteLogin {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO code application logic here
		try {
			FetchData fetchdata;

			fetchdata = new FetchData();

			String url = fetchdata.getData("URL");
			// System.exit(0);

			// System.out.println("Hello World");
			// BufferedReader br = null;
			// String url = "";
			// try { // check the input file
			// File f = new File("C:\\Users\\Pere\\Documents\\input.txt");
			// if (f.exists()) {
			// System.out.println("File Exsists");
			// } else {
			// System.out.println("File Doesnt Exsist");
			// System.exit(0);
			// }
			//
			// br = new BufferedReader(new
			// FileReader("C:\\Users\\Pere\\Documents\\input.txt"));
			// url = br.readLine();

			// System.out.println("URL is \"" + url + "\"");

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Pere\\Documents\\NetBeansProjects\\RemoteLogin\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver.wait(5000);
			// System.out.println();

			driver.get(url);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if (url.contains("gmail")) {
				gmailAutomation(driver, fetchdata);

			}
			// else if (url.contains("makemytrip")) {
			// String username = br.readLine();
			// String pass = br.readLine();
			// driver.findElement(By.id("ch_login_icon")).click();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// WebElement mmtmail =
			// driver.findElement(By.xpath("//input[@id='ch_login_email']"));
			// mmtmail.sendKeys(username);
			// WebElement mmtpass =
			// driver.findElement(By.xpath("//input[@id='ch_login_password']"));
			// mmtpass.sendKeys(pass);
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//
			// }

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			Thread.sleep(10000);
			driver.close();

			// try {
			// br.close();
			// } catch (IOException e) {
			// driver.close();
			// System.out.println("Driver CLosed.");
			// e.printStackTrace();
			// }
			System.out.println("----------------------Execution Finished.----------------------------");
			// } catch (Exception E) {
			//
			// E.printStackTrace();
			// }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void gmailAutomation(WebDriver driver, FetchData fetchdata) {

		// String username = br.readLine();
		// String pass = br.readLine();
		// String recvr = br.readLine();
		// String sub = br.readLine();
		// String mail = br.readLine();
		// String temp = "";
		// while (temp != null) {
		// mail = mail + "\n" + temp;
		// temp = br.readLine();
		// }
		//
		// username.trim();
		// pass.trim();
		// recvr.trim();

		try {
			WebElement gmail = driver.findElement(By.xpath("//input[@id='identifierId']"));
			gmail.sendKeys(fetchdata.getData("Username"));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.findElement(By.id("identifierNext")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			password.sendKeys(fetchdata.getData("Password"));
			Thread.sleep(1000);

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("passwordNext")).click();
			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(300);
			WebElement reciepent = driver.findElement(By.xpath(".//textarea[contains(@aria-label, 'To')]"));
			Thread.sleep(300);
			reciepent.click();
			reciepent.sendKeys(fetchdata.getData("Reciever"));

			Thread.sleep(1000);
			WebElement subject = driver.findElement(By.xpath("//INPUT[@name='subjectbox']"));
			subject.sendKeys(fetchdata.getData("Subject"));
			Thread.sleep(1000);
			WebElement body = driver.findElement(By.xpath("(.//*[@aria-label='Message Body'])[2]"));
			body.click();
			body.sendKeys(fetchdata.getData("Mail"));
			Thread.sleep(1000);
			driver.findElement(By.xpath(".//*[contains(@aria-label, 'Send')]")).click();
			System.out.println("Email was sent successfully");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.navigate().to(
					"https://accounts.google.com/SignOutOptions?hl=en-GB&amp;continue=https://mail.google.com/mail&amp;service=mail");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
