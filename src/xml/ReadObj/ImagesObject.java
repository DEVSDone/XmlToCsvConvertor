package xml.ReadObj;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ImagesObject 
{

	private List<ImageObject> imageList;
	
	@XmlElement(name="Image")
	public List<ImageObject> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageObject> imageList) {
		this.imageList = imageList;
	}
}
