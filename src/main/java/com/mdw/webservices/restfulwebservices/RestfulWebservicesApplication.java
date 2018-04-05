package com.mdw.webservices.restfulwebservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.text.DateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.mdw.webservices.restfulwebservices.Sender.SenderRepository;
import com.mdw.webservices.restfulwebservices.Sender.Sender;
import com.mdw.webservices.restfulwebservices.email.Email;
import com.mdw.webservices.restfulwebservices.email.EmailRepository;
import org.springframework.core.io.ClassPathResource;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class RestfulWebservicesApplication implements CommandLineRunner  {

	@Autowired
	private SenderRepository senderRepository;

	@Autowired
	private EmailRepository emailRepository;



	public static void main(String[] args) {

		SpringApplication.run(RestfulWebservicesApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

		// Cleanup Database tables
		senderRepository.deleteAllInBatch();
		emailRepository.deleteAllInBatch();

		Sender sender = new Sender("Marilyn Waldman", "mdwaldman22@gmail.com");

		//Scanner scanner = new Scanner(new File("./resources/emails.csv"));
		File file = new ClassPathResource("emails.csv").getFile();
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			String[] fields = line.split(",");
			if (fields.length >= 7) // At least one address specified.
			{
				Date date1 = getDate(fields[1]);
				if(date1 != null) {

					Email newEmail = new Email(new Date(), date1, fields[2],
							fields[3], fields[4], fields[5], fields[6]);
					newEmail.setSender(sender);
					sender.getEmails().add(newEmail);
				}

			}
			else
			{
				System.err.println("Invalid record: " + line);
			}
		}

		senderRepository.save(sender);

		// ======================================


	}

	private Date getDate(String yeartime) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		String[] fields = yeartime.split(" ");

		//parse year
		if(fields.length != 2) return null;
		String[] year = fields[0].split("/");
		if(year.length != 3) return null;
		String str = year[2] + "-" + getLengthTwo(year[0]) + "-" + getLengthTwo(year[1]) + "T";
		//System.out.println(str);

		String[] time = fields[1].split(":");

		if( time.length != 3){

			str = str + "00:00:00";
		} else {
			str = str +  getLengthTwo(time[0]) + ":" + getLengthTwo(time[1]) + ":" + getLengthTwo(time[2]);
		}

		str = str + ".424+0000";
		//System.out.println(str);

		Date date = null;
		try {
			date = df.parse(str);
			String newDateString = df.format(date);
			//System.out.println(" date is " + newDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;


	}

	private String getLengthTwo(String s) {
		if(s.length() == 2) return s;
		return "0" + s;
	}
}
