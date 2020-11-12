package com.ifam.sistema_estagio.reports;

import java.io.*;
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

	private static final String DIR_RELATORIOS = "src/main/resources/reports/";
	private static final String RELATORIO_CERTIFICADO = "certificado-banca.jrxml";
	private static final String RELATORIO_ATA_ESTAGIO = "ata-estagio.jrxml";
	private static final String RELATORIO_ATA_PROJETO = "ata-projeto.jrxml";
	private static final String RELATORIO_FICHA_ESTAGIO = "ficha-de-avaliacao-estagio.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_CAPA = "ficha-de-avaliacao-projeto-capa.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_RELATORIO = "ficha-de-avaliacao-projeto-relatorio.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_DEFESA = "ficha-de-avaliacao-projeto-defesa.jrxml";
	private static final String IMAGEM_BRASAO = "brasaorepublica.png";
	private static final String IMAGEM_CANTO = "imagem-canto.png";
	private static final String IMAGEM_IFAM = "ifam.jpg";

	private JasperReport compilarRelatorio(String nomeArquivo) throws JRException, IOException {
		InputStream jasperTemplate = new FileInputStream(DIR_RELATORIOS + nomeArquivo);
		return JasperCompileManager.compileReport(jasperTemplate);
	}

	private String carregarRecursosDeImagem(String nomeArquivo) {
		String caminho = DIR_RELATORIOS + nomeArquivo;
		File file = new File(caminho);
		return file.getAbsolutePath();
	}

	private JasperPrint retornarJasperPrint(JasperReport report, Map<String, Object> parameters, List<?> list) throws JRException {
		return JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(list));
	}
	
	private byte[] gerarPdf(JasperPrint print) throws JRException {
		return JasperExportManager.exportReportToPdf(print);		
	}

	public byte[] gerarCertificado(List<CertificadoFields> certificados)
			throws JRException, IOException {
		String image1 = carregarRecursosDeImagem(IMAGEM_CANTO);
		String image2 = carregarRecursosDeImagem(IMAGEM_BRASAO);
		String image3 = carregarRecursosDeImagem(IMAGEM_IFAM);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("image1", image1);
		parameters.put("image2", image2);
		parameters.put("image3", image3);

		JasperReport report = compilarRelatorio(RELATORIO_CERTIFICADO);
		JasperPrint print = retornarJasperPrint(report, parameters, certificados);
		byte[] pdf = gerarPdf(print);
		return pdf;
	}

	public byte[] gerarFichaDeAvaliacaoEstagio(
			List<FichaDeAvaliacaoEstagioFields> fichas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_FICHA_ESTAGIO);
		JasperPrint print = retornarJasperPrint(report, null, fichas);
		byte[] pdf = gerarPdf(print);
		return pdf;
	}

	public byte[] gerarFichaDeAvaliacaoProjeto(
			List<FichaDeAvaliacaoProjetoRelatorioFields> relatorios,
			List<FichaDeAvaliacaoProjetoDefesaFields> defesas,
			List<FichaDeAvaliacaoProjetoCapaFields> capa
	) throws JRException, IOException {
		JasperReport capaReport = compilarRelatorio(RELATORIO_FICHA_PROJETO_CAPA);
		JasperReport defesaReport = compilarRelatorio(RELATORIO_FICHA_PROJETO_DEFESA);
		JasperReport relatorioReport = compilarRelatorio(RELATORIO_FICHA_PROJETO_RELATORIO);
		
		List<JasperPrint> prints = new ArrayList<>();
		
		prints.add(retornarJasperPrint(capaReport, null, capa));
		prints.add(retornarJasperPrint(defesaReport, null, defesas));
		prints.add(retornarJasperPrint(relatorioReport, null, relatorios));
		
		JRPdfExporter exporter = new JRPdfExporter();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.setExporterInput(SimpleExporterInput.getInstance(prints));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		exporter.setConfiguration(new SimplePdfExporterConfiguration());
		exporter.exportReport();
		
		byte[] pdf = out.toByteArray();
		
		return pdf;
	}

	public byte[] gerarAtaEstagio(List<AtaEstagioFields> atas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_ATA_ESTAGIO);
		JasperPrint print = retornarJasperPrint(report, null, atas);
		byte[] pdf = gerarPdf(print);
		return pdf;
	}

	public byte[] gerarAtaProjeto(List<AtaProjetoFields> atas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_ATA_PROJETO);
		JasperPrint print = retornarJasperPrint(report, null, atas);
		byte[] pdf = gerarPdf(print);
		return pdf;
	}
}