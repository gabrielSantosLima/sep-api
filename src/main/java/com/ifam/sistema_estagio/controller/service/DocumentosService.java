package com.ifam.sistema_estagio.controller.service;

import java.io.File;
import java.io.InputStream;
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

@Service
public class DocumentosService {

	private static final String PACKAGE_PATH = "/com/ifam/sistema_estagio/reports/";

	private JasperReport loadTemplateAndCompile(String fileName) throws JRException {
		InputStream jasperTemplate = DocumentosService
				.class
				.getResourceAsStream(PACKAGE_PATH + fileName);

		JasperReport report = JasperCompileManager.compileReport(jasperTemplate);
		
		return report;
	}

	private String loadResource(String nameFile) {
		String path = "src/main/java" + PACKAGE_PATH + nameFile;

		File file = new File(path);

		return file.getAbsolutePath();
	}

	private byte[] getPdf(JasperReport report, Map<String, Object> parameters,
			List<?> list) throws JRException {
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource(list));

		return JasperExportManager.exportReportToPdf(print);
	}

	public byte[] generateCertificado(List<CertificadoFields> certificados) throws JRException {
		
		String image1 = loadResource("imagem-canto.png");
		String image2 = loadResource("brasaorepublica.png");
		String image3 = loadResource("ifam.jpg");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("image1", image1);
		parameters.put("image2", image2);
		parameters.put("image3", image3);

		JasperReport report = loadTemplateAndCompile("certificado-banca.jrxml");
		
		byte[] pdf = getPdf(report, parameters, certificados);
		
		return pdf;
	}

	public byte[] generateFichaDeAvaliacaoEstagio(
			List<FichaDeAvaliacaoEstagioFields> ficha) {

		return null;
	}

	public byte[] generateFichaDeAvaliacaoProjeto(
			List<FichaDeAvaliacaoProjetoRelatorioFields> av_relatorios,
			List<FichaDeAvaliacaoProjetoDefesaFields> av_defesas,
			List<FichaDeAvaliacaoProjetoCapaFields> capa) {

		return null;
	}

	public byte[] generateAtaEstagio(List<AtaEstagioFields> ata) {

		return null;
	}

	public byte[] generateAtaProjeto(List<AtaProjetoFields> ata) {

		return null;
	}
}