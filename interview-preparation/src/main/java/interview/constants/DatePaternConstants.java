package interview.constants;

import java.util.regex.Pattern;

public class DatePaternConstants {
	public static final Pattern PTRN_YYYY_MM_DDT_HH_MM     		 = Pattern.compile("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}T([0-9]{1,2}|-):([0-9]{1,2}|-):([0-9]{1,2}|-)\\+[0-9]{1,2}:[0-9]{1,2}");
	public static final Pattern PTRN_YYYY_00_00_00_HH_MM   		 = Pattern.compile("[0-9]{1,4}----T-:-:-\\+[0-9]{1,2}:[0-9]{1,2}");
	public static final Pattern PTRN_YYYY                  		 = Pattern.compile("[0-9]{1,4}");
	public static final Pattern PTRN_YYYY_MM_DD_HH_mm_ss_S 		 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}");
	public static final Pattern PTRN_DD_MMM_YY_HH_mm_ss_00000000 = Pattern.compile("^\\d{2}-[A-Z]{1,3}-\\d{2} \\d{2}.\\d{2}.\\d{2}\\.\\d{9} ([AP]{1}[M])");
	public static final Pattern PTRN_YYYYMMDD   				 = Pattern.compile("\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|[3][01])");
	public static final Pattern PTRN_YYYYDDMM   				 = Pattern.compile("\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|[3][01])");
	public static final Pattern PTRN_DDMMYYYY   				 = Pattern.compile("(0[1-9]|[12][0-9]|[3][01])(0[1-9]|1[012])\\d{4}");
	public static final Pattern PTRN_YYYY_MM_DD 				 = Pattern.compile("\\d{4} (0[1-9]|1[012]) (0[1-9]|[12][0-9]|[3][01])");
	public static final Pattern PTRN_DD_S_MM_S_YYYY 			 = Pattern.compile("(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}");
	public static final Pattern PTRN_DD_MMM_YYYY 				 = Pattern.compile("(0[1-9]|[12][0-9]|[3][01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{4}");
	public static final Pattern PTRN_DD_SP_MMM_SP_YYYY 			 = Pattern.compile("(0[1-9]|[12][0-9]|[3][01]) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC) \\d{4}");
	public static final Pattern PTRN_SYSTEM_TIMESTAMP 			 = Pattern.compile("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2} ([0-9]{1,2}|-):([0-9]{1,2}|-):([0-9]{1,2}|-).\\d{1,3}");
	public static final Pattern PTRN_BIGDECIMAL 				 = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static final Pattern PTRN_DD_MMM_YY                   = Pattern.compile("(0[1-9]|[12][0-9]|[3][01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{2}");
	
	public static final String  FMT_DD_MM_YYYY 					 = "dd/MM/yyyy";
	public static final String  FMT_YYYY_MM_DD_HH_mm_ss_S 		 = "yyyy-MM-dd HH:mm:ss.S";
	public static final String  FMT_DD_MMM_YY_HH_mm_ss_00000000  = "dd-MMM-yy HH.mm.ss.SSSSSSSSS a";
	public static final String  FMT_YYYYMMDD 				   	 = "yyyymmdd";
	public static final String  FMT_YYYYDDMM  					 = "yyyyddmm";
	public static final String  FMT_DDMMYYYY  					 = "ddmmyyyy";
	public static final String  FMT_YYYY_MM_DD 					 = "yyyy mm dd";
	public static final String  FMT_DD_MMM_YYYY  				 = "dd-MMM-yyyy";
	public static final String  FMT_DD_SP_MMM_SP_YYYY  			 = "dd MMM yyyy";
	public static final String  FMT_SYSTEM_TIMESTAMP             = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String  FMT_DD_MMM_YY                   = "dd-MMM-yy";
}