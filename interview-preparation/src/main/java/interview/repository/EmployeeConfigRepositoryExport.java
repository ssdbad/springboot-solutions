package interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import interview.model.exportmodel.EmployeeConfig;

@Repository
public interface EmployeeConfigRepositoryExport extends JpaRepository<EmployeeConfig, Long> {
    public int countByBusinessProcessConfigAndProtocolProcessMapAndDomainName(Long processId, Long protocolId,
            String domainName);
}
