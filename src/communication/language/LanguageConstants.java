package communication.language;

public class LanguageConstants {

	public static final String ES="module.data.name.es";
	
	public static final String EN="module.data.name.en";
	
	public static final String PT="module.data.name.pt";
	
	public static String getAbr(String keyLanguage){
		if(ES.equalsIgnoreCase(keyLanguage)){
			return "es";
		}
		if(EN.equalsIgnoreCase(keyLanguage)){
			return "en";
		}
		if(PT.equalsIgnoreCase(keyLanguage)){
			return "pt";
		}
		return "undefined";
	}

}
