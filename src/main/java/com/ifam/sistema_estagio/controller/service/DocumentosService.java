package com.ifam.sistema_estagio.controller.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.reports.fields.AtaEstagioFields;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class DocumentosService {

	private static final String PACKAGE_PATH = "/com/ifam/sistema_estagio/reports/";

	private JasperReport loadTemplateAndCompile(String fileName) throws JRException {
		InputStream jasperTemplate = DocumentosService.class
				.getResourceAsStream(PACKAGE_PATH + fileName + ".jrxml");

		JasperReport report = JasperCompileManager.compileReport(jasperTemplate);

		return report;
	}

	private String loadResource(String nameFile) {
		String path = "src/main/java" + PACKAGE_PATH + nameFile;

		File file = new File(path);

		return file.getAbsolutePath();
	}

	private JasperPrint getJasperPrint(JasperReport report, Map<String, Object> parameters,
			List<?> list) throws JRException {

		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource(list));
		
		return print;
	}
	
	private byte[] getPdf(JasperPrint print) throws JRException {
		return JasperExportManager.exportReportToPdf(print);		
	}

	public byte[] generateCertificado(List<CertificadoFields> certificados)
			throws JRException {

		String image1 = loadResource("imagem-canto.png");
		String image2 = loadResource("brasaorepublica.png");
		String image3 = loadResource("ifam.jpg");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("image1", image1);
		parameters.put("image2", image2);
		parameters.put("image3", image3);

		JasperReport report = loadTemplateAndCompile("certificado-banca");

		byte[] pdf = getPdf(getJasperPrint(report, parameters, certificados));

		return pdf;
	}

	public byte[] generateFichaDeAvaliacaoEstagio(
			List<FichaDeAvaliacaoEstagioFields> fichas) throws JRException {

		JasperReport report = loadTemplateAndCompile("ficha-de-avaliacao-estagio");

		byte[] pdf = getPdf(getJasperPrint(report, null, fichas));

		return pdf;
	}

	public byte[] generateFichaDeAvaliacaoProjeto(
			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios,
			List<FichaDeAvaliacaoProjetoDefesaFields> defesas,
			List<FichaDeAvaliacaoProjetoCapaFields> capa) throws JRException {
		JasperReport capaReport = loadTemplateAndCompile("ficha-de-avaliacao-projeto-capa");
		JasperReport defesaReport = loadTemplateAndCompile("ficha-de-avaliacao-projeto-defesa");
		JasperReport relatorioReport = loadTemplateAndCompile("ficha-de-avaliacao-projeto-relatorio");
		
		List<JasperPrint> prints = new ArrayList<>();
		
		prints.add(getJasperPrint(capaReport, null, capa));
		prints.add(getJasperPrint(defesaReport, null, defesas));
		prints.add(getJasperPrint(relatorioReport, null, relatorios));
		
		JRPdfExporter exporter = new JRPdfExporter();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.setExporterInput(SimpleExporterInput.getInstance(prints));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		exporter.setConfiguration(new SimplePdfExporterConfiguration());
		exporter.exportReport();
		
		byte[] pdf = out.toByteArray();
		
		return pdf;
	}

	public byte[] generateAtaEstagio(List<AtaEstagioFields> atas) throws JRException {
		JasperReport report = loadTemplateAndCompile("ata-estagio");

		byte[] pdf = getPdf(getJasperPrint(report, null, atas));

		return pdf;
	}

	public byte[] generateAtaProjeto(List<AtaProjetoFields> atas) throws JRException {
		JasperReport report = loadTemplateAndCompile("ata-projeto");

		byte[] pdf = getPdf(getJasperPrint(report, null, atas));

		return pdf;
	}
}