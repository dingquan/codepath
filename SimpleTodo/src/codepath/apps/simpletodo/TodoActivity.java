package codepath.apps.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Build;

public class TodoActivity extends ActionBarActivity {
	private static final int REQUEST_CODE_EDIT = 1;
	
	private ListView lvItems;
	private ArrayList<String> items;
	private ArrayAdapter<String> itemsAdapter;
	private EditText etNewItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);

		lvItems = (ListView)findViewById(R.id.lvItems);
		etNewItem = (EditText)findViewById(R.id.etNewItem);
//		items = new ArrayList<String>();
		items = readItems();
		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		setupListViewListener();
	}

	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
				items.remove(pos);
				itemsAdapter.notifyDataSetInvalidated();
				saveItems();
				return true;
			}
			
		});
		
		lvItems.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
				i.putExtra("itemText", items.get(position));
				i.putExtra("position", position);
				startActivityForResult(i, REQUEST_CODE_EDIT);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
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

	public void addTodoItem(View v){
		String itemText = etNewItem.getText().toString();
		itemsAdapter.add(itemText);;
		etNewItem.setText("");
		saveItems();
	}
	
	private ArrayList<String> readItems(){
		ArrayList<String> items;
		File filesDir = this.getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try{
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		}
		catch(IOException e){
			items = new ArrayList<String>();
		}
		return items;
	}
	
	private void saveItems(){
		File filesDir = this.getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try{
			FileUtils.writeLines(todoFile, items);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EDIT){
			String savedItem = data.getExtras().getString("savedItem");
			int pos = data.getExtras().getInt("position");
			items.set(pos, savedItem);
			saveItems();
			itemsAdapter.notifyDataSetChanged();
		}
	}
}
