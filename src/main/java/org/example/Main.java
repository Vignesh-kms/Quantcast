package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            logger. info("Request received to process Most Active cookie by Date");
            validateArguments(args);
            String cookieLogsPath=args[1];
            LocalDate dateKey=LocalDate.parse(args[3]);
            InputStream cookieFile=Main.class.getClassLoader().getResourceAsStream(cookieLogsPath);
            BufferedReader cookieLogsReader = new BufferedReader(new InputStreamReader(cookieFile));
            logger. info("Reading "+cookieLogsPath+" file");
            cookieLogsReader.readLine();
            CookieLog cookieLog=new CookieLog();
            String logLine;
            while ((logLine=cookieLogsReader.readLine())!=null) {
                String[] logInfo=logLine.split(",");
                CookieLogEvent cookieLogEvent=new CookieLogEvent(logInfo[1],logInfo[0]);
                cookieLog.addLog(cookieLogEvent);
            }
            logger. info("Fetching most Active Cookie on "+dateKey);
            List<String> mostActiveCookies=cookieLog.getMostActiveCookie(dateKey);
            System.out.println("Most Active Cookies on "+dateKey+" are :");
            mostActiveCookies.forEach(System.out::println);
        }catch(InvalidArgumentsException invalidArgumentsException){
            System.out.println(invalidArgumentsException.getMessage());
        }catch(DateNotFoundException dateNotFoundException){
            System.out.println(dateNotFoundException.getMessage());
        }catch(IOException ioException){
            System.out.println("IOException Occurred "+ioException.getMessage());
        }catch (Exception exception){
            System.out.println("Exception Occurred "+exception.getMessage());
        }

    }
    public static void validateArguments(String[] args) throws InvalidArgumentsException{
        logger. info("Validating command line Arguments...");
        if(args.length!=4 || !args[0].equals("-f") || !args[1].contains(".csv") || !args[2].equals("-d")){
            throw new InvalidArgumentsException("InvalidArgumentException : Invalid Arguments");
        }
        logger.info("Validation Succeeded");
    }
}