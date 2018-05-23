package uno.unisal.com.uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JogarActivity extends AppCompatActivity {


    private ListView maoJogador;
    private String[] cartasJogador = {
            "1Amarelo","2Azul","3vermelho", "Pula", "beico"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar);

        maoJogador = findViewById(R.id.listViewJogarId);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                cartasJogador
        );

        maoJogador.setAdapter (adaptador);
    }
}
