package magic.card;

import magic.model.MagicCounterType;
import magic.model.MagicGame;
import magic.model.MagicPayedCost;
import magic.model.MagicPermanent;
import magic.model.MagicSource;
import magic.model.action.MagicChangeLifeAction;
import magic.model.condition.MagicCondition;
import magic.model.condition.MagicConditionFactory;
import magic.model.event.MagicActivationHints;
import magic.model.event.MagicEvent;
import magic.model.event.MagicPermanentActivation;
import magic.model.event.MagicRemoveCounterEvent;
import magic.model.event.MagicSacrificeEvent;
import magic.model.event.MagicTiming;
import magic.model.trigger.MagicWhenOtherComesIntoPlayTrigger;

public class Sunspring_Expedition {
    public static final MagicWhenOtherComesIntoPlayTrigger T = Ior_Ruin_Expedition.T;
    
    public static final MagicPermanentActivation A = new MagicPermanentActivation(
            new MagicCondition[]{MagicConditionFactory.ChargeCountersAtLeast(3)},
            new MagicActivationHints(MagicTiming.Pump),
            "Life") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return new MagicEvent[]{
                new MagicRemoveCounterEvent(source,MagicCounterType.Charge,3),
                new MagicSacrificeEvent(source)};
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "PN gains 8 life"
            );
        }
        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object[] choiceResults) {
            game.doAction(new MagicChangeLifeAction(event.getPlayer(),8));
        }
    };
}
