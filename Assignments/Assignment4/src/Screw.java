/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class Screw extends OuterThreaded {
	private static final long serialVersionUID = 5243515556904782720L;
	private String[] headList = { "Bugle", "Flat", "Oval", "Pan", "Round" };
	private String head;
	private String drive;
	// private attributes

	public Screw(double length, String diameter, String material, String finish, String head, String drive,
			double unitPrice, int numPerUnit) throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit, diameter, length);
		this.head = head;
		this.drive = drive;
		if (!isIn(head, headList))
			throw new IllegalFastener("Illegal head!");
	} // Screw Constructor

	@Override
	public String toString() {
		return super.toString() + ", Head: " + head + ", Drive: " + drive;
	} // toString method

	@Override
	public void checkFinishes(String finish, String material) throws IllegalFastener {
		String finishesForSteel[] = { "Chrome, Hot Dipped Galvanized, Plain, Yellow Zinc, Zinc", "Black Phosphate",
				"ACQ 1000 Hour", "Lubricated" };
		if (material.equalsIgnoreCase("Steel") && !isIn(finish, finishesForSteel))
			throw new IllegalFastener("Illegal finish!");
	} // checkFinishes method

} // Screw class
