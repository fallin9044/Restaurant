package restaurant.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "menuId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long menuId;

	@Column(name = "dishId")
	private long dishId;

	@Column(name = "tableId")
	private long tableId;

	@Column(name = "dishState")
	private int dishState;

	public Menu() {
		super();
	}

	public Menu(long menuId, long dishId, long tableId, int dishState) {
		super();
		this.menuId = menuId;
		this.dishId = dishId;
		this.tableId = tableId;
		this.dishState = dishState;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public int getDishState() {
		return dishState;
	}

	public void setDishState(int dishState) {
		this.dishState = dishState;
	}

}
