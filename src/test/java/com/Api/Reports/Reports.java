package com.Api.Reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;


public class Reports {

	public  void GenerateJvmReport(String jsonfile) {
		
				File file = new File(System.getProperty("user.dir") + "\\target");

				Configuration configuration = new Configuration(file, "OMR Branch");
				
				configuration.addClassifications("Sprint", "30");
				configuration.addClassifications("Testing", "Reg");
				configuration.addClassifications("Author", "Rajasekar");
				configuration.addClassifications("environment", "QA");

				List<String> list = new ArrayList<>();
				list.add(jsonfile);
				ReportBuilder builder = new ReportBuilder(list, configuration);
				builder.generateReports();
}
}
