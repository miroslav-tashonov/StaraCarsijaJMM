package mk.finki.ukim.jmm.staracarsija;

import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private CommentsDataSource datasource;
	public final static String EXTRA_MESSAGE = "mk.finki.ukim.jmm.staracarsija.MESSAGE";
  public void onCreate(Bundle icicle) {
	  super.onCreate(icicle);    
	   

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView

    String[] values = new String[] { "Ресторани", "Златари", "Кафулиња и барови",
        "Споменици и музеи",
        "Занаетчии" };
    
	  MySimpleAdapter adapter = new MySimpleAdapter(this, values);
	  setListAdapter(adapter);

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

  @Override
  protected void onListItemClick(ListView l, View v,int position, long id) {
	 
	        
	        String item = (String) getListAdapter().getItem(position);
            
    		Intent intent = new Intent(MainActivity.this, Objekti.class);
    		intent.putExtra(EXTRA_MESSAGE, item);
    		startActivity(intent);
  }
} 