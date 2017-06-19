package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by LeeJaeWon on 2017-04-30.
 */

public class content_order_sub1 extends Fragment {
    protected static final int REQUEST_CQPTRUE = 3;
    protected static final int REQUEST_GALLERY = 2;
    File photoFile = null;
    String photoPath = null;
    Uri photoUri = null;
    String fileAddr = null;

    Button btn = null;
    ImageView iv = null;

    int category = 0;

    String start_addr;
    String desty_addr;

    EditText start;
    EditText desty;
    ImageButton bt1;
    ImageButton bt2;

    RadioButton bt_damas;
    RadioButton bt_rabo;
    RadioButton bt_ban;
    RadioButton bt_ton;

    RadioButton bt_paper;
    RadioButton bt_small;
    RadioButton bt_big;
    RadioButton bt_food;
    RadioButton bt_flower;
    RadioButton bt_ex;

    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;

    RadioGroup group_car;

    int meter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.content_order_sub1, container, false);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.textView3);
        TextView textView2 = (TextView) view.findViewById(R.id.textView4);
        TextView textView3 = (TextView) view.findViewById(R.id.radio_small);
        TextView textView4 = (TextView) view.findViewById(R.id.radio_flower);
        TextView textView5 = (TextView) view.findViewById(R.id.radio_big);
        TextView textView6 = (TextView) view.findViewById(R.id.radio_ex);
        TextView textView7 = (TextView) view.findViewById(R.id.textView6);
        TextView textView8 = (TextView) view.findViewById(R.id.textView7);
        TextView textView9 = (TextView) view.findViewById(R.id.button4);
        TextView textView10 = (TextView) view.findViewById(R.id.radio_paper);
        TextView textView11 = (TextView) view.findViewById(R.id.radio_food);
        TextView textView12 = (TextView) view.findViewById(R.id.put_pic);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        textView6.setTypeface(typeface1);
        textView7.setTypeface(typeface1);
        textView8.setTypeface(typeface1);
        textView9.setTypeface(typeface1);
        textView10.setTypeface(typeface1);
        textView11.setTypeface(typeface1);
        textView12.setTypeface(typeface1);


        start = (EditText) view.findViewById(R.id.order_start);
        desty = (EditText) view.findViewById(R.id.order_desti);
        bt1 = (ImageButton) view.findViewById(R.id.search1);
        bt2 = (ImageButton) view.findViewById(R.id.search2);
        listener lis = new listener();
        bt1.setOnClickListener(lis);
        bt2.setOnClickListener(lis);

        group_car = (RadioGroup) view.findViewById(R.id.group_car);

        rg1 = (RadioGroup) view.findViewById(R.id.rag1);
        rg2 = (RadioGroup) view.findViewById(R.id.rag2);
        rg3 = (RadioGroup) view.findViewById(R.id.rag3);
        rg1.setOnCheckedChangeListener(rglis1);
        rg2.setOnCheckedChangeListener(rglis2);
        rg3.setOnCheckedChangeListener(rglis3);

        btn = (Button) view.findViewById(R.id.put_pic);
        iv = (ImageView) view.findViewById(R.id.imagetest);

        bt_ban = (RadioButton) view.findViewById(R.id.delivery_ban);
        bt_damas = (RadioButton) view.findViewById(R.id.delivery_damas);
        bt_rabo = (RadioButton) view.findViewById(R.id.delivery_rabo);
        bt_ton = (RadioButton) view.findViewById(R.id.delivery_ton);

        bt_paper = (RadioButton) view.findViewById(R.id.radio_paper);
        bt_small = (RadioButton) view.findViewById(R.id.radio_small);
        bt_big = (RadioButton) view.findViewById(R.id.radio_big);
        bt_food = (RadioButton) view.findViewById(R.id.radio_food);
        bt_flower = (RadioButton) view.findViewById(R.id.radio_flower);
        bt_ex = (RadioButton) view.findViewById(R.id.radio_ex);

        bt_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 0;
                quote();
            }
        });

        bt_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 1;
                quote();
            }
        });

        bt_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 2;
                quote();
            }
        });

        bt_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 3;
                quote();
            }
        });

        bt_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 4;
                quote();
            }
        });

        bt_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 5;
                quote();
            }
        });

        if (((main) getActivity()).car == 0) {
            group_car.setVisibility(View.GONE);
            rg1.setVisibility(View.VISIBLE);
            rg2.setVisibility(View.VISIBLE);
            rg3.setVisibility(View.VISIBLE);
        } else {
            group_car.setVisibility(View.VISIBLE);
            rg1.setVisibility(View.GONE);
            rg2.setVisibility(View.GONE);
            rg3.setVisibility(View.GONE);
        }

        bt_damas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((main) getActivity()).car = 1;
                quote();
            }
        });

        bt_ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((main) getActivity()).car = 3;
                quote();
            }
        });

        bt_rabo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((main) getActivity()).car = 2;
                quote();
            }
        });

        bt_ton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((main) getActivity()).car = 4;
                quote();
            }
        });


        imagelistener imag_lis = new imagelistener();
        btn.setOnClickListener(imag_lis);

        return view;

    }

    //견적가 계산
    public void quote() {
        switch (((main) getActivity()).car) {
            case 0:
                Log.i("견적계산용 거리", String.valueOf(meter));
                if (meter <= 5000) {
                    ((main) getActivity()).quote = "5000";
                } else {
                    int extra = (meter - 4000) / 1000;
                    extra = extra * 1000 + 5000;
                    ((main) getActivity()).quote = String.valueOf(extra);
                }
                break;
            case 1:
                Log.i("견적계산용 거리", String.valueOf(meter));
                ((main) getActivity()).quote = "20000";
                break;
            case 2:
                ((main) getActivity()).quote = "40000";
                break;
            case 3:
                ((main) getActivity()).quote = "60000";
                break;
            case 4:
                ((main) getActivity()).quote = "80000";
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        ((main) getActivity()).start = start.getText().toString();
        ((main) getActivity()).desti = desty.getText().toString();
        ((main) getActivity()).category = this.category;

    }


    private RadioGroup.OnCheckedChangeListener rglis1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(rglis2);
                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(rglis3);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener rglis2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(rglis1);
                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(rglis3);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener rglis3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(rglis1);
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(rglis2);
            }
        }
    };


    class listener implements View.OnClickListener {
        public void onClick(View v) {
            int i = 0;
            switch (v.getId()) {
                case R.id.search1:
                    i = 0;
                    search_add(v, i);
                    break;
                case R.id.search2:
                    i = 1;
                    search_add(v, i);
                    break;
                case R.id.radio_paper:
                    category = 0;
                    quote();
                    break;
                case R.id.radio_small:
                    category = 1;
                    quote();
                    break;
                case R.id.radio_big:
                    category = 2;
                    quote();
                    break;
                case R.id.radio_food:
                    category = 3;
                    quote();
                    break;
                case R.id.radio_flower:
                    category = 4;
                    quote();
                    break;
                case R.id.radio_ex:
                    category = 5;
                    quote();
                    break;

            }


        }
    }

    @Override
    public void onStart() {
        super.onStart();

//        Bundle args = this.getArguments();

//            if(this.getArguments().getBundle("juso")!= null){
//                start.setText(this.getArguments().getBundle("juso").getString("juso"));
//            }
//        if(args!=null){
//            String str = args.toString();
//            Toast.makeText(getActivity(),str+"받음 프래그먼트",Toast.LENGTH_LONG).show();
//            start.setText(args.getString("juso"));


//        }
    }

//    public void aa(){
//        ((main)getActivity())


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("start", start.getText().toString());
        outState.putString("start", desty.getText().toString());
    }

    public void setText(String str) {
        start.setText(str);
    }

    public void search_add(View v, int i) {
        Intent intent = new Intent(getActivity(), webview.class);
        startActivityForResult(intent, i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = null;
        if (requestCode == 0) {
            if (resultCode == 1) {
                start.setText(data.getStringExtra("juso"));

                try {



                    list = geocoder.getFromLocationName(data.getStringExtra("juso"), 1);
                    Log.i("리스트",data.getStringExtra("juso"));
                    if (list.size()>0) {

                        Address addr = list.get(0);
                        ((main) getActivity()).start_Latitude = String.valueOf(addr.getLatitude());
                        ((main) getActivity()).start_Longitude = String.valueOf(addr.getLongitude());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getActivity(), data.getStringExtra("juso") + "fragment + la:" + ((main) getActivity()).start_Latitude + "long" + ((main) getActivity()).start_Longitude, Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == 1) {
            if (resultCode == 1) {
                desty.setText(data.getStringExtra("juso"));

                try {
                    list = geocoder.getFromLocationName(data.getStringExtra("juso"), 1);
                    Log.i("리스트",data.getStringExtra("juso"));
                    if (list.size()>0) {
                        Address addr = list.get(0);
                        ((main) getActivity()).desti_Latitude = String.valueOf(addr.getLatitude());
                        ((main) getActivity()).desti_Longitude = String.valueOf(addr.getLongitude());

                        TMapView tMapView = new TMapView(getActivity());
                        tMapView.setSKPMapApiKey("8c430cbd-0174-365a-879a-98909c5e6f73");


                        TMapData tMapData = new TMapData();
                        TMapPoint start = new TMapPoint(Double.valueOf(((main) getActivity()).start_Latitude), Double.valueOf(((main) getActivity()).start_Longitude));
                        TMapPoint desti = new TMapPoint(Double.valueOf(((main) getActivity()).desti_Latitude), Double.valueOf(((main) getActivity()).desti_Longitude));
                        tMapData.findPathDataAll(start, desti, new TMapData.FindPathDataAllListenerCallback() {
                            @Override
                            public void onFindPathDataAll(Document document) {
                                XMLDOMParser parser = new XMLDOMParser();
                                Document doc = document;
                                NodeList nodeList = doc.getElementsByTagName("kml");
                                for (int i = 0; i < nodeList.getLength(); i++) {
                                    Element e = (Element) nodeList.item(i);
                                    ((main) getActivity()).totaldistance = parser.getValue(e, "tmap:totalDistance");
                                    meter = Integer.valueOf(((main) getActivity()).totaldistance);
                                    int integer = meter / 1000;
                                    int prime = meter % 1000;
                                    ((main) getActivity()).distance = integer + "." + prime + "Km";
                                    Log.i("거리", ((main) getActivity()).distance);


                                    ((main) getActivity()).totaltime = parser.getValue(e, "tmap:totalTime");
                                    int minute = Integer.valueOf(((main) getActivity()).totaltime) / 60;
                                    //분으로 변환한 나머지가 변환된 초
                                    int chsecond = Integer.valueOf(((main) getActivity()).totaltime) - minute * 60;
                                    //분을 60으로 나누면 시간
                                    int hour = minute / 60;
                                    //시간으로 변환한 나머지가 변환된 분
                                    minute = minute - hour * 60;
                                    ((main) getActivity()).time = hour + ":" + minute;
                                    Log.i("시간", ((main) getActivity()).time);

                                }
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getActivity(), data.getStringExtra("juso") + "fragment + la:" + ((main) getActivity()).desti_Latitude + "long" + ((main) getActivity()).desti_Longitude, Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUEST_CQPTRUE) {




                String imagePath = photoUri.getPath();
                Bitmap photo = BitmapFactory.decodeFile(imagePath);
                ExifInterface exif = null;
                try {
                    exif = new ExifInterface(photoPath);
                    int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                        int exifDegree = exifOrientationToDegrees(exifOrientation);
                    int exifDegree = 90;
                    Toast.makeText(getActivity(), exifOrientation + "," + exifDegree, Toast.LENGTH_LONG).show();
                    photo = rotate(photo, exifDegree);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "취소됨", Toast.LENGTH_LONG).show();
                }


                //회전시킨 이미지를 저장
                saveExifFile(photo, photoPath);


                //비트맵 메모리 반환
//                    photo.recycle();
//

//            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setImageBitmap(photo);


                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(photoUri);
                Toast.makeText(getActivity(), photoUri.toString(), Toast.LENGTH_LONG).show();
                getActivity().sendBroadcast(mediaScanIntent);

                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

                Toast.makeText(getActivity(), sdPath, Toast.LENGTH_LONG).show();


                String jspUri = "http://220.122.180.160:8080/goods.jsp";
                DoFileUpload(jspUri, fileAddr);



        }
    }


    public File createFile() throws IOException {

        String imageFileName = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        Toast.makeText(getActivity(), imageFileName, Toast.LENGTH_LONG).show();
        File storageDir = new File(Environment.getExternalStorageDirectory(), imageFileName);
        photoPath = storageDir.getAbsolutePath();
        return storageDir;

    }

    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }


    public Bitmap rotate(Bitmap bitmap, int degrees) {
        Bitmap retBitmap = bitmap;

        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != converted) {
                    retBitmap = converted;
                    bitmap.recycle();
                    bitmap = null;
                }
            } catch (OutOfMemoryError ex) {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
                Toast.makeText(getActivity(), "회전 못했음", Toast.LENGTH_LONG).show();
            }
        }
        return retBitmap;
    }

    public void saveExifFile(Bitmap imageBitmap, String savePath) {
        FileOutputStream fos = null;
        File saveFile = null;

        try {
            saveFile = new File(savePath);
            fos = new FileOutputStream(saveFile);
            //원본형태를 유지해서 이미지 저장
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fileAddr = saveFile.getPath().toString();
            Toast.makeText(getActivity(), "경로 : " + saveFile.getPath().toString(), Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            //("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            //("IOException", e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
            }
        }
    }


    public void DoFileUpload(String apiUrl, String absolutePath) {

        HttpFileUpload(apiUrl, "", absolutePath);
        Log.d("캡쳐", "load1");


    }


    public void HttpFileUpload(String urlString, String params, String fileName) {


        String lineEnd = "\r\n";

        String twoHyphens = "--";

        String boundary = "*****";

        try {

            File sourceFile = new File(fileName);

            DataOutputStream dos;

            if (!sourceFile.isFile()) {

                Log.e("uploadFile", "Source File not exist :" + fileName);

            } else {

                FileInputStream mFileInputStream = new FileInputStream(sourceFile);

                URL connectUrl = new URL(urlString);
                Toast.makeText(getActivity(), "loding", Toast.LENGTH_LONG).show();

                // open connection

                HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();

                conn.setDoInput(true);

                conn.setDoOutput(true);

                conn.setUseCaches(false);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Connection", "Keep-Alive");

                conn.setRequestProperty("ENCTYPE", "multipart/form-data");

                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                conn.setRequestProperty("uploaded_file", fileName);

                // write data

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);

                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);


                int bytesAvailable = mFileInputStream.available();

                int maxBufferSize = 1024 * 1024;

                int bufferSize = Math.min(bytesAvailable, maxBufferSize);


                byte[] buffer = new byte[bufferSize];

                int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);


                // read image

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);

                    bytesAvailable = mFileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);

                    bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

                }


                dos.writeBytes(lineEnd);

                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                mFileInputStream.close();

                dos.flush(); // finish upload...

                if (conn.getResponseCode() == 200) {

                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");

                    BufferedReader reader = new BufferedReader(tmp);

                    StringBuffer stringBuffer = new StringBuffer();

                    String line;

                    while ((line = reader.readLine()) != null) {

                        stringBuffer.append(line);

                    }

                }

                mFileInputStream.close();

                dos.close();

                Toast.makeText(getActivity(), "완료", Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


    }


    public class imagelistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;

                try {
                    photoFile = createFile();
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "createImageFile Failed", Toast.LENGTH_LONG).show();
                }


                if (photoFile != null) {
                    photoUri = Uri.fromFile(photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, REQUEST_CQPTRUE);


                }
            }
        }
    }


}
