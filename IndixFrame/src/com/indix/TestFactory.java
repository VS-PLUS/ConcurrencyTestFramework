package com.indix;

import org.testng.annotations.Factory;

public class TestFactory {
	private int numberOfUsers = 1000;

	@Factory
	public Object[] factoryMethod() {
		Object[] obj = new Object[numberOfUsers];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = new RestTest();
		}
		return obj;
	}

}
