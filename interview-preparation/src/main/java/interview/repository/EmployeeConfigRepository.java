package interview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import interview.model.exportmodel.*;
import interview.dto.EmployeeStatusDto;

@Repository
public interface EmployeeConfigRepository extends JpaRepository<EmployeeConfig, Long> {

	public List<EmployeeConfig> findByBusinessProcessConfigProcessIdAndProtocolProcessMapProtocolProcessId(long processId,
			long protocolId);

	@Query(value = "SELECT count(1) FROM DOMAIN_CONFIG DC WHERE  DC.PROCESS_ID= ?1 and  DC.PROTOCOL_PROCESS_ID= ?2 and DC.DOMAIN_NAME = ?3", nativeQuery = true)
	public int countExistingDomainNameForProcessProtocol(
			long processId, long protocolId, String domainName);

	@Query(value = "SELECT * FROM DOMAIN_CONFIG DC WHERE DC.STATUS = ?1 AND DC.PROCESS_ID= ?2 ORDER BY NVL(DC.UPDATED_TMSTMP, DC.INSERTED_TMSTMP) DESC", nativeQuery = true)
	public List<EmployeeConfig> findDomainConfigs(String status, long processId);

	@Query(value = "SELECT * FROM domain_config dc WHERE UPPER(dc.DOMAIN_NAME) = UPPER(?1) AND dc.PROCESS_ID = ?2", nativeQuery = true)
	public List<EmployeeConfig> findDuplicateDomainBusinessProcess(String domainName, Long processId);

	@Query(value = "SELECT * FROM domain_config dc WHERE UPPER(dc.DOMAIN_NAME) = UPPER(?1) AND dc.PROCESS_ID = ?2 AND dc.PROTOCOL_PROCESS_ID = ?3", nativeQuery = true)
	public List<EmployeeConfig> findDuplicateDomainBusinessProcessProtocol(String domainName, Long processId,
			Long protocolProcessId);

	public List<EmployeeConfig> findByinsertedBy(String userId);

	public List<EmployeeConfig> findByInsertedByAndProtocolProcessMapProtocolProcessId(String userKey, Long protocolId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE domain_config dc SET dc.STATUS =?2 WHERE DOMAIN_ID = ?1 ", nativeQuery = true)
	public int updateDomainStatus(Long domainId, String status);

	@Query(value = "SELECT  dc.DOMAIN_ID domainId, dc.domain_name domainName, dc.status status " + " FROM \r\n"
			+ "  DOMAIN_CONFIG dc,\r\n" + "  BUSINESS_PROCESS_CONFIG bp\r\n"
			+ "WHERE bp.PROCESS_ID         = dc.PROCESS_ID\r\n" + "AND  dc.PROTOCOL_PROCESS_ID IS NULL\r\n"
			+ "AND  dc.PROCESS_ID = :processId "
			+ "ORDER BY NVL(dc.UPDATED_TMSTMP, dc.INSERTED_TMSTMP ) DESC ", nativeQuery = true)
	public List<EmployeeStatusDto> findDomainByProcess(long processId);

	@Query(value = "SELECT dc.DOMAIN_ID domainId, dc.domain_name domainName, dc.status status" + " FROM \r\n"
			+ "  DOMAIN_CONFIG dc,\r\n" + "  BUSINESS_PROCESS_CONFIG bp,\r\n" + "  PROTOCOL_PROCESS_MAP ppm\r\n"
			+ "WHERE bp.PROCESS_ID         = dc.PROCESS_ID\r\n"
			+ "AND dc.PROTOCOL_PROCESS_ID = ppm.PROTOCOL_PROCESS_ID\r\n"
			+ "AND ppm.PROTOCOL_PROCESS_ID = :protocolProcessId "
			+ "ORDER BY NVL(dc.UPDATED_TMSTMP, dc.INSERTED_TMSTMP) DESC ", nativeQuery = true)
	public List<EmployeeStatusDto> findDomainByProtocol(long protocolProcessId);

	public EmployeeConfig findByDomainNameIgnoreCaseAndBusinessProcessConfigProcessId(String domainName, Long processId);

	public EmployeeConfig findByDomainNameIgnoreCaseAndBusinessProcessConfigProcessIdAndProtocolProcessMapProtocolProcessId(
			String domainName, Long processId, Long protocolId);
}
