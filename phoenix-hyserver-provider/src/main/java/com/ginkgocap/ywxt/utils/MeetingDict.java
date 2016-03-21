package com.ginkgocap.ywxt.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 行业
 * @author wangmeizhou
 *
 */
public class MeetingDict {

	public static final String INDUSTRY = "industry";
	
	private Map<String, Map<String, String>> dict;
	
	public Map<String, Map<String, String>> getDict() {
		return dict;
	}

	public void setDict(Map<String, Map<String, String>> dict) {
		this.dict = dict;
	}

	public Map<String, String> getDict(String dictName) {
		return dict.get(dictName);
	}
	
	public String getDictStr(List<String> list, String dictName) {
		String dictValue = "";
		Map<String, String> dictMap = dict.get(dictName);
		if(list!=null && dictMap!=null) {
			for(String s : list) {
				if(dictMap.containsValue(s)) {
					Iterator<String> it = dictMap.keySet().iterator();
					while(it.hasNext()) {
						String key = it.next();
						String value = dictMap.get(key);
						if(s.equals(value)) {
							dictValue += key + ",";
							break;
						}
					}
				}
			}
			if(dictValue.length() > 0) {
				dictValue = "," + dictValue;
			}
		}
		return dictValue;
	}
	
	public List<String> getDictList(String dictValue, String dictName) {
		List<String> dictList = new ArrayList<String>();
		Map<String, String> dictMap = dict.get(dictName);
		if(dictValue!=null && dictMap!=null) {
			String[] arr = dictValue.split(",");
			for(String s : arr) {
				String value = dictMap.get(s);
				if(value!=null && !dictList.contains(value)) {
					dictList.add(value);
				}
			}
		}
		return dictList;
	}
	
	public String getDictKey(String dictValue, String dictName) {
		String dictKey = "";
		Map<String, String> dictMap = dict.get(dictName);
		if(dictValue!=null && dictMap!=null) {
			Iterator<String> it = dictMap.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				String value = dictMap.get(key);
				if(dictValue.equals(value)) {
					dictKey = key;
					break;
				}
			}
		}
		return dictKey;
	}
	
	public String getDictValue(String dictKey, String dictName) {
		String dictValue = "";
		Map<String, String> dictMap = dict.get(dictName);
		if(dictKey!=null && dictMap!=null) {
			dictValue = dictMap.get(dictKey);
		}
		return dictValue;
	}
}
