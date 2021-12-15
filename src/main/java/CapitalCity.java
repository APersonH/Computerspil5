public class CapitalCity extends BorderCity{
    public CapitalCity(String name, int value, Country country) { super(name, value, country); }

    @Override
    public int arrive(Player p) {
        int pMoney = p.getMoney();
        int bonus = super.arrive(p);
        int spend = getCountry().getGame().getRandom().nextInt(pMoney + bonus);
        changeValue(spend);

        return bonus - spend;
    }
}
