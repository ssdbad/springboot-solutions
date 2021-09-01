package interview.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import interview.constants.AppConstants;
import interview.util.FileZipUtil;
import interview.util.Util;
import interview.dto.ApiConfigurationDTO;
import interview.dto.EmployeeBusinessDTO;
import interview.dto.StandardResponseWrapper;
import interview.service.ApiConfigService;
import interview.service.EmployeeService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestApplicationController {
	private static final Logger logger = LogManager.getLogger(TestApplicationController.class);
	
	public static final String X_AUTH_USER_QID = "x-auth-user-qid";
	public static final String baseFolder = "tempspace/";
	
	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
				"Accept,Origin,Content-Type,X-Amz-Date,X-Requested-With,Authorization,X-Api-Key,X-Amz-Security-Token,X-Amz-User-Agent");
	}

	@Autowired
	private ApiConfigService apiConfigService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(value = "save/domain/process")
	public ResponseEntity<StandardResponseWrapper> saveEmployeeBusinessProcess(@RequestHeader(X_AUTH_USER_QID) String userKey,
			@RequestBody EmployeeBusinessDTO domainBusinessDTO) {
		StandardResponseWrapper response = employeeService.saveEmployeeBusinessProcessLevel(userKey,domainBusinessDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation("Get details about API configuration")
	@GetMapping(value = "get/api/configuration/details")
	public ResponseEntity<StandardResponseWrapper> getApiConfigurationDetails(@RequestHeader(X_AUTH_USER_QID) String userKey) {
		StandardResponseWrapper response = apiConfigService.getApiConfigurationDetails();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation("Update Api Configuration Value { Name & Keys are non-updatable fields}. Please contact DBA for new entries.")
	@PutMapping(value = "update/api/configuration/details")
	public ResponseEntity<StandardResponseWrapper> updateApiConfigurationDetails(@RequestHeader(X_AUTH_USER_QID) String userKey,
			@Valid @RequestBody ApiConfigurationDTO apiConfigurationDTO) {
		StandardResponseWrapper response = apiConfigService.updateApiConfigurationDetails(apiConfigurationDTO);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	@ApiOperation("Get domain JSON")
	@GetMapping(value = "exportDomainJson")
	public ResponseEntity<Resource> exportDomainJson(Long domainIds[], String password) throws Exception {
		String requestId = Util.generateRandomString();
		ArrayList<File> fileList = new ArrayList<>();
		for (Long domainId : domainIds) {
			String jsonContent = Util.convertObjectToJson(apiConfigService.getApiDomainJson(domainId));
			fileList.add(new File(apiConfigService.saveFile(domainId, jsonContent, password, requestId)));
		}
		String zipFile = baseFolder + requestId + File.separator + requestId + ".zip";
		FileZipUtil.zipPasswordProtect(password, fileList, zipFile);
		File fileToReturn = new File(zipFile);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(fileToReturn));
		return ResponseEntity.ok().contentLength(fileToReturn.length())
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + requestId + ".zip")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@PostMapping("/importDomainJson")
	public ResponseEntity<StandardResponseWrapper> importDomainJson(@RequestHeader(X_AUTH_USER_QID) String userKey,
			@RequestParam("uploadFile") MultipartFile file, Long protocolId, Long processId, String password) {
		try {
			String requestId = Util.generateRandomString();
			if (file.isEmpty())
				logger.info("file is empty");
			else
				logger.info("file has content");
			File directory = new File(baseFolder + requestId + File.separator);
			if (!directory.exists()) {
				directory.mkdir();
			}
			Path copyLocation = Paths.get(
					directory.getAbsolutePath() + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			List<Long> newDomainIds = apiConfigService.importDomain(userKey, copyLocation, password, requestId, protocolId,
					processId);
			Map<String, Object> map = new HashMap<>(2);
			map.put("domainIds", newDomainIds);
			return new ResponseEntity<>(
					new StandardResponseWrapper(map, HttpStatus.OK.value(), AppConstants.RESOURCE_SENT),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Issue in controller while importing", e);
		}
		return new ResponseEntity<>(new StandardResponseWrapper(null, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				AppConstants.RESOURCE_SENT), HttpStatus.OK);

	}
	//Employee is a domain
	//
}