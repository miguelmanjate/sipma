package mz.ciuem.sipma.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.zkoss.bind.BindContext;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;

public class FileManager {

	public mz.ciuem.sipma.entity.File uploadImage(BindContext ctx)
			throws IOException {
		UploadEvent upEvent = null;

		Object objUploadEvent = ctx.getTriggerEvent();
		mz.ciuem.sipma.entity.File file = new mz.ciuem.sipma.entity.File();

		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}

		if (upEvent != null) {

			Media media = upEvent.getMedia();
			file.setFileName(media.getName());
			file.setFileType(media.getContentType());
			
			if (media.isBinary()) {
				file.setContent(media.getByteData());
			} else {
			    String s = media.getStringData();
			    file.setContent(s.getBytes());
			}
		}
		return file;
	}

	// this must be generic as it is! we can change this name
	public AMedia byteToFile(String name, String type, byte[] buffer) {
		ByteArrayInputStream is = new ByteArrayInputStream(buffer);
		String format = type.substring(type.indexOf("/") + 1);
		AMedia fileContent = new AMedia(name, format, type, is);
		return fileContent;
	}
}
