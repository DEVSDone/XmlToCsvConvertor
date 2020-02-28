package xml.main;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import xml.ReadObj.NmLoader;
import xml.ReadObj.csvBeginWTDocument;


public class XmlToCsv {

	static Set<String[]> allItems = new LinkedHashSet<String[]>();
	static String folderPath="E:/ptc/WC11_0/Windchill/src/loadFiles"; //TODO :Comment this while creating jar
	
	static Path path = FileSystems.getDefault().getPath(".").toAbsolutePath(); //Original value for path :Takes the path where runnable jar is placed.

	//static String folderPath = path.toString(); // :Temporary commented ...:Remove Comment while creating jar .

	public static void main(String[] args) 
	{
		System.out.println("Path: "+folderPath);
		LocalDateTime start = LocalDateTime.now();  
		System.out.println("## Start Time: "+start.toString());

		XmlToCsv();  //This function modified for Image tag from PartList Item

		//Below function for xml and Meta file conversion 
		//XmlAndMetaToCsv();
		LocalDateTime end = LocalDateTime.now();  
		System.out.println("## End Time: "+end.toString());
	}
	private static void XmlToCsv()
	{
		try
		{
			int fileProcessCount = 1 ;
			ArrayList<String> fileNames = getAllFilesFromFolder(folderPath);
			for(String fname:fileNames)
			{
				String fileExtension = fname.substring(fname.lastIndexOf("."));				
				if(!fileExtension.equals(".xml") )
				{
					continue;
				}

				System.out.println("################## File in Process : "+fileProcessCount +" File Name : "+fname);

				String currentFile =folderPath+"/"+fname;			

				File xmlfile = new File(currentFile);

				JAXBContext jaxbContext = JAXBContext.newInstance(NmLoader.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				NmLoader nmLoader = (NmLoader) jaxbUnmarshaller.unmarshal(xmlfile);
				List<csvBeginWTDocument> listOfWTDoc = nmLoader.getCsvBeginWTDocument();
		//		List<MetaDataObject> metaObjList = prtListObj.getMetaDataObject();
		//		List<ItemsObject> itemsObjList = prtListObj.getItemsObject();

				String partListNumber =" " ;  //PartList number 
System.out.println(listOfWTDoc.size());
			/*	//For loop for PartList Metadata
				for (csvBeginWTDocument DataObject : listOfWTDoc)
				{
					//System.out.println("Meta Info :"+metaDataObject.getId());
					System.out.println("WTDoc Details: ");
					System.out.println(":"+DataObject.get);					
				}
*/
			}
		} catch (Exception e)
		{
			e.printStackTrace();
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
