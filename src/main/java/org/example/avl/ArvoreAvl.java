package org.example.avl;

public class ArvoreAvl {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        } else {
            // Valor duplicado não é permitido em uma AVL
            return no;
        }

        // Atualiza a altura do nó atual
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        // Verifica o balanceamento e realiza as rotações, se necessário
        int balanceamento = getBalanceamento(no);

        // Rotação simples à direita
        if (balanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }

        // Rotação simples à esquerda
        if (balanceamento < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }

        // Rotação dupla à esquerda-direita
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Rotação dupla à direita-esquerda
        if (balanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        if (no == null) {
            return no;
        }

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            // Nó com apenas um filho ou sem filho
            if ((no.esquerda == null) || (no.direita == null)) {
                No temp = null;
                if (temp == no.esquerda) {
                    temp = no.direita;
                } else {
                    temp = no.esquerda;
                }

                // Caso sem filho
                if (temp == null) {
                    temp = no;
                    no = null;
                } else { // Caso com um filho
                    no = temp; // Copia o conteúdo do filho não nulo
                }
            } else {
                // Nó com dois filhos, obter o sucessor
                No temp = encontrarMinimo(no.direita);

                // Copia o valor do sucessor para este nó
                no.valor = temp.valor;

                // Remove o sucessor
                no.direita = remover(no.direita, temp.valor);
            }
        }

        // Se a árvore tinha apenas um nó, então retorna
        if (no == null) {
            return no;
        }

        // Atualiza a altura do nó atual
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        // Verifica o balanceamento e realiza as rotações, se necessário
        int balanceamento = getBalanceamento(no);

        // Rotação simples à direita
        if (balanceamento > 1 && getBalanceamento(no.esquerda) >= 0) {
            return rotacaoDireita(no);
        }

        // Rotação dupla à esquerda-direita
        if (balanceamento > 1 && getBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Rotação simples à esquerda
        if (balanceamento < -1 && getBalanceamento(no.direita) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação dupla à direita-esquerda
        if (balanceamento < -1 && getBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No encontrarMinimo(No no) {
        No atual = no;

        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }

        return atual;
    }

    public String imprimirArvore() {
        return imprimirRec(raiz, "", false);
    }

    private String imprimirRec(No no, String prefix, boolean isEsquerda) {
        StringBuilder sb = new StringBuilder();

        if (no != null) {
            sb.append(prefix);
            sb.append("└── ");
            sb.append(no.valor);
            sb.append("\n");

            if (no.esquerda != null) {
                sb.append(imprimirRec(no.esquerda, prefix + (isEsquerda ? "│   " : "    "), true));
            }

            if (no.direita != null) {
                sb.append(imprimirRec(no.direita, prefix + (isEsquerda ? "│   " : "    "), false));
            }
        }

        return sb.toString();
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    private int getBalanceamento(No no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }
}