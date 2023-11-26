## LDTS_<4><4> - <PacMan>

O PacMan é um jogo eletrónico no qual o jogador é uma cabeça redonda com uma boca que se abre e fecha, posicionado num labirinto simples repleto de pontos e quatro fantasmas que o perseguem. Porém, o PacMan também persegue os fantasmas em determinadas alturas.
O jogo é constituído por vários níveis, nos quais a dificuldade aumenta progressivamente. O objetivo é comer todos os pontos para avançar para o próximo nível.
Este projeto foi desenvolvido pelo Alexandre Costa (up202207499@fe.up.pt), Bárbara Ribeiro (up202209504@fe.up.pt) e Bernardo Costa (up202207579@fe.up.pt).

### Funções implementadas

- **Mapa** - Criação de um mapa através da leitura de um ficheiro.
- **Movimento do jogador** - O jogador move-se livremente dentro dos limites do mapa, e só precisa de fornecer uma entrada para alterar a sua trajetória (visto que o PacMan se move sem precisar de entrada).
- **Movimento dos fantasmas** - Tal como o jogador, eles movem-se dentro dos limites do mapa. Eles movem-se com base num alvo específico para cada fantasma.
- **Animações do PacMan e dos fantasmas** - Troca entre abrir e fechar da boca do PacMan e os olhos dos fantasmas apontam na direção do seu movimento.
- **Atualização da tela** - Apenas os locais nos quais existe movimento (alterações de tela) são atualizados. Foi utilizado um conjunto de retângulos que guardam essas áreas.

### Funções por implementar

- **Comer pontos, frutas e pontos especiais** - Permitir que o jogador passe por cima destas estruturas e elas desapareçam, considerando os seus efeitos.
- **Passagem de nível** - Caso o jogador "coma" todos os pontos do mapa do nível atual, a tela atualiza, um novo nível é carregado e a dificuldade aumenta.
- **Troca entre estados** - Os fantasmas mudam o seu comportamento, ficando suscetíveis a "serem comidos" pelo jogador.
- **Colisão entre jogador e fantasma** - No estado normal, se houver colisão, o nível reinicia e o jogador perde uma tentativa. O número de tentativas é limitado. No estado alternativo, o jogador "come" um fantasma, colocando-o de novo na caixa inicial.
- **Movimento dos fantasmas em modo alternativo** - Os fantasmas invertem imediatamente o seu sentido. A cada interseção, escolhem uma direção aleatoriamente.
- **Animações** - Animações como PacMan a "morrer", a "comer", entre outras.

### DESIGN

#### STRATEGY PATTERN

**Problema no contexto**

Todos os fantasmas se movem e são desenhados da mesma maneira. Contudo, cada um deles têm um alvo e cor específicos. 

**O Padrão**

Foram criadas uma interface genérica e uma classe abstrata para representar as estratégias de movimento dos fantasmas que permitem que cada um tenha uma estratégia associada.
Assim, foram aproveitadas as semelhanças entre os fantasmas, e implementadas apenas as diferenças.

**Implementação**

https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/GenericMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/Monster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/RedMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/OrangeMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/PinkMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/tree/master/src/main/java/org/example/BlueMonster.java

**Consequências**

- Facilita a adição de novos comportamentos para os fantasmas sem modificar a classe principal do fantasma.
- Reduz a necessidade de alterar o código existente sempre que um novo comportamento é introduzido.
- A manutenção do código é simplificada, uma vez que a lógica específica de cada comportamento é encapsulada em classes separadas.


#### OBSERVER PATTERN

**Problemas no contexto**

Existem diferentes estados de jogo. Seria inadequado alterar o estado de jogo em cada elemento do mapa.

**O Padrão**

Foi criada uma classe "GameState" que possui uma lista de "GameObservers" (Interface para jogador e monstros). Cada mudança na classe notifica os seus observadores, levando à alteração do seu estado.

**Implementação**

https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Player.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GenericMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameObserver.java

**Consequências**

- É fácil adicionar novos observadores (por exemplo, novos tipos de fantasmas) sem modificar o código existente, promovendo a extensibilidade.
- A lógica relacionada à resposta dos fantasmas às ações do Pac-Man é encapsulada nas classes de observadores, facilitando a manutenção e a compreensão do código.


#### Alguns defeitos de código

- Algumas funções contêm uma implementação extensa e confusa.
- Lógica de animação do PacMan e dos fantasmas pode ser melhorada.
- As cores ainda não estão armazenadas numa lista.

### TESTES

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/IMG_20231125_170302.jpg?raw=true)

### Auto-avaliação

- Alexandre: 40%
- Bárbara: 30%
- Bernardo: 30%
