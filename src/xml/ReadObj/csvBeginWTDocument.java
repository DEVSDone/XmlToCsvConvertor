package xml.ReadObj;



import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "csvBeginWTDocument")
public class csvBeginWTDocument {

	@XmlAttribute(name="csvname")
	private String csvname;
	
	public String getCsvname() {
		return csvname;
	}
	public void setCsvname(String csvname) {
		this.csvname = csvname;
	}
/*	@XmlAttribute(name="csvtitle")
	private String csvtitle;
	@XmlAttribute(name="csvnumber")
	private String csvnumber;
	@Override
	public String toString() {
		return "csvBeginWTDocument [csvname=" + csvname + ", csvtitle=" + csvtitle + ", csvnumber=" + csvnumber
				+ ", csvtype=" + csvtype + ", csvdescription=" + csvdescription + ", csvdepartment=" + csvdepartment
				+ ", csvsaveIn=" + csvsaveIn + ", csvteamTemplate=" + csvteamTemplate + ", csvdomain=" + csvdomain
				+ ", csvlifecycletemplate=" + csvlifecycletemplate + ", csvlifecyclestate=" + csvlifecyclestate
				+ ", csvtypedef=" + csvtypedef + ", csvversion=" + csvversion + ", csviteration=" + csviteration
				+ ", csvsecurityLabels=" + csvsecurityLabels + "]";
	}
	@XmlAttribute(name="csvtype")
	private String csvtype;
	@XmlAttribute(name="csvdescription")
	private String csvdescription;
	@XmlAttribute(name="csvdepartment")
	private String csvdepartment;
	@XmlAttribute(name="csvsaveIn")
	private String csvsaveIn;
	@XmlAttribute(name="csvteamTemplate")
	private String csvteamTemplate;
	@XmlAttribute(name="csvdomain")
	private String csvdomain;
	@XmlAttribute(name="csvlifecycletemplate")
	private String csvlifecycletemplate;
	@XmlAttribute(name="csvlifecyclestate")
	private String csvlifecyclestate;
	@XmlAttribute(name="csvtypedef")
	private String csvtypedef;
	@XmlAttribute(name="csvversion")
	private String csvversion;
	@XmlAttribute(name="csviteration")
	private String csviteration;
	@XmlAttribute(name="csvsecurityLabels")
	private String csvsecurityLabels;
	
	public String getCsvname() {
		return csvname;
	}
	public void setCsvname(String csvname) {
		this.csvname = csvname;
	}
	public String getCsvtitle() {
		return csvtitle;
	}
	public void setCsvtitle(String csvtitle) {
		this.csvtitle = csvtitle;
	}
	public String getCsvnumber() {
		return csvnumber;
	}
	public void setCsvnumber(String csvnumber) {
		this.csvnumber = csvnumber;
	}
	public String getCsvtype() {
		return csvtype;
	}
	public void setCsvtype(String csvtype) {
		this.csvtype = csvtype;
	}
	public String getCsvdescription() {
		return csvdescription;
	}
	public void setCsvdescription(String csvdescription) {
		this.csvdescription = csvdescription;
	}
	public String getCsvdepartment() {
		return csvdepartment;
	}
	public void setCsvdepartment(String csvdepartment) {
		this.csvdepartment = csvdepartment;
	}
	public String getCsvsaveIn() {
		return csvsaveIn;
	}
	public void setCsvsaveIn(String csvsaveIn) {
		this.csvsaveIn = csvsaveIn;
	}
	public String getCsvteamTemplate() {
		return csvteamTemplate;
	}
	public void setCsvteamTemplate(String csvteamTemplate) {
		this.csvteamTemplate = csvteamTemplate;
	}
	public String getCsvdomain() {
		return csvdomain;
	}
	public void setCsvdomain(String csvdomain) {
		this.csvdomain = csvdomain;
	}
	public String getCsvlifecycletemplate() {
		return csvlifecycletemplate;
	}
	public void setCsvlifecycletemplate(String csvlifecycletemplate) {
		this.csvlifecycletemplate = csvlifecycletemplate;
	}
	public String getCsvlifecyclestate() {
		return csvlifecyclestate;
	}
	public void setCsvlifecyclestate(String csvlifecyclestate) {
		this.csvlifecyclestate = csvlifecyclestate;
	}
	public String getCsvtypedef() {
		return csvtypedef;
	}
	public void setCsvtypedef(String csvtypedef) {
		this.csvtypedef = csvtypedef;
	}
	public String getCsvversion() {
		return csvversion;
	}
	public void setCsvversion(String csvversion) {
		this.csvversion = csvversion;
	}
	public String getCsviteration() {
		return csviteration;
	}
	public void setCsviteration(String csviteration) {
		this.csviteration = csviteration;
	}
	public String getCsvsecurityLabels() {
		return csvsecurityLabels;
	}
	public void setCsvsecurityLabels(String csvsecurityLabels) {
		this.csvsecurityLabels = csvsecurityLabels;
	}
	
	*/
 
}
