package interview.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import interview.constants.AppConstants;
import interview.dto.EmployeeBusinessDTO;
import interview.model.exportmodel.EmployeeConfig;
import oracle.jdbc.OracleTypes;
import zipFile.exception.CustomJsonException;
import zipFile.exception.DataBaseException;

public class Util {
	
	private static final Logger logger = LogManager.getLogger(Util.class);
	
	private static final String EMPTY = "";

	public static String generateRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
	
	public static String convertObjectToJson(Object request) {
		String jsonFormat = EMPTY;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			jsonFormat = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
		} catch (JsonProcessingException e) {
			logger.error("Util::convertObjectToJson exception occured {} ", e.getMessage());
			throw new CustomJsonException(e.getMessage());
		}
		return jsonFormat;
	}

	public static <T> T getObjectFromJSONFile(String filePath, Class<T> className) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File initialFile = new File(filePath);
		InputStream targetStream = new FileInputStream(initialFile);
		return mapper.readValue(targetStream, className);
	}
	
	/**
	 * @param values
	 * @return
	 */
	public static String convertListToString(List<String> values) {
		String strValue = EMPTY;
		if (!CollectionUtils.isEmpty(values)) {
			strValue = values.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "[", "]"));
		}
		return strValue;
	}
	
	/**
	 * @param stringValue
	 * @return
	 */
	public static List<String> convertStringToList(String stringValue) {
		List<String> stringList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			stringList = mapper.readValue(stringValue, new TypeReference<List<String>>() {
			});
		} catch (JsonProcessingException e) {
			logger.error("Util::convertStringToList exception occured {} ", e.getMessage());
			throw new CustomJsonException(e.getMessage());
		}
		return stringList;
	}
	
	/**
	 * @param plainString
	 * @return
	 */
	public static JsonNode convertFlatStringToJsonObject(String key, String value) {
		JsonNode node = null;
		try {
			String joinDetail = "{\"" + key + "\":" + "\"" + value + "\"}";
			ObjectMapper mapper = new ObjectMapper();
			node = mapper.readTree(joinDetail);
		} catch (JsonProcessingException e) {
			logger.error("Util::convertFlatStringToJsonObject exception occured {} ", e.getMessage());
			throw new CustomJsonException(e.getMessage());
		}
		return node;
	}
	
	public static String getLoggerMessage(String className, String methodName, String exception, String operation) {
		StringBuilder loggerMessage = new StringBuilder();
		loggerMessage.append("Exception occured in ").append(className).append("::").append(methodName)
				.append(" during ").append(operation).append("->").append(exception);
		return loggerMessage.toString();
	}

	/**
	 * @param value
	 * @param dataType
	 * @return
	 */
	public static String wrapDataAsPerDataType(String value, String dataType) {
		StringBuilder wrappedValue = new StringBuilder();
		String condValue = null;
		if (!dataType.equalsIgnoreCase("int")) {
			condValue = value.contains("$")
					? value.replace("$", "").replaceAll(AppConstants.REGEX_SUFFIX, "").replaceAll("#", "").toUpperCase()
					: "\"" + value.replace("$", "").replaceAll("#", "").toUpperCase() + "\"";
		} else {
			condValue = value.replace("$", "") + "";
		}
		wrappedValue.append(condValue);
		return wrappedValue.toString();
	}
	
	/**
	 * @return
	 */
	public static String getSystemDate() {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime localDate = LocalDateTime.now();
		return localDate.format(pattern);
	}
	
	public static String getSystemDateTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		return instant.toString();
	}
	
	public static String getDateMinusDay(String date, String days) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateTime = LocalDate.parse(date, pattern);
		LocalDate subtractedDate = dateTime.minusDays(Long.valueOf(days));
		return subtractedDate.format(pattern);
	}

	public static String getDatePlusDay(String date, String days) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateTime = LocalDate.parse(date, pattern);
		LocalDate subtractedDate = dateTime.plusDays(Long.valueOf(days));
		return subtractedDate.format(pattern);
	}

	public static List<String> extractDateComponent(String strDate) {
		StringBuilder stringDt = new StringBuilder(strDate);
		List<String> comps = new ArrayList<>();
		if (strDate.contains("$")) {
			comps.add(stringDt.substring(stringDt.indexOf("(") + 1, stringDt.indexOf(")")));
		} else {
			comps.add(" ");
		}
		if (strDate.contains("sysdate") || strDate.contains("SYSDATE")) {
			comps.add("true");
		} else {
			comps.add("false");
		}
		if (strDate.contains("-") || strDate.contains("+")) {
			comps.add(strDate.contains("-") ? "-" : "+");
			comps.add(strDate.contains("-") ? getNumericCharacter(strDate, "-") : getNumericCharacter(strDate, "+"));
		} else {
			comps.add("");
			comps.add("");
		}
		return comps;
	}
	
	public static String getDaysFromDate(String date) {
		String defaultVal = "0";
		if (date.contains("-")) {
			return date.substring(date.lastIndexOf("-") + 1, date.length()).trim();
		} else if (date.contains("+")) {
			return date.substring(date.lastIndexOf("+") + 1, date.length()).trim();
		}
		return defaultVal;
	}
	
	private static String getNumericCharacter(String strValue, String operator) {
		String stringDigit = strValue.substring(strValue.indexOf(operator) + 1, strValue.length());
		StringBuilder number = new StringBuilder();
		for (char ch : stringDigit.toCharArray()) {
			if (Character.isDigit(ch)) {
				number.append(ch);
			}
		}
		return number.toString();
	}

	public static boolean isDigit(String strDigit) {
		String str = "\\d+";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(strDigit);
		return m.matches();
	}

	public static StringBuilder removeToStringMethod(StringBuilder caseTemplate, String seprator) {
		if (caseTemplate.toString().split("toString()").length > 1
				&& caseTemplate.lastIndexOf(AppConstants.FUN_TO_STRING) != -1) {
			caseTemplate.delete(caseTemplate.lastIndexOf(AppConstants.FUN_TO_STRING), caseTemplate.indexOf(seprator));
		}
		return caseTemplate;
	}
	
	public static boolean clearRuleData(JdbcTemplate jdbcTemplate, String step, Long reuleId, String userId)
			throws Exception {
		logger.info("Inside Util::clearRuleData with params step {}, reuleId {}, userId {}", step, reuleId, userId);
		boolean result = false;
		try {
			List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR),
					new SqlParameter(Types.LONGNVARCHAR), new SqlParameter(Types.VARCHAR));

			jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cs = con.prepareCall("{call Proc_Rule_Steps_Cleanup(?,?,?)}");
					cs.setString(1, step);
					cs.setLong(2, reuleId);
					cs.setString(3, userId);
					return cs;
				}
			}, parameters);
			result = true;
		} catch (Exception e) {
			logger.error("Util::clearRuleDataSP Invocation Failed with message {}", e.getMessage(), e);
			throw new DataBaseException(String.format("Cleanup invocation failed due to : %s", e.getMessage()));
		}
		logger.info("Exiting Util::clearRuleDataSP with params step {}, reuleId {}, userId {}", step, reuleId, userId);
		return result;
	}
	
	public static boolean UserCleanupProc(JdbcTemplate jdbcTemplate, Long userId) throws Exception {
		logger.info("Inside Util::clear User Access data with params userId {}", userId);
		boolean result = false;

		List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NUMERIC),
				new SqlOutParameter("O_Status", Types.NUMERIC));
		try {
			jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cs = con.prepareCall("{call TestUser_Cleanup(?,?)}");//resource file path
					cs.setLong(1, userId);
					cs.registerOutParameter(2, OracleTypes.NUMERIC);
					return cs;
				}
			}, parameters);
			result = true;
		} catch (Exception e) {
			logger.error("Util::UserCleanupProc Invocation Failed with message {}", e.getMessage(), e);
			throw new DataBaseException(String.format("Cleanup invocation failed due to : %s", e.getMessage()));
		}
		logger.info("Exiting Util::UserCleanupProc with parameter userId {}", userId);
		return result;
	}

	public static EmployeeBusinessDTO getPreviousDomainConfig(EmployeeConfig domainConfig) {
		EmployeeBusinessDTO domainDTO = new EmployeeBusinessDTO();
		domainDTO.setDomainName(domainConfig.getDomainName());
		domainDTO.setDomainDescription(domainConfig.getDomainDescription());
//		domainDTO.setProcessId(domainConfig.getBusinessProcessConfig().getProcessId());
		if(null != domainConfig.getProtocolProcessMap()) {
//			domainDTO.setProtocolProcessId(domainConfig.getProtocolProcessMap().getProtocolProcessId());
		}
		return domainDTO;
	}
}
