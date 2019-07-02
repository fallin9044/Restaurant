package restaurant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	private long menuId;
	private long dishId;
	private long tableId;
	private int dishState;

}
