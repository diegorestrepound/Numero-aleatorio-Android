package com.example.primera_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.primera_app.clases.Numero;


public class MainActivity extends AppCompatActivity {
    private PendingIntent pendingIntent;
    private final static String CHANEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;



    Numero num = new Numero();

    private Button salir;
    private EditText numero;
    private Button aleatorio;
    private TextView numaleatorio;
    public int al;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numero = findViewById(R.id.txtnumero);
        aleatorio = findViewById(R.id.btnaleatorio);
        numaleatorio = findViewById(R.id.txtnumaleatorio);
        salir = findViewById(R.id.btnsalir);

        aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num.setNumero(Integer.parseInt(numero.getText().toString()));

                al = (int)(Math.random()*100) + 1;
                numaleatorio.setText(String.valueOf(al));
                numaleatorio.setVisibility(view.VISIBLE);

                if(num.getNumero() == al){
                    Toast.makeText(MainActivity.this, "Has ganado", Toast.LENGTH_LONG).show();
                }else{
                    createNotificacion();

                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }
    private void createNotificacion(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID);
        builder.setSmallIcon(R.drawable.ic_error_black_24dp);
        builder.setContentTitle("¡Perdiste!");
        builder.setContentText("Sigue intentando");
        builder.setColor(Color.rgb(116,243, 252));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }


}
