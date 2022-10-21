package lab2;

/**
 * Representação do registro de tempo online de um aluno
 *
 * @author Ismael Neto
 */
public class RegistroTempoOnline {

    /**
     * Define o nome da Disciplina a qual o Registro Online se refere
     */
    private String nomeDisciplina;

    /**
     * Define o tempo online esperado da Disciplina a qual o Registro Online se
     * refere
     * <p>
     * Tempo online esperado é a quantidade de horas esperadas que um aluno dedique
     * ao estudo daquela Disciplina
     */
    private int tempoOnlineEsperado;

    /**
     * Define o tempo online gasto pelo aluno estudando a Disciplina a qual o
     * Registro Online se refere
     */
    private int tempoOnlineGasto;

    /**
     * Construtor para todos os argumentos
     *
     * @param nomeDisciplina      O nome da Disciplina a qual o Registro Online irá
     *                            se referir
     * @param tempoOnlineEsperado O tempo online esperado para a Disciplina a qual
     *                            o Registro Online irá se referir
     */
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineEsperado = tempoOnlineEsperado;
    }

    /**
     * Construtor recebe o nome e define o tempo online esperado da disciplina. Valor
     * padrão é 120
     *
     * @param nomeDisciplina O nome da Disciplina a qual o Registro Online irá
     *                       se referir
     */
    public RegistroTempoOnline(String nomeDisciplina) {
        this(nomeDisciplina, 120);
    }

    /**
     * Método que adiciona tempo online dedicado à disciplina
     *
     * @param tempo Tempo online gasto pelo aluno estudando àquela Disciplina
     */
    public void adicionaTempoOnline(int tempo) {
        if (tempo > 0) {
            this.tempoOnlineGasto += tempo;
        }
    }

    /**
     * Método que verifica se o aluno atingiu a meta de tempo online esperado para
     * àquela Disciplina
     *
     * @return um booleano verdadeiro, caso tenha atingido; e falso, caso contrário
     */
    public boolean atingiuMetaTempoOnline() {
        if (this.tempoOnlineGasto >= this.tempoOnlineEsperado) {
            return true;
        }
        return false;
    }

    /**
     * Método que mostra uma representação textual do Registro Online
     *
     * @return A representação textual do Registo Online, na forma "nomeDisciplina
     * tempoGasto/tempoEsperado"
     */
    public String toString() {
        return nomeDisciplina + " " + tempoOnlineGasto + "/" + tempoOnlineEsperado;
    }

}
