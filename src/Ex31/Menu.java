package Ex31;

public class Menu {
	private int id;
	private String entree;
	private String plat;
	private String accompagnement;
	private String boisson;
	private String dessert;
	

	public Menu (int id, String entree,String plat, String accompagnement, String boisson, String dessert)
	{
		setId(id);
		setEntree(entree);
		setPlat(plat);
		setAccompagnement(accompagnement);
		setBoisson(boisson);
		setDessert(dessert);
	}
	
	public Menu (int id , String entree,String plat, String accompagnement, String dessert)
	{
		setId(id);
		setEntree(entree);
		setPlat(plat);
		setAccompagnement(accompagnement);
		setDessert(dessert);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntree() {
		return entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getAccompagnement() {
		return accompagnement;
	}

	public void setAccompagnement(String accompagnement) {
		this.accompagnement = accompagnement;
	}

	public String getBoisson() {
		return boisson;
	}

	public void setBoisson(String boisson) {
		this.boisson = boisson;
	}

	public String getDessert() {
		return dessert;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	@Override
	public String toString() {
		return "menu [entree=" + entree + ", plat=" + plat + ", accompagnement=" + accompagnement + ", boisson="
				+ boisson + ", dessert=" + dessert + "]";
	}
	
}

