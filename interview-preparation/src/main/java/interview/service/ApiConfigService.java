package interview.service;

import java.nio.file.Path;
import java.util.List;

import interview.model.exportmodel.EmployeeConfig;
import interview.model.exportmodel.EmployeeSourceMap;
import interview.dto.ApiConfigurationDTO;
import interview.dto.StandardResponseWrapper;

public interface ApiConfigService {
	StandardResponseWrapper getApiConfigurationDetails();

	StandardResponseWrapper updateApiConfigurationDetails(ApiConfigurationDTO apiConfigurationDTO);

	EmployeeConfig getApiDomainJson(Long domainId);

	String saveFile(Long domainId, String content, String password, String requestId);

	List<Long> importDomain(String userKey, Path path, String password, String requestId, Long protocolId, Long processId);
	
	List<EmployeeSourceMap> getAllDomainSourceMap(Long domainId);
}
