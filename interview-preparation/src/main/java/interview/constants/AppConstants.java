package interview.constants;

import java.util.regex.Pattern;

public class AppConstants {

	public static final String RESOURCE_SENT = "Requested resource is sent in the response";
	public static final String GET_RESOURCE_LIST_SUCCESS = "Resource list is successfully retrieved";
	public static final String SQL_EXCEPTION_MSG = "Exception occured while performing the action %s";
	public static final String API_CONFIGURATION_CREATED = "Configuration value is updated and saved successfully.";
	public static final String DUPLICATE_API_CONFIGURATION_MESSAGE = "Configuration name and key combination already exists.";
	public static final String REGEX_SUFFIX = "_[0-9]{1,9}";
	public static final String FUN_TO_STRING = ".toString()";
	public static final String DATA_STORE_EXCEPTION_MSG = "Could not save data as dependent %s could not be found";
	public static final String DUPLICATE_DOMAIN_BP_MSG = "Domain name for this Business Process already exists";
	
	public static final Pattern PTRN_BIGDECIMAL 				 = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static final String RESOURCE_CREATED_MSG = "Requested resource has been created successfully";
	public static final String RESOURCE_UPDATED_MSG = "Requested resource has been updated successfully";
}