package com.ifam.sistema_estagio.reports;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ifam.sistema_estagio.reports.fields.*;
import lombok.val;
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
public class DocumentosManager {

	private static final String DIR_RELATORIOS = "src/main/resources/reports/";
	private static final String RELATORIO_CERTIFICADO = "certificado-banca.jrxml";
	private static final String RELATORIO_CERTIFICADO_FRENTE = "frente-certificado-banca.jrxml";
	private static final String RELATORIO_ATA_ESTAGIO = "ata-estagio.jrxml";
	private static final String RELATORIO_ATA_PROJETO = "ata-projeto.jrxml";
	private static final String RELATORIO_FICHA_ESTAGIO = "ficha-de-avaliacao-estagio.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_CAPA = "ficha-de-avaliacao-projeto-capa.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_RELATORIO = "ficha-de-avaliacao-projeto-relatorio.jrxml";
	private static final String RELATORIO_FICHA_PROJETO_DEFESA = "ficha-de-avaliacao-projeto-defesa.jrxml";
	private static final String IMAGEM_BRASAO = "brasaorepublica.png";
	private static final String IMAGEM_CANTO = "imagem-canto.png";
	private static final String IMAGEM_IFAM = "ifam.jpg";
	private static final String IMAGEM_IFAM_LIVRO_REGISTRO = "livro-registro.jpg";

	private JasperReport compilarRelatorio(String nomeArquivo) throws JRException, IOException {
		val jasperTemplate = new FileInputStream(DIR_RELATORIOS + nomeArquivo);
		return JasperCompileManager.compileReport(jasperTemplate);
	}

	private String carregarRecursosDeImagem(String nomeArquivo) {
		val caminho = DIR_RELATORIOS + nomeArquivo;
		val file = new File(caminho);
		return file.getAbsolutePath();
	}

	private JasperPrint retornarJasperPrint(
			JasperReport report,
			Map<String, Object> parametros,
			List<?> list
	) throws JRException {
		return JasperFillManager.fillReport(
				report,
				parametros,
				new JRBeanCollectionDataSource(list)
		);
	}

	private byte[] gerarMultiPdfs(List<JasperPrint> prints) throws JRException {
		val exporter = new JRPdfExporter();
		val out = new ByteArrayOutputStream();
		exporter.setExporterInput(SimpleExporterInput.getInstance(prints));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		exporter.setConfiguration(new SimplePdfExporterConfiguration());
		exporter.exportReport();
		return out.toByteArray();
	}

	private byte[] gerarPdf(JasperPrint print) throws JRException {
		return JasperExportManager.exportReportToPdf(print);		
	}

	public byte[] gerarCertificado(List<CertificadoFields> certificados, List<FrenteCertificadoFields> certificadosFrente)
			throws JRException, IOException {
		val image = carregarRecursosDeImagem(IMAGEM_IFAM_LIVRO_REGISTRO);
		val image1 = carregarRecursosDeImagem(IMAGEM_CANTO);
		val image2 = carregarRecursosDeImagem(IMAGEM_BRASAO);
		val image3 = carregarRecursosDeImagem(IMAGEM_IFAM);

		val parametros = new HashMap<String, Object>();
		parametros.put("image1", image1);
		parametros.put("image2", image2);
		parametros.put("image3", image3);

		val parametrosFrente = new HashMap<String, Object>();
		parametrosFrente.put("image", image);

		val prints = new ArrayList<JasperPrint>();
		val reportVerso = compilarRelatorio(RELATORIO_CERTIFICADO);
		val reportFrente = compilarRelatorio(RELATORIO_CERTIFICADO_FRENTE);
		prints.add(retornarJasperPrint(reportVerso, parametros, certificados));
		prints.add(retornarJasperPrint(reportFrente, parametrosFrente, certificadosFrente));
		return gerarMultiPdfs(prints);
	}

	public byte[] gerarFichaDeAvaliacaoEstagio(List<FichaDeAvaliacaoEstagioFields> fichas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_FICHA_ESTAGIO);
		JasperPrint print = retornarJasperPrint(report, null, fichas);
		return gerarPdf(print);
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
		return gerarMultiPdfs(prints);
	}

	public byte[] gerarAtaEstagio(List<AtaEstagioFields> atas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_ATA_ESTAGIO);
		JasperPrint print = retornarJasperPrint(report, null, atas);
		return gerarPdf(print);
	}

	public byte[] gerarAtaProjeto(List<AtaProjetoFields> atas) throws JRException, IOException {
		JasperReport report = compilarRelatorio(RELATORIO_ATA_PROJETO);
		JasperPrint print = retornarJasperPrint(report, null, atas);
		return gerarPdf(print);
	}
}