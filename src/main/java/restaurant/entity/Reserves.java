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
	private Timestamp reserveTime;

	@Column(name = "reserveTele")
	private String reserveTele;

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

	public Timestamp getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getReserveTele() {
		return reserveTele;
	}

	public void setReserveTele(String reserveTele) {
		this.reserveTele = reserveTele;
	}

	public Reserves() {
		super();
	}

	public Reserves(long tableId, Timestamp reserveTime, String reserveTele) {
		super();
		this.tableId = tableId;
		this.reserveTime = reserveTime;
		this.reserveTele = reserveTele;
	}

}
