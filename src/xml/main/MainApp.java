package xml.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVWriter;

import xml.ReadObj.ItemObject;
import xml.ReadObj.ItemsObject;
import xml.ReadObj.MetaDataObject;
import xml.ReadObj.MetaFileObject;
import xml.ReadObj.PartListObject;
import xml.ReadObj.Property;

public class MainApp 
{

	static Set<String[]> allItems = new LinkedHashSet<String[]>();
	//	static String folderPath="D:/JCI project/XMLToCsvConversion/PartListFolder/20May2019/500Testing";
	static Path path = FileSystems.getDefault().getPath(".").toAbsolutePath(); //Original value for path 
	//static String path = "D:/JCI project/XMLToCsvConversion/Testing";  //Testing purpose variable:
	static String folderPath = path.toString();

	public static void main(String[] args) 
	{
		System.out.println("Path: "+folderPath);
		  LocalDateTime start = LocalDateTime.now();  
		System.out.println("## Start Time: "+start.toString());
		XmlAndMetaToCsv();
		LocalDateTime end = LocalDateTime.now();  
		System.out.println("## End Time: "+end.toString());
	}

	//Function : This function read xml and meta  files and take some values from both files and write into csv file.
	private static void XmlAndMetaToCsv()
	{
		try
		{
			int fileProcessCount = 0 ;
			ArrayList<String> fileNames = getAllFilesFromFolder(folderPath);
			for(String fname:fileNames)
			{
				String fileExtension = fname.substring(fname.lastIndexOf("."));				
				if(!fileExtension.equals(".xml") )
				{
					continue;
				}

				System.out.println("################## File in Process : "+fileProcessCount++ +" File Name : "+fname);

				String currentFile =folderPath+"/"+fname;			

				File xmlfile = new File(currentFile);

				JAXBContext jaxbContext = JAXBContext.newInstance(PartListObject.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				PartListObject prtListObj = (PartListObject) jaxbUnmarshaller.unmarshal(xmlfile);
				List<MetaDataObject> metaObjList = prtListObj.getMetaDataObject();
				List<ItemsObject> itemsObjList = prtListObj.getItemsObject();

				String partListNumber =" " ;  //PartList number 

				//For loop for PartList Metadata
				for (MetaDataObject metaDataObject : metaObjList)
				{
					//System.out.println("Meta Info :"+metaDataObject.getId());
					if(metaDataObject.getId().contains("PartList_"))
					{
						List<Property> propertyList = metaDataObject.getPropertyList();				
						for (Property propertyObject : propertyList) 
						{
							//System.out.println("Propery Info: "+propertyObject.getToken());
							if(propertyObject.getToken().equals("number") )
							{							
								partListNumber = propertyObject.getValue();
							}
						}
					}
				}
				//For loop for Items  Metadata
				for (ItemsObject itemsObject : itemsObjList) 
				{
					List<ItemObject> itemList = itemsObject.getItemList();
					if( (!(itemList == null) ) )
					{

						String se_Eff = getMeta_Ser_Effectivity(fname); //Getting Service Effectivity from 
						
					//	System.out.println("Se_Eff :"+se_Eff);
						for (ItemObject itemObject : itemList) 
						{
							List<MetaDataObject> itemMetadataList = itemObject.getMetaDataObject();
							String itemNum="";
							String itemQty="";					
							String partNumber="";
							for (MetaDataObject metaDataObject : itemMetadataList)
							{
								//System.out.println("Meta Info :"+metaDataObject.getId());								
								if(metaDataObject.getId().contains("WTPart_"))
								{
									List<Property> propertyList = metaDataObject.getPropertyList();				
									for (Property propertyObject : propertyList) 
									{
										//System.out.println("Propery Info: "+propertyObject.getToken());
										if(propertyObject.getToken().equals("number") )
										{
											//System.out.println("Metadata Part Number :"+propertyObject.getValue());
											partNumber = propertyObject.getValue();
										}
									}
								}
								if(metaDataObject.getId().contains("PartListItem_"))
								{
									List<Property> propertyList = metaDataObject.getPropertyList();				
									for (Property propertyObject : propertyList) 
									{
										if(propertyObject.getToken().equals("itemNumber") )
										{
											//	System.out.println(" itemNumber :"+propertyObject.getValue());
											itemNum = propertyObject.getValue();
										}
										else if(propertyObject.getToken().equals("itemQuantity") )
										{
											//System.out.println(" itemQuantity :"+propertyObject.getValue());
											itemQty = propertyObject.getValue();
										}
									}
								}																
							}
						//	System.out.println("Values:"+partNumber+":"+itemNum+":"+itemQty+":");
							splitMeta_Ser_Eff(se_Eff,partListNumber,partNumber,itemNum,itemQty);

						}
					}
				}			
				if(fileProcessCount%500 == 0)
				{
					System.out.println("########### Total Lines to write in csv file : "+allItems.size()+" ########### ");
					writeMetaToCsvFile(allItems,fileProcessCount);
					allItems.clear();
				}
			}

			System.out.println("########### Total Lines to write in csv file : "+allItems.size()+" ########### ");
			writeMetaToCsvFile(allItems,fileProcessCount);
			allItems.clear();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private static String getMeta_Ser_Effectivity(String fname) 
	{
		String meta_Ser_Eff="";
		//get meta file for that same xml name		
		String	metaFileName = fname+".meta";
		String currentFile =folderPath+"/"+metaFileName;			
		try
		{
			File metaXmlFile = new File(currentFile);
			System.out.println(" currentFile Meta File :"+metaFileName);			
			if(metaXmlFile.exists() )
			{
				JAXBContext jaxbContext = JAXBContext.newInstance(MetaFileObject.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				//get root element Object from meta xml file 
				MetaFileObject metaFileObj = (MetaFileObject) jaxbUnmarshaller.unmarshal(metaXmlFile);
				//get Metadata elements under root element
				List<MetaDataObject> metaObjList = metaFileObj.getMetaDataObject();			
				//if  meta id contains "PartList_"
				for (MetaDataObject metaDataObject : metaObjList)
				{
					if(metaDataObject.getId().contains("PartList_"))
					{
						List<Property> propertyList = metaDataObject.getPropertyList();				
						for (Property propertyObject : propertyList) 
						{
							if(propertyObject.getToken().equals("ServiceEffectivity") )
							{
								//	System.out.println(" itemNumber :"+propertyObject.getValue());
								meta_Ser_Eff = propertyObject.getValue();
							}
						}
					}
				}
			}

		} catch (Exception e)
		{
		//	System.out.println("Meta file not available for :"+fname);
		//	System.out.println("Exception in getting metafile : "+e.getMessage() );
		//	e.printStackTrace();
		}

		return meta_Ser_Eff;
	}

	private static void splitMeta_Ser_Eff(String se_Eff, String partListNumber, String partNumber, String itemNum,String itemQty) 
	{

		String[] sSplit = se_Eff.split("PRODUCT");
		for (String string : sSplit) 
		{
			if(string.contains("INSTANCE"))
			{
			//	System.out.println("main SPlit :"+string+":");
				String[] splitByProd = string.split(" "); 

				String productName = splitByProd[1].replaceAll("[\\',)]", "");
				//	System.out.println("##:ProductName Finally :"+ productName );
				String[] splitForInstacne = string.split("INSTANCE");

				String instances = splitForInstacne[1];
				String[] inst = instances.split(" ");
				for (String instance : inst) 
				{
					//System.out.println("Second SPlit :"+instance+":");
					String singleInstName = StringUtils.substringBetween(instance,"'", "'");
					if((!(singleInstName == null))  )
					{
					//	System.out.println("productName:"+productName+" :INSTANCE :"+singleInstName);

						String[] foreachPrdDateRanges =new String[6]; 
						foreachPrdDateRanges[0]=partListNumber;
						foreachPrdDateRanges[1]= partNumber;
						foreachPrdDateRanges[2]=itemNum ;					
						foreachPrdDateRanges[3]=itemQty ;
						foreachPrdDateRanges[4]=productName ;
						foreachPrdDateRanges[5]=singleInstName;

						allItems.add(foreachPrdDateRanges);

					}
				}
			}
		}
	}

	//Function : This function read only xml files and take some values and write into csv file.
	public static void XmlToCsv()
	{

		try
		{
			int fileProcessCount = 0 ;
			ArrayList<String> fileNames = getAllFilesFromFolder(folderPath);
			for(String fname:fileNames)
			{
				String fileExtension = fname.substring(fname.lastIndexOf("."));				
				if(!fileExtension.equals(".xml") )
				{
					continue;
				}

				System.out.println("################## File in Process : "+fileProcessCount++ +" File Name : "+fname);

				String currentFile =folderPath+"/"+fname;			

				File xmlfile = new File(currentFile);

				JAXBContext jaxbContext = JAXBContext.newInstance(PartListObject.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				PartListObject prtListObj = (PartListObject) jaxbUnmarshaller.unmarshal(xmlfile);
				List<MetaDataObject> metaObjList = prtListObj.getMetaDataObject();
				List<ItemsObject> itemsObjList = prtListObj.getItemsObject();

				String partListNumber =" " ;  //PartList number 

				//For loop for PartList Metadata
				for (MetaDataObject metaDataObject : metaObjList)
				{
					//System.out.println("Meta Info :"+metaDataObject.getId());
					if(metaDataObject.getId().contains("PartList_"))
					{
						List<Property> propertyList = metaDataObject.getPropertyList();				
						for (Property propertyObject : propertyList) 
						{
							//System.out.println("Propery Info: "+propertyObject.getToken());
							if(propertyObject.getToken().equals("number") )
							{							
								partListNumber = propertyObject.getValue();
							}
						}
					}
				}
				//For loop for Items  Metadata
				for (ItemsObject itemsObject : itemsObjList) 
				{
					List<ItemObject> itemList = itemsObject.getItemList();
					if( (!(itemList == null) ) )
					{
						for (ItemObject itemObject : itemList) 
						{
							List<MetaDataObject> itemMetadataList = itemObject.getMetaDataObject();
							String itemNum="";
							String itemQty="";
							String se_Eff="" ;
							String partNumber="";
							for (MetaDataObject metaDataObject : itemMetadataList)
							{
								//System.out.println("Meta Info :"+metaDataObject.getId());								
								if(metaDataObject.getId().contains("WTPart_"))
								{
									List<Property> propertyList = metaDataObject.getPropertyList();				
									for (Property propertyObject : propertyList) 
									{
										//System.out.println("Propery Info: "+propertyObject.getToken());
										if(propertyObject.getToken().equals("number") )
										{
											//System.out.println("Metadata Part Number :"+propertyObject.getValue());
											partNumber = propertyObject.getValue();
										}
									}
								}
								if(metaDataObject.getId().contains("PartListItem_"))
								{
									List<Property> propertyList = metaDataObject.getPropertyList();				
									for (Property propertyObject : propertyList) 
									{
										if(propertyObject.getToken().equals("itemNumber") )
										{
											//	System.out.println(" itemNumber :"+propertyObject.getValue());
											itemNum = propertyObject.getValue();
										}
										else if(propertyObject.getToken().equals("itemQuantity") )
										{
											//System.out.println(" itemQuantity :"+propertyObject.getValue());
											itemQty = propertyObject.getValue();
										}
										else if(propertyObject.getToken().equals("ServiceEffectivity") )
										{
											//System.out.println(" ServiceEffectivity :"+propertyObject.getValue());
											se_Eff = propertyObject.getValue();										
										}
									}
								}																
							}
							//	System.out.println("Values:"+partNumber+":"+itemNum+":"+itemQty+":"+se_Eff);
							splitXml_Ser_Eff(se_Eff,partListNumber,partNumber,itemNum,itemQty);
						}
					}
				}			
				if(fileProcessCount%1000 == 0)
				{
					System.out.println("########### Total Lines to write in csv file : "+allItems.size()+" ########### ");
					writeCSVFile(allItems,fileProcessCount);
					allItems.clear();
				}
			}

			System.out.println("########### Total Lines to write in csv file : "+allItems.size()+" ########### ");
			writeCSVFile(allItems,fileProcessCount);
			allItems.clear();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public static void writeCSVFile(Set<String[]> allItems2,int CountAsNameOfFile) 
	{
		String fileName = String.valueOf(CountAsNameOfFile)+".csv";
		System.out.println("#######   Writing into csv file is going on ######### "+fileName);
		File file = new File(folderPath+"/"+fileName); 

		List<String[]> list = new ArrayList<String[]>(allItems2);
		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter csvWriter = new CSVWriter(outputfile); 
			csvWriter.writeNext(new String[] { "PartList_Number","Product_Name","Part_Number","itemNumber","itemQuantity","From_Date","To_Date" });
			csvWriter.writeAll(list); 

			// closing writer connection 
			csvWriter.close(); 
			System.out.println("#######   Writing into csv file is FINISH ######### ");
		} 
		catch (IOException e)
		{
			e.printStackTrace(); 
		} 

	}
	private static void writeMetaToCsvFile(Set<String[]> allItems2,int CountAsNameOfFile) 
	{
		String fileName = String.valueOf(CountAsNameOfFile)+".csv";
		System.out.println("#######   Writing into csv file is going on ######### "+fileName);
		File file = new File(folderPath+"/"+fileName); 

		List<String[]> list = new ArrayList<String[]>(allItems2);
		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter csvWriter = new CSVWriter(outputfile); 
			csvWriter.writeNext(new String[] { "PartList_Number","Part_Number","itemNumber","itemQuantity","Model_Number","Serial_Number"});
			csvWriter.writeAll(list); 

			// closing writer connection 
			csvWriter.close(); 
			System.out.println("#######   Writing into csv file is FINISH ######### ");
		} 
		catch (IOException e)
		{
			e.printStackTrace(); 
		} 

	}

	private static void splitXml_Ser_Eff(String se_Eff,String partListNum,String partNumber,String itemNum,String itemQty) 
	{

		String[] sSplit = se_Eff.split("PRODUCT");

		for (String string : sSplit) 
		{
			if(string.contains("DATE_RANGE"))
			{
				//	System.out.println("main SPlit :"+string+":");
				String[] splitByProd = string.split(" "); 

				//	System.out.println("ProductName :"+splitByProd[1]);
				String productName = splitByProd[1].replaceAll("[\\',)]", "");
				//	System.out.println("##:ProductName Finally :"+ productName );


				String[] splitForDate = string.split("DATE_RANGE"); 

				for (String string2 : splitForDate) 
				{
					if(string2.contains("00:00:00"))
					{
						//System.out.println(":Date SPlit  :"+string2);
						String[] foreachPrdDateRanges =new String[7];  //For each se_eff 

						String[] dates = string2.split(" ");
						//System.out.println(dates.length);
						String fromDate = dates[1];
						String toDate = dates[3];

						foreachPrdDateRanges[0]=partListNum;
						foreachPrdDateRanges[1]=productName ;
						foreachPrdDateRanges[2]=partNumber ;					
						foreachPrdDateRanges[3]=itemNum ;
						foreachPrdDateRanges[4]=itemQty ;
						foreachPrdDateRanges[5]=fromDate.replaceAll("[\\',)]", "");
						foreachPrdDateRanges[6]=toDate.replaceAll("[\\',)]", "");

						allItems.add(foreachPrdDateRanges);
					}
				}
			}
		}

	}

	public static ArrayList<String> getAllFilesFromFolder(String directoryName ) 
	{
		ArrayList<String> files =new ArrayList<String>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();

		for (File file : fList)
		{
			if (file.isFile())
			{
				//System.out.println(file.getName());
				files.add(file.getName());
			}
		}			

		return files;
	}

}
