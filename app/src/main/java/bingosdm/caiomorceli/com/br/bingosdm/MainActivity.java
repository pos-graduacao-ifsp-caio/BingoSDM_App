package bingosdm.caiomorceli.com.br.bingosdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder viewHolder = new ViewHolder();
    private Random geradorRandomico;
    private ArrayList<Integer> numerosDisponiveis = null;

    @Override
    protected void onStart() {
        super.onStart();
        this.iniializarBolasBingo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geradorRandomico = new Random(System.currentTimeMillis());   // Cria a instância do gerador randômico.

        this.viewHolder.btnSortearBola = this.findViewById(R.id.btn_sortear_bola);
        this.viewHolder.txtViewLetraColuna = this.findViewById(R.id.txtView_letra_coluna);
        this.viewHolder.imgViewLetraColuna = this.findViewById(R.id.imgView_letra_coluna);
        this.viewHolder.txtViewNumero = this.findViewById(R.id.txtView_numero);
        this.viewHolder.imgViewNroBola = this.findViewById(R.id.imgView_nro_bola);
        this.viewHolder.btnNovoSorteio = this.findViewById(R.id.btn_novo_sorteio);

        this.setListener();
    }

    private void setListener(){
        this.viewHolder.btnSortearBola.setOnClickListener(this);
        this.viewHolder.btnNovoSorteio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_sortear_bola){
            if((this.numerosDisponiveis.size() > 0)) {
                // Gera a posicao aleatória na lista.
                Integer posicaoLista = geradorRandomico.nextInt(this.numerosDisponiveis.size());
                // Recupera o número que está na posição informada da lista.
                Integer bolaSorteada = this.numerosDisponiveis.get(posicaoLista);

                this.numerosDisponiveis.remove(bolaSorteada);
                definirColuna(bolaSorteada);
                definirNumero(bolaSorteada);

            }   else{
                    Toast.makeText(getApplicationContext(), "Fim da rodada.", Toast.LENGTH_SHORT).show();
                    this.viewHolder.btnNovoSorteio.setVisibility(View.VISIBLE);
                }

        }   else if(id == R.id.btn_novo_sorteio){
                this.viewHolder.btnNovoSorteio.setVisibility(View.GONE);
                this.viewHolder.txtViewLetraColuna.setVisibility(View.GONE);
                this.viewHolder.imgViewLetraColuna.setVisibility(View.GONE);
                this.viewHolder.txtViewNumero.setVisibility(View.GONE);
                this.viewHolder.imgViewNroBola.setVisibility(View.GONE);
                iniializarBolasBingo();
        }
    }

    private void iniializarBolasBingo(){
        this.numerosDisponiveis = new ArrayList();      // iniciando os 75 números disponíveis do bingo.
        for(int i = 1; i <= 75; i++){
            this.numerosDisponiveis.add(i);
        }
    }

    private void definirColuna(int bolaSorteada){
        // defini o texto da letra da coluna e tambem define a imagem da letra da coluna
        if(bolaSorteada >=1 && bolaSorteada <= 15){
            this.viewHolder.imgViewLetraColuna.setImageResource(R.mipmap.letras_bingo_b);
        }   else if(bolaSorteada >=16 && bolaSorteada <= 30){
                this.viewHolder.imgViewLetraColuna.setImageResource(R.mipmap.letras_bingo_i);
        }   else if(bolaSorteada >=31 && bolaSorteada <= 45){
                this.viewHolder.imgViewLetraColuna.setImageResource(R.mipmap.letras_bingo_n);
        }   else if(bolaSorteada >=46 && bolaSorteada <= 60){
                this.viewHolder.imgViewLetraColuna.setImageResource(R.mipmap.letras_bingo_g);
        }   else{
                this.viewHolder.imgViewLetraColuna.setImageResource(R.mipmap.letras_bingo_o);
        }
    }

    private void definirNumero(int bolaSorteada){
        // Define a imagem da bola sorteada em tempo de execução.  Busca a imagem na pasta drawable.
        this.viewHolder.imgViewNroBola.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("numero_"+String.valueOf(bolaSorteada), "drawable", getPackageName())));

        this.viewHolder.txtViewLetraColuna.setVisibility(View.VISIBLE);
        this.viewHolder.imgViewLetraColuna.setVisibility(View.VISIBLE);
        this.viewHolder.txtViewNumero.setVisibility(View.VISIBLE);
        this.viewHolder.imgViewNroBola.setVisibility(View.VISIBLE);
    }

    private class ViewHolder {
        Button btnSortearBola;
        TextView txtViewLetraColuna;
        ImageView imgViewLetraColuna;
        TextView txtViewNumero;
        ImageView imgViewNroBola;
        Button btnNovoSorteio;
    }
}
