# **Documentação do Banco de Dados**

## **Visão Geral**

Este banco de dados é projetado para um sistema de rede social, onde usuários podem criar **tweets**, interagir com eles através de **likes** e **dislikes**, e deixar **comentários**. A estrutura do banco de dados contém quatro principais entidades: **Usuários** (app_users), **Tweets** (tweets), **Likes/Dislikes** (likes_or_dislikes) e **Comentários** (comments).

---

**Diagrama do Banco de Dados**

[![Diagrama ER](https://mermaid.ink/img/pako:eNqdVO1u2jAUfRXLUqVOoogQCF2k_aggSIiVIgKaNEVCXnIHVomNHIeVAg-zZ9mLzXGIYSH9Wv7E9_r4nBPfm7vDIY8AuxhEj5KFIHHAkHruxuP5zPcmPtrlieyZzQY9RCM0Hp5yvhSULRAjMVwkISZ0dcr2iAQUClCv6E6W8uk6Uq8ifQhYvph-87zpe02EnElg8m1FzZEmIAYR6g9Lkl8HQ8-fP0zmvYGv1xfqpZMmL38ByNLG0ZryEErKWUmr-3B_743-_wM_bKfqRoybU9H3-5sbvi9u30UB7gpKAvwCznyGRvJYeTTgI0cVcgrxi5SXVdDcqZBUIJ6iHiShDqp1qo9PIIQfUJx4R7lL1VMUK_oIaI8imhxXjDPDaG7y6gqNuCQJgqf1ioZE0g1JXLM3HqIvaCxoTMQWDWGLrrtLsgGd-vNb3fQnA-1n0D4XQBfsHOolUhC2ACrOwBNYkVD5JFkFuKmai2ax6pI0o-aq-pSgjV4neY8k6NpyR6_RFCX9l2mtRofqSb0nPkipa6_pND7nknCiyXmPwVv-qtqiyqvBRQXuwvbItV5RKjqo7Fz9gjl7xM_po0z4pFG4MTK4hmMQakxGagTrvguwXEKsFQIcEfGY9dZB4Ugqub9lIXalSKGGBU8XS-z-JKtERfn4PI5wk10T9p3zuDiiQuzu8BN2Lcupt9pO83PDbltW22m3aniL3RunVVfBbcdu3tpNRwEONfysGay67TRbVtu27E6z0WnY1uEv627rOA?type=png)](https://mermaid.live/edit#pako:eNqdVO1u2jAUfRXLUqVOoogQCF2k_aggSIiVIgKaNEVCXnIHVomNHIeVAg-zZ9mLzXGIYSH9Wv7E9_r4nBPfm7vDIY8AuxhEj5KFIHHAkHruxuP5zPcmPtrlieyZzQY9RCM0Hp5yvhSULRAjMVwkISZ0dcr2iAQUClCv6E6W8uk6Uq8ifQhYvph-87zpe02EnElg8m1FzZEmIAYR6g9Lkl8HQ8-fP0zmvYGv1xfqpZMmL38ByNLG0ZryEErKWUmr-3B_743-_wM_bKfqRoybU9H3-5sbvi9u30UB7gpKAvwCznyGRvJYeTTgI0cVcgrxi5SXVdDcqZBUIJ6iHiShDqp1qo9PIIQfUJx4R7lL1VMUK_oIaI8imhxXjDPDaG7y6gqNuCQJgqf1ioZE0g1JXLM3HqIvaCxoTMQWDWGLrrtLsgGd-vNb3fQnA-1n0D4XQBfsHOolUhC2ACrOwBNYkVD5JFkFuKmai2ax6pI0o-aq-pSgjV4neY8k6NpyR6_RFCX9l2mtRofqSb0nPkipa6_pND7nknCiyXmPwVv-qtqiyqvBRQXuwvbItV5RKjqo7Fz9gjl7xM_po0z4pFG4MTK4hmMQakxGagTrvguwXEKsFQIcEfGY9dZB4Ugqub9lIXalSKGGBU8XS-z-JKtERfn4PI5wk10T9p3zuDiiQuzu8BN2Lcupt9pO83PDbltW22m3aniL3RunVVfBbcdu3tpNRwEONfysGay67TRbVtu27E6z0WnY1uEv627rOA)

## **Tabelas**

### 1. **Tabela `app_users`**

Representa os usuários registrados na plataforma.

| **Campo**     | **Tipo** | **Descrição**                           | **Chave** |
|---------------|----------|-----------------------------------------|-----------|
| `id`          | UUID     | Identificador único do usuário          | PK        |
| `name`        | String   | Nome do usuário                         |           |
| `email`       | String   | E-mail do usuário                       |           |
| `createdAt`   | Date     | Data de criação do usuário              |           |
| `updateAt`    | Date     | Data da última atualização do usuário   |           |

### 2. **Tabela `tweets`**

Representa os tweets criados pelos usuários.

| **Campo**     | **Tipo** | **Descrição**                               | **Chave** |
|---------------|----------|---------------------------------------------|-----------|
| `id`          | UUID     | Identificador único do tweet                | PK        |
| `content`     | String   | Conteúdo do tweet                           |           |
| `createdAt`   | Date     | Data de criação do tweet                    |           |
| `userId`      | UUID     | Referência ao usuário que criou o tweet     | FK        |

### 3. **Tabela `likes_or_dislikes`**

Representa as interações de **like** e **dislike** que os usuários fazem nos tweets.

| **Campo**     | **Tipo** | **Descrição**                                  | **Chave** |
|---------------|----------|------------------------------------------------|-----------|
| `userId`      | UUID     | Referência ao usuário que curtiu ou descurtiu  | FK        |
| `tweetId`     | UUID     | Referência ao tweet que recebeu o like/dislike | FK        |
| `reaction`    | String   | Tipo de reação do usuário (`like`, `dislike`, `none`) |           |

### 4. **Tabela `comments`**

Representa os comentários feitos pelos usuários nos tweets.

| **Campo**     | **Tipo** | **Descrição**                              | **Chave** |
|---------------|----------|--------------------------------------------|-----------|
| `id`          | UUID     | Identificador único do comentário          | PK        |
| `content`     | String   | Conteúdo do comentário                     |           |
| `userId`      | UUID     | Referência ao usuário que fez o comentário  | FK        |
| `tweetId`     | UUID     | Referência ao tweet no qual o comentário foi feito | FK        |
| `createdAt`   | Date     | Data de criação do comentário              |           |

---

## **Relacionamentos**

### **1. Usuários e Tweets**
- **Relacionamento**: Um **usuário** pode criar vários **tweets**.
- **Explicação**: Cada tweet pertence a um único usuário. Portanto, há um relacionamento de **1:N** entre **`app_users`** e **`tweets`**.
- **Chave Estrangeira**: `userId` em **`tweets`** é uma chave estrangeira que se refere ao **`id`** em **`app_users`**.

### **2. Usuários e Comentários**
- **Relacionamento**: Um **usuário** pode fazer vários **comentários** em diferentes **tweets**.
- **Explicação**: Cada comentário é associado a um único usuário. Um usuário pode comentar várias vezes em diferentes tweets. O relacionamento é **1:N** entre **`app_users`** e **`comments`**.
- **Chave Estrangeira**: `userId` em **`comments`** é uma chave estrangeira que se refere ao **`id`** em **`app_users`**.

### **3. Tweets e Comentários**
- **Relacionamento**: Um **tweet** pode ter vários **comentários**, mas cada comentário está associado a um único tweet.
- **Explicação**: O relacionamento entre **`tweets`** e **`comments`** é **1:N**. Cada tweet pode ter múltiplos comentários, mas um comentário só pode pertencer a um único tweet.
- **Chave Estrangeira**: `tweetId` em **`comments`** é uma chave estrangeira que se refere ao **`id`** em **`tweets`**.

### **4. Usuários e Likes/Dislikes**
- **Relacionamento**: Um **usuário** pode curtir ou descurtir vários **tweets**.
- **Explicação**: A tabela **`likes_or_dislikes`** armazena as interações dos usuários com os tweets. Um usuário pode interagir com vários tweets, mas cada interação (like ou dislike) é única por usuário e tweet.
- **Chaves Estrangeiras**: 
  - `userId` em **`likes_or_dislikes`** se refere ao **`id`** em **`app_users`**.
  - `tweetId` em **`likes_or_dislikes`** se refere ao **`id`** em **`tweets`**.

### **5. Tweets e Likes/Dislikes**
- **Relacionamento**: Um **tweet** pode ser curtido ou descurtido por vários **usuários**.
- **Explicação**: Cada tweet pode ter várias reações (likes ou dislikes), mas cada interação é feita por um único usuário.
- **Chaves Estrangeiras**: 
  - `tweetId` em **`likes_or_dislikes`** se refere ao **`id`** em **`tweets`**.
  - `userId` em **`likes_or_dislikes`** se refere ao **`id`** em **`app_users`**.

---

## **Fluxo de Interações e Regras**

1. **Criar um Tweet**: Um usuário pode criar vários tweets, mas cada tweet pertence a um único usuário.
2. **Curtir ou Descurtir um Tweet**: Um usuário pode curtir ou descurtir vários tweets. Se um usuário curtir um tweet e depois descurtir, o like anterior será removido automaticamente.
3. **Comentar em um Tweet**: Um usuário pode comentar em vários tweets, e um tweet pode ter vários comentários. O mesmo usuário pode comentar várias vezes no mesmo tweet.
4. **Reações (Likes/Dislikes)**: Um tweet pode ser curtido ou descurtido por vários usuários, mas cada interação de like/dislike é única por par usuário/tweet.

---

## **Considerações Finais**

Este banco de dados fornece uma estrutura básica para um sistema de rede social simples, permitindo a criação de tweets, a interação dos usuários com esses tweets (curtidas, descurtidas) e a possibilidade de comentar. O relacionamento entre as tabelas é cuidadosamente projetado para garantir a integridade dos dados e permitir interações eficazes entre usuários e tweets.

--- 

