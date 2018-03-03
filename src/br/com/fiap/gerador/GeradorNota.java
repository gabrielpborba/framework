package br.com.fiap.gerador;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.fiap.core.Helper;
import br.com.fiap.entity.Item;
import br.com.fiap.entity.Pedido;

public class GeradorNota {

	public void gerarPDF(Pedido pedido) {
		Document document = new Document();
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\cupons\\producer\\pdf_nota.pdf"));
			
	
			document.open();
			document.add(new Paragraph("                              EMPRESA DO TESTE DO PDF"));
			document.add(new Paragraph("                                EMBU DAS ARTES 157"));
			document.add(new Paragraph("                                  SAO PAULO - SP"));
			document.add(new Paragraph("-----------------------------------------------------------------------------"));
			document.add(new Paragraph("CNPJ:11.111.111/111-11"));
			document.add(new Paragraph("IE:222.222.222.222"));
			document.add(new Paragraph("IM:3.333.333"));
			document.add(new Paragraph("-----------------------------------------------------------------------------"));
			document.add(new Paragraph(""));
			document.add(new Paragraph("12/12/2012         10:48             CCF:019200              COO:123445"));
			document.add(new Paragraph("CUPOM FISCAL"));
			double total = 0d;
			for (Item item : pedido.getItens()) {
				total = total + Double.parseDouble(item.getValor());
				document.add(new Paragraph(item.getId() + ".............."+ item.getDescricao()+".............." + item.getValor().toString()));
			}
			document.add(new Paragraph("TOTAL..........................................................." + total));
		
			document.add(new Paragraph("123313       2121313      33323      57876         5343       5        55353434"));
			
			
			PdfContentByte cb = writer.getDirectContent();
			Barcode128 barcoder128 = new Barcode128();
			barcoder128.setCode("teste");
			barcoder128.setCodeType(Barcode.CODE128);
			
			document.addSubject("GERANDO PDF DOS DEUSES");
			document.addKeywords("www.queota.com.br");
			document.addCreator("by noix");
			document.addAuthor("author");
		} catch (Exception e) {
			System.err.println("DEU RUIIIMMMMM   " + e.getMessage());
		}

		document.close();
	}

}
