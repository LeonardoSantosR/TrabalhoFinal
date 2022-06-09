import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) {
        int opcao;
		
		do {
			opcao = Integer.parseInt(showInputDialog(Util.Menu()));
			if (opcao < 1 || opcao > 5) {
				showMessageDialog(null, "Opção inválida");			
				} else {
					switch (opcao) {
					case 1: 
						Util.Cadastro();
					break;
					case 2: 
						Util.Pesquisar();
					break;
					case 3: 
						Util.Produtos();
					break;
					case 4: 
						Util.Excluir();
					break;
					case 5: 
						Util.EncerrarSessao();
                    break;
					}
				}
		} while(opcao != 6);
    }
    
	
	
}