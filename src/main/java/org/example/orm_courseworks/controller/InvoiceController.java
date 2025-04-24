package org.example.orm_courseworks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceController {

    @FXML
    private Label amountpaidLabel;

    @FXML
    private Label balancedueLabel;

    @FXML
    private Label contactnoLabel;

    @FXML
    private AnchorPane invoiceAnchorpane;

    @FXML
    private Label patientidLabel;

    @FXML
    private Label patientnameLabel;

    @FXML
    private Label programfeeLabel;

    @FXML
    private Label programidLabel;

    @FXML
    private Label programnameLabel;

    @FXML
    private Label sessiondateLabel;

    @FXML
    private Label therapistidLabel;

    @FXML
    private Label todaydateLabel;

    @FXML
    private Label invoiceNoLabel;

    private Stage stage;

    public void initialize() {
        // Generate unique invoice number
        String invoiceNumber = "INV-" + System.currentTimeMillis();
        invoiceNoLabel.setText("Invoice #: " + invoiceNumber);

        // Set current date
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        todaydateLabel.setText("Date: " + currentDate);
    }

    public void setInvoiceData(String patientId, String patientName, String patientContact,
                               String programId, String programName, String therapistId,
                               String sessionDate, String programFee, String amountPaid,
                               String paymentType) {

        patientidLabel.setText(patientId);
        patientnameLabel.setText(patientName);
        contactnoLabel.setText(patientContact);
        programidLabel.setText(programId);
        programnameLabel.setText(programName);
        therapistidLabel.setText(therapistId);
        sessiondateLabel.setText(sessionDate);

        // Format currency values
        double fee = Double.parseDouble(programFee);
        double paid = Double.parseDouble(amountPaid);
        double balance = fee - paid;

        programfeeLabel.setText(String.format("Rs. %.2f", fee));
        amountpaidLabel.setText(String.format("Rs. %.2f", paid));
        balancedueLabel.setText(String.format("Rs. %.2f", balance));
    }

//    @FXML
//    public void printInvoice(ActionEvent event) {
        /*// Get the button
        Node source = (Node) event.getSource();
        // Hide the button before printing
        source.setVisible(false);

        // Set up the printer job
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Show printer dialog
            boolean proceed = job.showPrintDialog(stage);

            if (proceed) {
                // Get the default page layout
                Printer printer = job.getPrinter();
                PageLayout pageLayout = printer.createPageLayout(
                        Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

                // Print the node
                boolean printed = job.printPage(pageLayout, stage.getScene().getRoot());

                if (printed) {
                    job.endJob();
                }
            }
        }

        // Show the button again after printing
        source.setVisible(true);*/
//    }

}
