package mk.finki.ukim.jmm.staracarsija;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Details extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
	    String message = intent.getStringExtra(Objekti.EXTRA_MESSAGE1);
		
		TextView text = (TextView) findViewById(R.id.textViewSome);
		text.setText("Додади рејтинг на "+message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
