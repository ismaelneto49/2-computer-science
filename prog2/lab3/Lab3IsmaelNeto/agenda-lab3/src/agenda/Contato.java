package agenda;

/**
 * Representação de um Contato no sistema.
 * Um Contato possui nome, sobrenome, telefone e uma lista de até 5 tags.
 *
 * @author Ismael
 */
public class Contato {

    /**
     * Constante que representa a quantidade máxima de tags que um Contato pode possuir.
     */
    private static final int QUANTIDADE_TAGS = 5;

    /**
     * Constante que representa a menor posição que um contato terá dentro da lista de tags.
     * A posicão do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MINIMA_TAG = 1;

    /**
     * Constante que representa a maior posição que um contato terá dentro da lista de tags.
     * A posicão do contato corresponde ao seu índice + 1.
     */
    public static final int POSICAO_MAXIMA_TAG = 5;

    /**
     * O nome do contato, um dos parâmetros para sua identificação única.
     */
    private String nome;

    /**
     * O sobrenome do contato, um dos parâmetros para sua identificação única.
     */
    private String sobrenome;

    /**
     * O telefone do contato. Geralmente uma string no formato (99) 99999-9999.
     */
    private String telefone;

    /**
     * A lista de tags que um contato possui.
     */
    private String[] tags;

    /**
     * Atributo que indica se um contato é ou não um favorito.
     */
    private boolean favorito;

    /**
     * Cria um contato.
     *
     * @param nome      O nome do contato a ser criado.
     * @param sobrenome O sobrenome do contato a ser criado.
     * @param telefone  O telefone do contato a ser criado.
     */
    public Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.tags = new String[QUANTIDADE_TAGS];
        this.favorito = false;
    }

    /**
     * Adiciona uma tag à lista de tags.
     * A posição da tag corresponde ao seu índice + 1
     *
     * @param posicao A posição que a tag será adicionada.
     * @param tag     O texto que representa a tag.
     */
    public void adicionaTag(int posicao, String tag) {
        this.tags[posicao - 1] = tag;
    }

    /**
     * Gera a representação textual de um Contato.
     * Caso o contato seja um favorito, a representação será precedida de um ❤️.
     *
     * @return A representação textual do Contato.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.favorito) {
            builder.append("❤️");
        }
        builder.append(this.nome);
        if (!"".equals(this.sobrenome)) {
            builder.append(" " + this.sobrenome);
        }
        builder.append("\n" + this.telefone + "\n");
        for (String t : this.tags) {
            if (t != null) {
                builder.append(t + " ");
            }
        }
        return builder.toString().trim();
    }

    /**
     * Compara o contato atual com um objeto e verifica sua igualdade.
     * Um contato é igual a outro caso seus nomes e sobrenomes coincidam.
     *
     * @param o O objeto a ser comparado com o contato atual.
     * @return Um booleano que indica se há ou não igualdade.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Contato c = (Contato) o;

        return this.nome.equals(c.getNome()) && this.sobrenome.equals(c.getSobrenome());
    }

    /**
     * Acessa o nome do contato.
     *
     * @return O nome do contato.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Acessa o sobrenome do contato.
     *
     * @return O sobrenome do contato.
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /**
     * Acessa o atributo que indica se o contato é ou não um favorito.
     *
     * @return O atributo que indica se o contato é ou não um favorito.
     */
    public boolean isFavorito() {
        return this.favorito;
    }

    /**
     * Altera o valor do atributo que indica se o contato é ou não um favorito.
     *
     * @param favorito o novo valor a ser colocado no atributo.
     */
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}
