package com.skilldistillery.filmquery.entities;

public class Language {

	private int languageId;
	private String languageName;
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + languageId;
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (languageId != other.languageId)
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return languageName; 
//		return "Language ID: " + languageId + "\n Language: " + languageName + "";
//		return "Language [languageId=" + languageId + ", languageName=" + languageName + "]";
	} 
	
}
