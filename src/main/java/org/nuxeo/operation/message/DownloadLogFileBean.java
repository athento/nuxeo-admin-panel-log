package org.nuxeo.operation.message;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.jsf.operations.DownloadFile;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.FileBlob;
import org.nuxeo.runtime.api.Framework;

@Name("download_log")
@Scope(ScopeType.CONVERSATION)
public class DownloadLogFileBean {

	private String input;

	public void submit() {
		// handle form submission
		/* output = "Caught message: " + input; */
		String pathLog;
		if (StringUtils.isEmpty(input)) {
			pathLog = "/var/log/nuxeo/server.log";
		} else {
			pathLog = input;
		}
		File logFile = new File(pathLog);
		if (logFile.exists()) {
			Blob blob = new FileBlob(logFile);
			OperationChain chain = new OperationChain("DownloadServerLogFile");
			chain.add(DownloadFile.ID);
			OperationContext ctx = new OperationContext();
			ctx.setInput(blob);
			try {
				Framework.getLocalService(AutomationService.class).run(ctx,
						chain);
			} catch (Exception e) {
				e.printStackTrace();
			}
			input = "";
		}
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}