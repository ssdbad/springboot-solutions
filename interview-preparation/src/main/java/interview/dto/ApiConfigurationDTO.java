package interview.dto;

import javax.validation.constraints.NotNull;

public class ApiConfigurationDTO {
	@NotNull
	private String name;
	@NotNull
	private String key;
	@NotNull
	private String value;

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
}