package com.webchatOil.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapToString {

	/**
	   * 将map 转为 string
	   * 
	   * @param map
	   * @return
	   */
	  public static String getUrlParamsByMap(Map<String, Object> map,
	          boolean isSort) {
	      if (map == null) {
	          return "";
	      }
	      StringBuffer sb = new StringBuffer();
	      List<String> keys = new ArrayList<String>(map.keySet());
	      if (isSort) {
	          Collections.sort(keys);
	      }
	      for (int i = 0; i < keys.size(); i++) {
	          String key = keys.get(i);
	          String value = map.get(key).toString();
	          sb.append(key + "=" + value);
	          sb.append("&");
	      }
	      String s = sb.toString();
	      if (s.endsWith("&")) {
	          s = s.substring(0, s.lastIndexOf("&"));
	      }
	      /*
	       * for (Map.Entry<String, Object> entry : map.entrySet()) {
	       * sb.append(entry.getKey() + "=" + entry.getValue()); sb.append("&"); }
	       * String s = sb.toString(); if (s.endsWith("&")) { //s =
	       * StringUtils.substringBeforeLast(s, "&"); s = s.substring(0,
	       * s.lastIndexOf("&")); }
	       */
	      return s;
	  }
}
