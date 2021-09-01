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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the DOMAIN_CONFIG database table.
 * 
 */
@Entity(name = "domainConfigExport")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "DOMAIN_CONFIG")
public class EmployeeConfig implements Serializable{
	private static final long serialVersionUID = 7936127920157744865L;

	@Id
	@Column(name = "DOMAIN_ID")
	@SequenceGenerator(name = "DOMAIN_CONFIG_DOMAINID_GENERATOR", sequenceName = "SEQ_DOMAIN_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_CONFIG_DOMAINID_GENERATOR")
	@JsonIgnore
	private Long domainId;

	@Column(name = "DOMAIN_DESCRIPTION")
	private String domainDescription;

	@Column(name = "DOMAIN_NAME")
	private String domainName;

	@Column(name = "STATUS")
	private String status;

	// bi-directional many-to-one association to EmployeeObjectMap
	@OneToMany(mappedBy = "domainConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeObjectMap> domainObjectMaps;

	// bi-directional many-to-one association to EmployeeSourceMap
	@OneToMany(mappedBy = "domainConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EmployeeSourceMap> domainSourceMaps;

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

	@Column(name = "PROCESS_ID")
	private Long businessProcessConfig;

	@Column(name = "PROTOCOL_PROCESS_ID")
	private Long protocolProcessMap;

	public Long getBusinessProcessConfig() {
		return this.businessProcessConfig;
	}

	public void setBusinessProcessConfig(Long businessProcessConfig) {
		this.businessProcessConfig = businessProcessConfig;
	}

	public Long getProtocolProcessMap() {
		return this.protocolProcessMap;
	}

	public void setProtocolProcessMap(Long protocolProcessMap) {
		this.protocolProcessMap = protocolProcessMap;
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

	public EmployeeConfig() {
	}

	public Long getDomainId() {
		return this.domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public String getDomainDescription() {
		return this.domainDescription;
	}

	public void setDomainDescription(String domainDescription) {
		this.domainDescription = domainDescription;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<EmployeeObjectMap> getDomainObjectMaps() {
		return this.domainObjectMaps;
	}

	public void setDomainObjectMaps(Set<EmployeeObjectMap> domainObjectMaps) {
		this.domainObjectMaps = domainObjectMaps;
	}

	public Set<EmployeeSourceMap> getDomainSourceMaps() {
		return this.domainSourceMaps;
	}

	public void setDomainSourceMaps(Set<EmployeeSourceMap> domainSourceMaps) {
		this.domainSourceMaps = domainSourceMaps;
	}

	@PrePersist
	private void prePersist() {
		domainObjectMaps.forEach(c -> {
			c.setDomainConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
		domainSourceMaps.forEach(c -> {
			c.setDomainConfig(this);
			c.setInsertedBy(this.getInsertedBy());
			c.setInsertedTmstmp(this.getInsertedTmstmp());
			// c.setUpdatedBy(this.getUpdatedBy());
			// c.setUpdatedTmstmp(this.getUpdatedTmstmp());
		});
	}
}
