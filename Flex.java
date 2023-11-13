
class Flex 
{
    static class Player 
{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;


    Player()
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
    Player (int id, String nome, int altura, int peso, String univerdade, int anoNascimento, String cidadeNascimento, String estadoNascimento)
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
    
    @Override
    protected Player clone() throws CloneNotSupportedException
    {
        return (Player) super.clone();
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


}
class Celula {
	public Player jogador; 
	public Celula prox;


	public Celula() {
		this(null);
	}


	public Celula(Player jogador) {
      this.jogador = jogador;
      this.prox = null;
	}
}

	private Celula primeiro;
	private Celula ultimo;


	public Flex() 
    {
		primeiro = new Celula();
		ultimo = primeiro;
	}

    public int tamanho() {
        int tamanho = 0; 
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
     }
  

	public void inserirInicio(Player jogador) {
		Celula tmp = new Celula(jogador);
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) 
        {                 
			ultimo = tmp;
		}
      tmp = null;
	}



	public void inserirFim(Player jogador) 
    {
		ultimo.prox = new Celula(jogador);
		ultimo = ultimo.prox;
	}



	public Player removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Player resp = primeiro.jogador;
        tmp.prox = null;
        tmp = null;
		return resp;
	}


	public Player removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Player resp = ultimo.jogador; 
        ultimo = i; 
        i = ultimo.prox = null;
      
		return resp;
	}


   public void inserir(Player jogador, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(jogador);
      } else if (pos == tamanho){
         inserirFim(jogador);
      } else {
		   
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = new Celula(jogador);
         tmp.prox = i.prox;
         i.prox = tmp;
         tmp = i = null;
      }
   }


	public Player remover(int pos) throws Exception {
      Player resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = i.prox;
         resp = tmp.jogador;
         i.prox = tmp.prox;
         tmp.prox = null;
         i = tmp = null;
      }

		return resp;
	
    }
    public void mostrar() 
    {
		int marcador = 0;
		for (Celula i = primeiro.prox; i != null; i = i.prox) 
        {
			i.jogador.imprimir(marcador);
            marcador++;
		}
	}
public static void main (String[] args) throws NumberFormatException, Exception
{

    Flex.Player[] jogador = new Player[3923];
    Flex fila = new Flex();
 
    Arq.openRead("/tmp/players.csv");

    String info = "";
    Arq.readLine();
    int pos = 0; 
    while (Arq.hasNext() && pos <= 3921) 
    {
        info = Arq.readLine();
        jogador[pos] = new Player(); 
        jogador[pos].ler(info); 
        pos++;
    }
    if (pos == 3921) 
    {  
        pos++;
        jogador[pos] = new Player(); 
        jogador[pos].ler(info); 
    }
    Arq.close();

    
    String id = MyIO.readLine();
    int idx = 0;
    while (!id.equals("FIM"))
    {
        for (int i = 0; i < jogador.length; i++) {
            if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(id)) {
                fila.inserir(jogador[i], idx);
                break; 
            }
        }
        id = MyIO.readLine();
        idx++;
    }
    
    int add = MyIO.readInt();
    int aux = 0;
    while (aux < add) 
    {
        String comando = MyIO.readLine();
        char primeiro = comando.charAt(0);
        char segundo = comando.charAt(1);
        if (primeiro == 'I' && segundo == 'I') 
        {
            String[] comando1 = comando.split(" ");
            String idtest = comando1[1];
            for (int i = 0; i < jogador.length; i++) 
            {
                if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(idtest)) 
                {
                    fila.inserirInicio(jogador[i]);
                }
            } 
        }
        else if (primeiro == 'I' && segundo == 'F') 
        {
            String[] comando1 = comando.split(" ");
            String idtest = comando1[1];
            for (int i = 0; i < jogador.length; i++) 
            {
                if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(idtest)) 
                {
                    fila.inserirFim(jogador[i]);
                }
            } 
        }
        else if (primeiro == 'R' && segundo == 'I') 
        {
            Player show = fila.removerInicio();
            MyIO.println("(R) "+show.nome);   
        }
        else if (primeiro == 'R' && segundo == 'F') 
        {
            Player show = fila.removerFim();
            MyIO.println("(R) "+show.nome); 
        }
        else if (primeiro == 'I' && segundo == '*')
        {
            String[] comando1 = comando.split(" ");
            String posicao = comando1[1];
            String idtest = comando1[2];
            for (int i = 0; i < jogador.length; i++) 
            {
                if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(idtest)) 
                {
                    fila.inserir(jogador[i], Integer.parseInt(posicao));
                }
            } 
        }
        else if (primeiro == 'R' && segundo == '*') 
        {
            String[] comando1 = comando.split(" ");
            String posicao = comando1[1];
            Integer tax = Integer.parseInt(posicao);
            Player show = fila.remover(tax);
            MyIO.println("(R) "+show.nome); 
        }
        aux++;
    }

    fila.mostrar();
    
}
}


