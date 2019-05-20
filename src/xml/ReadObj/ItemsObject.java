package xml.ReadObj;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class ItemsObject 
{
	private List<ItemObject> itemList;
	
	@XmlElement(name="Item")
	public List<ItemObject> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemObject> itemList) {
		this.itemList = itemList;
	}
	
}
