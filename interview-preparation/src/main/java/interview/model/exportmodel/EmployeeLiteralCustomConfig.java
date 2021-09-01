package interview.model.exportmodel;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the LITERAL_CUSTOM_CONFIG database table.
 * 
 */
@Entity(name = "literalCustomConfig")
@Table(name = "LITERAL_CUSTOM_CONFIG")
public class EmployeeLiteralCustomConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LITERAL_CUSTOM_CONFIG_LITERALCUSTOMID_GENERATOR", sequenceName = "SEQ_LITERAL_CUSTOM_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LITERAL_CUSTOM_CONFIG_LITERALCUSTOMID_GENERATOR")
	@Column(name = "LITERAL_CUSTOM_ID")
	@JsonIgnore
	private Long literalCustomId;

	@JoinColumn(name = "LITERAL_ID")
	@ManyToOne
	@JsonIgnore
	private EmployeeLiteralConfig literalConfig;

	@Column(name = "ORDER_BY")
	private String orderBy;

	@Column(name = "PARTITION_BY")
	private String partitionBy;

	@Column(name = "SQL_FORMAT")
	private String sqlFormat;

	@Column(name = "SQL_FUNCTION")
	private String sqlFunction;

	@Column(name = "DERIVATION_LOGIC")
	private String derivationLogic;

	@Column(name = "CUSTOM_LITERAL_NAME")
	private String customLiteralName;

	@Column(name = "CUSTOM_ALIAS_NAME")
	private String customAliasName;

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

	public EmployeeLiteralCustomConfig() {
	}

	public Long getLiteralCustomId() {
		return this.literalCustomId;
	}

	public void setLiteralCustomId(Long literalCustomId) {
		this.literalCustomId = literalCustomId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPartitionBy() {
		return this.partitionBy;
	}

	public void setPartitionBy(String partitionBy) {
		this.partitionBy = partitionBy;
	}

	public String getSqlFormat() {
		return this.sqlFormat;
	}

	public void setSqlFormat(String sqlFormat) {
		this.sqlFormat = sqlFormat;
	}

	public String getSqlFunction() {
		return this.sqlFunction;
	}

	public void setSqlFunction(String sqlFunction) {
		this.sqlFunction = sqlFunction;
	}

	public String getDerivationLogic() {
		return derivationLogic;
	}

	public void setDerivationLogic(String derivationLogic) {
		this.derivationLogic = derivationLogic;
	}

	public String getCustomLiteralName() {
		return customLiteralName;
	}

	public void setCustomLiteralName(String customLiteralName) {
		this.customLiteralName = customLiteralName;
	}

	public String getCustomAliasName() {
		return customAliasName;
	}

	public void setCustomAliasName(String customAliasName) {
		this.customAliasName = customAliasName;
	}
}