public class MafiaCountry extends Country {

    public MafiaCountry (String name) {
        super(name);
    }

    @Override
    public int bonus(int value) {
        if (value <= 0) { return 0; }
        int risk = getGame().getSettings().getRisk();
        int loss = getGame().getLoss() * -1;
        int calcRisk = getGame().getRandom().nextInt(100 + 1);
        if (calcRisk <= risk) {
            return loss;
        }
        return super.bonus(value);
    }
}
