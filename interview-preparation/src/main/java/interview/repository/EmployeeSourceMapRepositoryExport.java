package interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import interview.model.exportmodel.EmployeeSourceMap;


@Repository
public interface EmployeeSourceMapRepositoryExport extends JpaRepository<EmployeeSourceMap, Long>{
	
	@Query(value = "SELECT DSM FROM DOMAIN_SOURCE_MAP DSM WHERE DSM.DOMAIN_ID = :domainId", nativeQuery = true)
	List<EmployeeSourceMap> findAllDSM(Long domainId);
	
}