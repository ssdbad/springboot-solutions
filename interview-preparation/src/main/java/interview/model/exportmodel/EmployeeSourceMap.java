package interview.model.exportmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Base64;
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
 * The persistent class for the DOMAIN_SOURCE_MAP database table.
 * 
 */
@Entity(name = "domainSourceMap")
@Table(name = "DOMAIN_SOURCE_MAP")
public class EmployeeSourceMap implements Serializable {

	private static final long serialVersionUID = -368985035026969413L;

	@Id
	@SequenceGenerator(name = "DOMAIN_SOURCE_MAP_SOURCEID_GENERATOR", sequenceName = "SEQ_DOMAIN_SOURCE_MAP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_SOURCE_MAP_SOURCEID_GENERATOR")
	@Column(name = "SOURCE_ID")
	private Long sourceId;

	@Column(name = "SOURCE_NAME")
	private String sourceName;

	@Column(name = "SOURCE_OWNER")
	private String sourceOwner;

	@Column(name = "SOURCE_PASSWORD")
	private String sourcePassword;

	@Column(name = "SOURCE_PATH")
	private String sourcePath;

	@Column(name = "SOURCE_TYPE")
	private String sourceType;

	@Column(name = "SOURCE_URL")
	private String sourceUrl;

	@Column(name = "SOURCE_USER_NAME")
	private String sourceUserName;

	@Column(name = "CATEGORY_TYPE")
	private String categoryType;

	// bi-directional many-to-one association to EmployeeObjectConfig
	@OneToMany(mappedBy = "domainSourceMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeObjectConfig> domainObjectConfigs;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private EmployeeConfig domainConfig;

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

	public EmployeeSourceMap() {
	}

	public Long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceOwner() {
		return this.sourceOwner;
	}

	public void setSourceOwner(String sourceOwner) {
		this.sourceOwner = sourceOwner;
	}

	public String getSourcePassword() {

		String sourcePassword = new String(Base64.getDecoder().decode(this.sourcePassword));
		return sourcePassword;
	}

	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = Base64.getEncoder().encodeToString(sourcePassword.getBytes());
	}

	public String getSourcePath() {
		return this.sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUserName() {
		return this.sourceUserName;
	}

	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Set<EmployeeObjectConfig> getDomainObjectConfigs() {
		return this.domainObjectConfigs;
	}

	public void setDomainObjectConfigs(Set<EmployeeObjectConfig> domainObjectConfigs) {
		this.domainObjectConfigs = domainObjectConfigs;
	}

	@PrePersist
	private void prePersist() {
		domainObjectConfigs.forEach(c -> {
			c.setDomainSourceMap(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
	}
}