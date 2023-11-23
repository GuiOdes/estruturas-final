package org.example.hash;

import java.util.LinkedList;

public class TabelaHash<K, V> {
    private LinkedList<Entry<K, V>>[] tabela;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanhoInicial) {
        this.tabela = (LinkedList<Entry<K, V>>[]) new LinkedList[tamanhoInicial];
        this.tamanho = 0;
    }

    private static class Entry<K, V> {
        K chave;
        V valor;

        public Entry(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private int calcularIndice(K chave) {
        int hashCode = chave.hashCode();
        return Math.abs(hashCode % tabela.length);
    }

    public void adicionar(K chave, V valor) {
        int indice = calcularIndice(chave);

        if (tabela[indice] == null) {
            tabela[indice] = new LinkedList<>();
        }

        for (Entry<K, V> entry : tabela[indice]) {
            if (entry.chave.equals(chave)) {
                entry.valor = valor;
                return;
            }
        }

        tabela[indice].add(new Entry<>(chave, valor));
        tamanho++;

        if ((double) tamanho / tabela.length > 0.75) {
            redimensionarTabela();
        }
    }

    public V obter(K chave) {
        int indice = calcularIndice(chave);

        if (tabela[indice] != null) {
            for (Entry<K, V> entry : tabela[indice]) {
                if (entry.chave.equals(chave)) {
                    return entry.valor;
                }
            }
        }

        return null;
    }

    public void remover(K chave) {
        int indice = calcularIndice(chave);

        if (tabela[indice] != null) {
            tabela[indice].removeIf(entry -> entry.chave.equals(chave));
            tamanho--;
        }
    }

    private void redimensionarTabela() {
        int novoTamanho = tabela.length * 2;
        LinkedList<Entry<K, V>>[] novaTabela = new LinkedList[novoTamanho];

        for (LinkedList<Entry<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entry<K, V> entry : lista) {
                    int novoIndice = Math.abs(entry.chave.hashCode() % novoTamanho);

                    if (novaTabela[novoIndice] == null) {
                        novaTabela[novoIndice] = new LinkedList<>();
                    }

                    novaTabela[novoIndice].add(entry);
                }
            }
        }

        tabela = novaTabela;
    }

    public String imprimir() {
        StringBuilder resultado = new StringBuilder();

        for (LinkedList<Entry<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entry<K, V> entry : lista) {
                    resultado.append(entry.chave).append("->").append(entry.valor).append(", ");
                }
            }
        }

        if (!resultado.isEmpty()) {
            resultado.setLength(resultado.length() - 2);
        }

        return "[" + resultado + "]";
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}