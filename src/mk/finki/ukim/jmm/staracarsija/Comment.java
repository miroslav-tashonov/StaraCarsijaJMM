package mk.finki.ukim.jmm.staracarsija;

import com.google.android.gms.maps.MapFragment;

public class Comment {
	  private long id;
	  public String ime;
	  public int tip ;
	  public double rating ;
	  public String coords ;
	  
	  Comment(){}

	  Comment(String ime1, int tip1 , double rating1 , String coords1){
			ime = ime1;
			tip = tip1;
			rating = rating1;
			coords = coords1;
		}
	  
	  
	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public int getTip() {
		    return tip;
		  }

		  public void setTip(int tip) {
		    this.tip = tip;
		  }
		  
		  public double getRating() {
			    return id;
			  }

			  public void setRating(double rating) {
			    this.rating = rating ;
			  }
			  
			  public String getCoords() {
				    return coords;
				  }

				  public void setCoords(String coords) {
				    this.coords = coords;
				  }

	  public String getIme() {
	    return ime;
	  }

	  public void setIme(String ime) {
	    this.ime = ime;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return ime;
	  }
	} 
