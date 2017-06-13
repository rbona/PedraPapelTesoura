/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jogopedrapapeltesoura;

import br.com.jogopedrapapeltesoura.Exceptions.NoSuchStrategyError;
import br.com.jogopedrapapeltesoura.Exceptions.WrongNumberOfPlayersError;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class JogoPedraPapelTesoura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Object[] jogada = new Object[]{new String[]{"Armando", "P"}, new String[]{"Dave", "S"}};
        Object[] jogadas = new Object[]{
            new Object[]{
                new Object[]{new String[]{"Armando", "P"}, new String[]{"Dave", "S"}},
                new Object[]{new String[]{"Richard", "R"}, new String[]{"Michael", "S"}}
            },
            new Object[]{
                new Object[]{new String[]{"Allen", "S"}, new String[]{"Omer", "p"}},
                new Object[]{new String[]{"David E.", "R"}, new String[]{"Richard X.", "P"}}
            }
        };

        Regras regras = new Regras();

        try {
            String[] vencedor = regras.rps_game_winner(jogada);
            System.out.println("Jogada Simples - Vencedor [" + vencedor[0] + "]");

            vencedor = regras.rps_tournament_winner(jogadas);
            System.out.println("Torneio - Vencedor [" + vencedor[0] + "]");

        } catch (WrongNumberOfPlayersError ex) {
            Logger.getLogger(JogoPedraPapelTesoura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchStrategyError ex) {
            Logger.getLogger(JogoPedraPapelTesoura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
