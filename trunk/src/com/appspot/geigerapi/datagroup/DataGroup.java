package com.appspot.geigerapi.datagroup;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public interface DataGroup {

	public void writeCsvTo(StringBuffer buffer);
	
}
