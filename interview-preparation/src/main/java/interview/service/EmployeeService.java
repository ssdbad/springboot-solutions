package interview.service;

import interview.dto.EmployeeBusinessDTO;
import interview.dto.StandardResponseWrapper;

public interface EmployeeService {
	public StandardResponseWrapper saveEmployeeBusinessProcessLevel(String userKey, EmployeeBusinessDTO domainBusinessDTO);
}