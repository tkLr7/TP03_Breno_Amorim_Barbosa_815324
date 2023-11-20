
public class PilhaFlex {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    PilhaFlex()
    {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.anoNascimento = -1;
        this.nome = null;
        this.universidade = null;
        this.cidadeNascimento = null;
        this.estadoNascimento = null;
    }
    PilhaFlex(int id, String nome, int altura, int peso, String univerdade, int anoNascimento, String cidadeNascimento, String estadoNascimento)
    {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = univerdade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getId() 
    {
        return id;
    }
    
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    
    public String getNome() 
    {
        return nome;
    }
    
    public void setAltura(int altura) 
    {
        this.altura = altura;
    }
    
    public int getAltura() 
    {
        return altura;
    }
    
    public void setPeso(int peso)
     {
        this.peso = peso;
    }
    
    public int getPeso() 
    {
        return peso;
    }
    
    public void setUniversidade(String universidade) 
    {
        this.universidade = universidade;
    }
    
    public String getUniversidade() 
    {
        return universidade;
    }
    
    public void setAno(int ano) 
    {
        this.anoNascimento = ano;
    }
    
    public int getAno() 
    {
        return anoNascimento;
    }

    public void setCidade(String cidade) 
    {
        this.cidadeNascimento = cidade;
    }

    public String getCidade() 
    {
        return cidadeNascimento;
    }

    public void setEstado(String estado) 
    {
        this.estadoNascimento = estado;
    }

    public String getEstado() 
    {
        return estadoNascimento;
    }

    public void ler(String linha){              
        int indexVirgulas[] = new int[7];
        int contVirgulas = 0;
        for(int i=0; i<linha.length(); i++)
        {
            if(linha.charAt(i)==','){
                indexVirgulas[contVirgulas]=i;
                contVirgulas++;
            }
        }
            id = Integer.parseInt(linha.substring(0, indexVirgulas[0]));
            nome = linha.substring(indexVirgulas[0]+1, indexVirgulas[1]);
            altura = Integer.parseInt(linha.substring(indexVirgulas[1]+1, indexVirgulas[2]));
            peso = Integer.parseInt(linha.substring(indexVirgulas[2]+1, indexVirgulas[3]));
        
        if((indexVirgulas[4]-indexVirgulas[3]+1) == 2)
        {
            universidade = "nao informado";
        } else
        {
            universidade = linha.substring(indexVirgulas[3]+1, indexVirgulas[4]);
        }
        
            anoNascimento = Integer.parseInt(linha.substring(indexVirgulas[4]+1, indexVirgulas[5])); 
        
        if(indexVirgulas[6]-indexVirgulas[5]+1 == 2)
        {
           cidadeNascimento = "nao informado";
        } else
        {
            cidadeNascimento = linha.substring(indexVirgulas[5]+1, indexVirgulas[6]);
        }
        
        if(linha.length()-indexVirgulas[6]+1 == 2)
        {
            estadoNascimento = "nao informado";
        } else
        {
            estadoNascimento = linha.substring(indexVirgulas[6]+1, linha.length());
        }
    }
    public void imprimir(int i)
    {
        MyIO.print("["+i+"] ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+" ##\n");
    }

    
    static class Celula {
        public PilhaFlex jogador; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox.
    
        public Celula() {
            this(null);
        }
    
        public Celula(PilhaFlex jogador) {
          this.jogador = jogador;
          this.prox = null;
        }
        
        public int RMostrar(int mostrar)
        {
            int saida = 0;
            if (this.prox != null)
            {
                saida = this.prox.RMostrar(saida);
            }
            this.jogador.imprimir(saida);
            saida++;
            return saida;
        }
    }
    

    public static class Pilha {
        private Celula topo;
    
        public Pilha() {
            topo = null;
        }

        public void inserir(PilhaFlex jogador) {
            Celula tmp = new Celula(jogador);
            tmp.prox = topo;
            topo = tmp;
            tmp = null;
        }
    

        public PilhaFlex remover() throws Exception {
            if (topo == null) {
                throw new Exception("Erro ao remover!");
            }
            PilhaFlex resp = topo.jogador;
            Celula tmp = topo;
            topo = topo.prox;
            tmp.prox = null;
            tmp = null;
            return resp;
        }
        
        public void mostrar() {
            int x = 0;
            Celula i = topo;

            i.RMostrar(x);
        }

    
    }
public static void main (String[] args) throws Exception
{

    PilhaFlex[] jogador = new PilhaFlex[3923];
    Pilha pilha = new Pilha();
 
    Arq.openRead("/tmp/players.csv");

    String info = "";
    Arq.readLine();
    int pos = 0; 
    while (Arq.hasNext() && pos <= 3921) 
    {
        info = Arq.readLine();
        jogador[pos] = new PilhaFlex(); 
        jogador[pos].ler(info); 
        pos++;
    }
    if (pos == 3921) 
    {  
        pos++;
        jogador[pos] = new PilhaFlex(); 
        jogador[pos].ler(info); 
    }
    Arq.close();

    
    String id = MyIO.readLine();
    while (!id.equals("FIM"))
    {
        for (int i = 0; i < jogador.length; i++) 
        {
            if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(id)) 
            {
               pilha.inserir(jogador[i]);
               break;
            }
        }
        id = MyIO.readLine();
    }
    int add = MyIO.readInt();
    int aux = 0;
    while (aux < add) 
    {
        String comando = MyIO.readLine();
        char primeiro = comando.charAt(0);
        if (primeiro == 'I') 
        {
            String[] dividir = comando.split(" ");
            String idtest = dividir[1];
            for (int i = 0; i < jogador.length; i++) 
            {
                if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(idtest)) 
                {
                    pilha.inserir(jogador[i]);
                    break;
                }
            }
        }
        else if (primeiro == 'R') 
        {
            PilhaFlex show = pilha.remover();
            MyIO.println("(R) "+show.nome);
        }
        aux++;
    }
    pilha.mostrar();
}  
}
