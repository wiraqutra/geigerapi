package com.appspot.geigerapi.datagroup;

import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;

@XmlRootElement(name="radiation")
public final class ForecastRadiationDatagroup implements DataGroup {
		private Long id;
		private String datetime;
		private String label;
		private int valuetype;
		private double radiovalue;
		private double lat;
		private double lon;
		//forecast datagroup
		private Double temperature;
		private Double humidity;
		private Short weather;
		private Short winddirection;
		private Double windvelocity;
		private Double altitude;

	
		public ForecastRadiationDatagroup(){
		}
		
		public ForecastRadiationDatagroup(Radiation radiation) {
			super();
			this.id = radiation.getId();
			this.datetime = Radiation.DATE_FORMAT.format(radiation.getDatetime());
			this.label = radiation.getLabel();
			this.valuetype = radiation.getValuetype();
			this.radiovalue = radiation.getRadiovalue();
			this.lat = radiation.getLat();
			this.lon = radiation.getLon();
			
			//forecast datagroup
			this.setTemperature(radiation.getTemperature());
			this.setHumidity(radiation.getHumidity());
			this.setWeather(radiation.getWeather());
			this.setWinddirection(radiation.getWinddirection());
			this.setWindvelocity(radiation.getWindvelocity());
			this.setAltitude(radiation.getAltitude());

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

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
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
				
		public Double getTemperature() {
			return temperature;
		}

		public void setTemperature(Double temperature) {
			this.temperature = temperature;
		}

		public Double getHumidity() {
			return humidity;
		}

		public void setHumidity(Double humidity) {
			this.humidity = humidity;
		}

		public Short getWeather() {
			return weather;
		}

		public void setWeather(Short weather) {
			this.weather = weather;
		}

		public Short getWinddirection() {
			return winddirection;
		}

		public void setWinddirection(Short winddirection) {
			this.winddirection = winddirection;
		}

		public Double getWindvelocity() {
			return windvelocity;
		}

		public void setWindvelocity(Double windvelocity) {
			this.windvelocity = windvelocity;
		}

		public Double getAltitude() {
			return altitude;
		}

		public void setAltitude(Double altitude) {
			this.altitude = altitude;
		}

		public void writeCsvTo(StringBuffer buffer) {
			buffer.append(getDatetime());
			buffer.append(",");
			buffer.append(getLabel());
			buffer.append(",");
			buffer.append(getValuetype());
			buffer.append(",");
			buffer.append(getLat());
			buffer.append(",");
			buffer.append(getLon());
			buffer.append(",");
			buffer.append(getRadiovalue());
			buffer.append(",");
			if(this.getTemperature() != null) buffer.append(this.getTemperature());
			buffer.append(",");
			if(this.getHumidity() != null) buffer.append(this.getHumidity());
			buffer.append(",");
			if(this.getWeather() != null) buffer.append(this.getWeather());
			buffer.append(",");
			if(this.getWinddirection() != null) buffer.append(this.getWinddirection());
			buffer.append(",");
			if(this.getWindvelocity() != null) buffer.append(this.getWindvelocity());
			buffer.append(",");
			if(this.getAltitude() != null) buffer.append(this.getAltitude());			
			buffer.append("\r\n");
		}
		
}
		
