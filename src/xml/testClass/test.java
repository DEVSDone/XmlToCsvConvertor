package xml.testClass;


import org.apache.commons.lang3.StringUtils;

public class test

{
	public static void main(String[] args) 
	{

		String   se_Eff = "(OR (AND (PRODUCT 'YKHHGBJ1CYF') (INSTANCE 'SFPM851140' 'SFPM850990')) (AND (PRODUCT 'YCUL0120EC46XCB') (INSTANCE '2NTM002437' '2LTM001975' '2LTM001972' '2LTM001974' '2FVM003749' '2GTM001239' '2HTM001559' '2LTM001973')) (AND (PRODUCT 'YVAA0153BAV46AA') (INSTANCE '2FXM011981' '2FXM011980'))";

		String[] sSplit = se_Eff.split("PRODUCT");
		for (String string : sSplit) 
		{
			if(string.contains("INSTANCE"))
			{
				System.out.println("main SPlit :"+string+":");
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
						System.out.println("productName:"+productName+" :INSTANCE :"+singleInstName);
					}
				}
			}
		}
	}

}
