package interview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import interview.model.Configuration;
import interview.model.ConfigurationId;

public interface ConfigurationRepository extends JpaRepository<Configuration, ConfigurationId>{

	public List<Configuration> findByName(String name);
	
	@Query(value = "SELECT * FROM configuration config WHERE UPPER(config.NAME) = UPPER(?1) AND UPPER(config.KEY) = UPPER(?2)", nativeQuery = true)
	public Optional<Configuration> findByNameAndKey(String name, String key);
	
	@Query(value = "UPDATE configuration config SET config.value = ?3 WHERE UPPER(config.NAME) = UPPER(?1) AND UPPER(config.KEY) = UPPER(?2)", nativeQuery = true)
	public void updateValue(String name, String key, String value);
}
