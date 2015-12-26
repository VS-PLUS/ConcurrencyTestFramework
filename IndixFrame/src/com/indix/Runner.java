package com.indix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class Runner {
	public static int numberOfUser = 1;
	public static long testDuration = 300000;
	public static int delayBetweenRequests = 50000;

	public static void main(String args[]) {
		String commandLineValues = "";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-n")) {
				System.out.println("Test" + args[i + 1]);
				numberOfUser = Integer.parseInt(args[i + 1]);
			} else if (args[i].equals("-p")) {
				System.out.println("Test" + args[i + 1]);
				testDuration = Long.parseLong(args[i + 1]);
			} else if (args[i].equals("-d")) {
				System.out.println("Test" + args[i + 1]);
				delayBetweenRequests = Integer.parseInt(args[i + 1]);
			} else if (args[i].equals("-i")) {
				System.out.println("Test" + args[i + 1]);
			}
		}
		System.out.println(commandLineValues);
		Long currentTime = System.currentTimeMillis() + testDuration + 10L;
		XmlSuite suite = new XmlSuite();
		suite.setName("suite1");
		suite.setTimeOut(Long.toString(testDuration));
		suite.setVerbose(1);
		suite.setThreadCount(numberOfUser);
		suite.setDataProviderThreadCount(numberOfUser);
		suite.setParallel(ParallelMode.METHODS);
		XmlTest test = new XmlTest(suite);
		test.setName("Regression");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.indix.TestFactory"));
		test.setXmlClasses(classes);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		suite.addListener("com.indix.listner.Reporter");
		while (System.currentTimeMillis() < currentTime) {
			System.out.println(System.currentTimeMillis());
			System.out.println(currentTime);
			TestNG tng = new TestNG();
			test.setName("Regression" + System.currentTimeMillis());
			tng.setXmlSuites(suites);
			tng.run();
		}
	}

}
