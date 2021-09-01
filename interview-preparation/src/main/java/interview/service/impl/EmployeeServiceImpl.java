package interview.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import interview.constants.AppConstants;
import interview.dto.EmployeeBusinessDTO;
import interview.dto.StandardResponseWrapper;
import interview.model.exportmodel.EmployeeConfig;
import interview.repository.EmployeeConfigRepository;
import interview.service.EmployeeService;
import interview.util.Util;
import zipFile.exception.DataException;
import zipFile.exception.DuplicateConfigException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeConfigRepository employeeConfigRepo;
	
	@Override
	public StandardResponseWrapper saveEmployeeBusinessProcessLevel(String userKey, EmployeeBusinessDTO domainBusinessDTO) {
			
			boolean isUpdate = (domainBusinessDTO.getAction() != null
					&& domainBusinessDTO.getAction().getActionType().equalsIgnoreCase("Update")) ? true : false;
			List<EmployeeConfig> domainConfigs = new ArrayList<>();
			Optional<EmployeeConfig> optionalConfig = Optional.empty();
			EmployeeBusinessDTO previousConfig = null;
			if (isUpdate) {
				optionalConfig = employeeConfigRepo.findById(domainBusinessDTO.getDomainId());
				if (optionalConfig.isPresent()) {
					EmployeeConfig dbConfig = optionalConfig.get();
					previousConfig = Util.getPreviousDomainConfig(dbConfig);
					if (!dbConfig.getDomainName().equalsIgnoreCase(domainBusinessDTO.getDomainName())) {
						List<EmployeeConfig> searchConfig = employeeConfigRepo
								.findDuplicateDomainBusinessProcess(
										domainBusinessDTO.getDomainName(), domainBusinessDTO.getProcessId());
						if (!searchConfig.isEmpty()) {
							throw new DuplicateConfigException(AppConstants.DUPLICATE_DOMAIN_BP_MSG);
						}
					}
				}else {
					throw new DataException(String.format(AppConstants.DATA_STORE_EXCEPTION_MSG, "domain"));
				}
			}else {
				domainConfigs = employeeConfigRepo.findDuplicateDomainBusinessProcess(domainBusinessDTO.getDomainName(), domainBusinessDTO.getProcessId());
			}
			
			if (domainConfigs.isEmpty() || isUpdate) {
				EmployeeConfig domainConfig = saveOrUpdateDomainBusinessProcess(userKey, domainBusinessDTO, isUpdate,optionalConfig);
				domainBusinessDTO.setDomainId(domainConfig.getDomainId());
				return new StandardResponseWrapper(domainBusinessDTO, HttpStatus.OK.value(),
						isUpdate ? AppConstants.RESOURCE_UPDATED_MSG : AppConstants.RESOURCE_CREATED_MSG);
			}else {
				throw new DuplicateConfigException(AppConstants.DUPLICATE_DOMAIN_BP_MSG);
			}

			
		}

		private EmployeeConfig saveOrUpdateDomainBusinessProcess(String userKey, EmployeeBusinessDTO domainBusinessDTO,
				boolean updateDomainBPList,Optional<EmployeeConfig> dbConfig) {
			EmployeeConfig domainConfig = new EmployeeConfig();
			
			if (updateDomainBPList) {
				domainConfig = dbConfig.isPresent()?dbConfig.get():null;
				domainConfig.setUpdatedBy(userKey);
				domainConfig.setUpdatedTmstmp(new Timestamp((new Date()).getTime()));
			}else {
//				Optional<BusinessProcessConfig> businessProcessConfig = businessProcessRepo.findById(domainBusinessDTO.getProcessId());
//				if (businessProcessConfig.isPresent()) {
//					domainConfig.setBusinessProcessConfig(businessProcessConfig.get());
//				} else {
					throw new DataException(String.format(AppConstants.DATA_STORE_EXCEPTION_MSG, "employee process"));
				}
				domainConfig.setStatus("Draft");
				domainConfig.setInsertedBy(userKey);
				domainConfig.setInsertedTmstmp(new Timestamp((new Date()).getTime()));
//			}
			domainConfig.setDomainName(domainBusinessDTO.getDomainName());
			domainConfig.setDomainDescription(domainBusinessDTO.getDomainDescription());
			domainConfig = employeeConfigRepo.save(domainConfig);

			return domainConfig;
		}
	
}