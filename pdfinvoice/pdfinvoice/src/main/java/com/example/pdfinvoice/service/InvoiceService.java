package com.example.pdfinvoice.service;

import com.example.pdfinvoice.model.User;
import com.example.pdfinvoice.model.InsurancePolicy;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class InvoiceService {

    public String generateInvoice(User user, InsurancePolicy policy) {
        String fileName = "invoice_" + user.getId() + ".pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("-----------------------------"));
            document.add(new Paragraph("User Details:"));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Phone: " + user.getPhone()));
            document.add(new Paragraph("-----------------------------"));
            document.add(new Paragraph("Insurance Policy Details:"));
            document.add(new Paragraph("Policy Name: " + policy.getPolicyName()));
            document.add(new Paragraph("Policy Details: " + policy.getPolicyDetails()));
            document.add(new Paragraph("Premium Amount: $" + policy.getPremiumAmount()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fileName;
    }
}