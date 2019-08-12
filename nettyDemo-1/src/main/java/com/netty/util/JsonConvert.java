package com.netty.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Object与json相互转换类
 * @author CYQ
 *
 */
public abstract class JsonConvert {
	private static ObjectMapper mapper = new ObjectMapper();
	/**
	 * Object转json
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String Object2Json(Object obj) throws JsonProcessingException {
		String json = mapper.writeValueAsString(obj);
		return json;
	}
	/**
	 * json转object
	 * @param <T>
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T>T Json2Object(String json, Class<T> cls) throws JsonParseException, JsonMappingException, IOException{
		T t = mapper.readValue(json, cls);
		return t;
	}
}
