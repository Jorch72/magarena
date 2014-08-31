[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Pump),
        "Prevent"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicTapEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.POS_TARGET_CREATURE,
                MagicPreventTargetPicker.create(),
                this,
                "Prevent the next X damage that would be dealt to target creature\$ this turn, "+
                "where X is the number of Clerics in play."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game,{
                final MagicPlayer player = event.getPlayer()
                final int X = game.getNrOfPermanents(MagicSubType.Cleric);
                game.doAction(new MagicPreventDamageAction(it,X));
            });
        }
    }
]
