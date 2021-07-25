package zipFile.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import zipFile.dto.HealthCheckResponse;
import zipFile.dto.ResponseWrapper;
import zipFile.service.ZipConfigService;
import zipFile.util.ZipConstants;

@Service
public class ZipConfigServiceImpl implements ZipConfigService{

    @Override
    public ResponseWrapper healthCheck() {
        HealthCheckResponse responseDTO = new HealthCheckResponse();
        responseDTO.setHealthCheck("Checked");
        responseDTO.setHealthStatus("Success");
        responseDTO.setHealthAppId(1001L);
        return new ResponseWrapper(responseDTO, HttpStatus.OK.value(), ZipConstants.SUCCESS_CREATED);
    }
    
}
