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

@Entity(name = "objectFilterConfig")
@Table(name = "OBJECT_FILTER_CONFIG")
public class ObjectFilterConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@Column(name = "OBJECT_FILTER_ID")
	@SequenceGenerator(name = "OBJECT_FILTER_CONFIG_OBJECT_FILTER_ID_GENERATOR", sequenceName = "SEQ_OBJECT_FILTER_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBJECT_FILTER_CONFIG_OBJECT_FILTER_ID_GENERATOR")
	@JsonIgnore
	private Long objectFilterId;

	@JoinColumn(name = "DOMAIN_OBJECT_ID")
	@ManyToOne
	@JsonIgnore
	private EmployeeObjectConfig domainObjectConfig;
	
	@Column(name = "FIELD_NAME")
	private String fieldName;

	@Column(name = "ALLIAS_NAME")
	private String aliasName;

	@Column(name = "UI_COMPONENT")
	private String uiComponent;

	@Column(name = "SOURCE_FORMAT")
	private String sourceFormat;

	@Column(name = "FORMAT_CHECK_FLG")
	private String formatCheckFlag;

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

	public EmployeeObjectConfig getDomainObjectConfig() {
		return domainObjectConfig;
	}

	public void setDomainObjectConfig(EmployeeObjectConfig domainObjectConfig) {
		this.domainObjectConfig = domainObjectConfig;
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

	public ObjectFilterConfig() {
	}

	public Long getObjectFilterId() {
		return this.objectFilterId;
	}

	public void setObjectFilterId(Long objectFilterId) {
		this.objectFilterId = objectFilterId;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getUiComponent() {
		return uiComponent;
	}

	public void setUiComponent(String uiComponent) {
		this.uiComponent = uiComponent;
	}

	public String getSourceFormat() {
		return sourceFormat;
	}

	public void setSourceFormat(String sourceFormat) {
		this.sourceFormat = sourceFormat;
	}

	public String getFormatCheckFlag() {
		return formatCheckFlag;
	}

	public void setFormatCheckFlag(String formatCheckFlag) {
		this.formatCheckFlag = formatCheckFlag;
	}

}