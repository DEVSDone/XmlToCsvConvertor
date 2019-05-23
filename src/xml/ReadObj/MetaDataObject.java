package xml.ReadObj;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class MetaDataObject
{
	private String partListNumber;
	private String id;
	private List<Property> propertyList;
	
	@XmlElement(name="number")
	public String getPartListNumber() {
		return partListNumber;
	}

	
	public void setPartListNumber(String partListNumber) {
		this.partListNumber = partListNumber;
	}
	@XmlAttribute(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name="Property")
	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}


}
