package interview.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import interview.util.Action;
import interview.util.FileZipUtil;
import interview.util.Util;
import zipFile.exception.CustomJsonException;
import zipFile.exception.DataBaseException;
import zipFile.exception.DuplicateConfigException;
import interview.constants.AppConstants;
import interview.dto.ApiConfigurationDTO;
import interview.dto.StandardResponseWrapper;
import interview.dto.EmployeeSpecifyConditionReqConfig;
import interview.model.Configuration;
import interview.model.ConfigurationId;
import interview.model.exportmodel.EmployeeConfig;
import interview.model.exportmodel.EmployeeObjectMap;
import interview.model.exportmodel.EmployeeSourceMap;
import interview.repository.ConfigurationRepository;
import interview.repository.EmployeeConfigRepository;
import interview.repository.EmployeeConfigRepositoryExport;
import interview.repository.EmployeeObjectRepository;
import interview.repository.EmployeeSourceMapRepositoryExport;
import interview.service.ApiConfigService;

@Service
public class ApiConfigServiceImpl implements ApiConfigService {
	private static final Logger logger = LogManager.getLogger(ApiConfigServiceImpl.class);
	private String baseFolder = "tempspace/";

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private EmployeeConfigRepositoryExport domainConfigRepositoryExport;

	@Autowired
	private EmployeeConfigRepository domainConfigRepository;

	@Autowired
	private EmployeeObjectRepository domainObjectRepository;

	@Autowired
	private EmployeeSourceMapRepositoryExport domainSourceMapExportRepository;

	@Override
	public StandardResponseWrapper getApiConfigurationDetails() {
		try {
			List<Configuration> configuration = configurationRepository.findAll();
			return new StandardResponseWrapper(configuration, HttpStatus.OK.value(),
					AppConstants.GET_RESOURCE_LIST_SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(String.format(AppConstants.SQL_EXCEPTION_MSG,
					"for retrieving API Configuration details."));
		}
	}

	@Override
	public StandardResponseWrapper updateApiConfigurationDetails(ApiConfigurationDTO apiConfigurationDTO) {
		Optional<Configuration> configurationList = configurationRepository
				.findByNameAndKey(apiConfigurationDTO.getName(), apiConfigurationDTO.getKey());
		if (configurationList.isPresent()) {
			Configuration configuration = new Configuration();
			configuration.setConfigurationId(
					new ConfigurationId(apiConfigurationDTO.getName(), apiConfigurationDTO.getKey()));
			configurationRepository.updateValue(apiConfigurationDTO.getName(), apiConfigurationDTO.getKey(),
					apiConfigurationDTO.getValue());
			Optional<Configuration> configurations = configurationRepository
					.findByNameAndKey(apiConfigurationDTO.getName(), apiConfigurationDTO.getKey());
			return new StandardResponseWrapper(configurations, HttpStatus.CREATED.value(),
					AppConstants.API_CONFIGURATION_CREATED);
		} else {
			throw new DuplicateConfigException(
					String.format(AppConstants.DUPLICATE_API_CONFIGURATION_MESSAGE));
		}
	}

	@Override
	public EmployeeConfig getApiDomainJson(Long domainId) {
		return domainConfigRepositoryExport.getOne(domainId);
	}

	@Override
	public List<EmployeeSourceMap> getAllDomainSourceMap(Long domainId) {
		return domainSourceMapExportRepository.findAllDSM(domainId);
	}

	@Override
	public String saveFile(Long domainId, String content, String password, String requestId) {
		try {
			FileZipUtil.tempCleanUp();
			String inputDirectory = baseFolder + requestId + File.separator;
			String inputFile = inputDirectory + domainId;
			File directory = new File(inputDirectory);
			if (!directory.exists()) {
				directory.mkdir();
			}
			String privKey = "encryption.key";
			FileZipUtil.writeToFile(inputFile, content);
			return FileZipUtil.doEncryptRSAWithAES(privKey, inputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Long> importDomain(String userKey, Path path, String password, String requestId, Long protocolId,
			Long processId) {
		List<Long> domainIds = new ArrayList<>();
		try {
			FileZipUtil.tempCleanUp();
			File[] encryptedFiles = FileZipUtil.unzipPasswordProtect(password, path,
					baseFolder + requestId + File.separator + "extract");
			String pubKey = "encryption.pub";
			if (null != encryptedFiles && encryptedFiles.length > 0) {
				for (File encryptedFile : encryptedFiles) {
		try {
					
					logger.info("File to decrypt:" + encryptedFile.getAbsolutePath());
					FileZipUtil.doDecryptRSAWithAES(pubKey, encryptedFile.getAbsolutePath());
					EmployeeConfig dc = (EmployeeConfig) Util
							.getObjectFromJSONFile(encryptedFile.getAbsolutePath() + ".json", EmployeeConfig.class);
					if (domainConfigRepositoryExport.countByBusinessProcessConfigAndProtocolProcessMapAndDomainName(processId, protocolId,
							dc.getDomainName()) == 0) {
						dc = processDomainForImport(dc, userKey, protocolId, processId);
						domainIds.add(dc.getDomainId());
						logger.info("domainId : " + dc.getDomainId() + ", domain name : " + dc.getDomainName()
								+ ", protocolId : " + dc.getProtocolProcessMap() + ", processId : "
								+ dc.getBusinessProcessConfig());
					} else {
						logger.error("same name exists");
					}
				} catch (Exception e) {
					logger.error("ApiConfigServiceImpl.importDomain() has failed.");
					e.printStackTrace();
				}
				}
			}
		} catch (Exception e) {
			logger.error("ApiConfigServiceImpl.importDomain() has failed.");
			e.printStackTrace();
		}
		return domainIds;
	}

	private EmployeeConfig processDomainForImport(EmployeeConfig dc, String userKey, Long protocolId, Long processId) {
		Timestamp currentTS = new Timestamp((new Date()).getTime());
		// dc.setStatus(Status.DRAFT.getStatus());
		dc.setBusinessProcessConfig(processId);
		dc.setProtocolProcessMap(protocolId);
		dc.setInsertedBy(userKey);
		dc.setInsertedTmstmp(currentTS);
		// dc.setUpdatedBy(userKey);
		// dc.setUpdatedTmstmp(currentTS);

		Map<String, Long> dsmMappings = dc.getDomainSourceMaps().stream()
				.collect(Collectors.toMap(x -> x.getSourceOwner(), x -> x.getSourceId()));
		dc.getDomainSourceMaps().stream().forEach(x -> x.setSourceId(null));

		dc = domainConfigRepositoryExport.save(dc);

		EmployeeObjectMap domainObjectMap = (new ArrayList<EmployeeObjectMap>(dc.getDomainObjectMaps())).get(0);
		EmployeeSpecifyConditionReqConfig specificConditionConfig = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			specificConditionConfig = mapper.readValue(domainObjectMap.getObjectMap(), EmployeeSpecifyConditionReqConfig.class);
		} catch (JsonProcessingException e) {
			throw new CustomJsonException(String.format(
					"ApiConfigServiceImpl::processDomainForImport Failed to update custom value due to %s",
					e.getMessage()));
		}

		Map<Long, Long> dsmIdMap = dc.getDomainSourceMaps().stream()
				.collect(Collectors.toMap(x -> dsmMappings.get(x.getSourceOwner()), x -> x.getSourceId()));

		// update mapping with new ids
		// List<EmployeeSourceMap> sourceList =
		// domainSourceRepository.findByDomainConfigDomainId(dc.getDomainId());

		specificConditionConfig.getSpecifyConditions().stream().forEach(condition -> {
			if (condition.getSourceDataSourceId() != null)
				condition.setSourceDataSourceId(dsmIdMap.get(condition.getSourceDataSourceId()));
			if (condition.getTargetDataSourceId() != null)
				condition.setTargetDataSourceId(dsmIdMap.get(condition.getTargetDataSourceId()));
		});

		specificConditionConfig.setDomainId(dc.getDomainId());
		specificConditionConfig.setObjectMapId(domainObjectMap.getObjectMapId());
		specificConditionConfig.setAction(Action.SAVE);

		EmployeeObjectMap objectMap = new EmployeeObjectMap();
		objectMap.setObjectMapId(domainObjectMap.getObjectMapId());
		objectMap.setDomainConfig(dc);
		objectMap.setObjectMap(Util.convertObjectToJson(specificConditionConfig));
		objectMap.setInsertedTmstmp(currentTS);
		objectMap.setInsertedBy(userKey);
		objectMap.setSourceJoinCond(specificConditionConfig.getSourceJoinCondition());
		objectMap = domainObjectRepository.save(objectMap);
		return dc;
	}
}
