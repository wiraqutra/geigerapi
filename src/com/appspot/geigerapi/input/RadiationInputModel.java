package com.appspot.geigerapi.input;

import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;


@XmlRootElement
public final class RadiationInputModel {
	private String datetime;
	private String label;
	private Integer valuetype;
	private Double radiovalue;
	private Double lat;
	private Double lon;

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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValuetype() {
		return valuetype;
	}

	public void setValuetype(Integer valuetype) {
		this.valuetype = valuetype;
	}

	public Double getRadiovalue() {
		return radiovalue;
	}

	public void setRadiovalue(Double radiovalue) {
		this.radiovalue = radiovalue;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
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

	public Radiation create(){
		//TODO Check more strictly.
		if(getLabel() == null) throwUnprocessableEntity("Require field: label");
		if(getRadiovalue() == null) throwUnprocessableEntity("Require field: radiovalue");
		if(getLat() == null) throwUnprocessableEntity("Require field: lat");
		if(getLon() == null) throwUnprocessableEntity("Require field: lon");
		Radiation radiation = new Radiation();
		if(getDatetime() == null){
			radiation.setDatetime(new Date());
		}else{
			try {
				radiation.setDatetime(Radiation.DATE_FORMAT.parse(getDatetime()));
			} catch (ParseException e) {
				throwUnprocessableEntity("Couldn't parse datetime");
			}
		}
		radiation.setLabel(getLabel());
		radiation.setValuetype(getValuetype());
		radiation.setRadiovalue(getRadiovalue());
		radiation.setLat(getLat());
		radiation.setLon(getLon());
		
		//forecast datagroup
		radiation.setTemperature(this.getTemperature());
		radiation.setHumidity(this.getHumidity());
		radiation.setWeather(this.getWeather());
		radiation.setWinddirection(this.getWinddirection());
		radiation.setWindvelocity(this.getWindvelocity());
		radiation.setAltitude(this.getAltitude());

		//detail datagroup
		radiation.setTrnsname(this.getTrnsname());
		radiation.setHeight(this.getHeight());
		radiation.setValuepos(this.getValuepos());
		radiation.setCalibrated(this.getCalibrated());
		radiation.setPublicinfo(this.getPublicinfo());
		radiation.setEnvironment(this.getEnvironment());
		radiation.setDeflade(this.getDeflade());
		radiation.setCounterinfo(this.getCounterinfo());
		radiation.setTerminalinfo(this.getTerminalinfo());
		radiation.setCountertime(this.getCountertime());

		//notes datagroup
		radiation.setImageurl(this.getImageurl());
		radiation.setNotes(this.getNotes());
		radiation.setTags(this.getTags());

		return radiation;
	}

	private void throwUnprocessableEntity(String msg) {
		throw new WebApplicationException(Response.status(422).entity(msg).build());
	}
}
