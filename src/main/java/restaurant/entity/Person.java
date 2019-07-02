package restaurant.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

/**
 *
 * @author tianfushan
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "personId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "personName")
	private String personName;

	@Column(name = "password")
	private String password;

	@Column(name = "sex")
	private int sex;

	@Column(name = "personTele")
	private String personTele;

	@Column(name = "authority")
	private int authority;

	@Column(name = "iswork")
	private int iswork;

	@Column(name = "personState")
	private int personState;

	@Column(name = "personPicture")
	private Blob personPicture;

	public Person() {
		super();
	}

	public Person(Long id, String personName, String password, int sex, String personTele, int authority, int iswork,
			int personState, Blob personPicture) {
		super();
		this.id = id;
		this.personName = personName;
		this.password = password;
		this.sex = sex;
		this.personTele = personTele;
		this.authority = authority;
		this.iswork = iswork;
		this.personState = personState;
		this.personPicture = personPicture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPersonTele() {
		return personTele;
	}

	public void setPersonTele(String personTele) {
		this.personTele = personTele;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getIswork() {
		return iswork;
	}

	public void setIswork(int iswork) {
		this.iswork = iswork;
	}

	public int getPersonState() {
		return personState;
	}

	public void setPersonState(int personState) {
		this.personState = personState;
	}

	public Blob getPersonPicture() {
		return personPicture;
	}

	public void setPersonPicture(Blob personPicture) {
		this.personPicture = personPicture;
	}

}
