package com.indix.listner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

public class Reporter implements IReporter {

	@Override
	public void generateReport(List xmlSuites, List suites,
			String outputDirectory) {
		System.out.println("*****Custom Report******");
		List<ISuite> suitesList = suites;
		for (ISuite suite : suitesList) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			Set<String> keySet = suiteResults.keySet();
			for (String eachKey : keySet) {
				ISuiteResult eachResult = suiteResults.get(eachKey);
				ITestContext testContext = eachResult.getTestContext();
				ITestNGMethod[] methods = testContext.getAllTestMethods();
				IResultMap passedTests = testContext.getPassedTests();
				IResultMap failedTests = testContext.getFailedTests();
				IResultMap skippedTests = testContext.getSkippedTests();
				System.out.println("Total Passed :" + passedTests.size());
				System.out.println("Total Failed :" + failedTests.size());
				System.out.println("Total Skipped :" + skippedTests.size());
				/*System.out.println(testContext.getStartDate());
				System.out.println(testContext.getEndDate());*/
								int totalNumberOfTestCases=passedTests.size()+failedTests.size()+skippedTests.size();
				double averageResponsetime=(double) getExecutionTime(testContext.getStartDate(),
						testContext.getEndDate())/(double)totalNumberOfTestCases;
				System.out.println("Average Response Time "+averageResponsetime);
				System.out.println("Error Percentage "+((double) failedTests.size())/totalNumberOfTestCases);
				System.out.println("Throughput "+totalNumberOfTestCases/(double) getExecutionTime(testContext.getStartDate(),
						testContext.getEndDate())+" req/sec");
			}
		}
	}

	private Long getExecutionTime(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		return ((end.getTimeInMillis() - start.getTimeInMillis()) / (1000));

	}
}
