package interview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIGURATION")
public class Configuration implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfigurationId configurationId;

	@Column(name = "NAME", insertable = false, updatable = false)
	private String name;

	@Column(name = "KEY", insertable = false, updatable = false)
	private String key;

	@Column(name = "VALUE")
	private String value;

	public Configuration() {
		super();
	}

	public Configuration(ConfigurationId configurationId, String name, String key) {
		this.configurationId = configurationId;
		this.name = name;
		this.key = key;
	}

	public ConfigurationId getConfigurationId() {
		return configurationId;
	}

	public void setConfigurationId(ConfigurationId configurationId) {
		this.configurationId = configurationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Configuration [name=" + name + ", key=" + key + ", value=" + value + "]";
	}

}
