package xml.ReadObj;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ImageObject 
{
	private List<MetaDataObject> metaDataObject;

	@XmlElement(name="Metadata")
	public List<MetaDataObject> getMetaDataObject() {
		return metaDataObject;
	}
	public void setMetaDataObject(List<MetaDataObject> metaDataObject) {
		this.metaDataObject = metaDataObject;
	}
}
