package rogerio.pst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.mailer.Attachment;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MailerTest {
	@Inject
	Mailer mailer;
	@Inject
	MockMailbox mbox;

	@BeforeEach
	void clearMBox() {
		mbox.clear();
	}

	@Test
	public void assertBasicTextEmailSent() {
		final String mailTo = "rogerio.pst1@gmail.com";
		final String testingSubject = "Testing email";
		final String testingBody = "Hello World!";
		mailer.send(Mail.withText(mailTo, testingSubject, testingBody));
		assertEquals(mbox.getTotalMessagesSent(), 1);
		List<Mail> emails = mbox.getMessagesSentTo(mailTo);
		// assertThat(emails).hasSize(1);
		Mail email = emails.get(0);
		assertEquals(email.getSubject(), testingSubject);
		assertEquals(email.getText(), testingBody);
	}

	@Test
	void attachmentTest() throws Exception {
		final String mailTo = "test@example.org";
		final String testingSubject = "email with Attachment";
		final String html = "<strong>E-mail by:</strong>" + "\n" + "<p><img src=\"cid:logo@quarkus.io\"/></p>";
		sendEmail(mailTo, testingSubject, html);
		Mail email = mbox.getMessagesSentTo(mailTo).get(0);
		List<Attachment> attachments = email.getAttachments();
		assertEquals(email.getHtml(), html);
		// assertThat(attachments).hasSize(1);
		// assertThat(attachments.get(0).getFile()) .isEqualTo(new
		// File(getAttachmentURI()));
	}

	private void sendEmail(String to, String subject, String body) throws URISyntaxException {
		final File logo = new File("");
		Mail email = Mail.withHtml(to, subject, body).addInlineAttachment("quarkus-logo.svg", logo, "image/svg+xml",
				"<logo@quarkus.io>");
		mailer.send(email);
	}
}
