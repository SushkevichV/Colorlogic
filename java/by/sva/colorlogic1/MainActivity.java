package by.sva.colorlogic1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
{
    Cristals cristals = new Cristals();
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private TextView prompt;
    private Context context;
    private int[] combination; // начальная комбинация

    private int[] mainCombination; // сгенерерованная комбинация
    int attempt; // попытки

    // генерация комбинации
    private int[] generateCombination() {
        int[] c = new int[4];
        Random random = new Random();
        for(int i=0; i<4; i++){
            c[i] = random.nextInt(6)+1;
        }
        return c;
    }

    // инициализация
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // убрать ActionBar
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        mainCombination = generateCombination(); // сгенерировать комбинацию
        attempt = 1; // сбросить попытки
        prompt = findViewById(R.id.tv_prompt);
        prompt.setText("Выбери кристалл слева");

        Button btnPrompt = findViewById(R.id.btn_prompt);
        btnPrompt.setOnClickListener(this);

        Button btnNew = findViewById(R.id.btn_new);
        btnNew.setOnClickListener(this);

        imageView1 = findViewById(R.id.iv_c_blue);
        imageView2 = findViewById(R.id.iv_c_red);
        imageView3 = findViewById(R.id.iv_c_gold);
        imageView4 = findViewById(R.id.iv_c_green);
        imageView5 = findViewById(R.id.iv_c_grey);
        imageView6 = findViewById(R.id.iv_c_dblue);

        // создание начальной коллекции
        combination = new int[]{1, 1, 1, 1};
        imageView = findViewById(R.id.iv_c_1);
        cristals.addCristal(R.id.iv_c_1, imageView, 1);
        imageView = findViewById(R.id.iv_c_2);
        cristals.addCristal(R.id.iv_c_2, imageView, 1);
        imageView = findViewById(R.id.iv_c_3);
        cristals.addCristal(R.id.iv_c_3, imageView, 1);
        imageView = findViewById(R.id.iv_c_4);
        cristals.addCristal(R.id.iv_c_4, imageView, 1);

    }

    // кнопка "подсказки"
    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_prompt : {
                if (prompt.getVisibility() == View.INVISIBLE) {
                    prompt.setVisibility(View.VISIBLE);
                    view.setAlpha(1F);
                } else {
                    prompt.setVisibility(View.INVISIBLE);
                    view.setAlpha(0.5F);
                }
                break;
            }
            case R.id.btn_new: {
                Activity activity = MainActivity.this;
                activity.recreate(); // перезапуск
            }
        }
    }

    // анимация
    private void doAnim(final int id)
    {
        context = MainActivity.this;
        Animation anim = AnimationUtils.loadAnimation (context, id);

        // запуск анимации компонента
        imageView.startAnimation(anim);
    }

    // выбор кристалла основного ряда
    public void onClickCristal(View view) {
        context = MainActivity.this;

        if(attempt==11){
            prompt.setText("Попытки закончились");
            // показать Toast - всплывающая надпись
            Toast toast = Toast.makeText(context, "Попытки закончились", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        imageView = findViewById(view.getId());
        imageView.setAlpha(0.5F);
        prompt.setText("Выбери цвет кристалла");

        changeCristalClickable(false); // выключить активность основного ряда
        changeVisibility(); // показать выбор кристаллов
    }

    // смена режима видимости для меню выбора кристаллов
    private void changeVisibility() {
        if(imageView1.getVisibility()==View.INVISIBLE) {
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.VISIBLE);
            imageView5.setVisibility(View.VISIBLE);
            imageView6.setVisibility(View.VISIBLE);
        } else {
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
            imageView5.setVisibility(View.INVISIBLE);
            imageView6.setVisibility(View.INVISIBLE);
        }
    }

    // включение/выключение активности кристаллов основного ряда
    private void changeCristalClickable(boolean enable){
        if (enable){
            findViewById(R.id.iv_c_1).setClickable(true);
            findViewById(R.id.iv_c_2).setClickable(true);
            findViewById(R.id.iv_c_3).setClickable(true);
            findViewById(R.id.iv_c_4).setClickable(true);
            findViewById(R.id.iv_c_cur).setClickable(true);
        } else {
            findViewById(R.id.iv_c_1).setClickable(false);
            findViewById(R.id.iv_c_2).setClickable(false);
            findViewById(R.id.iv_c_3).setClickable(false);
            findViewById(R.id.iv_c_4).setClickable(false);
            findViewById(R.id.iv_c_cur).setClickable(false);
        }
    }

    // выбор цвета кристалла
    public void onClickSelCristal(View view) {

        changeVisibility(); // скрыть выбор кристаллов
        changeCristalClickable(true); // включить активность основного ряда
        prompt.setText("Собери свою комбинацию и нажми на кристалл справа");

        switch (view.getId()){
            case R.id.iv_c_blue: {
                imageView.setImageResource(R.drawable.cristal_blue);
                setItem(imageView.getId(), 1);
                cristals.addCristal(imageView.getId(), imageView, 1);
            } break;
            case R.id.iv_c_red: {
                imageView.setImageResource(R.drawable.cristal_red);
                setItem(imageView.getId(), 2);
                cristals.addCristal(imageView.getId(), imageView, 2);
            } break;
            case R.id.iv_c_gold: {
                imageView.setImageResource(R.drawable.cristal_gold);
                setItem(imageView.getId(), 3);
                cristals.addCristal(imageView.getId(), imageView, 3);
            } break;
            case R.id.iv_c_grey: {
                imageView.setImageResource(R.drawable.cristal_white);
                setItem(imageView.getId(), 4);
                cristals.addCristal(imageView.getId(), imageView, 4);
            } break;
            case R.id.iv_c_green: {
                imageView.setImageResource(R.drawable.cristal_green);
                setItem(imageView.getId(), 5);
                cristals.addCristal(imageView.getId(), imageView, 5);
            } break;
            case R.id.iv_c_dblue: {
                imageView.setImageResource(R.drawable.cristal_dblue);
                setItem(imageView.getId(), 6);
                cristals.addCristal(imageView.getId(), imageView, 6);
            } break;
        }

        Animation animation = AnimationUtils.loadAnimation (context, R.anim.alpha);
        imageView.startAnimation(animation);
        imageView.setAlpha(1F);

    }

    // заполнение комбинации пользователя
    private void setItem(int id, int i) {
        switch (id){
            case R.id.iv_c_1: combination[0] = i; break;
            case R.id.iv_c_2: combination[1] = i; break;
            case R.id.iv_c_3: combination[2] = i; break;
            case R.id.iv_c_4: combination[3] = i; break;
        }
    }

    // проверка совпадения
    public void check(View view) {

        if(attempt==11){
            Toast.makeText(getApplicationContext(), "Попытки закончились", Toast.LENGTH_SHORT).show(); // показать Toast - всплывающая надпись
            return;
        }

        int black = 0;
        int white = 0;
        int[] c1 = new int[4], c2 = new int[4];
        for (int i=0; i<4; i++){
            c1[i] = mainCombination[i];
            c2[i] = combination[i];
        }

        // проверка на полное совпадение
        for (int i = 0; i < 4; i++){
            if (c1[i] == c2[i]){
                black++;
                c1[i] = 0;
                c2[i] = -1;
            }
        }

        // проверка на неполное совпадение
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (c1[i] == c2[j]){
                    white++;
                    c1[i] = 0;
                    c2[j] = -1;
                    continue;
                }
            }
        }

        attempt++;
        showResult(black, white);

        if(black == 4){
            attempt=11;
            prompt.setText("Ты угадал комбинацию кристаллов!");
            prompt.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Ты угадал комбинацию кристаллов!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    // размещение введенной комбинации на слое ll_res_vert
    //private void showResult(Drawable resultImage) {
    private void showResult(int black, int white) {

        prompt.setText("Украшение справа разделено на четыре фрагмента\n" +
                        "Если фрагмент украшения окрасился в синий - один из кристаллов на своем месте\n"+
                        "Если в серый - кристалл расположен не на своем месте\n" +
                        "Если в белый - такого кристалла нет");

        LayoutInflater ltInflater = getLayoutInflater(); // получить LayoutInflater
        LinearLayout results = (LinearLayout) findViewById(R.id.ll_res_vert); // получить id нужного слоя для нового View

        // создать новый View из файла (XML-источник, слой, привязка к родителю)
        View view = ltInflater.inflate(R.layout.result_line, results, false);
        LayoutParams lp = view.getLayoutParams(); // получить параметры нового View

        results.addView(view); // добавить View на слой

        // получить ImageView и поменять картинку в соответствии с выбранной комбинацией (левая сторона)
        ImageView attemptImageView1 = view.findViewById(R.id.attemptImageView1);
        attemptImageView1.setImageDrawable(cristals.getCristal(R.id.iv_c_1).getDrawable());
        ImageView attemptImageView2 = view.findViewById(R.id.attemptImageView2);
        attemptImageView2.setImageDrawable(cristals.getCristal(R.id.iv_c_2).getDrawable());
        ImageView attemptImageView3 = view.findViewById(R.id.attemptImageView3);
        attemptImageView3.setImageDrawable(cristals.getCristal(R.id.iv_c_3).getDrawable());
        ImageView attemptImageView4 = view.findViewById(R.id.attemptImageView4);
        attemptImageView4.setImageDrawable(cristals.getCristal(R.id.iv_c_4).getDrawable());

        // собрать картинки, показывающие результат (правая сторона)
        // получить ImageView
        ImageView[] resultImages = new ImageView[4];
        resultImages[0] = view.findViewById(R.id.result_image_1);
        resultImages[1] = view.findViewById(R.id.result_image_2);
        resultImages[2] = view.findViewById(R.id.result_image_3);
        resultImages[3] = view.findViewById(R.id.result_image_4);

        //int j=black+white; // количество совпадений (полных и неполных)
        Animation animation;

        for(int i=0; i<4; i++){
            switch (i){ // выбирает анимацию для соответствующей ячейки
                case 1: animation = AnimationUtils.loadAnimation (context, R.anim.trans2); break;
                case 2: animation = AnimationUtils.loadAnimation (context, R.anim.trans3); break;
                case 3: animation = AnimationUtils.loadAnimation (context, R.anim.trans4); break;
                default: animation = AnimationUtils.loadAnimation (context, R.anim.trans1); break;
            }
            if(black>0){ // полное совпадение
                resultImages[i].setImageResource(R.drawable.cristal_quoter); // сменить картинку
                black--;
                resultImages[i].startAnimation(animation); // применить анимацию
                continue; // переход к следующей итерации цикла
            }
            if(white>0){ // неполное совпадение
                resultImages[i].setImageResource(R.drawable.cristal_quoter_tinted); // сменить картинку
                resultImages[i].startAnimation(animation); // применить анимацию
                white--;
                continue; // переход к следующей итерации цикла
            }
            // пустой сектор
            resultImages[i].startAnimation(animation); // применить анимацию
        }

    }
}