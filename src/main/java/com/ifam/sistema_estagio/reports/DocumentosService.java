package com.ifam.sistema_estagio.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ifam.sistema_estagio.reports.fields.*;
import org.springframework.stereotype.Service;

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

	private static final String DIR_RELATORIOS = "/src/main/resources/reports/";
	private static final String RELATORIO_CERTIFICADO = "certificado-banca";
	private static final String RELATORIO_ATA_ESTAGIO = "ata-estagio";
	private static final String RELATORIO_ATA_PROJETO = "ata-projeto";
	private static final String RELATORIO_FICHA_ESTAGIO = "ficha-de-avaliacao-estagio";
	private static final String RELATORIO_FICHA_PROJETO_CAPA = "ficha-de-avaliacao-projeto-capa";
	private static final String RELATORIO_FICHA_PROJETO_RELATORIO = "ficha-de-avaliacao-projeto-relatorio";
	private static final String RELATORIO_FICHA_PROJETO_DEFESA = "ficha-de-avaliacao-projeto-defesa";
	private static final String IMAGEM_BRASAO = "brasaorepublica.png";
	private static final String IMAGEM_CANTO = "imagem-canto.png";
	private static final String IMAGEM_IFAM = "ifam.jpg";

	private JasperReport carregarECompilarModelo(String fileName) throws JRException {
		InputStream jasperTemplate = DocumentosService.class.getResourceAsStream(DIR_RELATORIOS + fileName + ".jrxml");

		JasperReport report = JasperCompileManager.compileReport(jasperTemplate);

		return report;
	}

	private String carregarRecursos(String nameFile) {
		String path = DIR_RELATORIOS + nameFile;

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

	public byte[] gerarCertificado(List<CertificadoFields> certificados)
			throws JRException {

		String image1 = carregarRecursos(IMAGEM_CANTO);
		String image2 = carregarRecursos(IMAGEM_BRASAO);
		String image3 = carregarRecursos(IMAGEM_IFAM);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("image1", image1);
		parameters.put("image2", image2);
		parameters.put("image3", image3);

		JasperReport report = carregarECompilarModelo(RELATORIO_CERTIFICADO);

		byte[] pdf = getPdf(getJasperPrint(report, parameters, certificados));

		return pdf;
	}

	public byte[] gerarFichaDeAvaliacaoEstagio(
			List<FichaDeAvaliacaoEstagioFields> fichas) throws JRException {

		JasperReport report = carregarECompilarModelo(RELATORIO_FICHA_ESTAGIO);

		byte[] pdf = getPdf(getJasperPrint(report, null, fichas));

		return pdf;
	}

	public byte[] gerarFichaDeAvaliacaoProjeto(
			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios,
			List<FichaDeAvaliacaoProjetoDefesaFields> defesas,
			List<FichaDeAvaliacaoProjetoCapaFields> capa
	) throws JRException {
		JasperReport capaReport = carregarECompilarModelo(RELATORIO_FICHA_PROJETO_CAPA);
		JasperReport defesaReport = carregarECompilarModelo(RELATORIO_FICHA_PROJETO_DEFESA);
		JasperReport relatorioReport = carregarECompilarModelo(RELATORIO_FICHA_PROJETO_RELATORIO);
		
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

	public byte[] gerarAtaEstagio(List<AtaEstagioFields> atas) throws JRException {
		JasperReport report = carregarECompilarModelo(RELATORIO_ATA_ESTAGIO);

		byte[] pdf = getPdf(getJasperPrint(report, null, atas));

		return pdf;
	}

	public byte[] gerarAtaProjeto(List<AtaProjetoFields> atas) throws JRException {
		JasperReport report = carregarECompilarModelo(RELATORIO_ATA_PROJETO);

		byte[] pdf = getPdf(getJasperPrint(report, null, atas));

		return pdf;
	}
}