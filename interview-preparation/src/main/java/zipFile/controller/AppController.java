package zipFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import zipFile.dto.ResponseWrapper;
import zipFile.service.ExportService;
import zipFile.service.ImportService;
import zipFile.service.ZipConfigService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppController {

    public static final String baseFolder = "tempspace/";

    @Autowired
    private ZipConfigService zipConfigService;
    
    @Autowired
    private ImportService importService;

    @Autowired
    private ExportService exportService;
    
    @GetMapping(value = "/health")
    public ResponseEntity<ResponseWrapper> healthCheck(){
        ResponseWrapper healthCheckResponseWrapper = zipConfigService.healthCheck();
        return new ResponseEntity<>(healthCheckResponseWrapper,HttpStatus.OK);
    }

    @PostMapping(value = "/importFile")
    public ResponseEntity<ResponseWrapper> importZipFile(){
        ResponseWrapper response = importService.importZipFile();
        return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/exportFile")
    public ResponseEntity<ResponseWrapper> exportZipFile(){
        ResponseWrapper response = exportService.exportZipFile();
        return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
    }
}