package com.appspot.geigerapi.datagroup;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;

@XmlRootElement(name="radiation")
public final class MinRadiationDatagroup implements DataGroup {
		private Long id;
		private Date datetime;
		private String label;
		private int valuetype;
		private double radiovalue;
		private double lat;
		private double lon;
		
		public MinRadiationDatagroup(){
		}
		
		public MinRadiationDatagroup(Radiation radiation) {
			super();
			this.id = radiation.getId();
			this.datetime = radiation.getDatetime();
			this.label = radiation.getLabel();
			this.valuetype = radiation.getValuetype();
			this.radiovalue = radiation.getRadiovalue();
			this.lat = radiation.getLat();
			this.lon = radiation.getLon();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public Date getDatetime() {
			return datetime;
		}

		public void setDatetime(Date datetime) {
			this.datetime = datetime;
		}

		public int getValuetype() {
			return valuetype;
		}

		public void setValuetype(int valuetype) {
			this.valuetype = valuetype;
		}

		public double getRadiovalue() {
			return radiovalue;
		}

		public void setRadiovalue(double radiovalue) {
			this.radiovalue = radiovalue;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double latitude) {
			this.lat = latitude;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double longtitude) {
			this.lon = longtitude;
		}
				
		public void writeCsvTo(StringBuffer buffer) {
			buffer.append(Radiation.DATE_FORMAT.format(getDatetime()));
			buffer.append(",");
			buffer.append(getLabel());
			buffer.append(",");
			buffer.append((getValuetype()));
			buffer.append(",");
			buffer.append(getLat());
			buffer.append(",");
			buffer.append(getLon());
			buffer.append(",");
			buffer.append(getRadiovalue());
			buffer.append("\r\n");
		}
		
}
		
