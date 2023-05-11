import java.util.Collections;

/**
 * A class represents a card war game.
 * The game has two players fighting.
 * The player who got out of cards loses.
 */
public class WarGame {
    private final Player firstPlayer = new Player();
    private final Player secondPlayer = new Player();


    /**
     * Constructor to initialize the players names.
     * @param firstPlayerName is the name of the first player.
     * @param secondPlayerName is the name of the second player.
     */
    public WarGame(String firstPlayerName, String secondPlayerName) {
        this.firstPlayer.setPlayerName(firstPlayerName);
        this.secondPlayer.setPlayerName(secondPlayerName);
    }

    /**
     * Creates a main deck of 52 cards and shuffle it.
     * </p>
     * Checks which player should start the game.
     * Deals the cards by the lexicography order.
     */
    public void initializeGame() {
        System.out.println("Initializing the game...");
        Deck mainDeck = new Deck(true);
        if (whoFirst(this.firstPlayer.getPlayerName(),
                this.secondPlayer.getPlayerName()) > 0) {
            dealCards(firstPlayer, secondPlayer, mainDeck);
        } else dealCards(secondPlayer, firstPlayer, mainDeck);
    }

    /* Returns the name of the winner */
    public String start() {
        return mainMethod(this.firstPlayer, this.secondPlayer);
    }

    /**
     * Checks which player should start by lexicography order.
     * </p>
     * Calls the gameRun function by the correct order of the players.
     * @param firstPlayer is the first player object.
     * @param secondPlayer is the second player object.
     * @return a string type describes the winner's name.
     */
    public String mainMethod(Player firstPlayer, Player secondPlayer) {
        String winnerName;
        if (whoFirst(firstPlayer.getPlayerName(), secondPlayer.getPlayerName()) > 0) {
            winnerName = gameRun(secondPlayer, firstPlayer);
        } else winnerName = gameRun(firstPlayer, secondPlayer);
        return winnerName;
    }

    /**
     * Checks which player should start the game by the lexicography order.
     * @param firstPlayerName is the name of the first player.
     * @param secondPlayerName is the name of the second player.
     * @return 1 if the first player should start or -1 if the second player should start.
     */
    public int whoFirst(String firstPlayerName, String secondPlayerName) {
        return firstPlayerName.compareTo(secondPlayerName);
    }

    /**
     * Shuffle the main deck and deal the cards, each player gets 26 cards.
     * @param player1: represents the first player who starts the game.
     * @param player2: represents the second player.
     * @param mainDeck: The main deck which was initialized with 52 cards.
     */
    public void dealCards(Player player1, Player player2, Deck mainDeck) {
        mainDeck.shuffle();
        for (int i = 0; i < (mainDeck.deck.size()); i += 2) {
            player1.playingDeck.addCard(mainDeck.deck.get(i));
            player2.playingDeck.addCard(mainDeck.deck.get(i + 1));
        }
    }

    /**
     * Switch between the player playing deck and his winning deck.
     * Doing so when the player's playing deck is empty.
     * @param player: The player which his deck got empty.
     */
    public void switchDecks(Player player) {
        player.winningDeck.shuffle();
        while (player.winningDeck.deck.size() != 0) {
            player.addCardToPlayingDeck(player.winningDeck.removeTopCard());
        }
        Collections.reverse(player.playingDeck.deck);
    }

    /* Prints the player's name and which card he drew during normal game. */
    public void drawCardOnGamePrint(Player player, Card card) {
        System.out.println(player.getPlayerName() + " drew " + card.toString());
    }

    /* Prints the player's name and which card he drew during a war. */
    public void drawCardWarPrint(Player player) {
        System.out.println(player.getPlayerName() + " drew a war card");
    }

    /**
     * Prints the winner's name of the round.
     * @param winner An int value describes the winner of the round,
     *              where 1 means the first player won and -1 the second.
     * @param player1 represents the first player.
     * @param player2 represents the second player.
     */
    public void printWinnerOnRound(int winner, Player player1, Player player2) {
        if (winner == 1) {
            System.out.println(player1.getPlayerName() + " won");
        } else if (winner == -1) {
            System.out.println(player2.getPlayerName() + " won");
        }
    }

    /**
     * Checks if a player got out of cards.
     * If so, the game is over.
     * @param player1Playingdeck is the first player's playing deck.
     * @param player1Winningdeck is the first player's winning deck.
     * @param player2Playingdeck is the second player's playing deck.
     * @param player2Winningdeck is the second player's winning deck.
     * @return true if we can continue the game and false otherwise.
     */
    public boolean gameIsRunning(Deck player1Playingdeck, Deck player1Winningdeck,
                                 Deck player2Playingdeck, Deck player2Winningdeck) {
        boolean player1 = false;
        boolean player2 = false;
        if (player1Playingdeck.isEmpty() && player1Winningdeck.isEmpty()) {
            player1 = true;
        }
        if (player2Playingdeck.isEmpty() && player2Winningdeck.isEmpty()) {
            player2 = true;
        }
        return !player1 && !player2;
    }

    /* Returns "empty" if the player's playing deck got empty. */
    public String playingDeckCheck(Player player) {
        if (player.playingDeck.isEmpty()) {
            return "Empty";
        } else {
            return "NotEmpty";
        }
    }

    /* Returns "empty" if the player's winning deck got empty. */
    public String winningDeckCheck(Deck winningDeck) {
        if (winningDeck.isEmpty()) {
            return "Empty";
        } else {
            return "NotEmpty";
        }
    }

    /**
     * Initialize the game- dealing the cards for each player.
     * Every player in his turn draws a card.
     * the winner om a round is the player which his card's number is higher.
     * </p>
     * In every turn it must be checked that each player still has cards in one of his decks.
     * If a player has no more cards, the other wins.
     * </p>
     * In case where the players draw the same number it means there is a war.
     * We then call the warRun function which returns us the winner's name in the war.
     * @param player1 is the first player who starts the game.
     * @param player2 is the second player.
     * @return String type describes winner's name.
     */
    public String gameRun(Player player1, Player player2) {
        initializeGame();
        int winner, playerWon = 0;
        int roundNumber = 0;
        while (gameIsRunning(player1.playingDeck, player1.winningDeck,
                player2.playingDeck, player2.winningDeck)) {
            roundNumber += 1;
            System.out.println("------------------------- Round number "
                    + roundNumber + " -------------------------");
            if (playingDeckCheck(player1).equals("Empty")) {
                if (winningDeckCheck(player1.winningDeck).equals("Empty")) {
                    playerWon = -1;
                    break;
                } else {
                    switchDecks(player1);
                    player1.drawCard();

                    drawCardOnGamePrint(player1, player1.gameDeck.deck.get(0));

                }

            } else {
                player1.drawCard();

                drawCardOnGamePrint(player1, player1.gameDeck.deck.get(0));
            }

            if (playingDeckCheck(player2).equals("Empty")) {
                if (winningDeckCheck(player2.winningDeck).equals("Empty")) {
                    playerWon = 1;
                    break;
                } else {
                    switchDecks(player2);
                    player2.drawCard();

                    drawCardOnGamePrint(player2, player2.gameDeck.deck.get(0));
                }

            } else {
                player2.drawCard();

                drawCardOnGamePrint(player2, player2.gameDeck.deck.get(0));
            }

            winner = player1.gameDeck.deck.get(0).compare(player2.gameDeck.deck.get(0));
            if (winner == 1) {
                printWinnerOnRound(winner, player1, player2);
                player1.addCardToWinningDeck(player2.gameDeck.removeTopCard());
                player1.addCardToWinningDeck(player1.gameDeck.removeTopCard());
            } else if (winner == -1) {
                printWinnerOnRound(winner, player1, player2);
                player2.addCardToWinningDeck(player2.gameDeck.removeTopCard());
                player2.addCardToWinningDeck(player1.gameDeck.removeTopCard());
            } else {
                String x = warRun(player1, player2);
            }

        }
        if (playerWon == 1) {
            return player2.getPlayerName();
        } else {
            return player1.getPlayerName();
        }
    }

    /**
     * War happens when two players drew the same number on a card.
     * Each player in his turn draws two cards and putting the face down.
     * The third card determines which player won.
     * if numbers are same again, another war starts.
     * </p>
     * In each turn it must be checked that a player still has cards.
     * If playing game deck got empty, there is a switch with his winning deck.
     * If both decks are empty, the other player won the game.
     * @param player1 is the first player to draw.
     * @param player2 is the second player to draw.
     * @return the name of the winner in the war.
     */
    public String warRun(Player player1, Player player2) {
        int sizeGameDeck1 = player1.gameDeck.deck.size() - 1;
        int sizeGameDeck2 = player2.gameDeck.deck.size() - 1;
        boolean temp = true;   // boolean to know if we are still in war or not
        while (temp) {
            System.out.println("Starting a war...");
            if (playingDeckCheck(player1).equals("Empty")) {
                // Player 1 draw the first warCard
                if (winningDeckCheck(player1.winningDeck).equals("Empty")) {
                    break;
                } else {
                    switchDecks(player1);
                    player1.drawCard();
                    drawCardWarPrint(player1);
                }
            } else {
                player1.drawCard();
                drawCardWarPrint(player1);

            }


            if (playingDeckCheck(player2).equals("Empty")) {
                // Player 2 draw the first warCard
                if (winningDeckCheck(player2.winningDeck).equals("Empty")) {
                    break;
                } else {
                    switchDecks(player2);
                    player2.drawCard();
                    drawCardWarPrint(player2);
                }
            } else {
                player2.drawCard();
                drawCardWarPrint(player2);
            }

            if (playingDeckCheck(player1).equals("Empty")) {
                // Player 1 draw the second warCard
                if (winningDeckCheck(player1.winningDeck).equals("Empty")) {
                    break;
                } else {
                    switchDecks(player1);
                    player1.drawCard();
                    drawCardWarPrint(player1);
                }
            } else {
                player1.drawCard();
                drawCardWarPrint(player1);
            }


            if (playingDeckCheck(player2).equals("Empty")) {
                // Player 2 draw the second warCard
                if (winningDeckCheck(player2.winningDeck).equals("Empty")) {
                    break;
                } else {
                    switchDecks(player2);
                    player2.drawCard();
                    drawCardWarPrint(player2);
                }
            } else {
                player2.drawCard();
                drawCardWarPrint(player2);
                if (player2.playingDeck.isEmpty() && player2.winningDeck.isEmpty()){
                    return player1.getPlayerName();
                }
            }

            if (playingDeckCheck(player1).equals("Empty")) {
                // Player 1 draw the third Card
                if (winningDeckCheck(player1.winningDeck).equals("Empty")){
                    break;
                } else {
                    switchDecks(player1);
                    player1.drawCard();
                    drawCardOnGamePrint(player1, player1.gameDeck.deck.get(sizeGameDeck1));
                }
            } else {
                player1.drawCard();
                drawCardOnGamePrint(player1, player1.gameDeck.deck.get(sizeGameDeck1));
            }


            if (playingDeckCheck(player2).equals("Empty")) {
                // Player 2 draw the third Card
                if (winningDeckCheck(player2.winningDeck).equals("Empty")) {
                    break;
                } else {
                    switchDecks(player2);
                    player2.drawCard();
                    drawCardOnGamePrint(player2, player2.gameDeck.deck.get(sizeGameDeck2));
                }
            } else {
                player2.drawCard();
                drawCardOnGamePrint(player2, player2.gameDeck.deck.get(sizeGameDeck2));
            }


            if (player1.gameDeck.deck.get(sizeGameDeck1)
                    .compare(player2.gameDeck.deck.get(sizeGameDeck2)) == 1) {
                temp = false;
                System.out.println(player1.getPlayerName() + " won the war");
                while (player1.gameDeck.deck.size() != 0) {
                    player1.addCardToWinningDeck(player2.gameDeck.deck.get(0));
                    player2.gameDeck.deck.remove(0);
                    player1.addCardToWinningDeck(player1.gameDeck.deck.get(0));
                    player1.gameDeck.deck.remove(0);

                }
            } else if (player1.gameDeck.deck.get(sizeGameDeck1)
                    .compare(player2.gameDeck.deck.get(sizeGameDeck2)) == -1) {
                temp = false;
                System.out.println(player2.getPlayerName() + " won the war");
                while (player2.gameDeck.deck.size() != 0) {
                    player2.addCardToWinningDeck(player2.gameDeck.deck.get(0));
                    player2.gameDeck.deck.remove(0);
                    player2.addCardToWinningDeck(player1.gameDeck.deck.get(0));
                    player1.gameDeck.deck.remove(0);
                }
            }
        }
        return "";
    }
}