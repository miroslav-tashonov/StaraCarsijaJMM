package mk.finki.ukim.jmm.staracarsija;

import android.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;

  public MySimpleAdapter(Context context, String[] values) {
    super(context, R.layout.activity_main , values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.activity_main, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.label);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    textView.setText(values[position]);
    // Change the icon for Windows and iPhone
    String s = values[position];
    if (s.startsWith("Ресторани")) {
      imageView.setImageResource(R.drawable.restoran);
    }
    else if (s.startsWith("Златари")) {
        imageView.setImageResource(R.drawable.gold);
      }
    else if (s.startsWith("Споменици")) {
        imageView.setImageResource(R.drawable.spomenik);
      }
    else if (s.startsWith("Занаетчии")) {
        imageView.setImageResource(R.drawable.zanaetcii50);
      }
    else {
      imageView.setImageResource(R.drawable.kafe);
    }

    return rowView;
  }
} 