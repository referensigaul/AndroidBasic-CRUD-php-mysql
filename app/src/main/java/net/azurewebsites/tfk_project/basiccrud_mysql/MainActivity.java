package net.azurewebsites.tfk_project.basiccrud_mysql;

import java.util.HashMap;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	//Defining views
    private EditText editTextNama;
    private EditText editTextJab;
    private EditText editTextGaji;
 
    private Button buttonAdd;
    private Button buttonView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Initializing views
        editTextNama = (EditText) findViewById(R.id.nama);
        editTextJab = (EditText) findViewById(R.id.jabatan);
        editTextGaji = (EditText) findViewById(R.id.gaji);
 
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
 
        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    //Adding an employee
    private void addEmployee(){
 
        final String nama = editTextNama.getText().toString().trim();
        final String jab = editTextJab.getText().toString().trim();
        final String gaji = editTextGaji.getText().toString().trim();
 
        class AddEmployee extends AsyncTask<Void,Void,String>{
 
            ProgressDialog loading;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }
 
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
 
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put(Config.KEY_EMP_NAMA,nama);
                params.put(Config.KEY_EMP_JAB,jab);
                params.put(Config.KEY_EMP_GAJI,gaji);
 
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL+"tambahKaryawan.php", params);
                return res;
            }
        }
 
        AddEmployee ae = new AddEmployee();
        ae.execute();
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
		if(v == buttonAdd){
			addEmployee();
		}

		if(v == buttonView){
			startActivity(new Intent(this,ViewAllEmployee.class));
		}
	}
}
