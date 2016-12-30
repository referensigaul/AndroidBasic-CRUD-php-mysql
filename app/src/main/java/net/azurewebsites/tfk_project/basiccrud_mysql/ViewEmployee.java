package net.azurewebsites.tfk_project.basiccrud_mysql;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewEmployee extends AppCompatActivity implements View.OnClickListener {

	private EditText editTextId;
	private EditText editTextNama;
	private EditText editTextJab;
	private EditText editTextGaji;

	private Button buttonUpdate;
	private Button buttonDelete;

	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_employee);
		Intent intent = getIntent();

		id = intent.getStringExtra(Config.EMP_ID);

		editTextId = (EditText) findViewById(R.id.id);
		editTextNama = (EditText) findViewById(R.id.nama);
		editTextJab = (EditText) findViewById(R.id.jabatan);
		editTextGaji = (EditText) findViewById(R.id.gaji);

		buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
		buttonDelete = (Button) findViewById(R.id.buttonDelete);

		buttonUpdate.setOnClickListener(this);
		buttonDelete.setOnClickListener(this);

		editTextId.setText(id);

		getEmployee();
	}

	private void getEmployee(){
		class GetEmployee extends AsyncTask<Void,Void,String>{
			ProgressDialog loading;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(ViewEmployee.this,"Fetching...","Wait...",false,false);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				showEmployee(s);
			}

			@Override
			protected String doInBackground(Void... params) {
				RequestHandler rh = new RequestHandler();
				String s = rh.sendGetRequestParam(Config.URL+"lihatKaryawan.php?id=",id);
				return s;
			}
		}
		GetEmployee ge = new GetEmployee();
		ge.execute();
	}

	private void showEmployee(String json){
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
			JSONObject c = result.getJSONObject(0);
			String nama = c.getString(Config.TAG_NAMA);
			String jab = c.getString(Config.TAG_JAB);
			String gaji = c.getString(Config.TAG_GAJI);

			editTextNama.setText(nama);
			editTextJab.setText(jab);
			editTextGaji.setText(gaji);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(){
		final String nama = editTextNama.getText().toString().trim();
		final String jab = editTextJab.getText().toString().trim();
		final String gaji = editTextGaji.getText().toString().trim();

		class UpdateEmployee extends AsyncTask<Void,Void,String>{
			ProgressDialog loading;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(ViewEmployee.this,"Updating...","Wait...",false,false);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(ViewEmployee.this,s,Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(Void... params) {
				HashMap<String,String> hashMap = new HashMap<String, String>();
				hashMap.put(Config.KEY_EMP_ID,id);
				hashMap.put(Config.KEY_EMP_NAMA,nama);
				hashMap.put(Config.KEY_EMP_JAB,jab);
				hashMap.put(Config.KEY_EMP_GAJI,gaji);

				RequestHandler rh = new RequestHandler();

				String s = rh.sendPostRequest(Config.URL+"updateKaryawan.php",hashMap);

				return s;
			}
		}

		UpdateEmployee ue = new UpdateEmployee();
		ue.execute();
	}

	private void deleteEmployee(){
		class DeleteEmployee extends AsyncTask<Void,Void,String> {
			ProgressDialog loading;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(ViewEmployee.this, "Deleting...", "Wait...", false, false);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(ViewEmployee.this, s, Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(Void... params) {
				RequestHandler rh = new RequestHandler();
				String s = rh.sendGetRequestParam(Config.URL+"hapusKaryawan.php?id=", id);
				return s;
			}
		}

		DeleteEmployee de = new DeleteEmployee();
		de.execute();
	}

	private void confirmDeleteEmployee(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

		alertDialogBuilder.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				deleteEmployee();
				startActivity(new Intent(ViewEmployee.this,ViewAllEmployee.class));
			}
		});

		alertDialogBuilder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == buttonUpdate){
            updateEmployee();
        }
 
        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
	}
}
