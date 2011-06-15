package com.appspot.geigerapi.datagroup;

import javax.xml.bind.annotation.XmlRootElement;

import com.appspot.geigerapi.entity.Radiation;

@XmlRootElement(name="radiation")
public class NotesRadiationDatagroup extends MinRadiationDatagroup {
		//notes datagroup
		private String imageurl;
		private String notes;
		private String tags;
		
		public NotesRadiationDatagroup(){
		}
		
		public NotesRadiationDatagroup(Radiation radiation) {
			super(radiation);
			//notes datagroup
			this.setImageurl(radiation.getImageurl());
			this.setNotes(radiation.getNotes());
			this.setTags(radiation.getTags());
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
			super.writeCsvOneLineTo(buffer);

			//notes datagroup
			buffer.append(",");
			if(this.getImageurl() != null) buffer.append(this.getImageurl());
			buffer.append(",");
			if(this.getNotes() != null) buffer.append(this.getNotes());
			buffer.append(",");
			if(this.getTags() != null) buffer.append(this.getTags());

			buffer.append("\r\n");
		}
		
}
		
