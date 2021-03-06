package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Student {
	String JdbcURL = "jdbc:mysql://localhost:3306/studentdb";
    String Username = "root";
    String password = "root";
    Connection con = null;
	public void case1() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String q="Select * from Student";
			
			ResultSet rs=smt.executeQuery(q);
			
			//to print the resultset on console
			if(rs.next()){ 
				do{
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getDate(3)+","+rs.getDate(4));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void case2() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String q="Select * from Project";
			
			ResultSet rs=smt.executeQuery(q);
			
			if(rs.next()){ 
				do{
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getInt(3)+","+rs.getString(4));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void case3() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select COUNT(*) from StudentProject where prj_no = 'P01';";

			ResultSet rs=smt.executeQuery(q);
			
			if(rs.next()){ 
				System.out.println("Total number of students in 'P01': "+rs.getInt(1));
			
			}
			else{
				System.out.println("Record Not Found...");
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void case4() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select * from StudentProject group by st_no having COUNT(prj_no) > 1;";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			int n=0;
			//to print the resultset on console
			if(rs.next()){ 
				do{
					++n;
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			System.out.println("Total number of students who participated in more than one project: "+n);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void case5() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select * from student where st_no NOT In (select st_no from studentproject);";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			int n=0;
			//to print the resultset on console
			if(rs.next()){ 
				do{
					++n;
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			System.out.println("Total number of students who did not participate in any project: "+n);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void case6() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "Select student.st_no,student.st_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(now(),student.st_dob)), '%Y')+0 as age From student, "
					+ "studentproject where student.st_no = studentproject.st_no and studentproject.prj_no = 'P02' ";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			int n=0;
			//to print the resultset on console
			if(rs.next()){ 
				do{
					++n;
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getInt(3));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			System.out.println("Total number of students made project in java: "+n);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void case7() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select * from student where st_no in(select st_no from studentproject where designation = 'PROGRAMMER');";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			int n=0;
			//to print the resultset on console
			if(rs.next()){ 
				do{
					++n;	
					System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getDate(3)+","+rs.getDate(4));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			System.out.println("Total number of students who is programmer: "+n);
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void case8() {
		try {
		String s1 = "select st_no,count(distinct (designation)) from studentproject group by st_no,prj_no;";
		Connection conn = DriverManager.getConnection(JdbcURL, Username, password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		HashMap <String,Integer> l = new HashMap <String,Integer>();
		int flag=1;
		String a = null;
		int b = 0;
		while(rs1.next())
		{
			if(flag==1)
			{
				 a= rs1.getString(1);
				 b = rs1.getInt(2);
				 l.put(a, b);
				flag=0;
			}
			else if(a.equalsIgnoreCase(rs1.getString(1)) && flag==0)
			{
				if(b<rs1.getInt(2))
				{
					b=rs1.getInt(2);
					l.replace(a, b);
				}
				flag=1;
				
			}
			else
			{
				l.put(rs1.getString(1),rs1.getInt(2));
			}
				
		}
		int maxValueInMap=(Collections.max(l.values()));  
        for (java.util.Map.Entry<String, Integer> entry : l.entrySet()) {  
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey() + "\t" +entry.getValue());
              
            }
        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void case9() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select * from student where st_dob = (select max(st_dob) from student);";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			//to print the resultset on console
			if(rs.next()){ 
				do {
					System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getDate(3)+","+rs.getDate(4));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void case10() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(JdbcURL, Username, password);
			Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String q= "select * from student where st_no in (\r\n" 
			+"select st_no from studentproject where prj_no in (\r\n"
			+"select prj_no from studentproject group by prj_no having COUNT(Distinct(st_no))=3\r\n"
			+")\r\n"
			+")";
			//to execute query
			ResultSet rs=smt.executeQuery(q);
			//to print the resultset on console
			if(rs.next()){ 
				do {
					System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getDate(3)+","+rs.getDate(4));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String args[]) {
		Student student = new Student();
		System.out.println("\n1. Display list of all student.");
		System.out.println("\n2. Display list of all projects.");
		System.out.println("\n3. Display the number of students who are working on project ?P01?.");
		System.out.println("\n4. Display number of students who participated in more than one project.");
		System.out.println("\n5. Find number of students who did not participate in any project.");
		System.out.println("\n6. Display the information (no,name,age) of student  who made the project in java.");
		System.out.println("\n7. Display the student information who is a programmer.");
		System.out.println("\n8. Display the student who played the max designation(e.g. manager,programmer) in the same project.");
		System.out.println("\n9. Display details of the youngest student.");
		System.out.println("\n10.Display the info of the student who participated in the project where total no of the student should be exact three.");
		System.out.println("\n11. For Exit");
		System.out.println();
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			switch(n) {
			case 1:
				student.case1();
				break;
			case 2:
				student.case2();
				break;
			case 3:
				student.case3();
				break;
			case 4:
				student.case4();
				break;
			case 5:
				student.case5();
				break;
			case 6:
				student.case6();
				break;
			case 7:
				student.case7();
				break;
			case 8:
				student.case8();
				break;
			case 9:
				student.case9();
				break;
			case 10:
				student.case10();
				break;
			case 11:
				System.exit(0);
			}
		}
	}
}
