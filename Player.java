import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Player 
{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    static List<Player> players = new ArrayList<>();

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

    public static void inserirInicio(Player jogador)
    {
        players.add(0, jogador);
    }

    public static void inserirFim(Player jogador)
    {
        players.add(jogador);
    }

    public static void inserir(Player jogador, int poisicao)
    {
        players.add(poisicao, jogador);
    }

    public static Player remover(int poisicao)
    {
        Player retorno = players.get(poisicao);
        players.remove(poisicao);
        return retorno;
    }

    public static Player removerInicio()
    {
        Player retorno = players.get(0);
        players.remove(0);
        return retorno;
    }
    
    public static Player removerFim()
    {
        Player retorno = players.get(players.size() - 1);
        players.remove(players.size() - 1);
        return retorno;
    }   
    
public static void main (String[] args) throws IOException
{

    Player[] jogador = new Player[3923];
 
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
    while (!id.equals("FIM"))
    {
        for (int i = 0; i < jogador.length; i++) 
        {
            if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(id)) 
            {
                players.add(jogador[i]);
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
        char segundo = comando.charAt(1);
        if (primeiro == 'I' && segundo == 'I') 
        {
            String[] comando1 = comando.split(" ");
            String idtest = comando1[1];
            for (int i = 0; i < jogador.length; i++) 
            {
                if (jogador[i] != null && jogador[i].getId() == Integer.parseInt(idtest)) 
                {
                    inserirInicio(jogador[i]);
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
                    inserirFim(jogador[i]);
                }
            } 
        }
        else if (primeiro == 'R' && segundo == 'I') 
        {
            Player show = removerInicio();
            MyIO.println("(R) "+show.nome);   
        }
        else if (primeiro == 'R' && segundo == 'F') 
        {
            Player show = removerFim();
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
                    inserir(jogador[i], Integer.parseInt(posicao));
                }
            } 
        }
        else if (primeiro == 'R' && segundo == '*') 
        {
            String[] comando1 = comando.split(" ");
            String posicao = comando1[1];
            Integer tax = Integer.parseInt(posicao);
            Player show = remover(tax);
            MyIO.println("(R) "+show.nome); 
        }
        aux++;
    }
    int marcador = 0;
    for(Player player : players)
    {
        player.imprimir(marcador);
        marcador++;
    }
}
}

