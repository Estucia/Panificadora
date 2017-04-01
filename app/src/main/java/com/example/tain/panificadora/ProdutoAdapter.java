package com.example.tain.panificadora;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tainã on 25/03/2017.
 */

public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> lista = new ArrayList<>();
    private Context context;


    public ProdutoAdapter(List<Produto> lista,Context context){
        this.lista = lista;
        this.context = context;
    }

    public void remover(Produto produto){
        lista.remove(produto);
        notifyDataSetChanged();
    }

    public void alterar(Produto produto){
        int index = lista.indexOf(produto);
        if (index > -1){
            lista.set(index, produto);
            notifyDataSetChanged();
        }
    }


    public void addProduto(Produto produto){
        lista.add(produto);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return lista.size();
    }

    @Override
    public Object getItem(int position){
        if (lista.size() > position){
            return lista.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(R.layout.produto_lista,parent,false);

        TextView txtCodigo = (TextView)convertView.findViewById(R.id.txtCodigo);
        TextView txtNomeProduto = (TextView)convertView.findViewById(R.id.txtNomeProduto);
        TextView txtPrecoCusto = (TextView)convertView.findViewById(R.id.txtPrecoCusto);
        TextView txtPrecoVenda = (TextView)convertView.findViewById(R.id.txtPrecoVenda);
        TextView txtObs = (TextView)convertView.findViewById(R.id.txtObs);
        TextView txtEmbalagem = (TextView)convertView.findViewById(R.id.txtEmbalagem);
        TextView txtMarca = (TextView)convertView.findViewById(R.id.txtMarca);

        Produto produto = lista.get(position);
        txtCodigo.setText(produto.getCodigo());
        txtNomeProduto.setText(produto.getNomeProduto());
        txtPrecoCusto.setText(produto.getPrecoCusto());
        txtPrecoVenda.setText(produto.getPrecoVenda());
        txtObs.setText(produto.getObs());
        txtEmbalagem.setText(produto.getEmbalagem());
        txtMarca.setText(produto.getMarca());


        return convertView;
    }

}
