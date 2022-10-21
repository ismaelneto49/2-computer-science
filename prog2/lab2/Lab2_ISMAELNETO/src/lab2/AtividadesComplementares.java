package lab2;

import java.util.ArrayList;

/**
 * Gerenciador de atividades complementares de um aluno
 *
 * @author Ismael Neto
 */
public class AtividadesComplementares {

    /**
     * Vetor de inteiros, no qual cada elemento representa as horas de um estágio
     * adicionado
     */
    private int[] horasEstagio;

    /**
     * Vetor de inteiros, no qual cada elemento representa os meses de um projeto
     * adicionado
     */
    private int[] mesesProjeto;

    /**
     * Lista de decimais, na qual cada elemento representa as horas de um curso
     * adicionado
     * <p>
     * Espera-se que as frações de hora se limitem apenas a 0,5h
     */
    private ArrayList<Double> horasCurso;

    /**
     * Construtor padrão
     */
    public AtividadesComplementares() {
        this.horasEstagio = new int[9];
        this.mesesProjeto = new int[16];
        this.horasCurso = new ArrayList<Double>();
    }

    /**
     * Método que adiciona a quantidade de horas de um estágio
     *
     * @param horas representa as horas de um estágio a ser adicionado
     */
    public void adicionarEstagio(int horas) {
        for (int i = 0; i < horasEstagio.length; i++) {
            if (horasEstagio[i] == 0) {
                this.horasEstagio[i] = horas;
                break;
            }
        }
    }

    /**
     * Método que adiciona a quantidade de meses de um projeto
     *
     * @param meses representa os meses de um projeto a ser adicionado
     */
    public void adicionarProjeto(int meses) {
        for (int i = 0; i < mesesProjeto.length; i++) {
            if (mesesProjeto[i] == 0) {
                this.mesesProjeto[i] = meses;
                break;
            }
        }
    }

    /**
     * Método que adiciona a quantidade de horas de um curso
     *
     * @param horas representa as horas de um curso a ser adicionado
     */
    public void adicionarCurso(double horas) {
        this.horasCurso.add(horas);
    }

    /**
     * Método que conta quantos créditos o aluno possui baseado em suas horas de
     * estágio, cursos e meses de projeto
     * <p>
     * Sem proporcionalidade, a cada 300h de estágio o aluno recebe 5 créditos;
     * 3 meses de projeto, 2 créditos;
     * 30h de curso, 1 crédito
     *
     * @return A quantidade de créditos que o aluno possui
     */
    public int contaCreditos() {
        int creditos = 0;

        int totalHorasEstagio = 0;
        int totalMesesProjeto = 0;
        double totalHorasCurso = 0;
        for (int horas : this.horasEstagio) {
            totalHorasEstagio += horas;
        }
        for (int meses : this.mesesProjeto) {
            totalMesesProjeto += meses;
        }
        for (Double horas : this.horasCurso) {
            totalHorasCurso += horas;
        }

        creditos += (totalHorasEstagio / 300) * 5;
        creditos += (totalMesesProjeto / 3) * 2;
        creditos += totalHorasCurso / 30;

        return creditos;
    }

    /**
     * Método que pega todas as atividades que o aluno possui (Estágios, Projetos e Cursos)
     *
     * @return As atividades que o aluno possui, assim como suas horas e créditos
     */
    public String[] pegaAtividades() {
        ArrayList<String> atividades = new ArrayList<String>();

        int totalHorasEstagio = 0;
        int totalMesesProjeto = 0;
        double totalHorasCurso = 0;
        for (int horas : this.horasEstagio) {
            totalHorasEstagio += horas;
            if (horas != 0) {
                atividades.add("Estagio " + horas);
            }
        }
        for (int meses : this.mesesProjeto) {
            totalMesesProjeto += meses;
            if (meses != 0) {
                atividades.add("Projeto " + meses);
            }
        }
        for (Double horas : this.horasCurso) {
            totalHorasCurso += horas;
        }
        atividades.add("Cursos " + totalHorasCurso);

        int creditosEstagio = (totalHorasEstagio / 300) * 5;
        int creditosProjeto = (totalMesesProjeto / 3) * 2;
        int creditosCurso = (int) totalHorasCurso / 30;
        atividades.add("Creditos_Estagio " + creditosEstagio);
        atividades.add("Creditos_Projeto " + creditosProjeto);
        atividades.add("Creditos_Cursos " + creditosCurso);

        return atividades.toArray(new String[0]);
    }

}
