public class No<X> {
	
    X dado;
    
    No<X> Direita, Esquerda;
    
    int Altura;
    

    public No(X dado){ 
    	
        this.dado = dado; 
        
        this.Esquerda = this.Direita = null;
    }
}