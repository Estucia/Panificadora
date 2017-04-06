package com.example.tain.panificadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView txtCodigo, txtNome,txtPrecoCusto,txtPrecoVenda,txtObs;
    private EditText edtCodigo,edtNomeProduto,edtPrecoCusto,edtPrecoVenda,edtObs;
    private Button btnExcluir, btnSalvar, btnNovo;
    private Spinner spnMarca,spnEmbalagem;
    private Produto produto;
    private ProdutoAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnEmbalagem = (Spinner)findViewById(R.id.spnEmbalagem);
        spnMarca = (Spinner)findViewById(R.id.spnMarca);
        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtNome = (TextView)findViewById(R.id.txtNomeProduto);
        txtPrecoCusto = (TextView)findViewById(R.id.txtPrecoCusto);
        txtPrecoVenda = (TextView)findViewById(R.id.txtPrecoVenda);
        txtObs = (TextView)findViewById(R.id.txtObs);
        edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        edtNomeProduto = (EditText)findViewById(R.id.edtNomeProduto);
        edtPrecoCusto = (EditText)findViewById(R.id.edtPrecoCusto);
        edtPrecoVenda = (EditText)findViewById(R.id.edtPrecoVenda);
        edtObs = (EditText)findViewById(R.id.edtObs);
        btnNovo = (Button) findViewById(R.id.btnNovo);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        listView = (ListView)findViewById(R.id.listView);

        final List<Produto> lista = new ArrayList<>();
        adapter = new ProdutoAdapter(lista,this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto prod = (Produto)adapter.getItem(position);
                edtCodigo.setText(prod.getCodigo());
                edtNomeProduto.setText(prod.getNomeProduto());
                edtPrecoCusto.setText(prod.getPrecoCusto());
                edtPrecoVenda.setText(prod.getPrecoVenda());
                edtObs.setText(prod.getObs());

                SpinnerAdapter spnAdapterEmbalagem = spnEmbalagem.getAdapter();
                for(int w= 0; w < spnAdapterEmbalagem.getCount(); w++){
                    String embalagem = spnAdapterEmbalagem.getItem(w).toString();

                    if (embalagem.equals(prod.getEmbalagem())){
                        spnEmbalagem.setSelection(w);
                        break;
                    }
                }

                SpinnerAdapter spnAdapterMarca = spnMarca.getAdapter();
                for(int x= 0; x  < spnAdapterMarca.getCount(); x++){
                    String marca = spnAdapterMarca.getItem(x).toString();

                    if (marca.equals(prod.getMarca())){
                        spnMarca.setSelection(x);
                        break;
                    }
                }
            }


        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir(v);
                Limpar();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi){
                salvar();
                Limpar();
            }
        });


        btnNovo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi){
                produto = null;
                Limpar();
            }
        });

        List<String> Embalagens = new ArrayList<>();
        Embalagens.add("Embalagem 1");
        Embalagens.add("Embalagem 2");
        ArrayAdapter adapterEmb = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,Embalagens.toArray());
        spnEmbalagem.setAdapter(adapterEmb);

        List<String> marcas = new ArrayList<>();
        marcas.add("Marca 1");
        marcas.add("Marca 2");
        ArrayAdapter adaptermarc = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,marcas.toArray());
        spnMarca.setAdapter(adaptermarc);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void excluir(View view){
           adapter.remover(produto);
    }

    public void salvar(){

        if (edtCodigo.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o Código",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtNomeProduto.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o Nome do Produto",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtPrecoCusto.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o Preço de Custo",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtPrecoVenda.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher o Preço de Venda",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtObs.getText().toString().isEmpty()){
            Toast.makeText(this,"Favor preencher a Observação",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        boolean editando = true;
        if (produto == null){
            produto = new Produto();
            editando = false;
        }

        produto.setCodigo(edtCodigo.getText().toString());
        produto.setNomeProduto(edtNomeProduto.getText().toString());
        produto.setPrecoCusto(edtPrecoCusto.getText().toString());
        produto.setPrecoVenda(edtPrecoVenda.getText().toString());
        produto.setObs(edtObs.getText().toString());
        produto.setEmbalagem(spnEmbalagem.getSelectedItem().toString());
        produto.setMarca(spnMarca.getSelectedItem().toString());

        if (editando){
            adapter.alterar(produto);
        }else{
            adapter.addProduto(produto);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putString("codigo",edtCodigo.getText().toString());
        state.putString("nomeproduto",edtNomeProduto.getText().toString());
        state.putString("precocusto",edtPrecoCusto.getText().toString());
        state.putString("precovenda",edtPrecoVenda.getText().toString());
        state.putString("obs",edtObs.getText().toString());
        state.putString("embalagem",spnEmbalagem.getSelectedItem().toString());
        state.putString("marca",spnMarca.getSelectedItem().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        SpinnerAdapter adapterEmbalagem = spnEmbalagem.getAdapter();
        int qtdEmbalagem = adapterEmbalagem.getCount();
        String embalagem = state.getString("embalagem");
        for (int k = 0; k < qtdEmbalagem;k++){
            if (adapter.getItem(k).equals(embalagem)){
                spnEmbalagem.setSelection(k);
            }
        }
        SpinnerAdapter adapterMarca = spnMarca.getAdapter();
        int qtdMarca = adapterMarca.getCount();
        String marca = state.getString("marca");
        for (int x = 0; x < qtdMarca;x++){
            if (adapter.getItem(x).equals(marca)){
                spnMarca.setSelection(x);
            }
        }

        edtCodigo.setText(state.getString("codigo"));
        edtNomeProduto.setText(state.getString("nomeproduto"));
        edtPrecoCusto.setText(state.getString("precocusto"));
        edtPrecoVenda.setText(state.getString("precovenda"));
        edtObs.setText(state.getString("obs"));

    }

    public void Limpar(){
        edtCodigo.setText("");
        edtNomeProduto.setText("");
        edtPrecoCusto.setText("");
        edtPrecoVenda.setText("");
        edtObs.setText("");
        spnEmbalagem.setSelection(0);
        spnMarca.setSelection(0);
    }

}
