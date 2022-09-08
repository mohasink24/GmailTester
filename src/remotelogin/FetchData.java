package remotelogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FetchData {
	private HashMap<String, String> data = new HashMap<String, String>();
	private File f;
	private BufferedReader br = null;

	FetchData() throws IOException {

		// check the input file
		f = new File("C:\\Users\\Pere\\Documents\\input.txt");
		if (f.exists()) {
			System.out.println("Input File Detected.");
			this.readData();
			System.out.println("Data Aquired Successfully.");
			this.display();
		} else {
			System.err.println("Input File Not Found. Please Verify Input File Details.");
			System.err.print("System Shutting Down.");
			for(int i=0;i<5;i++) { 
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.err.println("Safe Shutdown Protocol Failed.");
					e.printStackTrace();
				}
				
				System.out.print(" .. ");
				
			}
			System.exit(0);
		}
	}

	private void display() {
		System.out.println("Data Aquired\t");
		System.out.println("URL\t\t" + getData("URL"));
		System.out.println("Username\t" + getData("Username"));
		System.out.println("Pass\t\t" +"*NOT MADE VISIBLE DUE TO SECURITY REASONS*");
		System.out.println("Reciever\t" + getData("Reciever"));
		System.out.println("Subject\t\t" + getData("Subject"));
		System.out.println("Body\t\t\"" + getData("Mail") + "\"");

	}

	public String getData(String s) { // returns the requested value
		String temp = data.get(s);
		if (temp == null) {
			System.err.println("Data : " + s + " is missing"); // if data isnt avaliable
		}
		return temp;

	}

	private void readData() throws IOException { // Reads the data from files and stores in the hashmap

		try {
			br = new BufferedReader(new FileReader(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fetcher = "";
		// fetcher = br.readLine();
		data.put("URL", br.readLine().trim());
		data.put("Username", br.readLine().trim());
		data.put("Password", br.readLine().trim());
		data.put("Reciever", br.readLine().trim());
		data.put("Subject", br.readLine());

		//
		// String username = br.readLine();
		// String pass = br.readLine();
		// String recvr = br.readLine();
		// String sub = br.readLine();
		// String mail = br.readLine();
		String temp = "";
		while (temp != null) {
			temp = br.readLine();
			if (temp != (null))
				if (fetcher.equals("")) {
					fetcher = temp;
				} else {
					fetcher = fetcher + "\n" + temp;
				}
			else {
				break;
			}

		}
		data.put("Mail", fetcher);
	}
}