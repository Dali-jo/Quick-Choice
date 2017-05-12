package com.example.leejaewon.quickchoice;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by LeeJaeWon on 2017-05-07.
 */

public class address_search extends Activity{
    private String key="U01TX0FVVEgyMDE3MDUwNzEzMTEyODIwOTk2";

    private TextView addressEdit;
    private Button searchbtn;
    private ListView addressView;

    private ArrayAdapter<String> address_List_Adapter;

    private String putAddress;
    private ArrayList<String> address_Search_Result = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle saveInstanceState){
    super.onCreate(saveInstanceState);
        setContentView(R.layout.content_address_search);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        searchbtn = (Button) findViewById(R.id.searchbt);
        addressView = (ListView) findViewById(R.id.addressList);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddress(addressEdit.getText().toString());
            }
        });


        addressView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) addressView.getAdapter().getItem(position);
                Toast.makeText(getBaseContext(),item,Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("juso",item.toString());
                setResult(1,intent);
                finish();
            }
        });


    }





    public void getAddress(String kAddress){
        putAddress=kAddress;
        new GetAddressDataTask().execute();

    }

    private class GetAddressDataTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String  doInBackground(String... urls){
            String response =null;
            String apiurl =  "http://www.juso.go.kr/addrlink/addrLinkApi.do?keyword=";

            ArrayList<String> addressInfo = new ArrayList<String>();

            HttpURLConnection conn =null;
            try{
                StringBuffer sb = new StringBuffer(3);
                sb.append(apiurl);

                sb.append(URLEncoder.encode(putAddress, "UTF-8"));
                sb.append("&confmKey=" + key);
                String query = sb.toString();

                URL url = new URL(query);
                conn=(HttpURLConnection) url.openConnection();
                conn.setRequestProperty("accept-language", "ko");

                DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                byte[] bytes = new byte[4096];
                InputStream in = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while (true){
                    int red = in.read(bytes);
                    if (red < 0)
                        break;
                    baos.write(bytes, 0, red);
                }
                String xmlData = baos.toString("utf-8");
                baos.close();
                in.close();
                conn.disconnect();

                Document doc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
                Element el = (Element) doc.getElementsByTagName("results").item(0);
                for (int i = 0; i < el.getChildNodes().getLength()-1; i++)
                {
                    Node node = el.getChildNodes().item(i+1);
//                    if (!node.getNodeName().equals("juso"))
//                    {
//                        continue;
//                    }
                    String address = node.getChildNodes().item(1).getFirstChild().getNodeValue();
                    Log.w("흠", "address = " + address);
                    addressInfo.add(address);
                    address_Search_Result = addressInfo;
                    publishProgress();
                } } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (conn != null)
                        conn.disconnect();
                } catch (Exception e)
                {
                }
            }

            return response;

        }
        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

            String[] addressStrArray = new String[address_Search_Result.size()];
            addressStrArray = address_Search_Result.toArray(addressStrArray);

            address_List_Adapter = new ArrayAdapter<String>(address_search.this, android.R.layout.simple_list_item_1, addressStrArray);
            addressView.setAdapter(address_List_Adapter);
        }


    }


};

