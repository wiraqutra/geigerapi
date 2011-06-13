package com.appspot.geigerapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public interface OutputDataModel {

	public void writeCsvTo(StringBuffer buffer);
	
}
