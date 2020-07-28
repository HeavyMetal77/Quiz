package ua.tarastom.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.textLevels);
        text_levels.setText(R.string.level2); //установлен текст

        final ImageView imgLeft = findViewById(R.id.img_left);
        imgLeft.setClipToOutline(true); //код для скругления углов картинки, работает только в эмуляторе, а не в xml

        final ImageView imgRight = findViewById(R.id.img_right);
        imgRight.setClipToOutline(true); //код для скругления углов картинки, работает только в эмуляторе, а не в xml

        final TextView textLeft = findViewById(R.id.text_left);
        final TextView textRight = findViewById(R.id.text_right);

        View button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
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

        //устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg = dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.peviewimgtwo);
        //устанавливаем картинку в диалоговое окно - конец
        TextView textDialogLevel2 = dialog.findViewById(R.id.textdescription);
        textDialogLevel2.setText(R.string.leveltwo);

//        //кнопка закрытия диалогового окна
        TextView textViewClose = (TextView) dialog.findViewById(R.id.btnclose);
        textViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //вернуться назад к выбору уровня
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
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

        //_________________
        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрыть заголовок диалогового окна
        dialogEnd.setContentView(R.layout.dialogend);//устанавливаем путь к макету диалогового окна
        try {
            dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //устанавливаем прозрачность фона диалогового окна
        } catch (Exception e) {
        }
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//окно нельзя закрыть кнопкой НАЗАД

        TextView textDescriptionDialogEnd = (TextView) dialogEnd.findViewById(R.id.textdescriptionend);
        textDescriptionDialogEnd.setText(R.string.leveltwoend);

//        //кнопка закрытия диалогового окна
        TextView textViewClose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        textViewClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //вернуться назад к выбору уровня
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss(); //закрытие диалогового окна
            }
        });

        Button btnContinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level2.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss(); //закрытие диалогового окна

            }
        });

        //_________________

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13, R.id.point14,
                R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };


        //подключаем анимацию
        final Animation animation = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        imgLeft.setImageResource(array.images2[numLeft]);
        textLeft.setText(array.text2[numLeft]);

        numRight = random.nextInt(10);
        while (numRight == numLeft) {
            numRight = random.nextInt(10);
        }

        imgRight.setImageResource(array.images2[numRight]);
        textRight.setText(array.text2[numRight]);

        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //если коснулся картинки
                    imgRight.setEnabled(false);//блокируем правую картинку
                    if (numLeft > numRight) {
                        imgLeft.setImageResource(R.drawable.img_true);
                    } else {
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //если отпустил палец
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count++;
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++) {
                            TextView textView = findViewById(progress[i]);
                            textView.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count-2;
                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++) {
                            TextView textView = findViewById(progress[i]);
                            textView.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20) {
                        //EXIT FROM Level

                        //сохранение игры
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 2) {
                            //
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }

                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        imgLeft.setImageResource(array.images2[numLeft]);
                        imgLeft.startAnimation(animation);
                        textLeft.setText(array.text2[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }

                        imgRight.setImageResource(array.images2[numRight]);
                        imgRight.startAnimation(animation);
                        textRight.setText(array.text2[numRight]);
                        imgRight.setEnabled(true);
                    }
                }
                return true;
            }
        });


        //для правой картинки
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //если коснулся картинки
                    imgLeft.setEnabled(false);//блокируем правую картинку
                    if (numLeft < numRight) {
                        imgRight.setImageResource(R.drawable.img_true);
                    } else {
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //если отпустил палец
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count++;
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++) {
                            TextView textView = findViewById(progress[i]);
                            textView.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count-2;
                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++) {
                            TextView textView = findViewById(progress[i]);
                            textView.setBackgroundResource(R.drawable.style_points);
                        }
                        //определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20) {
                        //EXIT FROM Level

                        //сохранение игры
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 2) {
                            //
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }


                        dialogEnd.show();//показать диалоговое окно

                    } else {
                        numLeft = random.nextInt(10);
                        imgLeft.setImageResource(array.images2[numLeft]);
                        imgLeft.startAnimation(animation);
                        textLeft.setText(array.text2[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }

                        imgRight.setImageResource(array.images2[numRight]);
                        imgRight.startAnimation(animation);
                        textRight.setText(array.text2[numRight]);
                        imgLeft.setEnabled(true);
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }

    }

}