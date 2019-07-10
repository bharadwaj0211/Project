package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public  static String KEY = "name";
    public static String NAME_KEY = "name";
    public static String PASSWORD_KEY = "pwd";
    public static String REMEM_PASSWORD_KEY="rempswd";
    private NotificationCompat.Builder mNotifyBuilder;
    public static String FILE_NAME = "sageit";
    EditText nameEditText, passwordEditText;
    CheckBox checkBox;

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();
        nameEditText=findViewById(R.id.unametxt);
        passwordEditText=findViewById(R.id.pwdtxt);
        checkBox=findViewById(R.id.checkBox);
    }


    public void handler(View view) {
        switch (view.getId()){
            case R.id.loginbtn:
                EditText editText1= findViewById(R.id.unametxt);
                String name=editText1.getText().toString();
                Intent intent=new Intent(this, LoginActivity.class);
                intent.putExtra(KEY,name);
                startActivity(intent);
                break;

            case R.id.notfctnbtn:
                Intent dialIntent = new Intent(MainActivity.this,LoginActivity.class);
                PendingIntent notifyPendingIntent =
                        PendingIntent.getActivity(this,NOTIFICATION_ID,dialIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelid")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Whatsapp")
                        .setContentText("Update")
                        .setProgress(100,60,false)
                        .addAction(R.drawable.abc, "Reply", notifyPendingIntent )
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                mNotifyManager.notify(NOTIFICATION_ID, builder.build());
                break;

        }

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "mychannel";
            //getString(R.string.channel_name);
            String description = "channel description";
            //getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelid", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {

        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Boolean isChecked=checkBox.isChecked();
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        //open file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(PASSWORD_KEY,password);
        editor.putBoolean(REMEM_PASSWORD_KEY,isChecked);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        //read the file
        String name = sharedPreferences.getString(NAME_KEY,"");
        String pass = sharedPreferences.getString(PASSWORD_KEY,"");
        Boolean checked=sharedPreferences.getBoolean(REMEM_PASSWORD_KEY,false);
        //put the data in edittexts
        nameEditText.setText(name);
        passwordEditText.setText(pass);
        checkBox.setChecked(checked);
    }
}
