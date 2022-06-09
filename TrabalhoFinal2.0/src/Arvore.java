import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Arvore<X extends Comparable<X>> {
    No<X> raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void Deletar(X dado) { 
        raiz = Deletamento(raiz, dado); 
    } 
   
    private No<X> Deletamento(No<X> raiz, X dado)  { 
    	
        if (raiz == null) {
            return raiz;
        } 
        else if (dado.compareTo(raiz.dado) < 0){
        	
        	
            raiz.Esquerda = Deletamento(raiz.Esquerda, dado);

        }
        
        else if (dado.compareTo(raiz.dado) > 0)
        {
        	
            raiz.Direita = Deletamento(raiz.Direita, dado); 
        }
        
        else  { 
            if (raiz.Esquerda == null) {
            	
                return raiz.Direita; 
            }
            else if (raiz.Direita == null) {
            	
                return raiz.Esquerda; 
            }

          
            raiz.dado = ValorMin(raiz.Direita); 
            
            raiz.Direita = Deletamento(raiz.Direita, raiz.dado); 
        }
        
        AlturaNova(raiz);
        
        
        return Rebalanceamento(raiz); 
    } 

    private X ValorMin(No<X> raiz){
    	
        X Min = raiz.dado;

        while(raiz.Esquerda != null){
        	
        	Min = raiz.Esquerda.dado;
        	
            raiz = raiz.Esquerda; 
        } 
        return Min; 
    }

    public void inserir(X dado)  { 
        raiz = inserirRecursivo(raiz, dado); 
    }

    private No<X> inserirRecursivo(No<X> raiz, X dado) { 
        if (raiz == null) { 
            raiz = new No<X>(dado);  
        } 
        else if (dado.compareTo(raiz.dado) < 0){
            raiz.Esquerda = inserirRecursivo(raiz.Esquerda, dado);
        }
        else if (dado.compareTo(raiz.dado) > 0) {
            raiz.Direita = inserirRecursivo(raiz.Direita, dado); 
        }  

        AlturaNova(raiz);
        return Rebalanceamento(raiz);
    }

  
    public Optional<X> Pesquisar(X dado)  { 
    	
        Optional<No<X>> talvezNo = Optional.ofNullable(Pesquisa(raiz, dado));
        
        return talvezNo.map( (No<X> t) -> t.dado);
    } 

    private No<X> Pesquisa(No<X> raiz, X dado)  { 
        if (raiz==null || raiz.dado.compareTo(dado) == 0) {
            return raiz; 
        }   
        if (raiz.dado.compareTo(dado) > 0){
            return Pesquisa(raiz.Esquerda, dado); 
        }
        return Pesquisa(raiz.Direita, dado); 
    }

    private int Altura(No<X> no) {
        return no != null ? no.Altura : -1;
    }
    
    private void AlturaNova(No<X> no) {
    	
        int alturaEsquerda = Altura(no.Esquerda);
        
        int alturaDireita = Altura(no.Direita);
        
        no.Altura = Math.max(alturaEsquerda, alturaDireita) + 1;
    }
    
    private int Balanceamento(No<X> no) {
        return Altura(no.Direita) - Altura(no.Esquerda);
    }

    private No<X> TransicaoDir(No<X> no) {
        No<X> noEsquerda = no.Esquerda;
        
        no.Esquerda = noEsquerda.Direita;
        
        noEsquerda.Direita = no;
        
        AlturaNova(no);
        
        AlturaNova(noEsquerda);
        
        return noEsquerda;
    }

    private No<X> TransicaoEsq(No<X> no) {
        No<X> noDireita = no.Direita;
        
        no.Direita = noDireita.Esquerda;
        noDireita.Esquerda = no;
        
        AlturaNova(no);
        
        AlturaNova(noDireita);
        
        return noDireita;
    }

    private No<X> Rebalanceamento(No<X> no) {
        int Balanceamento = Balanceamento(no);
      
     
        if (Balanceamento < -1) {
        	
          if (Balanceamento(no.Esquerda) <= 0) {
      
            no = TransicaoDir(no);
          } else {

            no.Esquerda = TransicaoDir(no.Esquerda);
            no = TransicaoEsq(no);
          }
        }
      
        if (Balanceamento > 1) {
          if (Balanceamento(no.Direita) >= 0) {
     
            no = TransicaoEsq(no);
          } else {
     
            no.Direita = TransicaoDir(no.Direita);
            no = TransicaoEsq(no);
          }
        }
        return no;
    }
    

    
    
    
    
    public List<X> emOrdem(List<X> listaEmOrdem) { 
        List<No<X>> listaNo = listaEmOrdem.stream().map((t) -> new No<X>(t)).collect(Collectors.toList());
        return emOrdemRecursivo(raiz, listaNo)
        .stream().map((t) -> t.dado).collect(Collectors.toList());
    } 

    public List<No<X>> emOrdemNo(List<No<X>> listaEmOrdem) { 
        return emOrdemRecursivo(raiz, listaEmOrdem);
    } 
   
    private List<No<X>> emOrdemRecursivo(No<X> raiz, List<No<X>> listaEmOrdem) {
        if (raiz != null) { 
            emOrdemRecursivo(raiz.Esquerda, listaEmOrdem); 
            listaEmOrdem.add(raiz);
            emOrdemRecursivo(raiz.Direita, listaEmOrdem);
        }
        return listaEmOrdem;
    }

}