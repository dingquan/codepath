package codepath.apps.simpletodo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class EditItemActivity extends ActionBarActivity {

	private EditText etEditItem;
	private int pos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		etEditItem = (EditText)findViewById(R.id.etEditItem);
		etEditItem.setText(getIntent().getStringExtra("itemText"));
		etEditItem.setSelection(etEditItem.getText().length());
		pos = getIntent().getIntExtra("position", -1);
		if (pos == -1){
			throw new IllegalArgumentException("Invalid edit item position");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void saveTodoItem(View v){
		Intent data = new Intent();
		data.putExtra("savedItem", etEditItem.getText().toString());
		data.putExtra("position", pos);
		this.setResult(RESULT_OK, data);
		finish();
	}
}
