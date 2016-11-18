package le_project.com.linkem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class CameraLauncher extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_PICK_FROM_GALLERY = 2888;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) this.findViewById(R.id.background);


        //Take photo button and activity
        Button photoButton = (Button) this.findViewById(R.id.take_photo);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        //Select from gallery button and activity
        Button selectFromGallery = (Button) this.findViewById(R.id.load_from_gallery);
        selectFromGallery.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, CAMERA_PICK_FROM_GALLERY);

            }
        });

        //Load Notes button
        Button loadNotes = (Button) this.findViewById(R.id.load_notes);
        loadNotes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_save_notes);
            }

        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

        if(requestCode == CAMERA_PICK_FROM_GALLERY){
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera_layout, menu);
        return true;
    }

}





