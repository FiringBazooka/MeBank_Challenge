# MeBank_Challenge

The goal of the challenge is to implement a system that analyses financial transaction records.

This is a maven project versioned with Jdk 1.8

# Run via command line

```
mvn install
java -jar <jarFile> <path to csv file> <accountId> "<startDate>" "<EndDate>"

java -jar target\ME_Challenge-0.0.1-SNAPSHOT.jar C:\Users\sbhattip\Desktop\test.csv ACC778899 "20/10/2018 12:00:00" "21/10/2018 19:00:00"
```

# Expected output

Relative balance for the period is: $1027.25

Number of transactions included is: 3

