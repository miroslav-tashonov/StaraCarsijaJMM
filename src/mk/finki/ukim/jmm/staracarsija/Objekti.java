
	
	package mk.finki.ukim.jmm.staracarsija;

	import android.app.*;
	import android.content.*;
	import android.os.*;
	import android.util.Log;
	import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

	public class Objekti extends ListActivity {

		public final static String EXTRA_MESSAGE1 = "mk.finki.ukim.jmm.staracarsija.MESSAGE";
		float[] ratings = new float[]{5,4,5,4,5,4,5,3,1,2,3};
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.rating_main);
			
			Intent intent = getIntent();
		    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
			
			String[] values =new String[]{};
   
		    //Тука ќе се лоадираат локалите од база
		    if(message.equals("Ресторани"))
		    {
		     values = new String[] { "Ресторан1", "Ресторан2", "Ресторан3", "Ресторан4", "Ресторан5", "Ресторан6","Ресторан7", "Ресторан8", "Ресторан9",
		 	        "Ресторан10", "Ресторан11" };
		    }
		    else if(message.equals("Златари"))
		    {
		     values = new String[] { "Златара1", "Златара2", "Златара3", "Златара4", "Златара5", "Златара6","Златара7", "Златара8", "Златара9",
		        "Златара10", "Златара11" };
		    }
		    else if(message.equals("Кафулиња и барови"))
		    {
		     values = new String[] { "Кафиќ1", "Кафиќ2", "Кафиќ3", "Кафиќ4", "Кафиќ5", "Кафиќ6","Кафиќ7", "Кафиќ8", "Кафиќ9",
		        "Кафиќ10", "Кафиќ11" };
		    }
		    else if(message.equals("Споменици и музеи"))
		    {
		     values = new String[] { "Знаменитост1", "Знаменитост2", "Знаменитост3", "Знаменитост4", "Знаменитост5", "Знаменитост6","Знаменитост7", "Знаменитост8", "Знаменитост9",
		        "Знаменитост10", "Знаменитост11" };
		    }
		    else if(message.equals("Занаетчии"))
		    {
		     values = new String[] { "Занаетчија1", "Занаетчија2", "Занаетчија3", "Занаетчија4", "Занаетчија5", "Занаетчија6","Занаетчија7", "Занаетчија8", "Занаетчија9",
		        "Занаетчија10", "Занаетчија11" };
		    }
			
		   
			
			RatingAdapter adapter = new RatingAdapter(this, R.layout.activity_objekti, values);
			setListAdapter(adapter);
			
			
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
