import static org.junit.jupiter.api.Assertions.*;

import agenda.Agenda;
import agenda.Contato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendaTest {

    private Agenda a;

    @BeforeEach
    public void initAgenda() {
        this.a = new Agenda();
        this.a.cadastraContato(1, "Bob", "Dylan", "(00) 00000-0000");
        this.a.cadastraContato(2, "Leonard", "Cohen", "(00) 00000-0000");
        this.a.cadastraContato(3, "Ozzy", "Osbourne", "(00) 00000-0000");
    }

    @Test
    public void testAgenda() {
        new Agenda();
    }

    @Test
    public void testGetContatos() {
        Contato[] contatos = {this.a.getContatoById(1), this.a.getContatoById(2), this.a.getContatoById(3)};
        Contato[] getContatos = this.a.getContatos();

        for (int i = 0; i < contatos.length; i++) {
            Contato c = contatos[i];
            Contato g = getContatos[i];
            if (c.equals(g)) {
                continue;
            } else {
                fail();
            }
        }
    }

    @Test
    public void testGetFavoritos() {
        Contato c = this.a.getContatoById(1);
        this.a.adicionaFavorito(c, 2);

        Contato[] favoritos = this.a.getFavoritos();
        for (Contato f : favoritos) {
            if (f == null) {
                continue;
            } else if (!f.isFavorito()) {
                fail();
            } else if (!(f.equals(c))) {
                fail();
            }
        }
    }

    @Test
    public void testCadastraContato() {
        Contato ismael = new Contato("Ismael", "Neto", "(00) 00000-0000");
        this.a.cadastraContato(4, "Ismael", "Neto", "(00) 00000-0000");

        Contato c = this.a.getContatoById(4);
        assertEquals(ismael, c);
    }

    @Test
    public void testCadastraContatoAbaixoLimiteInferior() {
        try {
            this.a.cadastraContato(0, "Ismael", "Neto", "(00) 00000-0000");
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testCadastraContatoAcimaLimiteSuperior() {
        try {
            this.a.cadastraContato(101, "Ismael", "Neto", "(00) 00000-0000");
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testCadastraContatoSobrescrita() {
        Contato carlinhosDaora = new Contato("Carlinhos", "Daora", "(00) 00000-0000");
        this.a.cadastraContato(3, "Carlinhos", "Daora", "(00) 00000-0000");

        Contato c = this.a.getContatoById(3);
        assertEquals(carlinhosDaora, c);
    }

    @Test
    public void testCadastraContatoExistente() {
        this.a.cadastraContato(4, "Ismael", "Neto", "(00) 00000-0000");
        this.a.cadastraContato(5, "Ismael", "Neto", "(00) 00000-0000");
        Contato c = this.a.getContatoById(5);
        assertNull(c);
    }

    @Test
    public void testCadastraContatoSemNome() {
        this.a.cadastraContato(4, "", "Neto", "(00) 00000-0000");
        Contato c = this.a.getContatoById(4);
        assertNull(c);
    }

    @Test
    public void testCadastraContatoSemTelefone() {
        this.a.cadastraContato(4, "Ismael", "Neto", "    ");
        Contato c = this.a.getContatoById(4);
        assertNull(c);
    }

    @Test
    public void testGetContatoById() {
        Contato c = new Contato("Ismael", "Neto", "(11) 11111-1111");
        this.a.cadastraContato(4, "Ismael", "Neto", "(00) 00000-0000");
        Contato gbid = this.a.getContatoById(4);
        assertEquals(c, gbid);
    }

    @Test
    public void testGetContatoByIdAbaixoLimiteInferior() {
        try {
            this.a.getContatoById(0);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testGetContatoByIdAcimaLimiteSuperior() {
        try {
            this.a.getContatoById(101);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testRemoveContato() {
        this.a.removeContato(1);
        assertNull(this.a.getContatoById(1));
    }

    @Test
    public void testRemoveContatoAbaixoLimiteInferior() {
        try {
            this.a.removeContato(0);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testRemoveContatoaAcimaLimiteSuperior() {
        try {
            this.a.removeContato(101);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testRemoveContatoFavorito() {
        Contato c = this.a.getContatoById(1);
        this.a.adicionaFavorito(c, 1);

        this.a.removeContato(1);

        assertNull(this.a.getContatoById(1));
        assertNull(this.a.getFavoritos()[0]);
    }

    @Test
    public void testAdicionaFavorito() {
        Contato f = this.a.getContatoById(1);
        this.a.adicionaFavorito(f, 1);

        for (Contato c : this.a.getFavoritos()) {
            if (c == null) {
                continue;
            } else if (!(c.equals(f))) {
                fail();
            }
        }
    }

    @Test
    public void testAdicionaFavoritoAbaixoLimiteInferior() {
        Contato f = this.a.getContatoById(1);
        try {
            this.a.adicionaFavorito(f, 0);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testAdicionaFavoritoAcimaLimiteSuperior() {
        Contato f = this.a.getContatoById(1);
        try {
            this.a.adicionaFavorito(f, 11);
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testAdicionaFavoritoSobrescrita() {
        Contato f = this.a.getContatoById(1);
        this.a.adicionaFavorito(f, 1);

        Contato f2 = this.a.getContatoById(2);
        this.a.adicionaFavorito(f2, 1);

        for (Contato c : this.a.getFavoritos()) {
            if (c == null) {
                continue;
            } else if (!(c.equals(f2))) {
                fail();
            }
        }
    }

    @Test
    public void testContatoJahExisteTrue() {
        Contato c = new Contato("Bob", "Dylan", "(00) 00000-0000");
        assertTrue(this.a.contatoJahExiste(c));
    }

    @Test
    public void testContatoJahExisteFalse() {
        Contato c = new Contato("Contato", "Legal", "(00) 00000-0000");
        assertFalse(this.a.contatoJahExiste(c));
    }

    @Test
    public void testFavoritoJahExisteTrue() {
        Contato c = new Contato("Bob", "Dylan", "(00) 00000-0000");
        this.a.adicionaFavorito(c, 1);
        assertTrue(this.a.favoritoJahExiste(c));
    }

    @Test
    public void testFavoritoJahExisteFalse() {
        Contato c = new Contato("Contato", "Legal", "(00) 00000-0000");
        assertFalse(this.a.favoritoJahExiste(c));
    }

}
