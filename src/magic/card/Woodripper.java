package magic.card;

import magic.model.MagicCounterType;
import magic.model.MagicGame;
import magic.model.MagicManaCost;
import magic.model.MagicPayedCost;
import magic.model.MagicPermanent;
import magic.model.MagicSource;
import magic.model.action.MagicDestroyAction;
import magic.model.action.MagicPermanentAction;
import magic.model.choice.MagicTargetChoice;
import magic.model.condition.MagicCondition;
import magic.model.condition.MagicConditionFactory;
import magic.model.event.MagicActivationHints;
import magic.model.event.MagicEvent;
import magic.model.event.MagicPayManaCostEvent;
import magic.model.event.MagicPermanentActivation;
import magic.model.event.MagicRemoveCounterEvent;
import magic.model.event.MagicTiming;
import magic.model.target.MagicDestroyTargetPicker;

public class Woodripper {
    public static final MagicPermanentActivation A = new MagicPermanentActivation( 
            new MagicCondition[] {
                MagicConditionFactory.ChargeCountersAtLeast(1),
                MagicManaCost.ONE.getCondition()
            },
            new MagicActivationHints(MagicTiming.Removal),
            "Damage") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return new MagicEvent[]{
                new MagicPayManaCostEvent(source,source.getController(),MagicManaCost.ONE),
                new MagicRemoveCounterEvent(source,MagicCounterType.Charge,1)
            };
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.NEG_TARGET_ARTIFACT,
                new MagicDestroyTargetPicker(false),
                this,
                "Destroy target artifact$."
            );
        }
        @Override
        public void executeEvent(final MagicGame game,final MagicEvent event,final Object[] choiceResults) {
            event.processTargetPermanent(game,choiceResults,0,new MagicPermanentAction() {
                public void doAction(final MagicPermanent permanent) {
                    game.doAction(new MagicDestroyAction(permanent));
                }
            });
        }
    };
}
