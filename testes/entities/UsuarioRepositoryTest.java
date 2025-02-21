package entities;

import entities.Usuario;
import repositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepository();
        Usuario usuario = new Usuario("Ana", "26348543", "senha456", "123456");
    }

    @Test
    void testAdicionaEstudanteComSucesso() {

        Usuario estudante = new Usuario("Joao", "1234567890", "senha123", "112233");
        
        boolean resultado = usuarioRepository.adicionaEstudante("1234567890", estudante);
        
        assertTrue(resultado);
    }

    @Test
    void testAdicionaEstudanteComCpfExistente() {

        Usuario estudante1 = new Usuario("João", "1234567890", "senha123", "112233");
        usuarioRepository.adicionaEstudante("1234567890", estudante1);
        Usuario estudante2 = new Usuario("Camila", "1234567890", "senha123", "445566");

        boolean resultado = usuarioRepository.adicionaEstudante("1234567890", estudante2);
        
        assertFalse(resultado); // Já existe um estudante com o mesmo CPF
    }

    @Test
    void testListaEstudantes() {

        Usuario estudante1 = new Usuario("Maria", "1234567890", "senha567", "112233");
        Usuario estudante2 = new Usuario("Camila", "987654321", "senha123", "445566");
        usuarioRepository.adicionaEstudante("123456789", estudante1);
        usuarioRepository.adicionaEstudante("987654321", estudante2);
        

        String[] estudantes = usuarioRepository.listaEstudantes();
        

        assertEquals(2, estudantes.length);
        assertTrue(estudantes[0].contains("Camila"));
        assertTrue(estudantes[1].contains("Maria")); //Os estudantes são listados em ordem alfabética
    }

    @Test
    void testListaEstudantesRankingDicas() {

        Usuario estudante1 = new Usuario("Maria", "123456789", "senha123", "112233");
        Usuario estudante2 = new Usuario("João", "987654321", "senha123", "445566");
        usuarioRepository.adicionaEstudante("123456789", estudante1);
        usuarioRepository.adicionaEstudante("987654321", estudante2);
        
   
        String[] estudantesRanking = usuarioRepository.listaEstudantesRankingDicas();

        assertEquals(2, estudantesRanking.length);
        assertTrue(estudantesRanking[0].contains("Maria"));
        assertTrue(estudantesRanking[1].contains("João"));
    }

    @Test
    void testBuscaEstudante() {

        Usuario estudante = new Usuario("Maria", "123456789", "senha123", "112233");
        usuarioRepository.adicionaEstudante("123456789", estudante);
        
        Usuario encontrado = usuarioRepository.buscaEstudante("123456789", "senha123");
        
        assertNotNull(encontrado);
        assertEquals("Maria", encontrado.getNome());
    }

    @Test
    void testBuscaEstudanteComSenhaInvalida() {
  
        Usuario estudante = new Usuario("Camila", "987654321", "senha123", "445566");
        usuarioRepository.adicionaEstudante("987654321", estudante);

        assertThrows(IllegalArgumentException.class, () -> 
            usuarioRepository.buscaEstudante("987654321", "senhaInvalida"));
    }

    @Test
    void testBuscaEstudanteComCpfNaoExistente() {
      
        assertThrows(NullPointerException.class, () -> 
        usuarioRepository.buscaEstudante("00000000000", "senha123"));
    }
}

