/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.edr;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import eu.comprofits.LittleHelper;
import eu.comprofits.entities.edr.Edr;
import eu.comprofits.entities.employee.Employee;
import eu.comprofits.session.edr.EdrFacade;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author alexanderhoelzemann
 */
public class EdrUtilityBean {

    public boolean printEdr(Edr edr) throws UnsupportedEncodingException, PrintException, IOException {
        //works but need some improvements 

        String defaultPrinter
                = PrintServiceLookup.lookupDefaultPrintService().getName();
        // looking for default printer
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        InputStream is = new ByteArrayInputStream(edr.edrToFormattedString().getBytes("UTF8"));

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(is, flavor, null);
        DocPrintJob job = service.createPrintJob();

        LittleHelper printingHelper = new LittleHelper(job);
        job.print(doc, pras);
        printingHelper.waitForDone();
        is.close();

        return true;
    }

    public boolean exportEdr(String extension, String path, Edr edr) throws FileNotFoundException, DocumentException, IOException {
        // works but needs some improvements

        if (extension.equalsIgnoreCase("xml")) {
            FileWriter writer;
            File file;
            XStream xstream = new XStream(new DomDriver());
            String xml = xstream.toXML(edr.edrToFormattedString());

            file = new File(path);

            writer = new FileWriter(file, true);
            writer.write(xml);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
            writer.close();

            return true;

        } else if (extension.equalsIgnoreCase("pdf")) {

            // muss noch verbessert werden
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(path + "." + extension));

            document.open();

            document.add(new Paragraph(edr.edrToFormattedString()));

            document.close();

            return true;

        } else {

            return false;
        }
    }

    public boolean sendEdrByMail(Edr edr, String to, String from, EmailAttachment attachment, String hostname, int port, String username, String password, Boolean sslOnConnect,
            String subject, String activationLink) throws EmailException {

        if (attachment != null) {

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(hostname);
            email.addTo(to);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(sslOnConnect);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(edr.edrToFormattedString() + "\n\n For confirming your EDR please follow this link:\n" + activationLink);

            // add the attachment
            email.attach(attachment);

            // send the email
            email.send();
            return true;

        } else {

            Email email = new SimpleEmail();
            email.setHostName(hostname);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(sslOnConnect);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(edr.edrToFormattedString() + "\n\n For confirming your EDR please follow this link:\n" + activationLink);
            email.addTo(to);
            email.send();

            return true;
        }
    }
           public boolean closeEdrStatusByEmployee(Employee employee, Integer updatedStatus, Edr edrObject, EdrFacade edrFacade) {

        if (edrObject.getReviewedEmployeeIdemployee().getIdemployee() == employee.getIdemployee()) {
            edrObject.setStatus(updatedStatus);
            edrFacade.edit(edrObject);
            
            return true;
        } else {
            
            return true;
        }
    }
       
       public Edr followUpOnLatestEdr(Edr edr, Employee employee, EdrFacade edrFacade) {

//        Date date = new Date();
//        EdrFacade eFacade = new EdrFacade();
//        List<Edr> allEdr =  eFacade.findAll();
//        Edr latestEdr = allEdr.get(allEdr.size()-1);
           
        return null;
    }
}
