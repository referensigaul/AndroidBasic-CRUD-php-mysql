package net.azurewebsites.tfk_project.basiccrud_mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {

	//Methode untuk mengirim permintaan HttpPost
	public String sendPostRequest(String requestURL,
			HashMap<String, String> postDataParams) {
		//Mendefinisikan URL
		URL url;

		//Object StringBuilder untuk menyimpan pesan yang diambil dari server
		StringBuilder sb = new StringBuilder();
		try {
			//Inisialisasi Url
			url = new URL(requestURL);

			//Membuat sebuah objek Http url connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			//Konfigurasi properti koneksi
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			//Membuat objek OutputStream
			OutputStream os = conn.getOutputStream();

			//Menulis paramater ke permintaan 
			//Kita menggunakan sebuah method getPostDataString 
			//sebagaimana didefinisikan sebagai berikut
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(os, "UTF-8"));
			writer.write(getPostDataString(postDataParams));

			writer.flush();
			writer.close();
			os.close();
			int responseCode = conn.getResponseCode();

			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				sb = new StringBuilder();
				String response;
				//Membaca respon server
				while ((response = br.readLine()) != null){
					sb.append(response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String sendGetRequest(String requestURL){
		StringBuilder sb =new StringBuilder();
		try {
			URL url = new URL(requestURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String s;
			while((s=bufferedReader.readLine())!=null){
				sb.append(s+"\n");
			}
		}catch(Exception e){
		}
		return sb.toString();
	}

	public String sendGetRequestParam(String requestURL, String id){
		StringBuilder sb =new StringBuilder();
		try {
			URL url = new URL(requestURL+id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String s;
			while((s=bufferedReader.readLine())!=null){
				sb.append(s+"\n");
			}
		}catch(Exception e){
		}
		return sb.toString();
	}

	private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}

		return result.toString();
	}

}
