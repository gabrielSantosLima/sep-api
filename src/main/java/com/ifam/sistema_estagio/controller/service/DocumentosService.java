package com.ifam.sistema_estagio.controller.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Certificado;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class DocumentosService {

	private InputStream getInputStreamByJasperFile(String fileName) {
		return DocumentosService.class
				.getResourceAsStream("/com/ifam/sistema_estagio/reports/" + fileName);
	}
	
	public byte[] generateCertificado(List<Certificado> certificados) throws JRException, IOException {

		InputStream jasperTemplate = getInputStreamByJasperFile("certificado-banca.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(jasperTemplate);
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(certificados));
		
		return JasperExportManager.exportReportToPdf(print);
	}
}