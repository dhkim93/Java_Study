//20170925_김덕현

package control;

import java.util.ArrayList;

import vo.UserVO;
import dao.UserDAO;

public class UserInfoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO ud = new UserDAO();
		UserVO uv = ud.insertUser();
		ud.updateUser();
		ArrayList li = ud.searchName();
		ud.deleteUser();
	}

}
