package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 *
 * @author nazarenoandrade
 */
public class MainAgenda {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        System.out.println("Carregando agenda inicial");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaAgenda("agenda_inicial.csv", agenda);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, agenda, scanner);
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.println("\n---\nMENU\n" + "(C)adastrar Contato\n" + "(L)istar Contatos\n" + "(E)xibir Contato\n" + "(F)avoritos\n" + "(A)dicionar Favorito\n" + "(T)ags\n" + "(R)emover Contato\n" + "(S)air\n" + "\n" + "Opção> ");
        return scanner.next().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param agenda  A agenda que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, Agenda agenda, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraContato(agenda, scanner);
                break;
            case "L":
                listaContatos(agenda);
                break;
            case "E":
                exibeContato(agenda, scanner);
                break;
            case "F":
                listaFavoritos(agenda);
                break;
            case "A":
                adicionaFavorito(agenda, scanner);
                break;
            case "T":
                adicionaTag(agenda, scanner);
                break;
            case "R":
                removeContato(agenda, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Cadastra um contato na agenda.
     *
     * @param agenda  A agenda.
     * @param scanner Scanner para pedir informações do contato.
     */
    private static void cadastraContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nPosição na agenda> ");
        int posicao = scanner.nextInt();
        if (posicao < Agenda.POSICAO_MINIMA_AGENDA || posicao > Agenda.POSICAO_MAXIMA_AGENDA) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }

        System.out.print("\nNome> ");
        String nome = scanner.next();
        if (nome.trim().equals("")) {
            System.out.print("\nCONTATO INVALIDO");
            return;
        }

        System.out.print("\nSobrenome> ");
        String sobrenome = scanner.next();

        Contato temp = new Contato(nome, sobrenome, "(00) 00000-0000");
        if (agenda.contatoJahExiste(temp)) {
            System.out.print("\nCONTATO JA CADASTRADO");
            return;
        }

        System.out.print("\nTelefone> ");
        String telefone = scanner.next();
        if (telefone.trim().equals("")) {
            System.out.print("\nCONTATO INVALIDO");
            return;
        }

        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        System.out.println("CADASTRO REALIZADO");
    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     *
     * @param agenda  A agenda.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void exibeContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nQual contato> ");
        int posicao = scanner.nextInt();

        if (posicao < Agenda.POSICAO_MINIMA_AGENDA || posicao > Agenda.POSICAO_MAXIMA_AGENDA || agenda.getContatoById(posicao) == null) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }

        Contato contato = agenda.getContatoById(posicao);
        System.out.println("Dados do contato:\n" + contato);
    }

    /**
     * Formata um contato para impressão na interface.
     *
     * @param contato O contato a ser impresso.
     * @return A String formatada.
     */
    private static String formataContato(Contato contato, int posicao) {
        if ("".equals(contato.getSobrenome())) {
            return posicao + " - " + contato.getNome();
        }
        return posicao + " - " + contato.getNome() + " " + contato.getSobrenome();
    }

    /**
     * Imprime lista de contatos da agenda.
     *
     * @param agenda A agenda sendo manipulada.
     */
    private static void listaContatos(Agenda agenda) {
        System.out.println("\nLista de contatos: ");
        Contato[] contatos = agenda.getContatos();
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                System.out.println(formataContato(contatos[i], i + 1));
            }
        }
    }

    /**
     * Imprime a lista de contatos favoritados na agenda.
     *
     * @param agenda A agenda sendo manipulada.
     */
    private static void listaFavoritos(Agenda agenda) {
        System.out.println("\nLista de favoritos: ");
        Contato[] favoritos = agenda.getFavoritos();
        for (int i = 0; i < favoritos.length; i++) {
            if (favoritos[i] != null) {
                System.out.println(formataContato(favoritos[i], i + 1));
            }
        }
    }

    /**
     * Adiciona um contato à lista de favoritos com base na sua posição na agenda.
     *
     * @param agenda  A agenda sendo manipulada.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void adicionaFavorito(Agenda agenda, Scanner scanner) {
        System.out.print("\nContato> ");
        int posicaoContato = scanner.nextInt();
        if (posicaoContato < Agenda.POSICAO_MINIMA_AGENDA || posicaoContato > Agenda.POSICAO_MAXIMA_AGENDA) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }
        Contato c = agenda.getContatoById(posicaoContato);
        if (agenda.favoritoJahExiste(c)) {
            System.out.print("\nFAVORITO JÁ EXISTE");
            return;
        }

        System.out.print("\nPosicao> ");
        int posicaoFavorito = scanner.nextInt();
        if (posicaoFavorito < Agenda.POSICAO_MINIMA_FAVORITOS || posicaoFavorito > Agenda.POSICAO_MAXIMA_FAVORITOS) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }

        agenda.adicionaFavorito(c, posicaoFavorito);
        System.out.print("\nCONTATO FAVORITADO NA POSIÇÃO " + posicaoFavorito + "!");
    }

    /**
     * Adiciona uma tag a um ou mais contatos com base na sua posição na agenda.
     *
     * @param agenda  A agenda sendo manipulada.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void adicionaTag(Agenda agenda, Scanner scanner) {
        scanner.nextLine();
        System.out.print("\nContato(s)> ");
        String linhaPosicoes = scanner.nextLine();
        String[] posicoesContato = linhaPosicoes.split(" ");
        for (String posicao : posicoesContato) {
            int posicaoContato = Integer.parseInt(posicao);
            if (posicaoContato < Agenda.POSICAO_MINIMA_AGENDA || posicaoContato > Agenda.POSICAO_MAXIMA_AGENDA) {
                System.out.print("\nPOSIÇÃO INVÁLIDA: " + posicaoContato);
                return;
            }
        }

        System.out.print("\nTag> ");
        String tag = scanner.nextLine();

        System.out.print("\nPosicao tag> ");
        int posicaoTag = scanner.nextInt();
        if (posicaoTag < Contato.POSICAO_MINIMA_TAG || posicaoTag > Contato.POSICAO_MAXIMA_TAG) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }

        for (String posicao : posicoesContato) {
            int posicaoContato = Integer.parseInt(posicao);
            Contato c = agenda.getContatoById(posicaoContato);
            c.adicionaTag(posicaoTag, tag);
        }
    }

    /**
     * Remove um contato da agenda. Caso favorito, também o remove da lista de favoritos.
     *
     * @param agenda  A agenda sendo manipulada.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void removeContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nPosição na agenda> ");
        int posicao = scanner.nextInt();

        if (posicao < Agenda.POSICAO_MINIMA_AGENDA || posicao > Agenda.POSICAO_MAXIMA_AGENDA) {
            System.out.print("\nPOSIÇÃO INVÁLIDA");
            return;
        }
        agenda.removeContato(posicao);
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê uma agenda de um arquivo csv.
     *
     * @param arquivoContatos O caminho para o arquivo.
     * @param agenda          A agenda que deve ser populada com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
        LeitorDeAgenda leitor = new LeitorDeAgenda();

        int carregados = leitor.carregaContatos(arquivoContatos, agenda);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}
