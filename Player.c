#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 1000 // Tamanho máximo assumido para as strings de char
#define PLAY 3924

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
typedef struct Celula {
	struct Player *jogador;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(struct Player *player) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->jogador = player;
   nova->prox = NULL;
   return nova;
}


Celula* primeiro;
Celula* ultimo;

void start () {
   primeiro = novaCelula(NULL);
   ultimo = primeiro;
}

void inserirInicio(struct Player *x) {
   Celula* tmp = novaCelula(x);
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro == ultimo) {                    
      ultimo = tmp;
   }
   tmp = NULL;
}

void inserirFim(struct Player *player) {
   ultimo->prox = novaCelula(player);
   ultimo = ultimo->prox;
}

struct Player *removerInicio() {
   if (primeiro == ultimo) {
     printf("Erro ao remover!");
   }

   Celula* tmp = primeiro;
   primeiro = primeiro->prox;
   struct Player *resp = primeiro->jogador;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}

struct Player *removerFim() {
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
   } 

   // Caminhar ate a penultima celula:
   Celula* i;
   for(i = primeiro; i->prox != ultimo; i = i->prox);

   struct Player *resp = ultimo->jogador;
   ultimo = i;
   free(ultimo->prox);
   i = ultimo->prox = NULL;

   return resp;
}

int tamanho() {
   int tamanho = 0;
   Celula* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}

void inserir(struct Player *player, int pos) {

   int tam = tamanho();

   if(pos < 0 || pos > tam){
      printf("Erro ao inserir posicao (%d/ tamanho = %d) invalida!", pos, tam);
   } else if (pos == 0){
      inserirInicio(player);
   } else if (pos == tam){
      inserirFim(player);
   } else {
      // Caminhar ate a posicao anterior a insercao
      int j;
      Celula* i = primeiro;
      for(j = 0; j < pos; j++, i = i->prox);

      Celula* tmp = novaCelula(player);
      tmp->prox = i->prox;
      i->prox = tmp;
      tmp = i = NULL;
   }
}

struct Player *remover(int pos) {
   struct Player *resp;
   int tam = tamanho();

   if (primeiro == ultimo){
      printf("Erro ao remover (vazia)!");
   } else if(pos < 0 || pos >= tam){
      printf("Erro ao remover posicao (%d/ tamanho = %d) invalida!", pos, tam);
   } else if (pos == 0){
      resp = removerInicio();
   } else if (pos == tam - 1){
      resp = removerFim();
   } else {
      // Caminhar ate a posicao anterior a insercao
      Celula* i = primeiro;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      Celula* tmp = i->prox;
      resp = tmp->jogador;
      i->prox = tmp->prox;
      tmp->prox = NULL;
      free(tmp);
      i = tmp = NULL;
   }
   return resp;
}

void mostrar(int posicao) {
   Celula* i;
   for (i = primeiro->prox; i != NULL; i = i->prox) {
        tela(i->jogador, posicao);
        posicao++;
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
        inserir(resultado, contagem);
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
        char segundo = comando[1];

        if (primeiro == 'I' && segundo == 'I') 
        {
            char *token = strtok(comando, " ");
            token = strtok(NULL, " ");

            char idtest[sizeof(token)];
            strcpy(idtest, token);
            int numID = atoi(idtest);
            struct Player *resultado = ler(numID);
            inserirInicio(resultado);
        }
        else if (primeiro == 'I' && segundo == 'F') 
        {
            char *token = strtok(comando, " ");
            token = strtok(NULL, " ");

            char idtest[sizeof(token)];
            strcpy(idtest, token);
            int numID = atoi(idtest);
            struct Player *resultado = ler(numID);
            inserirFim(resultado);
        }
        else if (primeiro == 'R' && segundo == 'I') 
        {
            struct Player *show = removerInicio();
            printf("(R) %s\n", show->nome); ;   
        }
        else if (primeiro == 'R' && segundo == 'F') 
        {
            struct Player *show = removerFim();
            printf("(R) %s\n", show->nome); 
        }
        else if (primeiro == 'I' && segundo == '*')
        {
            char *token = strtok(comando, " ");
       
            token = strtok(NULL, " ");
            char posicao[sizeof(token)];
            strcpy(posicao, token);
            int pos = atoi(posicao);
       
            token = strtok(NULL, " ");
            char idtest[sizeof(token)];
            strcpy(idtest, token);
            int numID = atoi(idtest);

            struct Player *teste = ler(numID);
            inserir(teste, pos);
        }
        else if (primeiro == 'R' && segundo == '*') 
        {
            char *token = strtok(comando, " ");
       
            token = strtok(NULL, " ");
            char posicao[sizeof(token)];
            strcpy(posicao, token);
            int pos = atoi(posicao);

            struct Player *show = remover(pos);
            printf("(R) %s\n", show->nome);
            free(show); 
        }
        aux++;
    }
    
    mostrar(0);
    return 0;
}
