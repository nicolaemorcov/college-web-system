package com.kolia.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.Course;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.servlets.CourseServlet;
import com.opencsv.CSVReader;

@MultipartConfig
public class CsvHandler extends Handler{

	private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
	CourseService courseService;
	MyDBManager dbManager;
	CourseServlet courseServlet;

	public CsvHandler(MyDBManager dbManager) {
		this.courseService = new CourseService(dbManager);
		this.dbManager = dbManager;
	}
	
	@Override
	public ResponseHandler doPost(HttpServletRequest request) {
		
		
		
		String csvFile = "D:\\Tools\\Eclipse\\eclipse\\workspace\\MVCApplication\\src\\resources\\students3.csv";
		
		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while((line = reader.readNext()) != null) {
				System.out.println("Course [Name: " + line[0] + ", Length: " + line[1] + " Tuition Fee: " + line[2] + "]");
				String courseName = line[0];
	            int length = Integer.parseInt(line[1]);
	            double tuitionFee = Double.parseDouble(line[2]);
	            Course course = new Course(courseName, length, tuitionFee);
	            courseService.registerCourse(course);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseHandler();
		
//
//        Scanner scanner;
//		try {
//			scanner = new Scanner(new File(csvFile));
//			while (scanner.hasNext()) {
//	            List<String> line = parseLine(scanner.nextLine());
//	            System.out.println("Student [ID: " + line.get(0) + ", FirstName: " + line.get(1) + " LastName: " + line.get(2) + " Tuition fee: " + line.get(3) + "]");
//	            String courseName = line.get(1);
//	            int length = Integer.parseInt(line.get(2));
//	            double tuitionFee = Double.parseDouble(line.get(3));
//	            Course course = new Course(courseName, length, tuitionFee);
//	            courseService.registerCourse(course);
//	        }
//	        scanner.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//        return new ResponseHandler();
//
//    }
//
//    public static List<String> parseLine(String cvsLine) {
//        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
//    }
//
//    public static List<String> parseLine(String cvsLine, char separators) {
//        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
//    }
//
//    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {
//
//        List<String> result = new ArrayList<>();
//
//        //if empty, return!
//        if (cvsLine == null && cvsLine.isEmpty()) {
//            return result;
//        }
//
//        if (customQuote == ' ') {
//            customQuote = DEFAULT_QUOTE;
//        }
//
//        if (separators == ' ') {
//            separators = DEFAULT_SEPARATOR;
//        }
//
//        StringBuffer curVal = new StringBuffer();
//        boolean inQuotes = false;
//        boolean startCollectChar = false;
//        boolean doubleQuotesInColumn = false;
//
//        char[] chars = cvsLine.toCharArray();
//
//        for (char ch : chars) {
//
//            if (inQuotes) {
//                startCollectChar = true;
//                if (ch == customQuote) {
//                    inQuotes = false;
//                    doubleQuotesInColumn = false;
//                } else {
//
//                    //Fixed : allow "" in custom quote enclosed
//                    if (ch == '\"') {
//                        if (!doubleQuotesInColumn) {
//                            curVal.append(ch);
//                            doubleQuotesInColumn = true;
//                        }
//                    } else {
//                        curVal.append(ch);
//                    }
//
//                }
//            } else {
//                if (ch == customQuote) {
//
//                    inQuotes = true;
//
//                    //Fixed : allow "" in empty quote enclosed
//                    if (chars[0] != '"' && customQuote == '\"') {
//                        curVal.append('"');
//                    }
//
//                    //double quotes in column will hit this!
//                    if (startCollectChar) {
//                        curVal.append('"');
//                    }
//
//                } else if (ch == separators) {
//
//                    result.add(curVal.toString());
//
//                    curVal = new StringBuffer();
//                    startCollectChar = false;
//
//                } else if (ch == '\r') {
//                    //ignore LF characters
//                    continue;
//                } else if (ch == '\n') {
//                    //the end, break!
//                    break;
//                } else {
//                    curVal.append(ch);
//                }
//            }
//
//        }
//
//        result.add(curVal.toString());
//
//        return result;
	}
    }
	
