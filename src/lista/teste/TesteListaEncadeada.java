package lista.teste;

import lista.ListaEncadeada;

public class TesteListaEncadeada {

    public static void main(String[] args) {

        ListaEncadeada list = new ListaEncadeada<>();

        // teste metodo de adicionar
        list.adicionar(1);
        list.adicionar(2);
        list.adicionar(3);
        System.out.println(list);

        /*
         * Teste limpar lista
         * list.limparLista();
         * System.out.println(list);
         */

        // busca por elemento
        int descoberta = list.buscaElemento(1);
        System.out.println(descoberta);

        list.removerFinal();
        list.revomerIncio();
        list.removerQualquerPosicao(1);

        list.adicionarQualquerPosicao(1, 25);
    }

}
