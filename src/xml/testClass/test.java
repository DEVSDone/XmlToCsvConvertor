package xml.testClass;


import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import xml.ReadObj.ItemObject;
import xml.ReadObj.ItemsObject;
import xml.ReadObj.MetaDataObject;
import xml.ReadObj.PartListObject;
import xml.ReadObj.Property;
import xml.main.MainApp;

public class test
{
	static String path = "D:/JCI project/XMLToCsvConversion/Testing";  //Testing purpose variable:
	static String folderPath = path.toString();
	static Set<String[]> testSet = new LinkedHashSet<String[]>();
	
	public static void main(String[] args) 
	{

		ArrayList<String> fileNames = MainApp.getAllFilesFromFolder(folderPath);
		
		for(String fname:fileNames)
		{
			String currentFile =folderPath+"/"+fname;			
			System.out.println("File :"+currentFile);
			
			File xmlfile = new File(currentFile);
			try 
			{
/*				JAXBContext jaxbContext = JAXBContext.newInstance(PartListObject.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				PartListObject prtListObj = (PartListObject) jaxbUnmarshaller.unmarshal(xmlfile);
				List<MetaDataObject> metaObjList = prtListObj.getMetaDataObject();
				List<ItemsObject> itemsObjList = prtListObj.getItemsObject();*/


			//	Property customer = new Property("number", "COMPRESSOR & GASKETS (10241K32128300)");

				File file = new File("D:\\file.xml");
				JAXBContext jaxbContextW = JAXBContext.newInstance(Property.class);
				Marshaller jaxbMarshaller = jaxbContextW.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "US-ASCII");
				 CharacterEscapeHandler escapeHandler = new CharacterEscapeHandler() {
					
					@Override
					public void escape(char[] buf, int start, int len, boolean isAttValue,
							Writer out) throws IOException {
							for (int i = start; i < start + len; i++) {
							char ch = buf[i];
							out.write(ch);
							}
							}
				} ;
				 jaxbMarshaller.setProperty("com.sun.xml.bind.characterEscapeHandler", escapeHandler);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(customer, file);
				jaxbMarshaller.marshal(customer, System.out);
				
				/*	for (MetaDataObject metaDataObject : metaObjList)
				{
					//System.out.println("Meta Info :"+metaDataObject.getId());
					if(metaDataObject.getId().contains("PartList_"))
					{
						List<Property> propertyList = metaDataObject.getPropertyList();				
						for (Property propertyObject : propertyList) 
						{
							System.out.println("Propery Info: "+propertyObject.getToken());

						}
					}
				}*/

			/*	for (ItemsObject itemsObject : itemsObjList) 
				{
					List<ItemObject> itemList = itemsObject.getItemList();
					if( (!(itemList == null) ) )
					{
						for (ItemObject itemObject : itemList) 
						{
							List<MetaDataObject> itemMetadataList = itemObject.getMetaDataObject();
							for (MetaDataObject metaDataObject : itemMetadataList)
							{
								//System.out.println("Meta Info :"+metaDataObject.getId());								
								if(metaDataObject.getId().contains("WTPart_"))
								{
									List<Property> propertyList = metaDataObject.getPropertyList();				
									for (Property propertyObject : propertyList) 
									{
										System.out.println("Propery Info: "+propertyObject.getToken());
										//System.out.println("propertyObject.getValue(): "+propertyObject.getValue());
										
										if(propertyObject.getValue().toString().contains("&"))
										{
											System.out.println("propertyObject.getValue(): "+propertyObject.getValue());
										}

									}
								}
							}
						}
					}*/
				//}
			} catch (JAXBException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
