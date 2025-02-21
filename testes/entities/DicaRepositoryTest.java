package entities;

import entities.Dica;
import repositories.DicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DicaRepositoryTest {

    private DicaRepository repository;
    private Dica dicaValida;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        repository = new DicaRepository();
        usuario = new Usuario("Maria", "1234567890", "senha123", "112233");
        dicaValida = new Dica(usuario, "Monitoria");
    }

    @Test
    void testAdicionaDica() {
        int tamanho = repository.adicionaDica(dicaValida);
        assertEquals(1, tamanho);
        
        String[] listaDicas = repository.listaDicas();
        assertEquals(1, listaDicas.length);
        assertEquals(dicaValida.toString(), listaDicas[0]);
    }

    @Test
    void testAdicionaDicaNula() {
        assertThrows(NullPointerException.class, () -> repository.adicionaDica(null), "Deve lançar NullPointerException quando a dica for nula");
    }

    @Test
    void testListaDicas() {
        repository.adicionaDica(dicaValida);

        String[] lista = repository.listaDicas();
        assertNotNull(lista, "A lista de dicas não pode ser nula");
        assertEquals(1, lista.length, "Deve haver 1 dica na lista");
        assertEquals(dicaValida.toString(), lista[0]);
    }

    @Test
    void testListaDicasDetalhes() {
        repository.adicionaDica(dicaValida);

        String[] listaDetalhes = repository.listaDicasDetalhes();
        assertNotNull(listaDetalhes, "A lista de detalhes não pode ser nula");
        assertEquals(1, listaDetalhes.length, "Deve haver 1 dica na lista de detalhes");
        assertEquals(dicaValida.exibeDetalhes(), listaDetalhes[0]);
    }

    @Test
    void testListaDicaPosicaoValida() {
        repository.adicionaDica(dicaValida);

        String dica = repository.listaDica(1);
        assertEquals(dicaValida.toString(), dica, "A dica na posição 1 deve ser a dica válida");
    }

    @Test
    void testListaDicaPosicaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> repository.listaDica(99), "Deve lançar IllegalArgumentException para posição inválida");
    }

    @Test
    void testListaDicaDetalhesPosicaoValida() {
        repository.adicionaDica(dicaValida);

        String detalhes = repository.listaDicaDetalhes(1);
        assertEquals(dicaValida.exibeDetalhes(), detalhes, "Os detalhes da dica na posição 1 devem ser os detalhes válidos");
    }

    @Test
    void testListaDicaDetalhesPosicaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> repository.listaDicaDetalhes(99), "Deve lançar IllegalArgumentException para posição inválida");
    }

    @Test
    void testBuscaDicaPosicaoValida() {
        repository.adicionaDica(dicaValida);

        Dica dica = repository.buscaDica(1);
        assertNotNull(dica, "A dica na posição 1 não pode ser nula");
        assertEquals(dicaValida, dica, "A dica buscada na posição 1 deve ser a dica válida");
    }

    @Test
    void testBuscaDicaPosicaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> repository.buscaDica(99), "Deve lançar IllegalArgumentException para posição inválida");
    }
}
