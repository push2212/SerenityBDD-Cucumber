package utilities;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;

public class emailUtility extends PageObject {

	@Steps(shared = true)
	ReportMessege report;

	@Steps(shared = true)
	private common common;

	@Steps(shared=true) 
	private ReadWorkbook readWorkbook;

	public void sendEmail(String logicalName) throws IOException, AddressException, MessagingException{

		String filePath = "\\src\\test\\resources\\TestData\\TestData.xlsx";
		String dataSheet = "Project";
		Map <String,Map<Integer, String>> tableMap = ReadWorkbook.readRow(logicalName, filePath, dataSheet);	
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Row").size();i++) {
			String emailIDFrom = tableMap.get("emailIDFrom").get(i);
			String emailPassword = tableMap.get("emailPassword").get(i);
			//String tos = tableMap.get("emailIDTo").get(i);

			String DomainURL = "";
			String dir = System.getProperty("user.dir");

			String projectEmailAddress = Serenity.sessionVariableCalled("projectEmailAddress").toString();


			//String toIDs[] = tos.split(Pattern.quote(","));

			// Create object of Property file
			Properties props = new Properties();

			// this will set host of server- you can change based on your requirement 
			props.put("mail.smtp.host", "smtp.gmail.com");

			// set the port of socket factory 
			props.put("mail.smtp.socketFactory.port", "465");

			// set socket factory
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

			// set the authentication to true
			props.put("mail.smtp.auth", "true");

			// set the port of SMTP server
			props.put("mail.smtp.port", "465");

			// This will handle the complete authentication
			Session session = Session.getDefaultInstance(props,

					new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(emailIDFrom, emailPassword);

				}

			});

			try {

				// Create object of MimeMessage class
				Message message = new MimeMessage(session);						

				// Set the from address
				message.setFrom(new InternetAddress(emailIDFrom));

				// Set the recipient address							
				//	for (String to : toIDs) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(projectEmailAddress));
				//}

				// Add the subject link
				message.setSubject("Testing email via Automation");

				// Create object to add multimedia type content
				BodyPart messageBodyPart1 = new MimeBodyPart();

				// Set the body of email
				messageBodyPart1.setContent(
						"<h1>Test Mail from Automation for Incoming Email Test!</h1></br>" + "Testing via Automation", "text/html");

				// Create another object to add another content
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();


				// Mention the file which you want to send
				String filename = dir+"\\src\\test\\resources\\TestData\\profilePic.jpg";

				// Create data source and pass the filename
				DataSource source = new FileDataSource(filename);

				// set the handler
				messageBodyPart2.setDataHandler(new DataHandler(source));

				// set the file
				messageBodyPart2.setFileName(filename);

				// Create object of MimeMultipart class
				Multipart multipart = new MimeMultipart();

				// add body part 1
				multipart.addBodyPart(messageBodyPart2);

				// add body part 2
				multipart.addBodyPart(messageBodyPart1);

				// set the content
				message.setContent(multipart);

				// finally send the email
				Transport.send(message);

				report.Info("=====Email Sent=====");

			} catch (MessagingException e) {

				throw new RuntimeException(e);

			}
		}
	}

}

