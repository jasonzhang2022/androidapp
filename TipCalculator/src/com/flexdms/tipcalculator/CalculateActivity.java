package com.flexdms.tipcalculator;

import java.util.Formatter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

///TextWatcher,
public class CalculateActivity extends Activity implements TextWatcher {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		((EditText) findViewById(R.id.totalCost)).addTextChangedListener(this);
		((EditText) findViewById(R.id.tax)).addTextChangedListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		((EditText) findViewById(R.id.totalCost)).requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculate, menu);
		return true;
	}

	public Double getTotal() {
		EditText editText = (EditText) findViewById(R.id.totalCost);
		String text = editText.getText().toString();
		if (text == null || text.trim().length() == 0) {
			return null;
		}
		return Double.valueOf(text);

	}

	public Double getTax() {
		EditText editText = (EditText) findViewById(R.id.tax);
		String text = editText.getText().toString();
		if (text == null || text.trim().length() == 0) {
			return null;
		}
		return Double.valueOf(text);
	}

	public void calculateTip() {
		Double total = getTotal();
		Double tax = getTax();
		if (tax == null) {
			tax = 0d;
		}
		if (total == null) {
			return;
		}
		int percent = 0;
		int increment = 1;

		Formatter formatter = new Formatter();
		for (int i = 0; i < 31; i++) {
			double t = total * (100 + percent) / 100 + tax;
			formatter.format("%d%%: %.2f\n", percent, t);
			percent += increment;
		}
		((TextView) findViewById(R.id.tip)).setText(formatter.toString());
		formatter.close();

	}

	@Override
	public void afterTextChanged(Editable arg0) {
		calculateTip();

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	}
}
