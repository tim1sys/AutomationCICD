package companytim.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getDataFromJson {

	
	public List<HashMap<String, String>> getStringFromJson() throws IOException {
		
		// get file to string 
		String Jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\companytim\\data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		// turn string to hashmap using jackson databind 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(Jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
		
	}
	
}
