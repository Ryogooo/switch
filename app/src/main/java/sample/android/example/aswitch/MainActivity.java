package sample.android.example.aswitch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch button;

    //ノティフィケーション用のチャンネルIDの設定
    private String CHANNEL_ID = "KEY";
    private int notificationId = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        button = (Switch)findViewById(R.id.switch2);

        //switchのオンオフが切り替わった時の処理
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String code = "";
                if (isChecked){
                    //スイッチがONになったら文字列codeにonを代入
                    code = "on";

                    //ノティフィケーションを表示する設定
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("タイトル")
                            .setContentText("switchがonになったからnotificationでお知らせしたよ")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                    notificationManager.notify(notificationId,builder.build());

                }else {
                    //スイッチがoffになったら文字列codeにoffを代入
                    code = "off";
                }
                //スイッチをオンとオフに切り替えたときに文字列codeに入っている文字をトーストで表示する
                Toast.makeText(MainActivity.this,code,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
