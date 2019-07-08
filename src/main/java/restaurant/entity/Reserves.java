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
@Table(name = "reserves")
public class Reserves implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "reserveId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reserveId;

	@Column(name = "tableId")
	private long tableId;

	@Column(name = "reserveTime")
	private int reserveTime;
	
	@Column(name ="reserveTele")
	private int reserveTele;

	public long getReserveId() {
		return reserveId;
	}

	public void setReserveId(long reserveId) {
		this.reserveId = reserveId;
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public int getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(int reserveTime) {
		this.reserveTime = reserveTime;
	}

	public int getReserveTele() {
		return reserveTele;
	}

	public void setReserveTele(int reserveTele) {
		this.reserveTele = reserveTele;
	}

	public Reserves() {
		super();
	}

	public Reserves(long reserveId, long tableId, int reserveTime, int reserveTele) {
		super();
		this.reserveId = reserveId;
		this.tableId = tableId;
		this.reserveTime = reserveTime;
		this.reserveTele = reserveTele;
	}

	
}
