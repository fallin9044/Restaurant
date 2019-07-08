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
@Table(name = "dining")
public class Dining implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "tableId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tableId;

	@Column(name = "tableNum")
	private int tableNum;

	@Column(name = "tableState")
	private int tableState;


	public Dining() {
		super();
	}

	public Dining(long tableId, int tableNum, int tableState) {
		super();
		this.tableId = tableId;
		this.tableNum = tableNum;
		this.tableState = tableState;
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public int getTableState() {
		return tableState;
	}

	public void setTableState(int tableState) {
		this.tableState = tableState;
	}

}
