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
 * The persistent class for the DOMAIN_OBJECT_MAP database table.
 * 
 */
@Entity(name = "domainObjectMapExport")
@Table(name = "DOMAIN_OBJECT_MAP")
public class EmployeeObjectMap implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@Column(name = "OBJECT_MAP_ID")
	@SequenceGenerator(name = "DOMAIN_OBJECT_MAP_OBJECTMAPID_GENERATOR", sequenceName = "SEQ_DOMAIN_OBJECT_MAP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_OBJECT_MAP_OBJECTMAPID_GENERATOR")
	@JsonIgnore
	private Long objectMapId;

	@Column(name = "OBJECT_MAP")
	private String objectMap;

	@Column(name = "SOURCE_JOIN_COND")
	private String sourceJoinCond;

	@Column(name = "SOURCE_CONNECT_VAL")
	private String sourceConnectVal;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private EmployeeConfig domainConfig;

	@JsonIgnore
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@JsonIgnore
	@Column(name = "UDPATED_TMSTMP")
	private Timestamp updatedTmstmp;

	@JsonIgnore
	@Column(name = "INSERTED_BY", updatable = false)
	private String insertedBy;

	@JsonIgnore
	@Column(name = "INSERTED_TMSTMP", updatable = false)
	private Timestamp insertedTmstmp;

	public EmployeeConfig getDomainConfig() {
		return domainConfig;
	}

	public void setDomainConfig(EmployeeConfig domainConfig) {
		this.domainConfig = domainConfig;
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

	public EmployeeObjectMap() {
	}

	public Long getObjectMapId() {
		return this.objectMapId;
	}

	public void setObjectMapId(Long objectMapId) {
		this.objectMapId = objectMapId;
	}

	public String getObjectMap() {
		return this.objectMap;
	}

	public void setObjectMap(String objectMap) {
		this.objectMap = objectMap;
	}

	public String getSourceJoinCond() {
		return sourceJoinCond;
	}

	public void setSourceJoinCond(String sourceJoinCond) {
		this.sourceJoinCond = sourceJoinCond;
	}

	public String getSourceConnectVal() {
		return sourceConnectVal;
	}

	public void setSourceConnectVal(String sourceConnectVal) {
		this.sourceConnectVal = sourceConnectVal;
	}
}