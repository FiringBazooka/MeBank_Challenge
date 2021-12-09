package com.me.transaction;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class TransactionMainTest {

	String[] args = new String[4];

	@Before
	public void setup() {
		args[0] = "src/main/resources/test.csv";
		args[1] = "ACC334455";
		args[2] = "20/10/2018 12:00:00";
		args[3] = "21/10/2018 19:00:00";

	}

	@Test
	public void testMain() throws Exception {
		TransactionMain.main(args);
	}

	@Test(expected = FileNotFoundException.class)
	public void testInvalidCSV() throws Exception {

		args[0] = "src/main/resources/invalid.csv";
		TransactionMain.main(args);

	}
}
