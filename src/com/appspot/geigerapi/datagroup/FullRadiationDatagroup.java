package com.appspot.geigerapi.datagroup;

import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;

@XmlRootElement(name="radiation")
public final class FullRadiationDatagroup implements DataGroup {
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
		//detail datagroup
		private String trnsname;
		private Double height;
		private Short valuepos;
		private Boolean calibrated;
		private Boolean publicinfo;
		private Short environment;
		private Short deflade;
		private String counterinfo;
		private String terminalinfo;
		private Double countertime;
		//notes datagroup
		private String imageurl;
		private String notes;
		private String tags;
		
		public FullRadiationDatagroup(){
		}
		
		public FullRadiationDatagroup(Radiation radiation) {
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
			//detail datagroup
			this.setTrnsname(radiation.getTrnsname());
			this.setHeight(radiation.getHeight());
			this.setValuepos(radiation.getValuepos());
			this.setCalibrated(radiation.getCalibrated());
			this.setPublicinfo(radiation.getPublicinfo());
			this.setEnvironment(radiation.getEnvironment());
			this.setDeflade(radiation.getDeflade());
			this.setCounterinfo(radiation.getCounterinfo());
			this.setTerminalinfo(radiation.getTerminalinfo());
			this.setCountertime(radiation.getCountertime());
			//notes datagroup
			this.setImageurl(radiation.getImageurl());
			this.setNotes(radiation.getNotes());
			this.setTags(radiation.getTags());
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

		public String getTrnsname() {
			return trnsname;
		}

		public void setTrnsname(String trnsname) {
			this.trnsname = trnsname;
		}

		public Double getHeight() {
			return height;
		}

		public void setHeight(Double height) {
			this.height = height;
		}

		public Short getValuepos() {
			return valuepos;
		}

		public void setValuepos(Short valuepos) {
			this.valuepos = valuepos;
		}

		public Boolean getCalibrated() {
			return calibrated;
		}

		public void setCalibrated(Boolean calibrated) {
			this.calibrated = calibrated;
		}

		public Boolean getPublicinfo() {
			return publicinfo;
		}

		public void setPublicinfo(Boolean publicinfo) {
			this.publicinfo = publicinfo;
		}

		public Short getEnvironment() {
			return environment;
		}

		public void setEnvironment(Short environment) {
			this.environment = environment;
		}

		public Short getDeflade() {
			return deflade;
		}

		public void setDeflade(Short deflade) {
			this.deflade = deflade;
		}

		public String getCounterinfo() {
			return counterinfo;
		}

		public void setCounterinfo(String counterinfo) {
			this.counterinfo = counterinfo;
		}

		public String getTerminalinfo() {
			return terminalinfo;
		}

		public void setTerminalinfo(String terminalinfo) {
			this.terminalinfo = terminalinfo;
		}

		public Double getCountertime() {
			return countertime;
		}

		public void setCountertime(Double countertime) {
			this.countertime = countertime;
		}

		public String getImageurl() {
			return imageurl;
		}

		public void setImageurl(String imageurl) {
			this.imageurl = imageurl;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
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
			//forecast datagroup
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
			buffer.append(",");
			//detail datagroup
			if(this.getTrnsname() != null) buffer.append(this.getTrnsname());
			buffer.append(",");
			if(this.getHeight() != null) buffer.append(this.getHeight());
			buffer.append(",");
			if(this.getValuepos() != null) buffer.append(this.getValuepos());
			buffer.append(",");
			if(this.getCalibrated() != null) buffer.append(this.getCalibrated());
			buffer.append(",");
			if(this.getPublicinfo() != null) buffer.append(this.getPublicinfo());
			buffer.append(",");
			if(this.getEnvironment() != null) buffer.append(this.getEnvironment());
			buffer.append(",");
			if(this.getDeflade() != null) buffer.append(this.getDeflade());
			buffer.append(",");
			if(this.getCounterinfo() != null) buffer.append(this.getCounterinfo());
			buffer.append(",");
			if(this.getTerminalinfo() != null) buffer.append(this.getTerminalinfo());
			buffer.append(",");
			if(this.getCountertime() != null) buffer.append(this.getCountertime());
			buffer.append(",");
			//notes datagroup
			if(this.getImageurl() != null) buffer.append(this.getImageurl());
			buffer.append(",");
			if(this.getNotes() != null) buffer.append(this.getNotes());
			buffer.append(",");
			if(this.getTags() != null) buffer.append(this.getTags());
			buffer.append("\r\n");
		}
		
}
		
