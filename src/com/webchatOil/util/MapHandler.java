package com.webchatOil.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MapHandler {

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
	  
	  
	  /** 
	   * 方法名称:transStringToMap 
	   * 传入参数:mapString 形如 username'chenziwen^password'1234 
	   * 返回值:Map 
	  */  
	  public static Map transStringToMap(String mapString){  
	    Map map = new HashMap();  
	    java.util.StringTokenizer items;  
	    for(StringTokenizer entrys = new StringTokenizer(mapString, "^");
	    		entrys.hasMoreTokens();   
	      map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))  
	        items = new StringTokenizer(entrys.nextToken(), "'");  
	    return map;  
	  }  
}
