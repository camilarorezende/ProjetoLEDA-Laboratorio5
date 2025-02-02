package repositories;

import entities.Dica;
import java.util.ArrayDeque;
import java.util.Deque;
import validators.ValidadorDica;

/**
 * Classe responsável por gerenciar e armazenar as dicas em um repositório.
 * Esta classe permite adicionar e listar dicas.
 */

public class DicaRepository {

	private Deque<Dica> dicas;
	
	/**
     * Cria um novo repositório de dicas.
     */
	public DicaRepository() {
		this.dicas = new ArrayDeque<>();
	}
	
	/**
     * Adiciona uma dica ao repositório.
     *
     * @param dica a dica a ser adicionada
     * @return o número total de dicas no repositório após a adição ou 0 se a dica não foi adicionada
     * @throws NullPointerException se a dica for nula
     */
	public int adicionaDica(Dica dica) {
		ValidadorDica.validaDica(dica);
		return (this.dicas.add(dica)) ? this.dicas.size() : 0;
	}
	
	/**
     * Retorna uma lista das dicas como strings.
     *
     * @return um array de strings representando as dicas
     */
	public String[] listaDicas() {
		if (this.dicas.size() == 0) {
			return new String[0];
		}
		String[] lista = new String[this.dicas.size()];
		int i = 0;
		for (Dica dica : dicas) {
			lista[i++] = dica.toString();
		}
		return lista;
	}
	
	/**
     * Retorna uma lista detalhada das dicas.
     *
     * @return um array de strings com os detalhes das dicas
     */
	public String[] listaDicasDetalhes() {
		if (this.dicas.size() == 0) {
			return new String[0];
		}
		String[] lista = new String[this.dicas.size()];
		int i = 0;
		for (Dica dica : dicas) {
			lista[i++] = dica.exibeDetalhes();
		}
		return lista;
	}
	
	/**
	 * Retorna a representação em string da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return a representação em string da dica
	 * @throws IllegalArgumentException se posicao for inválida
	 */
	public String listaDica(int posicao) {
		return buscaDica(posicao).toString();
	}
	
	/**
     * Retorna os detalhes da dica na posição especificada.
     *
     * @param posicao a posição da dica a ser retornada (1-based)
     * @return os detalhes da dica
     * @throws IllegalArgumentException se posicao for inválida
     */
	public String listaDicaDetalhes(int posicao) {
		return buscaDica(posicao).exibeDetalhes();
	}
	
	/**
     * Busca e retorna a dica na posição especificada.
     *
     * @param posicao a posição da dica a ser buscada (1-based)
     * @return a dica na posição especificada
     * @throws IllegalArgumentException se posicao for inválida
     */
	public Dica buscaDica(int posicao) {
		ValidadorDica.validaPosicao(posicao, this.dicas.size());
		return (Dica) dicas.toArray()[posicao - 1];  // Utilizando toArray() para acessar a dica na posição
    }
}
