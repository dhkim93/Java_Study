//20170925_김덕현

package vo;

public class UserVO {
	private String name = null;
	private int age;

	public UserVO() {
	}

	public UserVO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
