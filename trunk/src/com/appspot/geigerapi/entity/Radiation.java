package com.appspot.geigerapi.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.auth.Authorization;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public final class Radiation {
		public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	
		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
		private Long id;
		
		@Persistent
		private String email;

		//min datagroup
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
		
		//forecast datagroup
		@Persistent
		private Double temperature;
		@Persistent
		private Double humidity;
		@Persistent
		private Short weather;
		@Persistent
		private Short winddirection;
		@Persistent
		private Double windvelocity;
		@Persistent
		private Double altitude;

		//detail datagroup
		@Persistent
		private String trnsname;
		@Persistent
		private Double height;
		@Persistent
		private Short valuepos;
		@Persistent
		private Boolean calibrated;
		@Persistent
		private Boolean publicinfo;
		@Persistent
		private Short environment;
		@Persistent
		private Short deflade;
		@Persistent
		private String counterinfo;
		@Persistent
		private String terminalinfo;
		@Persistent
		private Double countertime;
		
		//notes datagroup
		@Persistent
		private String imageurl;
		@Persistent
		private String notes;
		@Persistent
		private String tags;

		public Radiation(){
			this(Authorization.getEmail(),new Date(),"",0,0.0d,0.0d,0.0d);
		}
		
		public Radiation(String email,Date datetime, String label, int valuetype,
				double radiovalue, double latitude, double longtitude) {
			super();
			this.email = email;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		private static final String dupCond = "datetime==pDatetime && label==pLabel && valuetype==pValuetype && lat==pLat && lon==pLon && radiovalue==pRadiovalue";			
		private static final String dupDeclare = "java.util.Date pDatetime,String pLabel,int pValuetype,double pLat,double pLon,double pRadiovalue";			
		public boolean hasDulicated(PersistenceManager pm){
			Query query = pm.newQuery(getClass(),dupCond);
			query.declareParameters(dupDeclare);
			@SuppressWarnings("unchecked")
			List<Radiation> radiations = (List<Radiation>) query.executeWithArray(datetime,label,valuetype,lat,lon,radiovalue);
			return !radiations.isEmpty();
		}

		public void checkOwned() {
			if(!getEmail().equals(Authorization.getEmail())){
				throw new WebApplicationException(403);
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 * Automatic Generated by Eclipse.
		 * Checking all properties except "id" and "email"
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((altitude == null) ? 0 : altitude.hashCode());
			result = prime * result
					+ ((calibrated == null) ? 0 : calibrated.hashCode());
			result = prime * result
					+ ((counterinfo == null) ? 0 : counterinfo.hashCode());
			result = prime * result
					+ ((countertime == null) ? 0 : countertime.hashCode());
			result = prime * result
					+ ((datetime == null) ? 0 : datetime.hashCode());
			result = prime * result
					+ ((deflade == null) ? 0 : deflade.hashCode());
			result = prime * result
					+ ((environment == null) ? 0 : environment.hashCode());
			result = prime * result
					+ ((height == null) ? 0 : height.hashCode());
			result = prime * result
					+ ((humidity == null) ? 0 : humidity.hashCode());
			result = prime * result
					+ ((imageurl == null) ? 0 : imageurl.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			long temp;
			temp = Double.doubleToLongBits(lat);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(lon);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((notes == null) ? 0 : notes.hashCode());
			result = prime * result
					+ ((publicinfo == null) ? 0 : publicinfo.hashCode());
			temp = Double.doubleToLongBits(radiovalue);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((tags == null) ? 0 : tags.hashCode());
			result = prime * result
					+ ((temperature == null) ? 0 : temperature.hashCode());
			result = prime * result
					+ ((terminalinfo == null) ? 0 : terminalinfo.hashCode());
			result = prime * result
					+ ((trnsname == null) ? 0 : trnsname.hashCode());
			result = prime * result
					+ ((valuepos == null) ? 0 : valuepos.hashCode());
			result = prime * result + valuetype;
			result = prime * result
					+ ((weather == null) ? 0 : weather.hashCode());
			result = prime * result
					+ ((winddirection == null) ? 0 : winddirection.hashCode());
			result = prime * result
					+ ((windvelocity == null) ? 0 : windvelocity.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 * Automatic Generated by Eclipse.
		 * Checking all properties except "id" and "email"
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Radiation other = (Radiation) obj;
			if (altitude == null) {
				if (other.altitude != null)
					return false;
			} else if (!altitude.equals(other.altitude))
				return false;
			if (calibrated == null) {
				if (other.calibrated != null)
					return false;
			} else if (!calibrated.equals(other.calibrated))
				return false;
			if (counterinfo == null) {
				if (other.counterinfo != null)
					return false;
			} else if (!counterinfo.equals(other.counterinfo))
				return false;
			if (countertime == null) {
				if (other.countertime != null)
					return false;
			} else if (!countertime.equals(other.countertime))
				return false;
			if (datetime == null) {
				if (other.datetime != null)
					return false;
			} else if (!datetime.equals(other.datetime))
				return false;
			if (deflade == null) {
				if (other.deflade != null)
					return false;
			} else if (!deflade.equals(other.deflade))
				return false;
			if (environment == null) {
				if (other.environment != null)
					return false;
			} else if (!environment.equals(other.environment))
				return false;
			if (height == null) {
				if (other.height != null)
					return false;
			} else if (!height.equals(other.height))
				return false;
			if (humidity == null) {
				if (other.humidity != null)
					return false;
			} else if (!humidity.equals(other.humidity))
				return false;
			if (imageurl == null) {
				if (other.imageurl != null)
					return false;
			} else if (!imageurl.equals(other.imageurl))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (Double.doubleToLongBits(lat) != Double
					.doubleToLongBits(other.lat))
				return false;
			if (Double.doubleToLongBits(lon) != Double
					.doubleToLongBits(other.lon))
				return false;
			if (notes == null) {
				if (other.notes != null)
					return false;
			} else if (!notes.equals(other.notes))
				return false;
			if (publicinfo == null) {
				if (other.publicinfo != null)
					return false;
			} else if (!publicinfo.equals(other.publicinfo))
				return false;
			if (Double.doubleToLongBits(radiovalue) != Double
					.doubleToLongBits(other.radiovalue))
				return false;
			if (tags == null) {
				if (other.tags != null)
					return false;
			} else if (!tags.equals(other.tags))
				return false;
			if (temperature == null) {
				if (other.temperature != null)
					return false;
			} else if (!temperature.equals(other.temperature))
				return false;
			if (terminalinfo == null) {
				if (other.terminalinfo != null)
					return false;
			} else if (!terminalinfo.equals(other.terminalinfo))
				return false;
			if (trnsname == null) {
				if (other.trnsname != null)
					return false;
			} else if (!trnsname.equals(other.trnsname))
				return false;
			if (valuepos == null) {
				if (other.valuepos != null)
					return false;
			} else if (!valuepos.equals(other.valuepos))
				return false;
			if (valuetype != other.valuetype)
				return false;
			if (weather == null) {
				if (other.weather != null)
					return false;
			} else if (!weather.equals(other.weather))
				return false;
			if (winddirection == null) {
				if (other.winddirection != null)
					return false;
			} else if (!winddirection.equals(other.winddirection))
				return false;
			if (windvelocity == null) {
				if (other.windvelocity != null)
					return false;
			} else if (!windvelocity.equals(other.windvelocity))
				return false;
			return true;
		}


}
		
