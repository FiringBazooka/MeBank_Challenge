package com.me.transaction;

import java.io.BufferedReader;
import java.text.DecimalFormat;

public class TransactionMain {

	public static void main(String[] args) throws Exception {

		/*
		 * read input args
		 */

		// read CSV file
		BufferedReader reader = TransactionUtil.readCSV(args[0]);

		// find the balance
		OutputVO output = TransactionUtil.calculateBalance(reader, args);
		reader.close();

		System.out.println(
				"Relative balance for the period is: " + new DecimalFormat("$0.00;-$0.00").format(output.getBalance()));
		System.out.println("Number of transactions included is: " + output.getNumOfTxns());

	}

}
