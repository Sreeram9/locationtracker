package com.sreeram;
import java.io.FileWriter;
import java.util.Date;  
public class FileGenerator {  
	public static void main(String args[]){    
		while(true) {
			try{
				
				Date fileNameDate=new Date();
				FileWriter fw=new FileWriter("G:\\Sample\\"+fileNameDate.getTime()+".txt");
				System.out.println("Writing file "+fileNameDate.getTime()+".txt");
				for(int i=1;i<=4;i++){
					int latitude=100;
					int longitude=100;
					for(int j=1;j<=100;j++){
						Date date=new Date();
						fw.write(i+" "+latitude+" "+longitude+" "+date.getTime());
						fw.write("\r\n");
						latitude++;
						longitude++;
					}
				}
				fw.close();
				System.out.println("Writing file "+fileNameDate.getTime()+".txt completed");
				Thread.sleep(100000);
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}
}
