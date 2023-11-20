#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 1000 // Tamanho máximo assumido para as strings de char
#define PLAY 3924
#define MAXTAM 6

typedef struct Player {
    int id;
    char nome[MAX];
    int altura;
    int peso;
    int anoNascimento;
    char universidade[MAX];
    char cidadeNascimento[MAX];
    char estadoNascimento[MAX];
} Player;

struct Player *jogadores[PLAY];

Player *remover();

//SETS e GETS
void setId(struct Player *player, int newid) {
    player->id = newid;
}

int getId(struct Player *player) {
    return player->id;
}

void setNome(struct Player *player, const char *newnome) {
    strcpy(player->nome, newnome);
}

const char *getNome(struct Player *player) {
    return player->nome;
}

void setAltura(struct Player *player, int newaltura) {
    player->altura = newaltura;
}

int getAltura(struct Player *player) {
    return player->altura;
}

void setPeso(struct Player *player, int newpeso) {
    player->peso = newpeso;
}

int getPeso(struct Player *player) {
    return player->peso;
}

void setAno(struct Player *player, int newano) {
    player->anoNascimento = newano;
}

int getAno(struct Player *player) {
    return player->anoNascimento;
}

void setUniversidade(struct Player *player, const char *newuniversidade) {
    if (strcmp(newuniversidade, "") != 0) {
        strcpy(player->universidade, newuniversidade);
    } else {
        strcpy(player->universidade, "nao informado");
    }
}

const char *getUniversidade(struct Player *player) {
    return player->universidade;
}

void setCidade(struct Player *player, const char *newcidade) {
    if (strcmp(newcidade, "") != 0) {
        strcpy(player->cidadeNascimento, newcidade);
    } else {
        strcpy(player->cidadeNascimento, "nao informado");
    }
}

const char *getCidade(struct Player *player) {
    return player->cidadeNascimento;
}

void setEstado(struct Player *player, const char *newestado) {
    if (strcmp(newestado, "") != 0) {
        strcpy(player->estadoNascimento, newestado);
    } else {
        strcpy(player->estadoNascimento, "nao informado");
    }
}

const char *getEstado(struct Player *player) {
    return player->estadoNascimento;
}

void clone(struct Player *player, struct Player *newplayer) {
    player->id = newplayer->id;
    strcpy(player->nome, newplayer->nome);
    player->altura = newplayer->altura;
    player->peso = newplayer->peso;
    player->anoNascimento = newplayer->anoNascimento;
    strcpy(player->universidade, newplayer->universidade);
    strcpy(player->cidadeNascimento, newplayer->cidadeNascimento);
    strcpy(player->estadoNascimento, newplayer->estadoNascimento);
}

void tela(struct Player *player, int i) {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           i, player->nome, player->altura, player->peso, player->anoNascimento, 
           player->universidade, player->cidadeNascimento, player->estadoNascimento);
}

struct Player *ler(int idDesejado) {
    FILE *arquivo = fopen("/tmp/players.csv", "r");
    if (arquivo == NULL) {
        printf("Não foi possível abrir o arquivo.\n");
        return NULL; // Retorna código de erro
    }
    char linha[MAX];
    struct Player *jogadorEncontrado = malloc(sizeof(struct Player));
    int jogadorEncontradoFlag = 0;

    // Loop para ler o arquivo linha por linha
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        struct Player jogador;
        int camposLidos = sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]",
           &jogador.id, jogador.nome, &jogador.altura, &jogador.peso,
           jogador.universidade, &jogador.anoNascimento,
           jogador.cidadeNascimento, jogador.estadoNascimento);

        if (camposLidos < 8)
        {
        if (camposLidos < 1) {
            jogador.id = -1; // Valor inválido para ID
        }
        if (camposLidos < 2) {
            strcpy(jogador.nome, "nao informado");
        }
        if (camposLidos < 3) {
            jogador.altura = -1; // Valor inválido para altura
        }
        if (camposLidos < 4) {
            jogador.peso = -1; // Valor inválido para peso
        }
        if (camposLidos < 5) {
            strcpy(jogador.universidade, "nao informado"); 
        }
        if (camposLidos < 6) {
            jogador.anoNascimento = -1; // Valor inválido para ano de nascimento
        }
        if (camposLidos < 7) {
            strcpy(jogador.cidadeNascimento, "nao informado");
        }
        if (camposLidos < 8) {
            strcpy(jogador.estadoNascimento, "nao informado");
        }
        }
        if (jogador.id == idDesejado) {
            clone(jogadorEncontrado, &jogador);
            jogadorEncontradoFlag = 1;
            break; // Encerra a busca após encontrar o jogador
        }
    }

    fclose(arquivo);
    return jogadorEncontrado; // Retorna sucesso
}
Player *array[MAXTAM+1];   // Elementos da pilha 
int primeiro;          // Remove do indice "primeiro".
int ultimo;             // Insere no indice "ultimo".


void start(){
   primeiro = ultimo = 0;
}


void inserir(Player *x) {

    //validar insercao
    if (((ultimo + 1) % MAXTAM) == primeiro) {
        remover();
    }

    array[ultimo] = x;
    ultimo = (ultimo + 1) % MAXTAM;
}


Player *remover() {
    if (primeiro == ultimo) {
        printf("Erro ao remover!");
        exit(1);
    }
    Player *resp = array[primeiro];
    primeiro = (primeiro + 1) % MAXTAM;
    return resp;
}


void mostrar (){
    int i;
    int contagem = 0;
    int alturas = 0;
    double media = 0;
    for(i = primeiro; i != ultimo; i = ((i + 1) % MAXTAM)) {
        alturas = alturas + array[i]->altura;
        contagem++;
    }
    media = (double)alturas / contagem;
    if ((media - (int) media) >= 0.5)
    {
        media = media + 1;
    }   
    
    printf("%d\n", (int)media);
}

bool isVazia() {
   return (primeiro == ultimo); 
}
void mostrarFila (){
    int i;
    int idx = 0;
    for(i = primeiro; i != ultimo; i = ((i + 1) % MAXTAM)) {
        tela(array[i], idx);
        idx++;
    }
}

int main() {
    char idDesejado[MAX] = ""; //ID que você deseja encontrar
    int contagem = 0;
    start();
    while (true) 
    {
        scanf("%s", idDesejado);
        if (strcmp(idDesejado, "FIM") == 0) {
            break; // Encerra o programa se 'FIM' for digitado
        }
        int numID = atoi(idDesejado);
        struct Player *resultado = ler(numID);
        inserir(resultado);
        mostrar();
        contagem++;
    }
    int add;
    int aux = 0;

    scanf("%d", &add);

    while (aux < add)
    {
        char comando[50];
        scanf(" %[^\n]", comando);

        char primeiro = comando[0];

        if (primeiro == 'I') 
        {
            char *token = strtok(comando, " ");
            token = strtok(NULL, " ");

            char idtest[sizeof(token)];
            strcpy(idtest, token);
            int numID = atoi(idtest);
            struct Player *resultado = ler(numID);
            inserir(resultado);
            mostrar();
        }
        else if (primeiro == 'R') 
        {
            Player *show = remover();
            printf("(R) %s\n", show->nome);
        }
        aux++;
    }
    
    mostrarFila();
    return 0;
}
