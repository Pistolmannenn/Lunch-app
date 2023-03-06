package com.example.lunch_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera extends AppCompatActivity {
    private int REQUEST_CODE_PERMISSIONS = 101;
    private String[] REQUIRED_PERMISSONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_CODE = 22;
    TextureView textureView;
    Button btnpicture;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        btnpicture = findViewById(R.id.btncamera_id);
        textureView = findViewById(R.id.view_finder);


        btnpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            String picName = "photo.jpg";
            try (FileOutputStream out = openFileOutput(picName, Context.MODE_PRIVATE)){
                photo.compress(Bitmap.CompressFormat.JPEG, 100, out);
            } catch (IOException e){
                e.printStackTrace();
            }

            File file = new File(getFilesDir(), picName);
            if (file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(bitmap);

                MultiFormatReader reader = new MultiFormatReader();
                try {
                    int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
                    photo.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), pixels);
                    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result result = reader.decode(binaryBitmap);
                    String qrCodeData = result.getText();
                    Intent getData = new Intent(Camera.this, MainActivity.class);
                    getData.putExtra("String", qrCodeData);
                    Camera.this.startActivity(getData);
                } catch (NotFoundException e) {
                    Toast.makeText(this, "No QR code found in the image", Toast.LENGTH_SHORT).show();
                }

                file.delete();
            }

        }
        else{
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
