package xml.ReadObj;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PartsList")
public class PartListObject 
{
	private List<MetaDataObject> metaDataObject;
	private List<ItemsObject> itemsObject;
	private List<ImagesObject> imagesObject;
	
	@XmlElement(name="Metadata")
	public List<MetaDataObject> getMetaDataObject() {
		return metaDataObject;
	}
	public void setMetaDataObject(List<MetaDataObject> metaDataObject) {
		this.metaDataObject = metaDataObject;
	}
	
	@XmlElement(name="Items")
	public List<ItemsObject> getItemsObject() {
		return itemsObject;
	}
	public void setItemsObject(List<ItemsObject> itemsObject) {
		this.itemsObject = itemsObject;
	}
	
	@XmlElement(name="Images")
	public List<ImagesObject> getImagesObject() {
		return imagesObject;
	}
	public void setImagesObject(List<ImagesObject> imagesObject) {
		this.imagesObject = imagesObject;
	}
	
	
}
