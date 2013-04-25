
	
	package mk.finki.ukim.jmm.staracarsija;

	import android.webkit.WebViewClient;
	import android.graphics.Bitmap;
	import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.*;
	import android.content.*;
	import android.os.*;
	import android.util.Log;
	import android.view.*;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.*;

	public class Objekti extends ListActivity {
		String nekojString ;
		private static Context context;
		private static final String TAG = "Objekti";
		ProgressDialog progress ;
		
		public final static String EXTRA_MESSAGE1 = "mk.finki.ukim.jmm.staracarsija.MESSAGE";
		float[] ratings ;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			context = getApplicationContext();
			progress = new ProgressDialog(this);
			progress.setTitle("Loading");
			progress.setMessage("Please wait to load locations ...");
			progress.show();
			  
			
		    
		}
		@Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
			String item = (String) getListAdapter().getItem(position);
		    Intent intent = new Intent(this, Details.class);
		    intent.putExtra(EXTRA_MESSAGE1, item);
		    startActivity(intent);
	    }

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			readWebpage();
			
			
			
			//Metodot vo koj kje se postavi konekcija do bazata na podatoci.
			//Konekcijata se vospostavuva vo ovoj metod zatoa shto ova e metodot koj sledi po rekreiranjeto na
			//aktivnosta, odnosno po onStop(). Kodot za metodot onRestart kje bide postaven vo ovoj metod
			//zatoa shto neposredno po onRestart aktivnosta preminuva vo onStart
		}
	  
	  @Override
	  protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	  }
	  
	  @Override
	  protected void onPause() {
		  // TODO Auto-generated method stub
		  super.onPause();
		  
		  //Metod vo koj se zachuvuvaat podatocite na koj korisnikot ochekuva da
		  //im bide napraveno auto-save
	  }
	  
	  @Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			
			//se zachuvuvaat site podatoci vo baza zatoa shto ova e posledniot metod pred da aktivnosta da 
			//bide unishtena so onDestroy ili da bide prenesena do onStart
		}
	  
	  @Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	  
	  @Override
	protected void onRestoreInstanceState(Bundle state) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(state);
	}
		
		
		
		
		
		private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
			private CommentsDataSource datasource;
		    @Override
		    protected String doInBackground(String... urls) {
		    	

		    	datasource = new CommentsDataSource(Objekti.this);
			    datasource.open();
			    				
				Intent intent = getIntent();
			    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
			    
    	        String my = "";
    	        SharedPreferences mPrefs = getSharedPreferences("change", MODE_APPEND);
    		    String mString = mPrefs.getString("change", "false");    	        
    	        
    	        if(mString == "false")
    	        {
    	        try {
    	        	JSONParser jParser = new JSONParser();
        	        // getting JSON string from URL
        	        JSONObject json = jParser.getJSONFromUrl(urls[0]);
    	            // Getting Array of Contacts 
    	            JSONArray response = json.getJSONObject("response").getJSONArray("groups");
    	            
    	            
    	            // looping through All Venues
    	            for(int i = 0; i < response.length(); i++){
    	                JSONObject c = response.getJSONObject(i);
    	 
    	                // Storing each json item in variable
    	                JSONArray venues = c.getJSONArray("items");
    	                for(int j = 0; j < venues.length(); j++){
    	                	
    	                	JSONObject cc = venues.getJSONObject(j);
    	                	
    	                	String name = cc.getString("name");
    	                	JSONObject loc = cc.getJSONObject("location");
    	                	String ll = loc.getString("lat")+","+loc.getString("lng");
    	                	JSONObject stats = cc.getJSONObject("stats");
    	                	float rating = stats.getInt("tipCount")/2;
    	                	JSONArray cat = cc.getJSONArray("categories");
    	                	JSONObject obj0 = cat.getJSONObject(0);
    	                	String imeCat = obj0.getString("name");
    	                	
    	                	Comment comment = new Comment();
    	    			    comment.setIme(name);
    	    			    if(imeCat.contains("Restaurant") || imeCat.contains("Beer") || imeCat.contains("Brewery"))
    	    			    {
    	    			    	comment.setTip(0);
    	    			    }
    	    			    else if(imeCat.contains("Gold"))
    	    			    {
    	    			    	comment.setTip(1);
    	    			    }
    	    			    else if(imeCat.contains("Café")||imeCat.contains("Coffee")||imeCat.contains("Bar")||imeCat.contains("Tea")||imeCat.contains("Club"))
    	    			    {
    	    			    	comment.setTip(2);
    	    			    }
    	    			    else if(imeCat.contains("Antique"))
    	    			    {
    	    			    	comment.setTip(4);
    	    			    }
    	    			    else
    	    			    {
    	    			    	comment.setTip(3);
    	    			    }
    	    			    comment.setRating(rating);
    	    			    comment.setCoords(ll);
    	                	datasource.createComment(comment);
    	                	
    	                	if(j ==0 ){
    	                		my = my + name;
    	                	}
    	                	my = my + " - " + name ; 
    	                }
    	                
       	            }
    	            
    	        } catch (JSONException e) {
    	            e.printStackTrace();
    	        }
    	        	SharedPreferences.Editor mEditor = mPrefs.edit();
    	        	mEditor.putString("change", "true").commit();
    	        }
    	        
    	        
    	        List<Comment> values1 = datasource.getAllComments();
			    ratings = new float[values1.size()];
			    
				String[] values =new String[]{};
				my = "";
			    //Тука ќе се лоадираат локалите од база
			    if(message.equals("Ресторани"))
			    {
			    		    for(int i = 0 ; i < values1.size();i++){
			    		    	if(i==0){
			    		    		if(values1.get(i).getTip()==0)
			    		    		{
			    		    			my = values1.get(i).getIme()+" " +values1.get(i).getCoords();
			    		    			ratings[0] = (float) values1.get(i).getRating()/2;
			    		    		}
			    		    	}
			    		    	else
			    		    	{
			    		    		if(values1.get(i).getTip()==0)
			    		    		{
			    		    			my = my + "--"+ values1.get(i).getIme()+" " +values1.get(i).getCoords();
			    		    			ratings[i] = (float) values1.get(i).getRating()/2;
			    		    		}
			    		    	}
			    		    }
			        
			    }
			    else if(message.equals("Златари"))
			    {
			    	for(int i = 0 ; i < values1.size();i++){
	    		    	if(i==0){
	    		    		if(values1.get(i).getTip()==1)
	    		    		{
	    		    			my = values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[0] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    	else
	    		    	{
	    		    		if(values1.get(i).getTip()==1)
	    		    		{
	    		    			my = my + "--"+ values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[i] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    }
			    }
			    else if(message.equals("Кафулиња и барови"))
			    {
			    	for(int i = 0 ; i < values1.size();i++){
	    		    	if(i==0){
	    		    		if(values1.get(i).getTip()==2)
	    		    		{
	    		    			my = values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[0] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    	else
	    		    	{
	    		    		if(values1.get(i).getTip()==2)
	    		    		{
	    		    			my = my + "--"+ values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[i] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    }
			    }
			    else if(message.equals("Споменици и музеи"))
			    {
			    	for(int i = 0 ; i < values1.size();i++){
	    		    	if(i==0){
	    		    		if(values1.get(i).getTip()==3)
	    		    		{
	    		    			my = values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[0] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    	else
	    		    	{
	    		    		if(values1.get(i).getTip()==3)
	    		    		{
	    		    			my = my + "--"+ values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[i] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    }
			    }
			    else if(message.equals("Занаетчии"))
			    {
			    	for(int i = 0 ; i < values1.size();i++){
	    		    	if(i==0){
	    		    		if(values1.get(i).getTip()==4)
	    		    		{
	    		    			my = values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[0] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    	else
	    		    	{
	    		    		if(values1.get(i).getTip()==4)
	    		    		{
	    		    			my = my + "--"+ values1.get(i).getIme()+" " +values1.get(i).getCoords();
	    		    			ratings[i] = (float) values1.get(i).getRating()/2;
	    		    		}
	    		    	}
	    		    }
			    }
				
				return my;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		      //textView.setText(result);
		    	
		    	
		    	RatingAdapter adapter = new RatingAdapter(Objekti.this, R.layout.activity_objekti, result.split("--"));
				setListAdapter(adapter);
				
				progress.dismiss();
		    }
		  }

		  public void readWebpage() {
		    DownloadWebPageTask task = new DownloadWebPageTask();
		    task.execute(new String[] { "https://api.foursquare.com/v2/venues/search?ll=42.000453,21.436043&client_id=OQ3UZKC5P33EEWKP3CWRCQYZZZ020LZ5I5EN4BLRR4MK23IC&client_secret=CY0GGCJ0CY25DMGYIGEWBJ1LRF3AVCTS1M2N3KMLWR1UJ5QA" });

		  }
		
		  
		  class RatingAdapter extends ArrayAdapter<String> {
				Context ctx;
				String[] values;
				public RatingAdapter(Context context, int textViewResourceId, String[] objects) {
					super(context, textViewResourceId, objects);
					ctx = context;
					values = objects;
				}
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					View row = convertView;
					Intent intent = getIntent();
				    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

				    
				    
					if(row==null) { // Object reuse
						LayoutInflater inflater = getLayoutInflater();
						row = inflater.inflate(R.layout.activity_objekti, parent, false);
					}
					if (message.startsWith("Ресторани")) {
						ImageView imageView = (ImageView) row.findViewById(R.id.icona);
					    imageView.setImageResource(R.drawable.restoran);
					    }
					    else if (message.startsWith("Златари")) {
					    	ImageView imageView = (ImageView) row.findViewById(R.id.icona);
						    imageView.setImageResource(R.drawable.gold);
					      }
					    else if (message.startsWith("Споменици")) {
					    	ImageView imageView = (ImageView) row.findViewById(R.id.icona);
						    imageView.setImageResource(R.drawable.spomenik);
					      }
					    else if (message.startsWith("Занаетчии")) {
					    	ImageView imageView = (ImageView) row.findViewById(R.id.icona);
						    imageView.setImageResource(R.drawable.zanaetcii50);
					      }
					    else {
					    	ImageView imageView = (ImageView) row.findViewById(R.id.icona);
						    imageView.setImageResource(R.drawable.kafe);
					    }
					
					
					
					TextView tv = (TextView)row.findViewById(R.id.textView1);
					tv.setText(values[position]);
					RatingBar rb = (RatingBar) row.findViewById(R.id.ratingBar1);
					rb.setRating(ratings[position]);
					rb.setTag(position);
					rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
						public void onRatingChanged(RatingBar ratingBar, float rating,
								boolean fromUser) {
							if(!fromUser) return;
							int index = (Integer)(ratingBar.getTag());
							ratings[index] = rating;
						}
					});
					return row;
				}
				
				
			}
		
	}
