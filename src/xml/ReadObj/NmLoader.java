package xml.ReadObj;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NmLoader")
public class NmLoader {
	
	@XmlElement(name="csvBeginWTDocument")
	private List<csvBeginWTDocument> ListcsvBeginWTDocument;
	//private List<ItemsObject> itemsObject;
//	private List<ImagesObject> imagesObject;

	public List<csvBeginWTDocument> getCsvBeginWTDocument() {
		return ListcsvBeginWTDocument;
	}

	public void setCsvBeginWTDocument(List<csvBeginWTDocument> ListcsvBeginWTDocument) {
		this.ListcsvBeginWTDocument = ListcsvBeginWTDocument;
	}
	
	
}
