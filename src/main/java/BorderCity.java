public class BorderCity extends City {

    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int told = p.getMoney() * getCountry().getGame().getSettings().getTollToBePaid() / 100;
        if (!p.getFromCountry().equals(getCountry())) {
            p.setMoney(p.getMoney() - told);
            changeValue(told);
        }
        return arrive();
    }
}
