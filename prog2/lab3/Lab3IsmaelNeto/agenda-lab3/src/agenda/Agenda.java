package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos.
 *
 * @author nazareno
 */
public class Agenda {

    /**
     * Constante que representa o tamanho máximo de uma agenda, ou seja, a quantidade máxima de contatos aceitos.
     */
    private static final int TAMANHO_AGENDA = 100;

    /**
     * Constante que representa a quantidade máxima de contatos que podem ser adicionados à lista de favoritos.
     */
    private static final int QUANTIDADE_FAVORITOS = 10;

    /**
     * Constante que representa a menor posição que um contato terá dentro da agenda.
     * A posicão do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MINIMA_AGENDA = 1;

    /**
     * Constante que representa a maior posição que um contato terá dentro da agenda.
     * A posição do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MAXIMA_AGENDA = 100;

    /**
     * Constante que representa a menor posição que um contato terá na lista de favoritos.
     * A posição do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MINIMA_FAVORITOS = 1;

    /**
     * Constante que representa a maior posição que um contato terá na lista de favoritos.
     * A posição do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MAXIMA_FAVORITOS = 100;


    /**
     * Lista de todos os contatos da Agenda.
     */
    private Contato[] contatos;

    /**
     * Lista de todos os contatos favoritados na Agenda.
     */
    private Contato[] favoritos;

    /**
     * Cria uma agenda.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
        this.favoritos = new Contato[QUANTIDADE_FAVORITOS];
    }

    /**
     * Acessa a lista de contatos mantida.
     *
     * @return O array de contatos.
     */
    public Contato[] getContatos() {
        return this.contatos.clone();
    }

    /**
     * Acessa a lista de contatos favoritos mantida.
     *
     * @return O array de contatos favoritos.
     */
    public Contato[] getFavoritos() {
        return this.favoritos.clone();
    }

    /**
     * Acessa os dados de um contato específico.
     *
     * @param posicao Posição do contato na agenda.
     * @return Dados do contato. Null se não há contato na posição.
     */
    public Contato getContatoById(int posicao) {
        return contatos[posicao - 1];
    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     *
     * @param posicao   Posição do contato.
     * @param nome      Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone  Telefone do contato.
     */
    public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        Contato c = new Contato(nome, sobrenome, telefone);
        if (this.contatoJahExiste(c) || nome.trim().equals("") || telefone.trim().equals("")) {
            return;
        }
        this.contatos[posicao - 1] = c;
    }

    /**
     * Remove um contato da Agenda com base na sua posição.
     *
     * @param posicao A posição do contato a ser removido.
     */
    public void removeContato(int posicao) {
        Contato c = this.getContatoById(posicao);
        for (int i = 0; i < this.favoritos.length; i++) {
            if (this.favoritos[i] != null && this.favoritos[i].equals(c)) {
                this.favoritos[i] = null;
                break;
            }
        }
        this.contatos[posicao - 1] = null;
    }

    /**
     * Adiciona um contato à lista de favoritos.
     *
     * @param c       O Contato a ser adicionado.
     * @param posicao A posição que o contato será colocado na lista de favoritos.
     */
    public void adicionaFavorito(Contato c, int posicao) {
        if (this.favoritoJahExiste(c)) return;

        if (this.favoritos[posicao - 1] != null) {
            this.favoritos[posicao - 1].setFavorito(false);
        }
        this.favoritos[posicao - 1] = c;
        c.setFavorito(true);

    }

    /**
     * Verifica se o Contato dado existe na Agenda.
     *
     * @param c O Contato a ser verificado.
     * @return Um booleano que indica se o Contato está ou não presente na Agenda.
     */
    public boolean contatoJahExiste(Contato c) {
        for (Contato temp : this.contatos) {
            if (temp != null && temp.equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o Contato dado está na lista de favoritos.
     *
     * @param c O Contato recebido.
     * @return Um booleano que indica se o Contato está ou não presente na lista de favoritos.
     */
    public boolean favoritoJahExiste(Contato c) {
        return c.isFavorito();
    }

}
