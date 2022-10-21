package lab2;

/**
 * Representação do tempo de descanso de um estudante
 *
 * @author Ismael Neto
 */
public class Descanso {

    /**
     * Quantidade discreta de horas que o aluno descansou no período de N semanas
     */
    private int horasDescanso;

    /**
     * A quantidade de semanas as quais as horas de descanso do aluno se referem
     */
    private int numeroSemanas = 0;

    /**
     * Status do aluno, que varia entre "cansado"(padrão) e "descansado".
     * Caso ( horasDescanso / numeroSemanas ) < 26, então "cansado";
     * caso ( horasDescanso / numeroSemanas ) >= 26, então "descansado".
     */
    private String statusGeral;

    /**
     * Construtor que seta o valor padrão do status geral do aluno como "cansado"
     */
    public Descanso() {
        this.statusGeral = "cansado";
    }

    /**
     * Define as horas de descanso de um aluno
     *
     * @param valor A quantidade de horas descansadas
     */
    public void defineHorasDescanso(int valor) {
        this.horasDescanso = valor;
    }

    /**
     * Define a quantidade de semanas para as horas de descanso
     *
     * @param valor A quantidade de semanas
     */
    public void defineNumeroSemanas(int valor) {
        this.numeroSemanas = valor;
    }

    /**
     * Metódo que mostra o status geral do aluno
     *
     * @return O status geral do aluno
     */
    public String getStatusGeral() {
        if (this.numeroSemanas != 0 && this.horasDescanso / this.numeroSemanas >= 26) {
            this.statusGeral = "descansado";
        } else {
            this.statusGeral = "cansado";
        }
        return statusGeral;
    }

}
