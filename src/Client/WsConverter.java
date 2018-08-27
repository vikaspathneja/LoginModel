package Client;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WsConverter {
	public static Object[] parseJson(String jsonString) {
		System.out.println("reached in parseJson");
		JsonArray jsonArr = (new JsonParser()).parse(jsonString).getAsJsonArray();

		JsonElement element=jsonArr.get(0);
		JsonObject obj=element.getAsJsonObject();
		TreeSet<String>ts=new TreeSet<>();
		
		Set<Map.Entry<String, JsonElement>> entries=obj.entrySet();
        for (Map.Entry<String, JsonElement> entry: entries) {
            ts.add(entry.getKey());
        }
        
        return ts.toArray();
        

        
        
        
		//System.out.println("arrray"+jsonArr.toString());
//		int counter=0;
//		for (JsonElement element : jsonArr) {
//
//            if (element.isJsonObject()) {
//            	counter++;
//                JsonObject jsonobj= element.getAsJsonObject();
//               // System.out.println(jsonobj.toString());
////                System.out.println(jsonobj.get("altSpellings").toString());
//                Set<Map.Entry<String, JsonElement>> entries=jsonobj.getAsJsonObject().entrySet();
//                for (Map.Entry<String, JsonElement> entry: entries) {
//                    System.out.println(entry.getKey());
//                }
//            }
            
            
//		}
//		System.out.println("count of countries=="+counter);
		
		//JsonObject jobject=jsonArr.getAsJsonObject();
	    //System.out.println(jobject.toString());
	    

	}
}
