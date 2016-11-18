package le_project.com.linkem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import LinkParser.LinkParser;


public class SaveNoteScreen  extends AppCompatActivity {

    EditText editText;
    TextView textView;
    String file_name = "le-saved-links"; //make a const
    String Message;
    LinkParser parser = new LinkParser();
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_notes);
        editText = (EditText) findViewById(R.id.msg);
        textView = (TextView) findViewById(R.id.display);
        textView.setVisibility(View.GONE);

    }

    //save message to internal storage
    public void save_message(View view) throws FileNotFoundException {
        Message = editText.getText().toString();

        try {
            FileOutputStream outputStream = openFileOutput(file_name, getApplicationContext().MODE_APPEND);
            outputStream.write(Message.getBytes());
            outputStream.write('\n');
            Toast.makeText(getApplicationContext(), "Message Saved, Congratulations!", Toast.LENGTH_LONG).show();
            editText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //read message from internal storage + formatting
    public void load_message(View view) throws FileNotFoundException {

        try {
            FileInputStream fileInputStream = openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((Message = bufferedReader.readLine()) != null) {
                stringBuilder.append(counter).append(".\t");
                stringBuilder.append(parser.parseLink(Message) + "\n");
                counter++;
            }

            textView.setVisibility(View.VISIBLE);
            textView.setText(stringBuilder);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}