package info.tiefenauer.jeeexample.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PERSON")
@XmlRootElement
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3728801329064894614L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "zip")
	private int zip;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@PrePersist
	protected void onPersist(){
		createdAt = new Timestamp(System.currentTimeMillis());
	}
	
	@PreUpdate
	protected void onUpdate(){
		setUpdated(new Timestamp(System.currentTimeMillis()));
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String detail) {
		this.firstName = detail;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String category) {
		this.lastName = category;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String location) {
		this.street = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String subject) {
		this.city = subject;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Timestamp getCreated() {
		return createdAt;
	}

	public void setCreated(Timestamp created) {
		this.createdAt = created;
	}

	public Timestamp getUpdated() {
		return updatedAt;
	}

	public void setUpdated(Timestamp updated) {
		this.updatedAt = updated;
	}

}