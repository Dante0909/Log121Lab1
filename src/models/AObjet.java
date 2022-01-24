package models;

import java.util.ArrayList;

public abstract class AObjet {
	
	private String baseIconPath;
	
	public AObjet(ArrayList<String> p) throws Exception {
		if(p!= null && p.size()>0) {
			setPath(p.get(0));
		}
		else {
			throw new Exception("Invalid paths for icons");			
		} 
	}
	public AObjet(String p) {
		setPath(p);
	}
	public String getIcon() {
		return baseIconPath;
	}
	private void setPath(String b) 
	{
		baseIconPath = b;
	}
	
}