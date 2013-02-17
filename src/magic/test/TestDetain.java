
package magic.test;

import magic.model.MagicDuel;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicPlayerDefinition;
import magic.model.MagicPlayerProfile;
import magic.model.phase.MagicMainPhase;

class TestDetain extends TestGameBuilder {    
    public MagicGame getGame() {
        final MagicDuel duel=new MagicDuel();
        duel.setDifficulty(6);
        
        final MagicPlayerProfile profile=new MagicPlayerProfile("bgruw");
        final MagicPlayerDefinition player1=new MagicPlayerDefinition("Player",false,profile,15);
        final MagicPlayerDefinition player2=new MagicPlayerDefinition("Computer",true,profile,14);
        duel.setPlayers(new MagicPlayerDefinition[]{player1,player2});
        duel.setStartPlayer(0);
        
        final MagicGame game=duel.nextGame(true);
        game.setPhase(MagicMainPhase.getFirstInstance());
        final MagicPlayer player=game.getPlayer(0);
        final MagicPlayer opponent=game.getPlayer(1);

        MagicPlayer P = player;

        P.setLife(20);
        addToLibrary(P, "Plains", 10);
        createPermanent(game,P,"Rupture Spire",false,8);
        addToHand(P, "Azorius Arrester", 1);
        addToHand(P, "Isperia's Skywatch", 1);
        addToHand(P, "Soulsworn Spirit", 1);
        addToHand(P, "Inaction Injunction", 1);
        addToHand(P, "Lyev Skyknight", 1);
        addToHand(P, "Martial Law", 1);
        addToHand(P, "New Prahv Guildmage", 1);

        P = opponent;
        
        P.setLife(10);
        addToLibrary(P, "Plains", 10);
        createPermanent(game,P,"Rupture Spire",false,8);
        createPermanent(game,P,"Grizzly Bears",false,1);
        addToHand(P, "Grizzly Bears", 1);
        
        return game;
    }
}
