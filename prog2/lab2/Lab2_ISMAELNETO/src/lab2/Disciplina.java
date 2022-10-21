package lab2;

import java.util.Arrays;

/**
 * Representação de uma disciplina
 *
 * @author Ismael Neto
 */
public class Disciplina {

    /**
     * Define o nome da Disciplina
     */
    private String nome;

    /**
     * Define um vetor que irá conter as 4 notas do aluno
     */
    private double[] notas;

    /**
     * Define as horas de estudo que o aluno dedicará àquela disciplina
     */
    private int horasEstudo;

    /**
     * Construtor que recebe o nome da disciplina
     *
     * @param nome O nome da disciplina a ser criada
     */
    public Disciplina(String nome) {
        this.nome = nome;
        this.notas = new double[4];
    }

    /**
     * Método que adiciona horas de estudo àquela disciplina
     *
     * @param horas A quantidade de horas a serem adicionadas
     */
    public void cadastraHoras(int horas) {
        this.horasEstudo += horas;
    }

    /**
     * Método que adiciona uma nota à disciplina
     *
     * @param nota      o número da nota, varia entre 1, 2, 3 e 4
     * @param valorNota O valor da nota recebida
     */
    public void cadastraNota(int nota, double valorNota) {
        if ((nota >= 1 && nota <= 4) && valorNota >= 0) {
            notas[nota - 1] = valorNota;
        }
    }

    /**
     * Método que calcula a média aritmética do aluno
     *
     * @return a média aritmética do aluno
     */
    private double calculaMedia() {
        double media = 0;
        for (double nota : notas) {
            media += nota;
        }
        return media / 4;
    }

    /**
     * Método que informa se o aluno foi ou não aprovado
     *
     * @return verdadeiro, caso aprovado; falso, caso contrário
     */
    public boolean aprovado() {
        double media = calculaMedia();
        if (media >= 7.0) {
            return true;
        }
        return false;
    }

    /**
     * Método que mostra uma representação textual do objeto Disciplina
     *
     * @return Uma String no formato "nomeDisciplina horasEstudo média [nota1, nota2. nota3, nota4]"
     */
    public String toString() {
        return this.nome + " " + horasEstudo + " " + calculaMedia() + " " + Arrays.toString(notas);
    }

}
