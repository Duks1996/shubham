package com.airbnb.service;

import com.airbnb.entity.Booking;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PDFService {
    private final EmailService emailService;

    public PDFService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void generateBookingPDFAndSendingEmail(Booking booking) {
        Document document = new Document();
        try {
            File dir = new File("C:/shubham/bnbpdf");
            if (!dir.exists()) dir.mkdirs();

            String filePath = "C:/shubham/bnbpdf" + "/" + booking.getProperty().getName() + "_booking_Confirmation_"+booking.getId()+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph(booking.getProperty().getName()+" Booking Confirmation "+booking.getId(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // spacer

            // Table
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            addRow(table, "Booking ID", String.valueOf(booking.getId()));
            addRow(table, "User", booking.getAppUser().getUsername());
            addRow(table, "Property", booking.getProperty().getName());
            addRow(table, "Room Type", booking.getTypeOfRoom());
            addRow(table, "Check-In", booking.getCheckInDate().toString());
            addRow(table, "Check-Out", booking.getCheckOutDate().toString());
            addRow(table, "Total Price", String.valueOf(booking.getTotalPrice()));

            document.add(table);
            document.add(new Paragraph("Thank you for booking with us!"));

            emailService.sendEmailWithAttachment(booking.getEmail(), booking.getProperty().getName()+" Booking Confirmation "+booking.getId(),filePath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void addRow(PdfPTable table, String key, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(key));
        PdfPCell cell2 = new PdfPCell(new Phrase(value));
        table.addCell(cell1);
        table.addCell(cell2);
    }
}
