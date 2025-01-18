package com.grupopoop.tres_en_raya.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.media.MediaPlayer;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.grupopoop.tres_en_raya.MainActivity;
import com.grupopoop.tres_en_raya.R;

public class AddPlayers extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_players);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addplayer1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mediaPlayer = MediaPlayer.create(this,R.raw.sing2);
        mediaPlayer.setLooping(true); // Repetir música en bucle
        mediaPlayer.start();
        final EditText playerOne = findViewById(R.id.playerOneName);
        final View startGameBtn= findViewById(R.id.startGameBtm);


        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getPlayerOneName= playerOne.getText().toString();

                if(getPlayerOneName.isEmpty()){
                    Toast.makeText(AddPlayers.this,"Ingrese su nombre para iniciar",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("Jugador",getPlayerOneName) ;
                    intent.putExtra("Jugador2","Artificial Player");
                    intent.putExtra("IAstate",true);
                    startActivity(intent);

                }

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}