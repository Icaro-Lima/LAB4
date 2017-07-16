package lab4;

/**
 * Uma classe que representa a fachada do sistema, que apenas delega os métodos.
 * 
 * @author Ícaro Dantas
 *
 */
public class Fachada {

    Sistema sistema;

    /**
     * Inicializa o sistema, que tem um caixa e uma taxa.
     * 
     * @param caixa
     *            O caixa atual do sistema.
     * @param taxa
     *            A taxa que vai ser usada para taxar as apostas.
     */
    public void inicializa(int caixa, double taxa) {
	sistema = new Sistema(caixa, taxa);
    }

    /**
     * {@link Sistema#cadastrarCenario(String)}
     */
    public int cadastrarCenario(String descricao) {
	return this.sistema.cadastrarCenario(descricao);
    }

    /**
     * {@link Sistema#cadastrarCenario(String, int)}
     */
    public int cadastrarCenario(String descricao, int bonus) {
	return this.sistema.cadastrarCenario(descricao, bonus);
    }

    /**
     * {@link Sistema#exibirCenario(int)}
     */
    public String exibirCenario(int cenarioID) {
	return this.sistema.exibirCenario(cenarioID);
    }

    /**
     * {@link Sistema#exibirCenarios()}
     */
    public String exibirCenarios() {
	return this.sistema.exibirCenarios();
    }

    /**
     * {@link Sistema#cadastrarAposta(int, String, int, String)}
     */
    public void cadastrarAposta(int cenarioID, String apostador, int valor, String previsao) {
	this.sistema.cadastrarAposta(cenarioID, apostador, valor, previsao);
    }

    public int cadastrarApostaSeguraValor(int cenarioID, String apostador, int valor, String previsao, int valorSeguro,
	    int custoSeguro) {
	return this.sistema.cadastrarApostaSeguraValor(cenarioID, apostador, valor, previsao, valorSeguro, custoSeguro);
    }
    
    public int cadastrarApostaSeguraTaxa(int cenarioID, String apostador, int valor, String previsao, double taxaSeguro, int custoSeguro) {
	return this.sistema.cadastrarApostaSeguraTaxa(cenarioID, apostador, valor, previsao, taxaSeguro, custoSeguro);
    }
    
    public void alterarSeguroValor(int cenarioID, int apostaAssegurada, int seguroValor) {
	this.sistema.alterarSeguroValor(cenarioID, apostaAssegurada, seguroValor);
    }
    
    public void alterarSeguroTaxa(int cenarioID, int apostaAssegurada, double seguroTaxa) {
	this.sistema.alterarSeguroTaxa(cenarioID, apostaAssegurada, seguroTaxa);
    }

    /**
     * {@link Sistema#valorTotalDeApostas(int)}
     */
    public int valorTotalDeApostas(int cenarioID) {
	return this.sistema.valorTotalDeApostas(cenarioID);
    }

    /**
     * {@link Sistema#totalDeApostas(int)}
     */
    public int totalDeApostas(int cenarioID) {
	return this.sistema.totalDeApostas(cenarioID);
    }

    /**
     * {@link Sistema#exibeApostas(int)}
     */
    public String exibeApostas(int cenarioID) {
	return this.sistema.exibeApostas(cenarioID);
    }

    /**
     * {@link Sistema#fecharAposta(int, boolean)}
     */
    public void fecharAposta(int cenarioID, boolean ocorreu) {
	this.sistema.fecharAposta(cenarioID, ocorreu);
    }

    /**
     * {@link Sistema#getCaixaCenario(int)}
     */
    public int getCaixaCenario(int cenarioID) {
	return this.sistema.getCaixaCenario(cenarioID);
    }

    /**
     * {@link Sistema#getTotalRateioCenario(int)}
     */
    public int getTotalRateioCenario(int cenarioID) {
	return this.sistema.getTotalRateioCenario(cenarioID);
    }

    /**
     * {@link Sistema#getCaixa()}
     */
    public int getCaixa() {
	return this.sistema.getCaixa();
    }

}
