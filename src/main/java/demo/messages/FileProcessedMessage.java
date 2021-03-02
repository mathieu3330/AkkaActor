package demo.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileProcessedMessage {

    private List<LineProcessingResult> ipMap ;

	public List<LineProcessingResult> getIpMap() {
		return ipMap;
	}

	public FileProcessedMessage(List<LineProcessingResult> ipMap) {
		super();
		this.ipMap = ipMap;
	}

	public void setIpMap(List<LineProcessingResult> ipMap) {
		this.ipMap = ipMap;
	}

    

}
