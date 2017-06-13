/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jogopedrapapeltesoura;

import br.com.jogopedrapapeltesoura.Exceptions.NoSuchStrategyError;
import br.com.jogopedrapapeltesoura.Exceptions.WrongNumberOfPlayersError;

/**
 *
 * @author Rodrigo
 */
public class Regras {

    /***
     * Verifica várias jogadas até que seja encontrado um ganhador
     * @param tournament Lista das jogadas a serem verificadas
     * @return Array de string contendo o nome do jogador e a jogada vencedora
     * @throws WrongNumberOfPlayersError Caso sejam informados menos ou mais
     * jogadores, como também caso o formato dos arrays de jogadores não
     * estiverem como String[]
     * @throws NoSuchStrategyError Caso no array do jogador não seja informado
     * uma jogada ("R","P" ou "S")
     */
    public String[] rps_tournament_winner(Object[] tournament) throws WrongNumberOfPlayersError, NoSuchStrategyError {
        String[] vencedor = null;
        Object[] jogada = new Object[2];

        for (int indice = 0; indice <= 1; indice++) {
            if (tournament[indice] instanceof String[]) {
                jogada[indice] = (String[]) tournament[indice];
            } else if (tournament[indice] instanceof Object[]) {
                jogada[indice] = rps_tournament_winner((Object[]) tournament[indice]);
            }
        }

        vencedor = rps_game_winner(jogada);

        return vencedor;
    }

    /**
     * *
     * Retorna o jogador vencedor
     *
     * @param players array contendo os jogadores e suas jogadas
     * @return Jogador e a jogada vencedorea
     * @throws WrongNumberOfPlayersError Caso sejam informados menos ou mais
     * jogadores, como também caso o formato dos arrays de jogadores não
     * estiverem como String[]
     * @throws NoSuchStrategyError Caso no array do jogador não seja informado
     * uma jogada ("R","P" ou "S")
     */
    public String[] rps_game_winner(Object[] players) throws WrongNumberOfPlayersError, NoSuchStrategyError {
        String[] vencedor = null;
        String[] jogador1;
        String[] jogador2;

        verificar(players);

        jogador1 = (String[]) players[0];
        jogador2 = (String[]) players[1];

        vencedor = jogador1;
        switch (jogador1[1].toUpperCase()) {
            case "R":
                if (jogador2[1].equalsIgnoreCase("P")) {
                    vencedor = jogador2;
                }
                break;

            case "S":
                if (jogador2[1].equalsIgnoreCase("R")) {
                    vencedor = jogador2;
                }
                break;

            case "P":
                if (jogador2[1].equalsIgnoreCase("S")) {
                    vencedor = jogador2;
                }
                break;
        }

        return vencedor;
    }

    /**
     * *
     * Verifica se o array passado por parâmetro está dentro do padrão esperado
     * pelo método que verifica o vencedor
     *
     * @param players Array de objetos a ser verificado o padrão
     * @throws WrongNumberOfPlayersError Caso sejam informados menos ou mais
     * jogadores, como também caso o formato dos arrays de jogadores não
     * estiverem como String[]
     * @throws NoSuchStrategyError Caso no array do jogador não seja informado
     * uma jogada ("R","P" ou "S")
     */
    private void verificar(Object[] players) throws WrongNumberOfPlayersError, NoSuchStrategyError {

        if (players.length != 2
                || !(players[0] instanceof String[])
                || !(players[1] instanceof String[])) {
            throw new WrongNumberOfPlayersError();
        }

        for (Object pseudoPlayer : players) {
            String[] player = (String[]) pseudoPlayer;
            if (player.length != 2
                    || !player[1].toUpperCase().matches("^[RPS]$")) {
                throw new NoSuchStrategyError();
            }
        }
    }
}
