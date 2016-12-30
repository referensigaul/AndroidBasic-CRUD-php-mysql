package net.azurewebsites.tfk_project.basiccrud_mysql;

public class Config {
	
	//Address URL webservice
    public static final String URL="http://192.168.43.116/AndroidMySQL/";
    
    //tabel employe
    //Key yang akan digunakan untuk mengirim permintaan ke server
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_JAB = "jab";
    public static final String KEY_EMP_GAJI = "gaji";
 
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_JAB = "jab";
    public static final String TAG_GAJI = "gaji";
    
    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";

}
