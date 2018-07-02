package fr.wcs.liftsimulator_asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {
    private boolean isLiftMoving = false;
    private int currentFloor = 0;
    private static final int TIME_DELAY = 3000;
    private Button btn0, btn1, btn2, btn3 , btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        onBoutonClick(btn0,0);
        onBoutonClick(btn1,1);
        onBoutonClick(btn2,2);
        onBoutonClick(btn3,3);
        onBoutonClick(btn4,4);
        onBoutonClick(btn5,5);
        onBoutonClick(btn6,6);
        onBoutonClick(btn7,7);
        onBoutonClick(btn8,8);
        onBoutonClick(btn9,9);

        }

    private void onBoutonClick(Button button, final int floor) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(floor);
            }
        });
    }

    private void goToFloor(int floor) {
        if (!isLiftMoving && floor != currentFloor) {
            moveNextFloor(floor);
            isLiftMoving = false;
        }
    }

    private void moveNextFloor(int floor) {
        if (floor != currentFloor) {
            isLiftMoving = true;
            MoveLift moveLift = new MoveLift();
            moveLift.execute(floor);
            isLiftMoving = false;

        }
    }

    private class MoveLift extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {

            try {
                Thread.sleep(TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentFloor = (integers[0] > currentFloor) ? currentFloor + 1 : currentFloor - 1;

            publishProgress(currentFloor);

            moveNextFloor(integers[0]);

            return currentFloor;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            TextView floorCount = (TextView) findViewById(R.id.floor_count);
            floorCount.setText(getString(R.string.Etage) + String.valueOf(integer));


        }

    }
    }

