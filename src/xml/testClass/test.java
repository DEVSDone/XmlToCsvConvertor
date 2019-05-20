package xml.testClass;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class test

{
	public static void main(String[] args) 
	{

		   String name = "asfsa.xml.meta";
          String extension = name.substring(name.lastIndexOf("."));
          System.out.println("File Ext. :"+extension);
	/*	int fileProcessCount= 8232;
		for(int i= 1;i<=fileProcessCount ;i++)
		if(i%1000 == 0)
		{
			System.out.println(" i: "+i);
		}else if(i == fileProcessCount)
		{
			
			
				System.out.println(" i: "+i);
			
		}*/
		
		
/*		Set<String[]> allItems = new LinkedHashSet<String[]>();

		String s ="(OR (AND (PRODUCT 'BRCS0421BD_PH') (OR (DATE_RANGE '2008-05-26 00:00:00.0' '2008-09-05 00:00:00.0')"
				+ " (DATE_RANGE '2004-09-28 00:00:00.0' '2005-11-09 00:00:00.0') (DATE_RANGE '2008-05-26 00:00:00.0' '2008-09-05 00:00:00.0') "
				+ "(DATE_RANGE '1901-01-01 00:00:00.0' '2004-09-28 00:00:00.0') (DATE_RANGE '2004-09-28 00:00:00.0' '2008-05-26 00:00:00.0') "
				+ "(DATE_RANGE '2004-09-28 00:00:00.0' '2005-11-09 00:00:00.0') (DATE_RANGE '2004-09-28 00:00:00.0' '2008-05-26 00:00:00.0')))"
				+ " (AND (PRODUCT 'BRCS0182BE_PH') (DATE_RANGE '1901-01-01 00:00:00.0' '2004-09-28 00:00:00.0')) (AND (PRODUCT 'BRCS0301BD_PH')"
				+ " (OR (DATE_RANGE '2004-09-28 00:00:00.0' '2005-11-09 00:00:00.0') (DATE_RANGE '2004-09-28 00:00:00.0' '2008-05-26 00:00:00.0') "
				+ "(DATE_RANGE '2008-05-26 00:00:00.0' '2008-09-05 00:00:00.0') (DATE_RANGE '2004-09-28 00:00:00.0' '2005-11-09 00:00:00.0')"
				+ " (DATE_RANGE '1901-01-01 00:00:00.0' '2004-09-28 00:00:00.0') (DATE_RANGE '2008-05-26 00:00:00.0' '2008-09-05 00:00:00.0')"
				+ " (DATE_RANGE '2004-09-28 00:00:00.0' '2008-05-26 00:00:00.0')))";

		String[] sSplit = s.split("PRODUCT");

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

					//	System.out.println(":Date SPlit  :"+string2);
						String[] foreachPrdDateRanges =new String[3];  //For each se_eff 
						
						String[] dates = string2.split(" ");
						System.out.println(dates.length);
						String fromDate = dates[1];
						String toDate = dates[3];
						foreachPrdDateRanges[0]=productName ;
						foreachPrdDateRanges[1]=fromDate.replaceAll("[\\',)]", "");
						foreachPrdDateRanges[2]=toDate.replaceAll("[\\',)]", "");

						allItems.add(foreachPrdDateRanges);

					}
				}
			}
		}
		System.out.println("###################################");
			Iterator<String[]> itr = allItems.iterator();
		while(itr.hasNext())
		{
				String val[] =itr.next();
				System.out.println(":: "+val[0]+": "+val[1]+" : "+val[2]);
		}

*/
	}

}
