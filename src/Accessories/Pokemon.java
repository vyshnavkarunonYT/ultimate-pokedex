package Accessories;

public class Pokemon {
	public String pokedex_number;
	public String name;
	public String primary_type;
	public String secondary_type;
	public String classification;
	public String height;
	public String weight;
	public String primary_ability;
	public String primary_ability_def;
	public String secondary_ability;
	public String secondary_ability_def;
	public String region;
	
	
	public Pokemon(String pokedex_number, String name, String classification ,
			String primary_type, String secondary_type, String height,
			String weight, String primary_ability, String primary_ability_def, String secondary_ability, String secondary_ability_def, String region) {
			
		this.pokedex_number = pokedex_number;
		this.name = name;
		this.primary_type = primary_type;
		this.secondary_type = secondary_type;
		this.classification = classification;
		this.weight = weight;
		this.height = height;
		this.primary_ability = primary_ability;
		this.primary_ability_def = primary_ability_def;
		this.secondary_ability = secondary_ability;
		this.secondary_ability_def = secondary_ability_def;
		this.region = region;
		
	}
	
	
	public void printData() {
		System.out.println("NUMBER\t\t:\t"+pokedex_number);
		System.out.println("NAME\t\t:\t" + name);
		System.out.println("CLASSIFICATION\t:\t"+classification);
		System.out.println("PRIMARY TYPE\t:\t"+primary_type);
		System.out.println("SECONDARY TYPE\t:\t"+secondary_type);
	}
}
