package com.in28minutes.spring.basics.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScanPersonDao {
	@Autowired
	private ScanJdbcConnection scanJdbcConnection;

	public ScanJdbcConnection getScanJdbcConnection() {
		return scanJdbcConnection;
	}

	public void setScanJdbcConnection(ScanJdbcConnection scanJdbcConnection) {
		this.scanJdbcConnection = scanJdbcConnection;
	}
}