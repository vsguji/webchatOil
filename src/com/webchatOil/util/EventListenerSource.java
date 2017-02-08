package com.webchatOil.util;

import java.util.ArrayList;
import java.util.List;

import com.derrick.domain.InMessage;

public class EventListenerSource {
	
  private List<BarEventListener> listeners = new ArrayList<BarEventListener>();
  public EventListenerSource(){}
  
  public void addListener(BarEventListener someonListener){
	  listeners.add(someonListener);
  }
  
  public void notifyListenerEvent(){
	  for (BarEventListener eventListener:listeners){
		  EventListenerObject listener = new EventListenerObject(this);
		  eventListener.process(listener);
	  }
  }
  
  public void notifyListenerEventWithMessage(InMessage msg){
	  for (BarEventListener eventListener:listeners){
		  EventListenerObject listener = new EventListenerObject(this);
		  listener.setMsg(msg);
		  eventListener.process(listener);
	  }
  }
}
