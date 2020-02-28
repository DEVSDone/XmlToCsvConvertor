package xml.testClass;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.opencsv.CSVWriter;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class XmlToCsvUtility
{

	static Set<String[]> allItems = new LinkedHashSet<String[]>();
	public static String filePath ="";
	public static void main(String[] args) 
	{

		try {

			//inputFile =args[0];
			//File inputFile = new File("E:\\ptc\\WC11_0\\Windchill\\src\\loadFiles\\DRAFTDOC.xml");
			File inputFile = new File(args[0]);
			filePath =inputFile.getParent();
			System.out.println("File Path : "+filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
		//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nListBeginWTDocument = doc.getElementsByTagName("csvBeginWTDocument");
			NodeList nListIBAValues = doc.getElementsByTagName("csvIBAValue");
			NodeList nListEndWTDoc = doc.getElementsByTagName("csvEndWTDocument");
			System.out.println("----------------------------");
			int iba=0;
			for (int temp = 0; temp < nListBeginWTDocument.getLength(); temp++)
			{
				Node nNodeBeginDoc = nListBeginWTDocument.item(temp);
				Node nNodeEndWTDoc = nListEndWTDoc.item(temp);
				System.out.println("----------------------------");
				System.out.println("\nCurrent Element :" + nNodeBeginDoc.getNodeName());
				String[] forEachWTDocDetail=new String[21];

				if (nNodeBeginDoc.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNodeBeginDoc;
					/*  System.out.println("Student roll no : " 
		                  + eElement.getAttribute("rollno"));*/
					String csvname= eElement.getElementsByTagName("csvname").item(0).getTextContent();
					String csvtitle= eElement.getElementsByTagName("csvtitle").item(0).getTextContent();
					String csvnumber= eElement.getElementsByTagName("csvnumber").item(0).getTextContent();
					String csvtype= eElement.getElementsByTagName("csvtype").item(0).getTextContent();
					String csvdescription= eElement.getElementsByTagName("csvdescription").item(0).getTextContent();
					String csvdepartment= eElement.getElementsByTagName("csvdepartment").item(0).getTextContent();
					String csvsaveIn= eElement.getElementsByTagName("csvsaveIn").item(0).getTextContent();
					String csvteamTemplate= eElement.getElementsByTagName("csvteamTemplate").item(0).getTextContent();
					String csvdomain= eElement.getElementsByTagName("csvdomain").item(0).getTextContent();
					String csvlifecycletemplate= eElement.getElementsByTagName("csvlifecycletemplate").item(0).getTextContent();
					String csvlifecyclestate= eElement.getElementsByTagName("csvlifecyclestate").item(0).getTextContent();
					String csvtypedef= eElement.getElementsByTagName("csvtypedef").item(0).getTextContent();
					String csvversion= eElement.getElementsByTagName("csvversion").item(0).getTextContent();
					String csviteration= eElement.getElementsByTagName("csviteration").item(0).getTextContent();
					String csvsecurityLabels= eElement.getElementsByTagName("csvsecurityLabels").item(0).getTextContent();					
					
				
					forEachWTDocDetail[0]=csvname;
					forEachWTDocDetail[1]=csvtitle;
					forEachWTDocDetail[2]=csvnumber;
					forEachWTDocDetail[3]=csvtype;
					forEachWTDocDetail[4]=csvdescription;
					forEachWTDocDetail[5]=csvdepartment;
					forEachWTDocDetail[6]=csvsaveIn;
					forEachWTDocDetail[7]=csvteamTemplate;
					forEachWTDocDetail[8]=csvdomain;
					forEachWTDocDetail[9]=csvlifecycletemplate;
					forEachWTDocDetail[10]=csvlifecyclestate;
					forEachWTDocDetail[11]=csvtypedef;
					forEachWTDocDetail[12]=csvversion;
					forEachWTDocDetail[13]=csviteration;
					forEachWTDocDetail[14]=csvsecurityLabels;

			/*		System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdescription").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvdepartment").item(0).getTextContent());
					System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvsaveIn").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvteamTemplate").item(0).getTextContent());
					System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdomain").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvlifecycletemplate").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvlifecyclestate").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvtypedef").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvversion").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csviteration").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvsecurityLabels").item(0).getTextContent());
					System.out.println("First Name : " 
							+ eElement
							.getElementsByTagName("csvname")
							.item(0)
							.getTextContent());
					System.out.println("Last Name : " 
							+ eElement
							.getElementsByTagName("csvtitle")
							.item(0)
							.getTextContent());
					System.out.println("Nick Name : " 
							+ eElement
							.getElementsByTagName("csvnumber")
							.item(0)
							.getTextContent());
					System.out.println("Marks : " 
							+ eElement
							.getElementsByTagName("csvtype")
							.item(0)
							.getTextContent());
*/

				}

				Node nNodeIBA =  nListIBAValues.item(iba);
				Node nNodeIBA1 =  nListIBAValues.item(iba+1);
				Node nNodeIBA2 =  nListIBAValues.item(iba+2);
				Node nNodeIBA3 =  nListIBAValues.item(iba+3);
				Node nNodeIBA4 =  nListIBAValues.item(iba+4);					

				if (nNodeIBA.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeIBA;
					//String csvdefinition= eElement.getElementsByTagName("csvdefinition").item(0).getTextContent();
					String csvvalue1= eElement.getElementsByTagName("csvvalue1").item(0).getTextContent();
				//	forEachWTDocDetail[15]=csvdefinition;
					forEachWTDocDetail[15]=csvvalue1;
				/*	System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdefinition").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvvalue1").item(0).getTextContent());
				*/}
				if (nNodeIBA1.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeIBA1;
					//String csvdefinition1= eElement.getElementsByTagName("csvdefinition").item(0).getTextContent();
					String csvvalue11= eElement.getElementsByTagName("csvvalue1").item(0).getTextContent();
					//forEachWTDocDetail[17]=csvdefinition1;
					forEachWTDocDetail[16]=csvvalue11;
				/*	System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdefinition").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvvalue1").item(0).getTextContent());
				*/}
				if (nNodeIBA2.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeIBA2;
				//	String csvdefinition2= eElement.getElementsByTagName("csvdefinition").item(0).getTextContent();
					String csvvalue12= eElement.getElementsByTagName("csvvalue1").item(0).getTextContent();
				//	forEachWTDocDetail[19]=csvdefinition2;
					forEachWTDocDetail[17]=csvvalue12;
			/*		System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdefinition").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvvalue1").item(0).getTextContent());
			*/	}
				if (nNodeIBA3.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeIBA3;
					//String csvdefinition3= eElement.getElementsByTagName("csvdefinition").item(0).getTextContent();
					String csvvalue13= eElement.getElementsByTagName("csvvalue1").item(0).getTextContent();
					//forEachWTDocDetail[21]=csvdefinition3;
					forEachWTDocDetail[18]=csvvalue13;
			/*		System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdefinition").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvvalue1").item(0).getTextContent());
			*/	}
				if (nNodeIBA4.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeIBA4;
			//		String csvdefinition4= eElement.getElementsByTagName("csvdefinition").item(0).getTextContent();
					String csvvalue14= eElement.getElementsByTagName("csvvalue1").item(0).getTextContent();
					//forEachWTDocDetail[23]=csvdefinition4;
					forEachWTDocDetail[19]=csvvalue14;
			/*		System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvdefinition").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvvalue1").item(0).getTextContent());
			*/	}
				iba =iba+5;

				if (nNodeEndWTDoc.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNodeEndWTDoc;
				//	String csvprimarycontenttype= eElement.getElementsByTagName("csvprimarycontenttype").item(0).getTextContent();
					String csvpath= eElement.getElementsByTagName("csvpath").item(0).getTextContent();
					//forEachWTDocDetail[25]=csvprimarycontenttype;
					forEachWTDocDetail[20]=csvpath;
			/*		System.out.println("csvdefinition : " + eElement.getElementsByTagName("csvprimarycontenttype").item(0).getTextContent());
					System.out.println("csvvalue1 : " + eElement.getElementsByTagName("csvpath").item(0).getTextContent());
			*/	}

				allItems.add(forEachWTDocDetail);
			}
			//Call method to write data into csv file.
			writeMetaToCsvFile(allItems);
		} catch (Exception e) 
		{
			System.out.println("Error: "+e.getLocalizedMessage());
			e.printStackTrace();			
		}
	}
	
	private static void writeMetaToCsvFile(Set<String[]> allItems2) 
	{
		String fileName = "CSVFormattedOutputFile.csv";
		System.out.println("#######   Writing into csv file is going on ######### "+fileName);
	//	 String folderPath="D:/JCI project/XMLToCsvConversion/PartListFolder/ImageTagTesting";
		String outputFilePath = filePath+"\\"+fileName ;
		
		System.out.println("Output File Path : "+outputFilePath);
		
		 File outputFile = new File(outputFilePath);
	//	File file = new File(folderPath+"/"+fileName); 

		List<String[]> list = new ArrayList<String[]>(allItems2);
		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(outputFile); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter csvWriter = new CSVWriter(outputfile); 
			csvWriter.writeNext(new String[] { "name","title","number","type","description","department",
					"saveIn","teamTemplate","domain","lifecycletemplate","lifecyclestate","typedef"
							,"version","iteration","securityLabels","Department","Category","DATETIMEISSUED","Owner","Author","ApplicationData"});
			csvWriter.writeAll(list); 

			// closing writer connection 
			csvWriter.close(); 
			outputfile.close();			
			System.out.println("#######   Writing into csv file is FINISH ######### ");
		} 
		catch (IOException e)
		{
			e.printStackTrace(); 
		} 

	}
}
