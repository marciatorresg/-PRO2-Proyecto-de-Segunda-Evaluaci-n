package com.grupopoop.tres_en_raya;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.grupopoop.tres_en_raya.databinding.ActivityMainBinding;
import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.AIPlayer;
import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.Board;
import com.grupopoop.tres_en_raya.ui.controllers.GameControl;
import com.grupopoop.tres_en_raya.ui.controllers.IaControl;
import com.grupopoop.tres_en_raya.ui.sampledata.WinDialog;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variables de instancia
    private GameControl gameControl;
    private IaControl iaControl;
    private ActivityMainBinding binding;
    private TextView playerOneName;
    private TextView playerTwoName;
    private LinearLayout playerOneLayout, playerIaLayout;
    private ImageView image1a, image2a, image3a, image1b, image2b, image3b, image1c, image2c, image3c;
    private List<ImageView> listPositionsImages;
    private MediaPlayer mediaPlayer, clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuración inicial
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViews();
        initializeGameLogic();
        initializeMediaPlayer();
        initializeClickListeners();
    }

    // Métodos de inicialización
    private void initializeViews() {
        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName= findViewById(R.id.playerIaName);
        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerIaLayout = findViewById(R.id.playerIaLayout);

        image1a = findViewById(R.id.image1a);
        image2a = findViewById(R.id.image2a);
        image3a = findViewById(R.id.image3a);
        image1b = findViewById(R.id.image1b);
        image2b = findViewById(R.id.image2b);
        image3b = findViewById(R.id.image3b);
        image1c = findViewById(R.id.image1c);
        image2c = findViewById(R.id.image2c);
        image3c = findViewById(R.id.image3c);

        listPositionsImages = new ArrayList<>();
        loadList();
    }

    private void initializeGameLogic() {
        gameControl = new GameControl();
        iaControl = new IaControl(this,new AIPlayer(Board.AI));

        String getPlayerOneName = getIntent().getStringExtra("Jugador");
        String getPlayerTwoName= getIntent().getStringExtra("Jugador2");
        boolean getIaState = getIntent().getBooleanExtra("IAstate",false);
        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);
        iaControl.setActivated(getIaState);
    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.sing3);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        clickSound = MediaPlayer.create(this, R.raw.pulsar);
    }

    private void initializeClickListeners() {
        ImageView[] images = {image1a, image2a, image3a, image1b, image2b, image3b, image1c, image2c, image3c};

        for (int i = 0; i < images.length; i++) {
            final int position = i;
            images[i].setOnClickListener(view -> {
                if (gameControl.isBoxSelectable(position)) {
                    if(!iaControl.isActivated()) {
                        performAction((ImageView) view, position);
                    }else{
                        performActionIa((ImageView) view,position);
                    }
                }
            });
        }
    }

    // Métodos del ciclo de vida
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

    // Lógica del juego
    public void performAction(ImageView imageView, int selectedBoxP) {
        gameControl.getBoxPositions()[selectedBoxP] = gameControl.getPlayerTurn();

        if (gameControl.getPlayerTurn() == 1) {
            handlePlayerOneAction(imageView);
            changePlayerTurn(2);
        } else {
            handleIaAction(imageView);
            changePlayerTurn(1);
        }
    }
// Retocar
    public void performActionIa(ImageView imageView, int selectedBoxP) {
            changePlayerTurn(1);
            gameControl.getBoxPositions()[selectedBoxP] = 1;
            handlePlayerOneAction(imageView);

            if(!gameControl.checkPlayerWin()) {
                if (gameControl.getTotalSelectedBoxes() < 9) {
                    int selection = iaControl.makePlay(gameControl.getBoxPositions());
                    if (selection != -1) {
                        gameControl.getBoxPositions()[selection] = 2;
                        changePlayerTurn(2);
                        handleIaAction(listPositionsImages.get(selection));

                    }
                }
            }
    }

    private void handlePlayerOneAction(ImageView imageView) {
        clickSound.start();
        imageView.setImageResource(R.drawable.equis);

        if (gameControl.checkPlayerWin()) {
            showWinDialog(playerOneName.getText().toString() + " !!GANA¡¡");
        } else if (gameControl.getTotalSelectedBoxes() == 9) {
            showWinDialog(" !!EMPATE¡¡");
        } else {
            gameControl.setTotalSelectedBoxes(gameControl.getTotalSelectedBoxes() + 1);
        }
    }

    private void handleIaAction(ImageView imageView) {
        clickSound.start();
        imageView.setImageResource(R.drawable.circulo);

        if (gameControl.checkPlayerWin()) {
            showWinDialog(playerTwoName.getText().toString() + " !!GANA¡¡");
        } else if (gameControl.getTotalSelectedBoxes() == 9) {
            showWinDialog(" !!EMPATE¡¡");
        } else {
            gameControl.setTotalSelectedBoxes(gameControl.getTotalSelectedBoxes() + 1);
        }
    }

    private void showWinDialog(String message) {
        WinDialog winDialog = new WinDialog(MainActivity.this, message, MainActivity.this);
        winDialog.setCancelable(false);
        winDialog.show();
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        gameControl.setPlayerTurn(currentPlayerTurn);

        if (gameControl.getPlayerTurn() == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerIaLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        } else {
            playerIaLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    // Métodos de reinicio
    public void restartMatch() {
        gameControl = new GameControl();
        iaControl.setBoard(new Board());
        resetImages();
        renewList();
    }

    private void resetImages() {
        ImageView[] images = {image1a, image2a, image3a, image1b, image2b, image3b, image1c, image2c, image3c};
        for (ImageView image : images) {
            image.setImageResource(R.drawable.transparent2);
        }
    }

    private void renewList() {
        listPositionsImages = new ArrayList<>();
        loadList();
    }

    private void loadList() {
        listPositionsImages.add(image1a);
        listPositionsImages.add(image2a);
        listPositionsImages.add(image3a);
        listPositionsImages.add(image1b);
        listPositionsImages.add(image2b);
        listPositionsImages.add(image3b);
        listPositionsImages.add(image1c);
        listPositionsImages.add(image2c);
        listPositionsImages.add(image3c);
    }

    // Getters y Setters
    public List<ImageView> getListPositionsImages() {
        return listPositionsImages;
    }

    public void setListPositionsImages(List<ImageView> listPositionsImages) {
        this.listPositionsImages = listPositionsImages;
    }
}
