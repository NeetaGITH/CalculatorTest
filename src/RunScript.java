import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class RunScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//WebDriver driver=new ChromeDriver(); 
		System.out.println("--Execution Started");
		RunScript run=new RunScript();
		run.getTestScript();
		run.getResult();
		
	}

	public void getTestScript() {
		final String SEPARATOR=",";
		BufferedReader br=null;
		try {
			String line="";
			br=new BufferedReader(new FileReader("ExecutionManager.csv"));
			while((line=br.readLine())!=null) {
				String[] testLines=line.split(SEPARATOR);
				if(testLines[4].equalsIgnoreCase("yes")) {
					TestScriptParameters testParam= new TestScriptParameters(testLines[1],testLines[2],testLines[3]);
					executeTest(testParam);
					testParam.closeDriver();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		public void executeTest(TestScriptParameters testParam) {
			testParam.createDriver();
			getTestSteps(testParam);
		}
		
		public void getTestSteps(TestScriptParameters testParam)
		{
			BufferedReader br=null;
			String line="";
			try {
				
				br=new BufferedReader(new FileReader("TestScripts\\"+testParam.getModule()+".scn"));
				boolean tsFound=false;
				while ((line=br.readLine())!=null) {
					line=line.trim();
					if(line.equals("TestScript:"+testParam.getTestScript()+"]")) {
						tsFound=true;
					}else {
						if(tsFound) {
							if(line.startsWith("[TestScript:")) break;
							if(line.contains(">")) {
								String[] keys=line.split(">>");
								Keywords objLine=new Keywords(testParam.getDriver(),keys[1].trim(),keys[2].trim(),keys[3].trim());
								objLine.executeKey();
							}
						}
					}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(br!=null) {
						try {
							br.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
						}
						}
	}
		public void getResult() {
			DateFormat dateFormat=new SimpleDateFormat("dMMMyyy_hh_mm_ss");
			Calendar calendar=Calendar.getInstance();
			String dateTimeString=dateFormat.format(calendar.getTime());
			File source=new File("ExecutionLog.log");
			File dest=new File("Reports\\ExecutionLog_"+dateTimeString+"'log");
			System.out.println("Report generated as"+"ExecutionLog_"+dateTimeString+".log");
			//try {
			//FileUtils.copyFile(source, dest);
			//	} catch(IOException e) {
			//	e.printStackTrace();
			//}
			
		
		}
	}
