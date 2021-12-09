package com.me.transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionUtil {

	private TransactionUtil() {

	}

	public static BufferedReader readCSV(String file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	public static OutputVO calculateBalance(BufferedReader reader, String args[]) throws IOException {

		// read input
		String accountId = args[1];

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime fromDate = LocalDateTime.parse(args[2], df);
		LocalDateTime toDate = LocalDateTime.parse(args[3], df);

		String row = "";
		double balance = 0d;
		int numberOfTxns = 0;

		// loop through the rows
		while ((row = reader.readLine()) != null) {

			String[] rowElements = row.split(",");

			LocalDateTime txnDate = LocalDateTime.parse(rowElements[3], df);

			/*
			 * Check for all transactions matching either of the accountId inside the time
			 * frame
			 * 
			 * else , check for matching accountId and if it is reversal type
			 * 
			 */

			if (((txnDate.isAfter(fromDate) || txnDate.isEqual(fromDate))
					&& (txnDate.isBefore(toDate) || txnDate.isEqual(toDate)))
					|| rowElements[5].equalsIgnoreCase("REVERSAL")) {

				if (rowElements[1].equalsIgnoreCase(accountId)) {

					switch (rowElements[5]) {

					case "PAYMENT":
						// debit
						balance -= Double.valueOf(rowElements[4]);
						numberOfTxns++;
						break;

					case "REVERSAL":
						// credit
						balance += Double.valueOf(rowElements[4]);
						numberOfTxns--;
						break;

					}

				} else if (rowElements[2].equalsIgnoreCase(accountId)) {

					switch (rowElements[5]) {

					case "REVERSAL":
						// debit
						balance -= Double.valueOf(rowElements[4]);
						numberOfTxns--;
						break;

					case "PAYMENT":
						// credit
						balance += Double.valueOf(rowElements[4]);
						numberOfTxns++;
						break;

					}
				}
			}
		}

		OutputVO output = new OutputVO();
		output.setBalance(balance);
		output.setNumOfTxns(numberOfTxns);
		return output;
	}

}
