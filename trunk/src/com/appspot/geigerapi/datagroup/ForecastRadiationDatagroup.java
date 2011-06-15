package com.appspot.geigerapi.datagroup;

import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;

@XmlRootElement(name="radiation")
public class ForecastRadiationDatagroup extends MinRadiationDatagroup {
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
			super(radiation);
			
			//forecast datagroup
			this.setTemperature(radiation.getTemperature());
			this.setHumidity(radiation.getHumidity());
			this.setWeather(radiation.getWeather());
			this.setWinddirection(radiation.getWinddirection());
			this.setWindvelocity(radiation.getWindvelocity());
			this.setAltitude(radiation.getAltitude());

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
			super.writeCsvOneLineTo(buffer);
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
		
