package restaurant.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_stream")
public class OrderStream implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	@Column(name = "personId")
	private long personId;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "orderTime")
	private Timestamp orderTime;
	
	@Column(name = "tableId")
	private long tableId;

	public OrderStream() {
		super();
	}

	public OrderStream(long orderId, long personId, int total, Timestamp orderTime, long tableId) {
		super();
		this.orderId = orderId;
		this.personId = personId;
		this.total = total;
		this.orderTime = orderTime;
		this.tableId = tableId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

}
