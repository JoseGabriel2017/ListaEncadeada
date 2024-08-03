package lista;

public class ListaEncadeada<T> {

    private No<T> incio;
    private No<T> ultimo;
    private int tamanho;

    // retorna a quantidade de nós da lista
    public int getTamanho() {
        return this.tamanho;
    }

    // adiciona um elemento na lista
    public void adicionar(T elemento) {
        No<T> celula = new No<T>(elemento);
        if (tamanho == 0) {
            this.incio = celula;

        } else {
            this.ultimo.setProximo(celula);
        }
        this.ultimo = celula;
        this.tamanho++;
    }

    // se a lista n tiver itens adiciona normalmente, caso tenha adiciona anterior
    // ao No inicial
    public void adicionaInicio(T elemento) {
        if (this.tamanho == 0) {
            No<T> novoNo = new No<T>(elemento);
            this.incio = novoNo;
            this.ultimo = novoNo;
        } else {
            No<T> novoNo = new No<T>(elemento);
            novoNo.setProximo(this.incio);
            this.incio = novoNo;
        }
        tamanho++;
    }

    // adicionar em qualquer posição da lista
    public void adicionarQualquerPosicao(int posicao, T elemento) {

        if (posicao < 0 || posicao > this.tamanho) { // analisa se a posição selecionada é válida
            throw new IllegalArgumentException("Posição Inválida.");
        }

        if (posicao == 0) { // usa o metodo de adicionar no inicio
            this.adicionaInicio(elemento);

        } else if (posicao == this.tamanho) { // caso a posição escolhida seja igual ao tamanho da lista
            this.adicionar(elemento);

        } else { // adiciona no meio da lista em qualquer posição escolhida e reorganiza na ordem
                 // correta casa Nó
            No<T> NoAnterior = this.buscaDeNoPorPosicao(posicao);
            No<T> ProximoNo = NoAnterior.getProximo();
            No<T> novoNo = new No<T>(elemento);
            novoNo.setProximo(ProximoNo);
            NoAnterior.setProximo(novoNo);
            this.tamanho++;
        }

    }

    // busca a partir da posição dada, percorrendo toda a lista caso a posição seja
    // valida
    public No<T> buscaDeNoPorPosicao(int posicao) {

        if (!(posicao >= 0 && posicao <= this.tamanho)) {
            throw new IllegalArgumentException("Posição inexistente.");
        }

        No<T> atual = this.incio;

        for (int i = 0; i < posicao; i++) {
            atual = atual.getProximo();
        }

        return atual;
    }

    // pega o Nó da busca por posição e retorna o elemento contido
    public T buscaElementoPosicao(int posicao) {

        return this.buscaDeNoPorPosicao(posicao).getElemento();
    }

    // busca um elemento da lista e retorna o indice de sua posiçâo
    public int buscaElemento(T elemento) {

        No<T> atual = this.incio;
        int posicao = 0;

        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return posicao;
            }

            posicao++;
            atual = atual.getProximo();
        }

        return -1;

    }

    // remove um nó no inicio da lista e reorganiza
    public void revomerIncio() {

        if (this.tamanho == 0) {
            throw new IllegalArgumentException("A lista está vazia!");
        }

        this.incio = this.incio.getProximo();
        this.tamanho--;

        if (this.tamanho == 0) {
            this.ultimo = null;
        }
    }

    // remove um nó no final da lista e reorganiza
    public void removerFinal() {

        if (this.tamanho == 0) {
            throw new IllegalArgumentException("A lista está vazia!");
        }

        if (tamanho == 1) {
            this.revomerIncio();
        }

        No<T> penultimoNo = this.buscaDeNoPorPosicao(-2);
        penultimoNo.setProximo(null);
        this.ultimo = penultimoNo;
        this.tamanho--;
    }

    // remove por posição
    public void removerQualquerPosicao(int posicao) {

        if (this.tamanho == 0) {
            throw new IllegalArgumentException("A lista está vazia!");
        }

        if (posicao == 0) {
            this.revomerIncio();
        }

        if (posicao == this.tamanho - 1) {
            this.removerFinal();
        }

        No<T> anterior = this.buscaDeNoPorPosicao(posicao - 1);
        No<T> vaiSerRemovido = anterior.getProximo();
        No<T> proximo = vaiSerRemovido.getProximo();
        anterior.setProximo(proximo);
        vaiSerRemovido.setProximo(null); // acho opcional
        this.tamanho--;

    }

    // limpa todos os elementos da lista
    public void limparLista() {

        for (No<T> atual = this.incio; atual != null;) {
            No<T> proximoNo = atual.getProximo();
            atual.setProximo(null);
            atual.setElemento(null);
            atual = proximoNo;
        }

    }

    // toString para deixar mais bonitinho a impressão da lista
    @Override
    public String toString() {

        if (this.tamanho == 0) {
            return "[]";
        }

        StringBuilder build = new StringBuilder();

        No<T> atual = this.incio;
        build.append(atual.getElemento()).append(",");
        while (atual.getProximo() != null) {
            atual = atual.getProximo();
            build.append(atual.getElemento()).append(",");
        }
        return build.toString();
    }

}
