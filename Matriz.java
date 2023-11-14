public class Matriz 
{
    class Celula {
        public int elemento;
        public Celula inf, sup, esq, dir;
     
        public Celula(){
           this(0);
        }
     
        public Celula(int elemento){
           this(elemento, null, null, null, null);
        }
     
        public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
           this.elemento = elemento;
           this.inf = inf;
           this.sup = sup;
           this.esq = esq;
           this.dir = dir;
        }
    }
    private Celula inicio;
    private int linha, coluna;
 
    public Matriz (){
       this(0, 0);
    }
 
    public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
    }
 
 
    public Matriz soma (Matriz m) {
       Matriz resp = null;
 
       if(this.linha == m.linha && this.coluna == m.coluna){
          resp = new Matriz(this.linha, this.coluna);
          for(){
             for(){
               
               c.elemento = a.elemento + b.elemento;
             }
          }
          //...
       }
 
       return resp;
    }
 
    public Matriz multiplicacao (Matriz m) {
       Matriz resp = null;
 
       if(){
          //...
       }
 
       return resp;
    }
 
    public boolean isQuadrada(){
       boolean (this.linha == this.coluna);
    }
 
    public void mostrarDiagonalPrincipal (){
       if(isQuadrada() == true){
 
       }
    }
 
    public void mostrarDiagonalSecundaria (){
       if(isQuadrada() == true){
       }
    }
}
