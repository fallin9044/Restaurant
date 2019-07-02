package restaurant.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "dishId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long dishId;

	@Column(name = "dishName")
	private String dishName;

	@Column(name = "dishPrice")
	private int dishPrice;

	@Column(name = "dishDesc")
	private String dishDesc;

	@Column(name = "dishPicture")
	private Blob dishPicture;

	@Column(name = "isrecommend")
	private int isrecommend;

	public Dish() {
		super();
	}

	public Dish(long dishId, String dishName, int dishPrice, String dishDesc, Blob dishPicture, int isrecommend) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishDesc = dishDesc;
		this.dishPicture = dishPicture;
		this.isrecommend = isrecommend;
	}

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(int dishPrice) {
		this.dishPrice = dishPrice;
	}

	public String getDishDesc() {
		return dishDesc;
	}

	public void setDishDesc(String dishDesc) {
		this.dishDesc = dishDesc;
	}

	public Blob getDishPicture() {
		return dishPicture;
	}

	public void setDishPicture(Blob dishPicture) {
		this.dishPicture = dishPicture;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

}
