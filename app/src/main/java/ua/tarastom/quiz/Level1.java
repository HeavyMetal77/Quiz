package ua.tarastom.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    public int numLeft;
    public int numRight;
    Array array = new Array();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.textLevels);
        text_levels.setText(R.string.level1); //установлен текст

        final View img_left = findViewById(R.id.img_left);
        img_left.setClipToOutline(true); //код для скругления углов картинки, работает только в эмуляторе, а не в xml

        final View img_right = findViewById(R.id.img_right);
        img_right.setClipToOutline(true); //код для скругления углов картинки, работает только в эмуляторе, а не в xml

        View button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрыть заголовок диалогового окна
        dialog.setContentView(R.layout.previewdialog);//устанавливаем путь к макету диалогового окна
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //устанавливаем прозрачность фона диалогового окна
        } catch (Exception e) {
        }
        dialog.setCancelable(false);//окно нельзя закрыть кнопкой НАЗАД

//        //кнопка закрытия диалогового окна
        TextView textViewClose = (TextView)dialog.findViewById(R.id.btnclose);
        textViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //вернуться назад к выбору уровня
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialog.dismiss(); //закрытие диалогового окна
            }
        });

        Button btnContinue = (Button) dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); //закрытие диалогового окна

            }
        });

        dialog.show();//показать диалоговое окно
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }

    }

}