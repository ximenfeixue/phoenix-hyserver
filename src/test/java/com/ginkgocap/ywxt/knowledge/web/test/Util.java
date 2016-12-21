package com.ginkgocap.ywxt.knowledge.web.test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;

public class Util {

	   public static <T> T readValue(Class<T> valueType, final String jsonContent, String... values)
	    {
	        return readValue(null, valueType, jsonContent, values);
	    }

	    public static <T> T readValue(final FilterProvider filterProvider, Class<T> valueType, final String content, String... values)
	    {
	        JsonNode node = getJsonNode(content, values);
	        return readValue(filterProvider, valueType, node.toString());

	    }

	    public static <T> T readValue(Class<T> valueType, final String jsonContent) {
	        return readValue(null, valueType, jsonContent);
	    }
	    public static <T> T readValue(final FilterProvider filterProvider,Class<T> valueType, final String jsonContent) {
	        if (StringUtils.isBlank(jsonContent)) {
	            throw new IllegalArgumentException("Content is null");
	        }
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            if (filterProvider != null) {
	                objectMapper.setFilters(filterProvider);
	            }
	            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	            return objectMapper.readValue(jsonContent, valueType);
	        } catch(JsonParseException ex) {
	            ex.printStackTrace();
	        } catch(JsonMappingException ex) {
	            ex.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    public static <T> T readValue(TypeReference javaType, final String content) {
	        if (StringUtils.isBlank(content)) {
	            throw new IllegalArgumentException("Content is null");
	        }
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	            return objectMapper.readValue(content, javaType);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    public static <T> List<T> readListValue(Class<T> valueType, final String content) {
	        if (StringUtils.isBlank(content)) {
	            throw new IllegalArgumentException("Content is null");
	        }
	        try {
	            //TypeReference javaType = new TypeReference<List<T>>(){};
	        	ObjectMapper objectMapper = new ObjectMapper();
	            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, valueType);
	            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	            return objectMapper.readValue(content, javaType);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    public static JsonNode readTree(final String content) {
	        if (StringUtils.isBlank(content)) {
	            throw new IllegalArgumentException("Content is null");
	        }
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	        try {
	            return objectMapper.readTree(content);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    public static String writeObjectToJson(final Object jsonContent)
	    {
	        return writeObjectToJson(null, jsonContent);
	    }

	    public static String writeObjectToJson(final FilterProvider filterProvider, final Object jsonContent)
	    {
	        if (jsonContent == null) {
	            throw new IllegalArgumentException("Content is null");
	        }

	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            if (filterProvider != null) {
	                objectMapper.setFilters(filterProvider);
	            }
	            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	            return objectMapper.writeValueAsString(jsonContent);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static JsonNode getJsonNode(final String jsonStr, final String... values) {
	        if (StringUtils.isBlank(jsonStr)) {
	            throw new IllegalArgumentException("jsonStr is null");
	        }
	        if (values == null || values.length <= 0) {
	            throw new IllegalArgumentException("values is null");
	        }

	        JsonNode node = null;
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	            node = objectMapper.readTree(jsonStr);
	            if (values != null && values.length > 0) {
	                for (String v : values) {
	                    node = node.path(v);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return node;
	    }

	    
}
