package mz.ciuem.sipma.vm;

import java.util.Arrays;

import mz.ciuem.sipma.reports.CustomDataSource;
import mz.ciuem.sipma.reports.ReportConfig;
import mz.ciuem.sipma.reports.ReportType;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

public class JasperReportVM {

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("PDF", "pdf"), 
					new ReportType("HTML", "html"), 
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("Excel", "xls"), 
					new ReportType("Excel (JXL)", "jxl"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));


	@Command("showReport")
	@NotifyChange("reportConfig")
	public void showReport() {
		reportConfig = new ReportConfig();
		reportConfig.setType(reportType);
		reportConfig.setDataSource(new CustomDataSource());
	}
	
	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}
	
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
}
