package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText etBillAmt;
	private Float billAmt;
	private Float tipPercent;
//	private Button tip10;
//	private Button tip15;
//	private Button tip20;
	private TextView totalTip;
	private TextView finalTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etBillAmt = (EditText)findViewById(R.id.etBillAmt);
//		tip10 = (Button)findViewById(R.id.btTip10);
//		tip15 = (Button)findViewById(R.id.btTip15);
//		tip20 = (Button)findViewById(R.id.btTip20);
		totalTip = (TextView)findViewById(R.id.tvTipAmt);
		finalTotal = (TextView)findViewById(R.id.tvFinal);
		
	}
	
	public void calculateTip(View v)
	{
		
		billAmt = Float.valueOf(etBillAmt.getText().toString());
		
		switch (v.getId()){
		case R.id.btTip10:
			tipPercent = 0.1f;
			break;
		case R.id.btTip15:
			tipPercent = 0.15f;
			break;
		case R.id.btTip20:
			tipPercent = 0.2f;
			break;
		default:
			tipPercent = 0f;
		}
		Float totalTipAmt = billAmt * tipPercent;
		totalTip.setText(totalTipAmt.toString());
		Float finalTotalAmt = billAmt + totalTipAmt;
		finalTotal.setText(finalTotalAmt.toString());
	}
}
