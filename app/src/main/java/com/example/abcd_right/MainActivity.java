package com.example.abcd_right;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText a, b, c, d;
    Button btn;
    TextView result1, result2;
    AsyncMath functions;

    class AsyncMath extends AsyncTask <Integer, Void, Void>{
        @Override
        protected Void doInBackground(Integer... integers) {
            int a = integers[0];
            int b = integers[1];
            int c = integers[2];
            float disc = b*b - 4*a*c;
            if (disc < 0) result2.setText("Нет решений");
            else if (disc == 0) {
                String str = String.valueOf(-b/2*a);
                str+=";";
                result2.setText(str);
            }
            else {
                String str = String.valueOf((-b+Math.sqrt(disc))/(2*a));
                str+=";\n";
                str+= String.valueOf((-1*(b+Math.sqrt(disc)))/(2*a));
                str+=";";
                result2.setText(str);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void ... values) {}

        @Override
        protected void onPreExecute() {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.A);
        b = findViewById(R.id.B);
        c = findViewById(R.id.C);
        d = findViewById(R.id.D);
        btn = findViewById(R.id.btn);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {
                        int A = Integer.parseInt(a.getText().toString());
                        int B = Integer.parseInt(b.getText().toString());
                        int C = Integer.parseInt(c.getText().toString());
                        int D = Integer.parseInt(d.getText().toString());
                        if (functions == null || functions.getStatus() == AsyncTask.Status.FINISHED) {
                            functions = new AsyncMath();
                            functions.execute(B, C, D);
                            float disc = B * B - 4 * A * C;
                            if (disc < 0) result1.setText("Нет решений");
                            else if (disc == 0) {
                                String str = String.valueOf(-B / 2 * A);
                                str += ";";
                                result1.setText(str);
                            } else {
                                String str = String.valueOf((-B + Math.sqrt(disc)) / (2 * A));
                                str += ";\n";
                                str += String.valueOf(-1 * (B + Math.sqrt(disc)) / (2 * A));
                                str += ";";
                                result1.setText(str);
                            }
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Нужно ввести только цифры и всё, больше ничего", Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }

}