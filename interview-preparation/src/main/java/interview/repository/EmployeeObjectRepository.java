package interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import interview.model.exportmodel.EmployeeObjectMap;


@Repository
public interface EmployeeObjectRepository extends JpaRepository<EmployeeObjectMap, Long> {

	@Query(value = "SELECT * FROM DOMAIN_OBJECT_MAP dom WHERE (doc.DOMAIN_ID) = UPPER(?1)", nativeQuery = true)
	public List<EmployeeObjectMap> findSourceJoinCond(long domainId);

	public EmployeeObjectMap findByDomainConfigDomainId(long domainId);
}
