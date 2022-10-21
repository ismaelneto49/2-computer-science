import static org.junit.jupiter.api.Assertions.*;

import agenda.Contato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContatoTest {

    private Contato c;

    @BeforeEach
    public void initContato() {
        c = new Contato("Ismael", "Neto", "(00) 00000-0000");
    }

    @Test
    public void testContato() {
        new Contato("Ismael", "Neto", "(00) 00000-0000");
    }

    @Test
    public void testGetNome() {
        assertEquals(this.c.getNome(), "Ismael");
    }

    @Test
    public void testGetSobrenome() {
        assertEquals(this.c.getSobrenome(), "Neto");
    }

    @Test
    public void testIsFavoritoTrue() {
        this.c.setFavorito(true);
        assertTrue(this.c.isFavorito());
    }

    @Test
    public void testIsFavoritoFalse() {
        assertFalse(this.c.isFavorito());
    }

    @Test
    public void testSetFavorito() {
        Contato c2 = new Contato("Ralâmpago", "Marquinhos", "(00) 00000-0000");
        assertFalse(c2.isFavorito());
        c2.setFavorito(true);
        assertTrue(c2.isFavorito());
    }

    @Test
    public void testAdicionaTag() {
        this.c.adicionaTag(1, "legal");
        assertTrue(this.c.toString().contains("legal"));
    }

    @Test
    public void testAdicionaTagAbaixoLimiteInferior() {
        try {
            this.c.adicionaTag(0, "legal");
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testAdicionaTagAcimaLimiteSuperior() {
        try {
            this.c.adicionaTag(6, "legal");
            fail();
        } catch (IndexOutOfBoundsException iob) {
        }
    }

    @Test
    public void testAdicionaTagSobrescrita() {
        this.c.adicionaTag(2, "legal");
        assertTrue(this.c.toString().contains("legal"));

        this.c.adicionaTag(2, "daora");
        assertTrue(this.c.toString().contains("daora"));
        assertFalse(this.c.toString().contains("legal"));
    }

    @Test
    public void testToString() {
        String toString = this.c.toString();
        String esperado = "Ismael Neto\n" + "(00) 00000-0000";
        assertEquals(toString, esperado);
    }

    @Test
    public void testToStringFavorito() {
        this.c.setFavorito(true);
        String toString = this.c.toString();
        String esperado = "❤️Ismael Neto\n" + "(00) 00000-0000";
        assertEquals(toString, esperado);
    }

    @Test
    public void testToStringComTags() {
        this.c.adicionaTag(1, "legal");
        String toString = this.c.toString();
        String esperado = "Ismael Neto\n" + "(00) 00000-0000\n" + "legal";
        assertEquals(toString, esperado);
    }

    @Test
    public void testEqualsTrue() {
        Contato c2 = new Contato("Ismael", "Neto", "(11) 11111-1111");
        assertTrue(this.c.equals(c2));
    }

    @Test
    public void testEqualsFalse() {
        Contato c2 = new Contato("Esmael", "Neto", "(11) 11111-1111");
        assertFalse(this.c.equals(c2));
    }

    @Test
    public void testEqualsMesmoObjeto() {
        assertTrue(this.c.equals(this.c));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(this.c.equals(null));
    }

    @Test
    public void testEqualsTipo() {
        Object o = new Object();
        assertFalse(this.c.equals(o));
    }

}
