package com.revature.librarymanagement.util;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import com.revature.librarymanagement.model.IssueBook;

public class PDFGenerator {

	private PDFGenerator() {
	}

	public static ByteArrayInputStream userFineAmountReport(IssueBook issuedDetails) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 20, BaseColor.RED);
			Paragraph para = new Paragraph("Book Fine Receipt", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, BaseColor.BLACK);
			Paragraph para1 = new Paragraph("First Name :" + issuedDetails.getUser().getFirstName(), font1);
			para1.setAlignment(Element.ALIGN_CENTER);
			document.add(para1);

			Paragraph para2 = new Paragraph("Last Name :" + issuedDetails.getUser().getLastName(), font1);
			para2.setAlignment(Element.ALIGN_CENTER);
			document.add(para2);

			Paragraph para3 = new Paragraph(" User Role:" + issuedDetails.getUser().getUserRole(), font1);
			para3.setAlignment(Element.ALIGN_CENTER);
			document.add(para3);

			Paragraph para31 = new Paragraph("Mobilenumber:" + issuedDetails.getUser().getMobileNumber(), font1);
			para31.setAlignment(Element.ALIGN_CENTER);
			document.add(para31);

			Paragraph para4 = new Paragraph("ISBN Number :" + issuedDetails.getBook().getIsbn(), font1);
			para4.setAlignment(Element.ALIGN_CENTER);
			document.add(para4);

			Paragraph para5 = new Paragraph("Book Name :" + issuedDetails.getBook().getBookName(), font1);
			para5.setAlignment(Element.ALIGN_CENTER);
			document.add(para5);

			Paragraph para6 = new Paragraph("Author Name :" + issuedDetails.getBook().getAuthorName(), font1);
			para6.setAlignment(Element.ALIGN_CENTER);
			document.add(para6);

			Paragraph para61 = new Paragraph("Publisher :" + issuedDetails.getBook().getPublisher(), font1);
			para61.setAlignment(Element.ALIGN_CENTER);
			document.add(para61);

			Paragraph para62 = new Paragraph("Genre Of Book :" + issuedDetails.getBook().getGenre(), font1);
			para62.setAlignment(Element.ALIGN_CENTER);
			document.add(para62);

			Paragraph para7 = new Paragraph("Issue Date :" + issuedDetails.getIssueDate(), font1);
			para7.setAlignment(Element.ALIGN_CENTER);
			document.add(para7);

			Paragraph para8 = new Paragraph("Due Date :" + issuedDetails.getDueDate(), font1);
			para8.setAlignment(Element.ALIGN_CENTER);
			document.add(para8);

			Paragraph para9 = new Paragraph("Fine Amount : Rs." + issuedDetails.getFineAmount(), font1);
			para9.setAlignment(Element.ALIGN_CENTER);
			document.add(para9);

			document.close();
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
