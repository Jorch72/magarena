[
    new MagicPermanentActivation(
        [
            MagicCondition.SORCERY_CONDITION,
            MagicCondition.ABILITY_ONCE_CONDITION
        ],
        new MagicActivationHints(MagicTiming.Main),
        "+1") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return [
                MagicPutCounterEvent.Self(
                    source,
                    MagicCounterType.Charge,
                    1
                ),
                new MagicPlayAbilityEvent(source)
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Put a 3/3 green Beast creature token onto the battlefield."
            );
        }
        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object[] choiceResults) {
            game.doAction(new MagicPlayTokenAction(
                event.getPlayer(), 
                TokenCardDefinitions.get("Beast3")
            ));
        }
    },
    new MagicPermanentActivation(
        [
            MagicCondition.SORCERY_CONDITION,
            MagicCondition.ABILITY_ONCE_CONDITION,
            MagicConditionFactory.ChargeCountersAtLeast(3)
        ],
        new MagicActivationHints(MagicTiming.Main),
        "-3") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return [
                new MagicRemoveCounterEvent(
                    source,
                    MagicCounterType.Charge,
                    3
                ),
                new MagicPlayAbilityEvent(source)
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Draw cards equal to the greatest power among creatures you control."
            );
        }
        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object[] choiceResults) {
            final Collection<MagicPermanent> targets = game.filterPermanents(
                    event.getPlayer(),
                    MagicTargetFilter.TARGET_CREATURE_YOU_CONTROL);
            int power = 0;
            for (final MagicPermanent creature : targets) {
                power = Math.max(power,creature.getPower());
            }
            game.doAction(new MagicDrawAction(
                event.getPlayer(), 
                power
            ));
        }
    },
    new MagicPermanentActivation(
        [
            MagicCondition.SORCERY_CONDITION,
            MagicCondition.ABILITY_ONCE_CONDITION,
            MagicConditionFactory.ChargeCountersAtLeast(6)
        ],
        new MagicActivationHints(MagicTiming.Main),
        "-6") {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return [
                new MagicRemoveCounterEvent(
                    source,
                    MagicCounterType.Charge,
                    6
                ),
                new MagicPlayAbilityEvent(source)
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Put a 6/6 green Wurm creature token onto the battlefield for each land you control."
            );
        }
        @Override
        public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object[] choiceResults) {
            final int amt = event.getPlayer().getNrOfPermanentsWithType(MagicType.Land);
            game.doAction(new MagicPlayTokensAction(
                event.getPlayer(), 
                TokenCardDefinitions.get("Wurm6G"),
                amt
            ));
        }
    }
]
