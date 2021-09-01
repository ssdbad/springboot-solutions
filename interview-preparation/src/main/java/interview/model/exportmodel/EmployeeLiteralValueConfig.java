package interview.model.exportmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the LITERAL_VALUE_CONFIG database table.
 * 
 */
@Entity(name = "literalValueConfig")
@Table(name = "LITERAL_VALUE_CONFIG")
public class EmployeeLiteralValueConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@Column(name = "LITERAL_VAL_ID")
	@SequenceGenerator(name = "LITERAL_VALUE_CONFIG_LITERALVALID_GENERATOR", sequenceName = "SEQ_LITERAL_VALUE_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LITERAL_VALUE_CONFIG_LITERALVALID_GENERATOR")
	@JsonIgnore
	private BigDecimal literalValId;
	
	@JoinColumn(name = "LITERAL_ID")
	@ManyToOne
	@JsonIgnore
	private EmployeeLiteralConfig literalConfig;
	
	@Lob
	@Column(name = "LITERAL_VAL")
	private String literalVal;

	@JsonIgnore
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@JsonIgnore
	@Column(name = "UPDATED_TMSTMP")
	private Timestamp updatedTmstmp;

	@JsonIgnore
	@Column(name = "INSERTED_BY", updatable = false)
	private String insertedBy;

	@JsonIgnore
	@Column(name = "INSERTED_TMSTMP", updatable = false)
	private Timestamp insertedTmstmp;

	public EmployeeLiteralConfig getLiteralConfig() {
		return literalConfig;
	}

	public void setLiteralConfig(EmployeeLiteralConfig literalConfig) {
		this.literalConfig = literalConfig;
	}

	public String getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Timestamp getInsertedTmstmp() {
		return this.insertedTmstmp;
	}

	public void setInsertedTmstmp(Timestamp insertedTmstmp) {
		this.insertedTmstmp = insertedTmstmp;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedTmstmp() {
		return this.updatedTmstmp;
	}

	public void setUpdatedTmstmp(Timestamp updatedTmstmp) {
		this.updatedTmstmp = updatedTmstmp;
	}

	public EmployeeLiteralValueConfig() {
	}

	public String getLiteralVal() {
		return this.literalVal;
	}

	public void setLiteralVal(String literalVal) {
		this.literalVal = literalVal;
	}

	public BigDecimal getLiteralValId() {
		return this.literalValId;
	}

	public void setLiteralValId(BigDecimal literalValId) {
		this.literalValId = literalValId;
	}

}