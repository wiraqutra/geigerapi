package com.appspot.geigerapi;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GeigerData {
		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
		private Long id;
		
		@Persistent
		private Date datetime;

		@Persistent
		private String label;
		
		@Persistent
		private int valuetype;

		@Persistent
		private double radiovalue;

		@Persistent
		private double lat;

		@Persistent
		private double lon;
		
		public GeigerData(Date datetime, String label, int valuetype,
				double radiovalue, double latitude, double longtitude) {
			super();
			this.datetime = datetime;
			this.label = label;
			this.valuetype = valuetype;
			this.radiovalue = radiovalue;
			this.lat = latitude;
			this.lon = longtitude;
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
		
}
		
