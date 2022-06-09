public class Produto implements Comparable<Produto>{
    private int COD;
    private String nome;
    private double valor;
    private int Quantidade;
    
    public Produto(int COD, String nome, double valor, int Quantidade) {
        this.COD = COD;
        this.nome = nome;
        this.valor = valor;
        this.Quantidade = Quantidade;
    }

    public Produto(int codigo) {
        this.COD = codigo;
    }


    public int getCOD() {
        return COD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public int getEstoque() {
        return Quantidade;
    }


    @Override
    public int compareTo(Produto o) {
        if (this.COD > o.getCOD()){
            return 1;
        } else if (this.COD < o.getCOD()) {
            return -1;
        }
        return 0;
    }

    @Override
	public String toString() {
		return "\nNome do produto: " + this.nome + "\nCódigo do produto: " + this.COD + "\nValor do Produto: " + this.valor
				+ "\nQuantidade de produtos estocados: " + this.Quantidade;
	}
}