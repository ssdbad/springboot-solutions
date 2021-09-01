package interview.model.exportmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the LITERAL_CONFIG database table.
 * 
 */
@Entity(name = "literalConfig")
@Table(name = "LITERAL_CONFIG")
public class EmployeeLiteralConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@Column(name = "LITERAL_ID")
	@SequenceGenerator(name = "LITERAL_CONFIG_LITERALID_GENERATOR", sequenceName = "SEQ_LITERAL_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LITERAL_CONFIG_LITERALID_GENERATOR")
	@JsonIgnore
	private Long literalId;

	@JoinColumn(name = "DOMAIN_OBJECT_ID")
	@ManyToOne
	@JsonIgnore
	private EmployeeObjectConfig domainObjectConfig;

	@Column(name = "ALIAS_NAME")
	private String aliasName;

	@Column(name = "LITERAL_NAME")
	private String literalName;

	@Column(name = "UI_COMPONENT")
	private String uiComponent;

	@Column(name = "SOURCE_DATATYPE")
	private String sourceDatatype;

	@Column(name = "SOURCE_FORMAT")
	private String sourceFormat;

	@Column(name = "FORMAT_CHECK_FLG")
	private String formatCheckFlg;

	@Column(name = "RULE_LITERAL_FLG")
	private String ruleLiteralFlg;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EXCLUDE_FOR_MULE")
	private String excludeForMule;

	@Column(name = "TARGET_FORMAT")
	private String targetFormat;

	// bi-directional many-to-one association to EmployeeLiteralValueConfig
	@OneToMany(mappedBy = "literalConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeLiteralValueConfig> literalValueConfigs;

	// bi-directional many-to-one association to EmployeeLiteralValueConfig
	@OneToMany(mappedBy = "literalConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeLiteralCustomConfig> literalCustomConfig;

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

	public EmployeeLiteralConfig() {
	}

	public Set<EmployeeLiteralCustomConfig> getLiteralCustomConfig() {
		return literalCustomConfig;
	}

	public void setLiteralCustomConfig(Set<EmployeeLiteralCustomConfig> literalCustomConfig) {
		this.literalCustomConfig = literalCustomConfig;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuleLiteralFlg() {
		return ruleLiteralFlg;
	}

	public void setRuleLiteralFlg(String ruleLiteralFlg) {
		this.ruleLiteralFlg = ruleLiteralFlg;
	}

	public Long getLiteralId() {
		return this.literalId;
	}

	public void setLiteralId(Long literalId) {
		this.literalId = literalId;
	}

	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getLiteralName() {
		return this.literalName;
	}

	public void setLiteralName(String literalName) {
		this.literalName = literalName;
	}

	public String getUiComponent() {
		return this.uiComponent;
	}

	public void setUiComponent(String uiComponent) {
		this.uiComponent = uiComponent;
	}

	public String getSourceDatatype() {
		return sourceDatatype;
	}

	public void setSourceDatatype(String sourceDatatype) {
		this.sourceDatatype = sourceDatatype;
	}

	public String getSourceFormat() {
		return sourceFormat;
	}

	public void setSourceFormat(String sourceFormat) {
		this.sourceFormat = sourceFormat;
	}

	public Set<EmployeeLiteralValueConfig> getLiteralValueConfigs() {
		return this.literalValueConfigs;
	}

	public void setLiteralValueConfigs(Set<EmployeeLiteralValueConfig> literalValueConfigs) {
		this.literalValueConfigs = literalValueConfigs;
	}

	public String getFormatCheckFlg() {
		return formatCheckFlg;
	}

	public void setFormatCheckFlg(String formatCheckFlg) {
		this.formatCheckFlg = formatCheckFlg;
	}

	public String getTargetFormat() {
		return targetFormat;
	}

	public void setTargetFormat(String targetFormat) {
		this.targetFormat = targetFormat;
	}

	public String getExcludeForMule() {
		return excludeForMule;
	}

	public void setExcludeForMule(String excludeForMule) {
		this.excludeForMule = excludeForMule;
	}

	@PrePersist
	private void prePersist() {
		literalCustomConfig.forEach(c -> {
			c.setLiteralConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
		literalValueConfigs.forEach(c -> {
			c.setLiteralConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
	}
}