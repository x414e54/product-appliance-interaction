package nfc.MicowaveTimer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MicowaveTimer extends Activity {
	int numberpos = 0;
	int strength = 0;
	private Handler handler = new Handler();
	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private TextView display;
	private Button stop;
	private Button start;

	int time = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mAdapter = NfcAdapter.getDefaultAdapter(this);

		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		display = (TextView) findViewById(R.id.display);
		stop = (Button) findViewById(R.id.stop);
		start = (Button) findViewById(R.id.start);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("Foreground dispatch", "Foreground NFC dispatch enabled");
		mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
	}

	private Runnable clockTimer = new Runnable() {
		@Override
		public void run() {
			if (time == 0) {
				clearTime(null);
			}
			time--;
			UpdateDisplay();
			handler.postDelayed(this, 1000);
		}
	};

	public void startTimer(View view) {
		if (numberpos < 4)
			return;
		handler.removeCallbacks(clockTimer);
		stop.setText("STOP");
		if (start.getText().toString().compareTo("START") == 0) {
			start.setText("PAUSE");
			handler.postDelayed(clockTimer, 1000);
		} else {
			start.setText("START");
		}
	}

	public void setTime(View view) {
		int value = Integer.parseInt(((TextView) view).getText().toString());
		if (numberpos < 4) {
			time += value
					* (((numberpos < 2) ? 60 : 1) * (((numberpos % 2 == 0) ? 10
							: 1)));
			Log.i("TEST", time + "");
			numberpos++;
		}
		UpdateDisplay();
	}

	public void clearTime(View view) {
		stop.setText("CANCEL");
		start.setText("START");
		handler.removeCallbacks(clockTimer);
		time = 0;
		numberpos = 0;
		UpdateDisplay();
	}

	public void UpdateDisplay() {
		display.setText(String.format("%s%s:%s%s",
				(numberpos > 0) ? (time / 600) : "-",
				(numberpos > 1) ? ((time / 60) % 10) : "-",
				(numberpos > 2) ? ((time % 60) / 10) : "-",
				(numberpos > 3) ? (time % 10) : "-"));
	}

	public void onNewIntent(Intent intent) {
		Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
		Uri uri = intent.getData();
		if (uri.getScheme().compareTo("cook") == 0) {
			String[] segments = uri.getSchemeSpecificPart().split("\\.");
			if (segments[0].compareTo("mw") == 0) {
				time = Integer.parseInt(segments[2]);
				strength = Integer.parseInt(segments[1]);
				numberpos = 4;
				UpdateDisplay();
				RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
				group.check(group.getChildAt(strength).getId());
			}
		}
	}
}