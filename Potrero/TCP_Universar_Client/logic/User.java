package logic;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 13L;

	private String userName;
	private int id;

	private int numberOfGoats = 0;
	// Energy is a %
	private float energy = 0;

	public User() {

	}// empty constructor

	public User(int id, String userName) {
		this.userName = userName;
		this.id = id;
	}// basic constructor

	// ----------- GETTERS Y SETTERS -------------
	public void setId(int id) {
		this.id = id;
	}// change id

	public int getId() {
		return id;
	}// get id

	public String getUserName() {
		return userName;
	}// get user name

	public void setUserName(String userName) {
		this.userName = userName;
	}// get user name

	public int getNumberOfGoats() {
		return numberOfGoats;
	}// get number of goats

	public void setNumberOfGoats(int numberOfGoats) {
		this.numberOfGoats = numberOfGoats;
	}// set number of goats

	public float getEnergy() {
		return energy;
	}// get energy

	public void setEnergy(float energy) {
		this.energy = energy;
	} // set energy

}// USER
