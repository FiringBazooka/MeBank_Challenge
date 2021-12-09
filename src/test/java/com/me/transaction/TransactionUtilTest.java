package com.me.transaction;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionUtilTest {

	String[] args = new String[4];

	@Before
	public void setup() {
		args[0] = "src/main/resources/test.csv";
		args[1] = "ACC334455";
		args[2] = "20/10/2018 12:00:00";
		args[3] = "20/10/2018 19:00:00";

	}

	@Test
	public void testCSV() throws IOException {
		BufferedReader reader = TransactionUtil.readCSV(args[0]);
		Assert.assertTrue(reader.ready());
	}

	@Test
	public void testBalance() throws IOException {

		OutputVO output = TransactionUtil.calculateBalance(TransactionUtil.readCSV(args[0]), args);
		Assert.assertEquals("Account balance was not correct. ",-25d, output.getBalance(),0);
		Assert.assertEquals("Number of Txns was not correct. ", 1, output.getNumOfTxns());

	}

}
