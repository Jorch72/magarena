package magic.card;

import magic.model.MagicManaCost;
import magic.model.MagicPermanent;
import magic.model.condition.MagicCondition;
import magic.model.condition.MagicConditionFactory;
import magic.model.event.MagicActivationHints;
import magic.model.event.MagicEvent;
import magic.model.event.MagicPayManaCostTapEvent;
import magic.model.event.MagicPermanentActivation;
import magic.model.event.MagicTapCreatureActivation;
import magic.model.event.MagicTiming;

public class Gideon_s_Lawkeeper {
    public static final MagicPermanentActivation A1 = new MagicTapCreatureActivation(
            new MagicCondition[]{MagicCondition.CAN_TAP_CONDITION,MagicConditionFactory.ManaCost("{W}")},
            new MagicActivationHints(MagicTiming.Tapping),
            "Tap") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return new MagicEvent[]{
                new MagicPayManaCostTapEvent(source,source.getController(),
                MagicManaCost.create("{W}"))};
        }
    };
}
