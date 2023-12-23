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


![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/Strategy.JPG)

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

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/Observer.JPG)
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Player.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GenericMonster.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameObserver.java

**Consequências**

- É fácil adicionar novos observadores (por exemplo, novos tipos de fantasmas) sem modificar o código existente, promovendo a extensibilidade.
- A lógica relacionada à resposta dos fantasmas às ações do Pac-Man é encapsulada nas classes de observadores, facilitando a manutenção e a compreensão do código.

#### STATES PATTERNS:

#### States Patterns Player

**Problemas no contexto**

Após o jogador consumir um ponto especial surge a oportunidade de tornar os fantasmas comestíveis pelo jogador. Sem um estado específico para lidar com esse momento, a mecânica de tornar os fantasmas comestíveis não era clara para o jogador, gerando confusão ou subutilização dessa capacidade especial.

**O Padrão**

Foi criada uma classe abstrata "PacManState" na qual foram geridos dois novos estados de jogo, o "normalState" e o "eatenState".
No "normalState" o PacMan existe no seu movimento e animação normal ("drawNormal") e quando muda para o "eatenState" o movimento para e é iniciada a animação em que ele morre ("drawDead").

**Implementação**

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/PacManState.JPG)

https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/PacMan/Player.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/PacMan/eatingPacMan.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/PacMan/normalState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/PacMan/pacManState.java

**Consequências**

- Facilidade em gerir as transições relativas ao estado em que o pacMan se encontra.
- Foi adicionada uma nova animação ao decorrer do jogo.


#### States Patterns Monsters

**Problemas no contexto**

No decorrer do jogo criou-se a necessidade de gerir as diferentes situações em que os fantasmas se encontravam.

**O Padrão**

Foi criada uma classe abstrata "monsterState" na qual foram geridos seis novos estados de jogo, o "eatenState", "frightState", "huntState", "onCollisionState", "scatterState", e o "inCageState".
No estado "inCageState" os monstros enconram-se dentro da jaula e movem-se em direção ao exterior dela. Passam para o estado "scatterState" se forem os 10 primeiros segundos de jogo em que os monstros se dirigem para os 4 cantos do mapa ou para o modo "huntState" no qual perseguem o pacMan até um ponto especial ser comido pelo jogador. Quando isso acontece é inciado o estado "frightState" em que os fantasmas passam-se a mover na direção oposta ao movimento em que se encontravam, andando aleatóriamente pelo mapa. Neste estado eles podem ser comidos pelo PacMan passando para o estado "eatenState" no qual se dirige para o interior da jaula. O estado "onCollisionState" é implementado quando um monstro colide com o pacMan durante o estado "huntState" e faz com que os monstros desaparareçam.

**Implementação**

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/MonsterState.JPG)

https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/eatenState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/frightState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/huntState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/inCageState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/onCollisionState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/Monster/States/scatterState.java



**Consequências**

- Possibilita um controle preciso do comportamento dos inimigos ao longo do jogo.


#### States Patterns Game

**Problemas no contexto**

No decorrer do jogo criou-se a necessidade de gerir as diferentes situações em que o jogo se encontrava.

**O Padrão**

Foi criada uma classe abstrata "gameState" na qual foram geridos quatro novos estados de jogo, o "menuState", "pauseState", "changingLevelState" e o "playingState".
O estado "menuState" é iniciado no inicio do jogo e é repsponsáver por desenhar o menu na tela do jogo que permite ao utilizador escolher enicializar ("play"), obter informações ("info") ou saír ("exit") do jogo. O estado "pauseState" é iniciado quando o jogo está a decorrer e é primida a tecla "Escape". É responsavel por parar/pausar o jogo e desenhar o menu de pausa permitindo ao jogador voltar ao jogo ("resume") ou retornar ao menu inicial ("quit") voltando ao "menuState". No "chengingLevelState" é inicializada uma animação durante um periodo de tempo que indica ao utilizador o fim de um nivel e o inicio de outro. O "playingState" quando chamado é responsavel por inicializar um novo jogo caso o estado anterior tenha sido o "menuState".

**Implementação**

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/GameState.JPG)

https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameStates/ApplicationState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameStates/changingLevel.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameStates/menuState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameStates/pauseState.java
https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/src/main/java/org/example/GameStates/playingState.java

**Consequências**

- Facilidade em gerir as transições relativas ao estado em que o jogo se encontra.

#### Code Smells

- Algumas funções contêm uma implementação extensa e confusa.
- Performance podia ser melhorada.

### Coverage

![img](https://github.com/FEUP-LDTS-2023/project-l04gr04/blob/master/Coverage.JPG)

### Auto-avaliação

- Alexandre Costa: 40%
- Bárbara: 30%
- Bernardo: 30%
