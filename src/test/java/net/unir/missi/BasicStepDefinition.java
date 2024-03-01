package net.unir.missi;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasicStepDefinition {

    protected WebDriver dr;
    protected Document document;
    protected PdfWriter writer;

    protected void createPDFFile() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String dtime = dateFormat.format(new Date());
            document = new Document();
            String output = "./evidencias/evidencia_" + dtime + ".pdf";
            FileOutputStream fos = new FileOutputStream(output);
            writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void capturarPantalla() {
        try {
            byte[] input = ((TakesScreenshot) dr).getScreenshotAs(OutputType.BYTES);
            Image im = Image.getInstance(input);
            im.scaleToFit(PageSize.A4.getWidth() / 2, PageSize.A4.getHeight() / 2);
            // document.add(new Paragraph(" "));
            document.add(im);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void addTexto(String texto) {
        try {
            document.add(new Paragraph(texto));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void cerrarPdf() {
        document.close();
        writer.close();
    }

    protected void esperar(int segundos) {
        try {
            Thread.currentThread().wait(segundos * 1000);
        } catch (Exception ex) {}
    }
}
