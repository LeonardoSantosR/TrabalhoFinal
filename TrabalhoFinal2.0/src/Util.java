import static javax.swing.JOptionPane.showInputDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Util {
    private static Arvore<Produto> Estoque = new Arvore<Produto>();
	

	public static void Cadastro() {
		
		int COD;
		
		int Quantidade;
		
		String nome;
		
        double valor;

		COD = Integer.parseInt(showInputDialog("Codigo do Produto:"));
		
		nome = showInputDialog("Nome do Produto:");
		
		valor = Double.parseDouble(showInputDialog("Valor do Produto:"));
        
		Quantidade = Integer.parseInt(showInputDialog("Quantidade Em Estoque:"));

		Produto produto = new Produto(COD, nome, valor, Quantidade);

        if (Estoque.Pesquisar(produto).isPresent()) {
            JOptionPane.showMessageDialog(null,
                 "Ja existe um produto com esse codigo ja inserido");
        } else {
        	Estoque.inserir(produto);
            JOptionPane.showMessageDialog(null,
                 "Produto cadastrado com sucesso");
        }
	}

	public static void Produtos() {
        List<Produto> arrayProdutos = new ArrayList<Produto>();

        arrayProdutos = Estoque.emOrdem(arrayProdutos);
        String listaProdutos = arrayProdutos.stream().map(Object::toString)
                        .collect(Collectors.joining());

		JOptionPane.showMessageDialog(null, listaProdutos);
	}

	public static void Excluir() {
		
		
		Produto produto;
		
		
		produto = PesquisaCodigo();

		if(Estoque.Pesquisar(produto).isPresent()) {
			Estoque.Deletar(produto);
			JOptionPane.showMessageDialog(null, "O produto foi removido com successo, tenha um bom dia!");
		} else {
			JOptionPane.showMessageDialog(null, "Dentro do sistema, esse produto nao foi encontrado");
		}
	}

    public static void Pesquisar() {
    	
        Produto produto;
        
        produto = PesquisaCodigo();

        String prodEncontr = Estoque.Pesquisar(produto)
            .map(Produto::toString).orElse(
            "Nenhum produto encontrado"
        );
        
        JOptionPane.showMessageDialog(null, prodEncontr);
    }

    public static Produto PesquisaCodigo(){
        int codigo;
        codigo = Integer.parseInt(showInputDialog("Codigo do produto"));
		Produto produto = new Produto(codigo);
        return produto;
    }

	public static void maiorAltura(){
		List<No<Produto>> arrayProdutos = new ArrayList<No<Produto>>();
		arrayProdutos = Estoque.emOrdemNo(arrayProdutos);
		Integer max = arrayProdutos.stream()
			.mapToInt((prod) -> prod.Altura)
			.max()
			.orElse(0);
		JOptionPane.showMessageDialog(null, max);
	}

	public static void EncerrarSessao() {
		maiorAltura();
		System.exit(0);
	}


		public static String Menu(){
			String aux = "Digite numeros de 1 ate 5 : \n";
			aux += "1. Registro  \n";
			aux += "2. Pesquisa \n";
			aux += "3. Biblioteca \n";
			aux += "4. remocao \n";
			aux += "5. Saida do sistema \n";
			return aux;
	}

}