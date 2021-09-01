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
 * The persistent class for the DOMAIN_OBJECT_CONFIG database table.
 * 
 */
@Entity(name = "domainObjectConfig")
@Table(name = "DOMAIN_OBJECT_CONFIG")
public class EmployeeObjectConfig implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DOMAIN_OBJECT_CONFIG_DOMAINOBJECTID_GENERATOR", sequenceName = "SEQ_DOMAIN_OBJECT_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_OBJECT_CONFIG_DOMAINOBJECTID_GENERATOR")
	@Column(name = "DOMAIN_OBJECT_ID")
	@JsonIgnore
	private Long domainObjectId;

	@JoinColumn(name = "SOURCE_ID")
	@ManyToOne
	@JsonIgnore
	private EmployeeSourceMap domainSourceMap;

	@Column(name = "OBJECT_NAME")
	private String objectName;

	// bi-directional many-to-one association to EmployeeLiteralConfig
	@OneToMany(mappedBy = "domainObjectConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeLiteralConfig> literalConfigs;

	// bi-directional many-to-one association to ObjectFilterConfig
	@OneToMany(mappedBy = "domainObjectConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ObjectFilterConfig> objectFilterConfigs;

	@Column(name = "UPDATED_BY")
	@JsonIgnore
	private String updatedBy;

	@Column(name = "UPDATED_TMSTMP")
	@JsonIgnore
	private Timestamp updatedTmstmp;

	@Column(name = "INSERTED_BY", updatable = false)
	@JsonIgnore
	private String insertedBy;

	@Column(name = "INSERTED_TMSTMP", updatable = false)
	@JsonIgnore
	private Timestamp insertedTmstmp;

	public EmployeeSourceMap getDomainSourceMap() {
		return domainSourceMap;
	}

	public void setDomainSourceMap(EmployeeSourceMap domainSourceMap) {
		this.domainSourceMap = domainSourceMap;
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

	public EmployeeObjectConfig() {
	}

	public Long getDomainObjectId() {
		return this.domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Set<EmployeeLiteralConfig> getLiteralConfigs() {
		return this.literalConfigs;
	}

	public void setLiteralConfigs(Set<EmployeeLiteralConfig> literalConfigs) {
		this.literalConfigs = literalConfigs;
	}

	public Set<ObjectFilterConfig> getObjectFilterConfigs() {
		return this.objectFilterConfigs;
	}

	public void setObjectFilterConfigs(Set<ObjectFilterConfig> objectFilterConfigs) {
		this.objectFilterConfigs = objectFilterConfigs;
	}

	@PrePersist
	private void prePersist() {
		literalConfigs.forEach(c -> {
			c.setDomainObjectConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
		objectFilterConfigs.forEach(c -> {
			c.setDomainObjectConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
	}
}